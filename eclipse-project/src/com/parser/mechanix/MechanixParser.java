package com.parser.mechanix;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import edu.tamu.srl.sketch.core.virtual.SrlPoint;


public class MechanixParser {
    private ArrayList<SrlShape> mechanixSketchList = new ArrayList<SrlShape>();
    
	public MechanixParser() {
		super(); 
		this.mechanixSketchList=new ArrayList<SrlShape>();
	}

	
	public ArrayList<SrlShape> getMechanixSketchList() {
		return mechanixSketchList;
	}


	public void setMechanixSketchList(ArrayList<SrlShape> mechanixSketchList) {
		this.mechanixSketchList = mechanixSketchList;
	}


	public  void parse(String filename) throws ParserConfigurationException, IOException, SAXException {
		//ArrayList<MechanixSketch> rvSketchList = new ArrayList<MechanixSketch>();
		HashMap<String, SrlObject> mapforRefrenceObject = new HashMap<String, SrlObject>();
		File f = new File(filename);
		if (!f.exists()) {
			throw new FileNotFoundException("Filename: " + filename + " does not exist. !!!");
		}

		if (f.isDirectory()) {
			mechanixSketchList = getMechanixSketchFromDirectory(f,mapforRefrenceObject);
		} else {
			SrlShape sketchFormFile = getMechanixSketchFromfile(f,mapforRefrenceObject);
			mechanixSketchList.add(sketchFormFile);
		}
		//MechanixParser.setMechanixSketchList(rvSketchList);
		
	}
	
	private static SrlShape getMechanixSketchFromfile(File filename, HashMap<String, SrlObject> mapforRefrenceObject) throws ParserConfigurationException, IOException, SAXException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(filename);
		//doc.getDocumentElement().normalize();

		SrlShapeExtended sketch = new SrlShapeExtended(Long.parseLong(doc.getDocumentElement().getAttribute("time")), UUID.fromString(doc.getDocumentElement().getAttribute("id")), null, doc.getDocumentElement().getAttribute("type"));
		//sketch.setId(UUID.fromString(doc.getDocumentElement().getAttribute("id")));
		//sketch.setTime(doc.getDocumentElement().getAttribute("time"));
		sketch.setColor(doc.getDocumentElement().getAttribute("draw_color"));

		MechanixAttributes attributes = new MechanixAttributes();
		ArrayList<SrlShape> shapes = new ArrayList<SrlShape>();

