package com.parser.mechanix;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sketchshape.SrlPointExtended;
import com.sketchshape.SrlShapeExtended;
import com.sketchshape.SrlStrokeExtended;

import edu.tamu.srl.sketch.core.abstracted.SrlObject;
import edu.tamu.srl.sketch.core.object.SrlShape;
import edu.tamu.srl.sketch.core.object.SrlStroke;
import edu.tamu.srl.sketch.core.virtual.SrlPoint;

public class MechanixParser {
	private ArrayList<SrlShape> mechanixSketchList = new ArrayList<SrlShape>();

	private static HashMap<String, String> refkeyMap = new HashMap<String, String>();
	private static HashMap<String, String> attributMapGlobal = new HashMap<String, String>();

	private static HashMap<String, SrlObject> shapeRefobjectMap = new HashMap<String, SrlObject>();
	private static HashMap<String, SrlPoint> pointRefobjectMap = new HashMap<String, SrlPoint>();
	private static HashMap<String, SrlStroke> strokeRefobjectMap = new HashMap<String, SrlStroke>();

	private static HashMap<String, SrlPoint> unprocessedPoints = new HashMap<String, SrlPoint>();
	private static HashMap<String, SrlShape> unprocessedShapes = new HashMap<String, SrlShape>();
	private static HashMap<String, SrlStroke> unprocessedStroke = new HashMap<String, SrlStroke>();

	public MechanixParser() {
		super();
		this.mechanixSketchList = new ArrayList<SrlShape>();
	}

	public ArrayList<SrlShape> getMechanixSketchList() {
		return mechanixSketchList;
	}

	public void setMechanixSketchList(ArrayList<SrlShape> mechanixSketchList) {
		this.mechanixSketchList = mechanixSketchList;
	}

	public void parse(String filename) throws ParserConfigurationException, IOException, SAXException {
		File f = new File(filename);
		if (!f.exists()) {
			throw new FileNotFoundException("Filename: " + filename + " does not exist. !!!");
		}

		if (f.isDirectory()) {
			mechanixSketchList = getMechanixSketchFromDirectory(f);
		} else {
			SrlShape sketchFormFile = getMechanixSketchFromfile(f);
			mechanixSketchList.add(sketchFormFile);
		}

	}

	private static SrlShape getMechanixSketchFromfile(File filename) throws ParserConfigurationException, IOException, SAXException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(filename);
		// doc.getDocumentElement().normalize();

		SrlShapeExtended sketch = new SrlShapeExtended(Long.parseLong(doc.getDocumentElement().getAttribute("time")), UUID.fromString(doc.getDocumentElement().getAttribute("id")), null, doc.getDocumentElement().getAttribute("type"));
		sketch.setColor(doc.getDocumentElement().getAttribute("draw_color"));

		ArrayList<SrlShape> shapes = new ArrayList<SrlShape>();
		NodeList sketchNodes = doc.getDocumentElement().getChildNodes();

