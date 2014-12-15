package com.parser.mechanix;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.gson.Gson;

import edu.tamu.srl.sketch.core.object.SrlShape;


public class MechanixParser {
    private ArrayList<MechanixSketch> mechanixSketchList = new ArrayList<MechanixSketch>();
    
	public ArrayList<MechanixSketch> getMechanixSketchList() {
		return mechanixSketchList;
	}

	public MechanixParser() {
		super(); 
		this.mechanixSketchList=new ArrayList<MechanixSketch>();
	}

	public void setMechanixSketchList(ArrayList<MechanixSketch> mechanixSketchList) {
		this.mechanixSketchList = mechanixSketchList;
	}

	public  void parse(String filename) throws ParserConfigurationException, IOException, SAXException {
		//ArrayList<MechanixSketch> rvSketchList = new ArrayList<MechanixSketch>();
		File f = new File(filename);
		if (!f.exists()) {
			throw new FileNotFoundException("Filename: " + filename + " does not exist. !!!");
		}

		if (f.isDirectory()) {
			mechanixSketchList = getMechanixSketchFromDirectory(f);
		} else {
			MechanixSketch sketchFormFile = getMechanixSketchFromfile(f);
			mechanixSketchList.add(sketchFormFile);
		}
		//MechanixParser.setMechanixSketchList(rvSketchList);
		
	}
	
	private static MechanixSketch getMechanixSketchFromfile(File filename) throws ParserConfigurationException, IOException, SAXException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(filename);
		//doc.getDocumentElement().normalize();

		MechanixSketch sketch = new MechanixSketch();
		sketch.setId(UUID.fromString(doc.getDocumentElement().getAttribute("id")));
		sketch.setTime(doc.getDocumentElement().getAttribute("time"));
		sketch.setDraw_color(doc.getDocumentElement().getAttribute("draw_color"));

		MechanixAttributes attributes = new MechanixAttributes();
		ArrayList<MechanixShape> shapes = new ArrayList<MechanixShape>();

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
					MechanixShape shape = getShape(eElement);
					shapes.add(shape);
					break;
				}

			}

		}
		sketch.setAttributes(attributes);
		sketch.setShapes(shapes);
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
	private static MechanixShape getShape(Element shape) {
		MechanixShape mechanixShape = new MechanixShape();
		ArrayList<MechanixShape> subShapeList = new ArrayList<MechanixShape>();
		if(shape.getAttribute("id") != null && !shape.getAttribute("id").equals("")){
			mechanixShape.setId(UUID.fromString(shape.getAttribute("id")));
		}
		
		if(shape.getAttribute("time") != null && !shape.getAttribute("time").equals("")){
			mechanixShape.setTime((String)shape.getAttribute("time"));
		}
		 
		if(shape.getAttribute("simpl:id") != null && !shape.getAttribute("simpl:id").equals("")){
			mechanixShape.setSimpl_id((String)shape.getAttribute("simpl:id"));
		}
		
		if(shape.getAttribute("simpl:ref") != null && !shape.getAttribute("simpl:ref").equals("")){
			mechanixShape.setSimpl_ref((String)shape.getAttribute("simpl:ref"));
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
					mechanixShape.setAttributes(attributes);
					break;
				case "stroke":
					MechanixStroke stroke = getStroke(eElement);
					mechanixShape.setStroke(stroke);
					break;
				case "interpretations":
					MechanixInterpretations interpretations = getInterpretations(eElement);
					mechanixShape.setInterpretations(interpretations);
				case "shape":
					MechanixShape subShape = getShape(eElement);
					subShapeList.add(subShape);
				}

			}

		}
		mechanixShape.setShapes(subShapeList);
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

	private static MechanixStroke getStroke(Element eElement) {
		MechanixStroke stroke = new MechanixStroke();
		ArrayList<MechanixPoint> points = new ArrayList<MechanixPoint>();
		
		if(UUID.fromString(eElement.getAttribute("id")) != null){
			stroke.setId(UUID.fromString(eElement.getAttribute("id")));
		}
		if((String)eElement.getAttribute("time") != null){
			stroke.setTime((String)eElement.getAttribute("time"));
		}
		if((String)eElement.getAttribute("draw_color") != null){
			stroke.setDraw_color((String)eElement.getAttribute("draw_color"));
		}
		
		NodeList sketchNodes = eElement.getChildNodes();
		for (int i = 0; i < sketchNodes.getLength(); i++) {
			Node sketchNode = sketchNodes.item(i);
			if (sketchNode.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) sketchNode;
				switch (element.getNodeName()) {
				case "elem_stroke":
					MechanixElementStroke elementStroke = getElemStroke(element);
					stroke.setElem_stroke(elementStroke);
					break;
				case "point":
					MechanixPoint point = getPoint(element);
					points.add(point);
					break;
				}

			}

		}
		
		
		stroke.setPoints(points);
		return stroke;
	}

	private static MechanixPoint getPoint(Element element) {
		MechanixPoint point = new MechanixPoint();
		
		if((element.getAttribute("id")) != null && !element.getAttribute("id").equals("")){
			point.setId(UUID.fromString(element.getAttribute("id")));
		}
		if((element.getAttribute("time")) != null && !element.getAttribute("time").equals("")){
			point.setTime((String)element.getAttribute("time"));
		}
		if((element.getAttribute("x")) != null && !element.getAttribute("x").equals("")){
			point.setX(Double.parseDouble(element.getAttribute("x")));
		}
		if((element.getAttribute("y")) != null && !element.getAttribute("y").equals("")){
			point.setY(Double.parseDouble(element.getAttribute("y")));
		}
		if((element.getAttribute("simpl:id")) != null && !element.getAttribute("simpl:id").equals("")){
			point.setSimpl_id((String)element.getAttribute("simpl:id"));
		}
		if((element.getAttribute("simpl:ref")) != null && !element.getAttribute("simpl:ref").equals("")){
			point.setSimpl_ref((String)element.getAttribute("simpl:ref"));
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
	
	private static ArrayList<MechanixSketch> getMechanixSketchFromDirectory(File dirName) throws ParserConfigurationException, IOException, SAXException {

		ArrayList<MechanixSketch> rvList = new ArrayList<MechanixSketch>();
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
	
	public static void main(String[] args) throws ParseException {
		System.out.println("Ankit");
		MechanixParser MechanixSketch = new MechanixParser(); 
		ArrayList<MechanixSketch> SketchList = new ArrayList<MechanixSketch>();
		ArrayList<MechanixSketch> SketchList1 = new ArrayList<MechanixSketch>();
		try {
			MechanixSketch.parse("C:\\Users\\Ankit\\Desktop\\Fall 2014\\Sketch Recognition\\project\\Data\\SketchData.xml");
			SketchList = MechanixSketch.getMechanixSketchList();
			
		} catch (ParserConfigurationException | IOException | SAXException e) {
			System.out.println("Exception:  " + e.getMessage());
			e.printStackTrace();
		}

	}
	
}