		NodeList sketchNodes = doc.getDocumentElement().getChildNodes();
		for (int i = 0; i < sketchNodes.getLength(); i++) {
			Node sketchNode = sketchNodes.item(i);
			if (sketchNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) sketchNode;
				switch (eElement.getNodeName()) {
				case "attributes":
					attributes = getSketchAttributes(eElement);
					break;
				case "shape":
					SrlShapeExtended shape = getShape(eElement);
					mapforRefrenceObject.put(shape.getMechanixSimId(),shape);
					shapes.add(shape);
					break;
				}

			}

		}
		sketch.setAttribute(attributes);
		sketch.addAll(shapes);
		//updateSketch(sketch, points, strokes);
		return sketch;
	}
	
	private static MechanixAttributes getSketchAttributes(Element attributesList) {
		MechanixAttributes attributes = new MechanixAttributes();
		ArrayList<MechanixAttribute> attributeList = new ArrayList<MechanixAttribute>();
		
		NodeList sketchNodes = attributesList.getChildNodes();
		for (int i = 0; i < sketchNodes.getLength(); i++) {
			Node sketchNode = sketchNodes.item(i);
			if (sketchNode.getNodeType() == Node.ELEMENT_NODE) {
				MechanixAttribute attr = getAttribute((Element)sketchNode);
				attributeList.add(attr);
			}

		}
		attributes.setAtributes(attributeList);
		
		return attributes;
	}
	
	private static MechanixAttribute getAttribute(Element attribute) {
		MechanixAttribute attr = new MechanixAttribute();
		attr.setKey((String)attribute.getAttribute("key"));
		attr.setValue((String)attribute.getAttribute("value"));
		if(attribute.getAttribute("simpl:id") != null){
			attr.setSimpl_id((String)attribute.getAttribute("simpl:id"));
		}
		
		return attr;
	 
	}
	private static SrlShapeExtended getShape(Element shape) {

		
		SrlShapeExtended mechanixShape = new SrlShapeExtended(0,UUID.fromString(shape.getAttribute("id")),null,"");
		ArrayList<SrlShape> subShapeList = new ArrayList<SrlShape>();
		/*
		if(shape.getAttribute("id") != null && !shape.getAttribute("id").equals("")){
			mechanixShape.set(UUID.fromString(shape.getAttribute("id")));
		}
		
		if(shape.getAttribute("time") != null && !shape.getAttribute("time").equals("")){
			mechanixShape.setTime((String)shape.getAttribute("time"));
		}
		*/ 
		if(shape.getAttribute("simpl:id") != null && !shape.getAttribute("simpl:id").equals("")){
			mechanixShape.setMechanixSimId((String)shape.getAttribute("simpl:id"));
		}
		
		if(shape.getAttribute("simpl:ref") != null && !shape.getAttribute("simpl:ref").equals("")){
			mechanixShape.setMechanixSimRef((String)shape.getAttribute("simpl:ref"));
		}
		
		MechanixAttributes attributes;
		
		NodeList sketchNodes = shape.getChildNodes();
		for (int i = 0; i < sketchNodes.getLength(); i++) {
			Node sketchNode = sketchNodes.item(i);
			if (sketchNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) sketchNode;
				switch (eElement.getNodeName()) {
				case "attributes":
					attributes = getSketchAttributes(eElement);
					mechanixShape.setAttribute(attributes);
					break;
				case "stroke":
					SrlStrokeExtended stroke = getStroke(eElement);
					mechanixShape.add(stroke);
					break;
				case "interpretations":
					//TODO:Add  the support for this
				//	MechanixInterpretations interpretations = getInterpretations(eElement);
//				//	mechanixShape.setConfidence(interpretations.);
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

	private static MechanixInterpretations getInterpretations(Element eElement) {
		MechanixInterpretations interpretations = new MechanixInterpretations(); 
		ArrayList<MechanixInterpretation> interpretationList = new ArrayList<MechanixInterpretation>();
		
		NodeList sketchNodes = eElement.getChildNodes();
		for (int i = 0; i < sketchNodes.getLength(); i++) {
			Node sketchNode = sketchNodes.item(i);
			if (sketchNode.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) sketchNode;
				switch (element.getNodeName()) {
				case "interpretation":
					MechanixInterpretation interpretation = getInterpretation(element);
					interpretationList.add(interpretation);
					break;
				
				}

			}

		}
		interpretations.setInterpretations(interpretationList);
		
		return interpretations;
	}

	private static MechanixInterpretation getInterpretation(Element element) {
		MechanixInterpretation interpretation = new MechanixInterpretation();
		interpretation.setConfidence(Double.parseDouble(element.getAttribute("confidence")));
		interpretation.setLabel((String)element.getAttribute("label"));
		
		return interpretation;
	}

	private static SrlStrokeExtended getStroke(Element eElement) {
		SrlStrokeExtended stroke = new SrlStrokeExtended(Long.parseLong((String)eElement.getAttribute("time")),UUID.fromString(eElement.getAttribute("id")),false);
		ArrayList<SrlPoint> points = new ArrayList<SrlPoint>();
		
		/*
		if(UUID.fromString(eElement.getAttribute("id")) != null){
			stroke.setId(UUID.fromString(eElement.getAttribute("id")));
		}
		if((String)eElement.getAttribute("time") != null){
			stroke.setTime((String)eElement.getAttribute("time"));
		}
		*/
		if((String)eElement.getAttribute("draw_color") != null){
			stroke.setColor((String)eElement.getAttribute("draw_color"));
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
		
		
		stroke.addPoints(points);
		return stroke;
	}

	private static SrlPoint getPoint(Element element) {
		SrlPointExtended point = new SrlPointExtended(Double.parseDouble(element.getAttribute("x")),Double.parseDouble(element.getAttribute("y")),Long.parseLong((String)element.getAttribute("time")), UUID.fromString(element.getAttribute("id")));
		
		/*
		if((element.getAttribute("id")) != null && !element.getAttribute("id").equals("")){
			point.setId(UUID.fromString(element.getAttribute("id")));
		}
		if((element.getAttribute("time")) != null && !element.getAttribute("time").equals("")){
			point.setTime((String)element.getAttribute("time"));
		}
		if((element.getAttribute("x")) != null && !element.getAttribute("x").equals("")){
			point.setX(Double.parseDouble(element.getAttribute("x")));
		}*
		if((element.getAttribute("y")) != null && !element.getAttribute("y").equals("")){
			point.setY(Double.parseDouble(element.getAttribute("y")));
		}
		*/
		if((element.getAttribute("simpl:id")) != null && !element.getAttribute("simpl:id").equals("")){
			point.setMechanixSimId((String)element.getAttribute("simpl:id"));
		}
		if((element.getAttribute("simpl:ref")) != null && !element.getAttribute("simpl:ref").equals("")){
			point.setMechanixSimRef(((String)element.getAttribute("simpl:ref")));
		}
		
		return point;
	}

	private static MechanixElementStroke getElemStroke(Element element) {
		MechanixElementStroke elementStroke = new MechanixElementStroke();
		elementStroke.setCap(Double.parseDouble(element.getAttribute("cap")));
		elementStroke.setWidth(Double.parseDouble(element.getAttribute("width")));
		elementStroke.setMiter_limit(Double.parseDouble(element.getAttribute("miter_limit")));
		return elementStroke;
	}
	
	private static ArrayList<SrlShape> getMechanixSketchFromDirectory(File dirName, HashMap<String, SrlObject> mapforRefrenceObject) throws ParserConfigurationException, IOException, SAXException {

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
			MechanixSketch.parse("C:\\Users\\Owner\\Dropbox\\Courses\\Skech Recognition\\Project\\SketchData.xml");
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