		for (int i = 0; i < sketchNodes.getLength(); i++) {
			Node sketchNode = sketchNodes.item(i);
			if (sketchNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) sketchNode;
				switch (eElement.getNodeName()) {
				case "attributes":
					getSketchAttributes(eElement, sketch);
					break;
				case "shape":
					SrlShapeExtended shape = getShape(eElement);
					shapeRefobjectMap.put(shape.getMechanixSimId(), shape);
					shapes.add(shape);
					break;
				}

			}

		}
		sketch.addAll(shapes);
		updateUnProcessedObjects();
		return sketch;
	}

	private static void updateUnProcessedObjects() {
		updateUnprossedPoints();
		updateUnprossedShapes();
		updateUnprossedStroke();
	}

	private static void updateUnprossedStroke() {
		for (Entry<String, SrlStroke> entry : unprocessedStroke.entrySet()) {
			String key = entry.getKey();
			if (strokeRefobjectMap.containsKey(key)) {
				unprocessedStroke.put(key, strokeRefobjectMap.get(key));
			}
		}
	}
	private static void updateUnprossedPoints() {
		for (Entry<String, SrlPoint> entry : unprocessedPoints.entrySet()) {
			String key = entry.getKey();
			if (pointRefobjectMap.containsKey(key)) {
				unprocessedPoints.put(key, pointRefobjectMap.get(key));
			}
		}
	}

	private static void updateUnprossedShapes() {
		for (Entry<String, SrlShape> entry : unprocessedShapes.entrySet()) {
			String key = entry.getKey();
			if (shapeRefobjectMap.containsKey(key)) {
				unprocessedShapes.put(key, (SrlShape) shapeRefobjectMap.get(key));
			}
		}
	}

	private static void getSketchAttributes(Element attributesList, SrlObject srlObject) {
		NodeList sketchNodes = attributesList.getChildNodes();
		for (int i = 0; i < sketchNodes.getLength(); i++) {
			Node sketchNode = sketchNodes.item(i);
			if (sketchNode.getNodeType() == Node.ELEMENT_NODE) {
				getAttribute((Element) sketchNode, srlObject);
			}

		}
	}

	private static void getAttribute(Element attribute, SrlObject srlObject) {

		if (attribute.getAttribute("simpl:ref") != null) {
			String refId = (String) attribute.getAttribute("simpl:ref");
			if (refkeyMap.containsKey(refId)) {
				String key = refkeyMap.get(refId);
				String value = attributMapGlobal.get(key);
				srlObject.setAttribute(key, value);
			}
		}
		// not found
		else {
			srlObject.setAttribute((String) attribute.getAttribute("key"), (String) attribute.getAttribute("value"));
			attributMapGlobal.put((String) attribute.getAttribute("key"), (String) attribute.getAttribute("value"));

			if (attribute.getAttribute("simpl:id") != null) {
				String refId = (String) attribute.getAttribute("simpl:id");
				refkeyMap.put(refId, (String) attribute.getAttribute("key"));
			}
		}

	}

	private static SrlShapeExtended getShape(Element shape) {

		UUID shape_id = null;
		if (shape.getAttribute("id") != null && !shape.getAttribute("id").equals("")) {
			shape_id = UUID.fromString(shape.getAttribute("id"));
		}

		SrlShapeExtended mechanixShape = new SrlShapeExtended(0, shape_id, null, "");
		ArrayList<SrlShape> subShapeList = new ArrayList<SrlShape>();

		if (shape.getAttribute("simpl:ref") != null && !shape.getAttribute("simpl:ref").equals("")) {
			String refKey = (String) shape.getAttribute("simpl:ref");
			mechanixShape.setMechanixSimRef((String) shape.getAttribute("simpl:ref"));
			if (shapeRefobjectMap.containsKey(refKey)) {
				mechanixShape = (SrlShapeExtended) shapeRefobjectMap.get(refKey);
				mechanixShape.setMechanixSimRef((String) shape.getAttribute("simpl:ref"));
			} else {
				unprocessedShapes.put(refKey, mechanixShape);
			}
		}

		if (shape.getAttribute("simpl:id") != null && !shape.getAttribute("simpl:id").equals("")) {
			mechanixShape.setMechanixSimId((String) shape.getAttribute("simpl:id"));
		}

		NodeList sketchNodes = shape.getChildNodes();
		for (int i = 0; i < sketchNodes.getLength(); i++) {
			Node sketchNode = sketchNodes.item(i);
			if (sketchNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) sketchNode;
				switch (eElement.getNodeName()) {
				case "attributes":
					getSketchAttributes(eElement, mechanixShape);
					break;
				case "stroke":
					SrlStrokeExtended stroke = getStroke(eElement);
					mechanixShape.add(stroke);
					break;
				case "interpretations":
					getInterpretations(eElement, mechanixShape);
					break;
				case "shape":
					SrlShapeExtended subShape = getShape(eElement);
					subShapeList.add(subShape);
				}

			}

		}
		mechanixShape.addAll(subShapeList);
		return mechanixShape;
	}

	private static void getInterpretations(Element eElement, SrlShapeExtended srlObject) {

		NodeList sketchNodes = eElement.getChildNodes();
		for (int i = 0; i < sketchNodes.getLength(); i++) {
			Node sketchNode = sketchNodes.item(i);
			if (sketchNode.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) sketchNode;
				switch (element.getNodeName()) {
				case "interpretation":
					getInterpretation(element, srlObject);
					break;

				}

			}

		}
	}

	private static void getInterpretation(Element element, SrlShapeExtended srlObject) {
		srlObject.setInterpretation((String) element.getAttribute("label"), Double.parseDouble(element.getAttribute("confidence")));
	}

	private static SrlStrokeExtended getStroke(Element eElement) {
		
		if (eElement.getAttribute("simpl:ref") != null && !eElement.getAttribute("simpl:ref").equals("")) {
			String refKey = (String) eElement.getAttribute("simpl:ref");

			if (strokeRefobjectMap.containsKey(refKey)) {
				SrlStrokeExtended mechanixStroke = (SrlStrokeExtended) strokeRefobjectMap.get(refKey);
				mechanixStroke.setMechanixSimRef(refKey);
				return mechanixStroke;
			} else {
				SrlStrokeExtended dummymechanixStroke= new SrlStrokeExtended();
				dummymechanixStroke.setMechanixSimRef(refKey);
				unprocessedStroke.put(refKey,dummymechanixStroke);
			}
		}

		SrlStrokeExtended stroke = new SrlStrokeExtended(Long.parseLong((String) eElement.getAttribute("time")), UUID.fromString(eElement.getAttribute("id")), false);
		ArrayList<SrlPoint> points = new ArrayList<SrlPoint>();
		
		if ((String) eElement.getAttribute("draw_color") != null) {
			stroke.setColor((String) eElement.getAttribute("draw_color"));
		}

		NodeList sketchNodes = eElement.getChildNodes();
		for (int i = 0; i < sketchNodes.getLength(); i++) {
			Node sketchNode = sketchNodes.item(i);
			if (sketchNode.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) sketchNode;
				switch (element.getNodeName()) {
				case "elem_stroke":
					MechanixElementStroke elementStroke = getElemStroke(element);
					stroke.setElementStroke(elementStroke);
					break;
				case "point":
					SrlPoint point = getPoint(element);
					points.add(point);
					break;
				}

			}

		}
		
		if ((eElement.getAttribute("simpl:id")) != null && !eElement.getAttribute("simpl:id").equals("")) {
			stroke.setMechanixSimId((String) eElement.getAttribute("simpl:id"));
			strokeRefobjectMap.put(stroke.getMechanixSimId(), stroke);
		}
		
		stroke.addPoints(points);
		return stroke;
	}

	private static SrlPoint getPoint(Element element) {

		if ((element.getAttribute("simpl:ref")) != null && !element.getAttribute("simpl:ref").equals("")) {
			String refKey = (String) element.getAttribute("simpl:ref");
			if (pointRefobjectMap.containsKey(refKey)) {
				SrlPointExtended point = (SrlPointExtended) pointRefobjectMap.get(refKey);
				point.setMechanixSimRef(refKey);
				return point;
			} else {

				double dummyX = 0.0, dummyY = 0.0;
				SrlPointExtended dummyPoint = new SrlPointExtended(dummyX, dummyY);
				dummyPoint.setMechanixSimRef(refKey);
				unprocessedPoints.put(refKey, dummyPoint);
				return dummyPoint;

			}

		}

		SrlPointExtended point = new SrlPointExtended(Double.parseDouble(element.getAttribute("x")), Double.parseDouble(element.getAttribute("y")), Long.parseLong((String)element.getAttribute("time")), UUID.fromString(element.getAttribute("id")));

		if ((element.getAttribute("simpl:id")) != null && !element.getAttribute("simpl:id").equals("")) {
			point.setMechanixSimId((String) element.getAttribute("simpl:id"));
		}

		pointRefobjectMap.put(point.getMechanixSimId(), point);

		return point;
	}

	private static MechanixElementStroke getElemStroke(Element element) {
		MechanixElementStroke elementStroke = new MechanixElementStroke();
		elementStroke.setCap(Double.parseDouble(element.getAttribute("cap")));
		elementStroke.setWidth(Double.parseDouble(element.getAttribute("width")));
		elementStroke.setMiter_limit(Double.parseDouble(element.getAttribute("miter_limit")));
		return elementStroke;
	}

	private static ArrayList<SrlShape> getMechanixSketchFromDirectory(File dirName) throws ParserConfigurationException, IOException, SAXException {

		ArrayList<SrlShape> rvList = new ArrayList<SrlShape>();
		FilenameFilter xmlFilter = new FilenameFilter() {

			@Override
			public boolean accept(File dir, String filename) {
				String lowercaseName = filename.toLowerCase();
				if (lowercaseName.endsWith(".xml")) {
					return true;
				} else {
					return false;
				}
			}
		};

		File[] directoryListing = dirName.listFiles(xmlFilter);
		for (File xmlfile : directoryListing) {
			rvList.add(getMechanixSketchFromfile(xmlfile));
		}

		return rvList;
	}

	public static void main(String[] args) {
		List<SrlShape> sketchList = new ArrayList<SrlShape>();
		try {
			MechanixParser MechanixSketch = new MechanixParser();
			MechanixSketch.parse("C:\\Users\\Owner\\Dropbox\\Courses\\Skech Recognition\\Project\\test");
			sketchList = MechanixSketch.getMechanixSketchList();
			for (SrlShape mechanixSketch : sketchList) {
				mechanixSketch.toString();
			}
			System.out.println("");
		} catch (ParserConfigurationException | IOException | SAXException e) {
			System.out.println("Exception:  " + e.getMessage());
			e.printStackTrace();
		}
	}

}
