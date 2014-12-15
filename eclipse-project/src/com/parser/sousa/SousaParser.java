package com.parser.sousa;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
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

public class SousaParser {

	public static List<SousaSketch> parse(String filename) throws ParserConfigurationException, IOException, SAXException {
		List<SousaSketch> rvSketchList = new ArrayList<SousaSketch>();
		File f = new File(filename);
		if (!f.exists()) {
			throw new FileNotFoundException("Filename: " + filename + " does not exist. !!!");
		}

		if (f.isDirectory()) {
			rvSketchList = getSousaSketchFromDirectory(f);
		} else {
			SousaSketch sketchFormFile = getSousaSketchFromfile(f);
			rvSketchList.add(sketchFormFile);
		}
		return rvSketchList;
	}

	private static SousaSketch getSousaSketchFromfile(File filename) throws ParserConfigurationException, IOException, SAXException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(filename);
		doc.getDocumentElement().normalize();

		SousaSketch sketch = new SousaSketch();
		sketch.setId(UUID.fromString(doc.getDocumentElement().getAttribute("id")));
		sketch.setAuthor(doc.getDocumentElement().getAttribute("author"));
		sketch.setType(doc.getDocumentElement().getAttribute("type"));

		List<SousaPoint> points = new ArrayList<SousaPoint>();
		List<SousaStroke> strokes = new ArrayList<SousaStroke>();

		NodeList sketchNodes = doc.getDocumentElement().getChildNodes();
		for (int i = 0; i < sketchNodes.getLength(); i++) {
			Node sketchNode = sketchNodes.item(i);
			if (sketchNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) sketchNode;
				switch (eElement.getNodeName()) {
				case "point":
					SousaPoint point = getPoint(eElement);
					points.add(point);
					break;
				case "stroke":
					SousaStroke stroke = getStroke(eElement);
					strokes.add(stroke);
					break;
				}

			}

		}
		updateSketch(sketch, points, strokes);
		return sketch;
	}

	private static void updateSketch(SousaSketch sketch, List<SousaPoint> points, List<SousaStroke> strokes) {

		for (SousaStroke sousaStroke : strokes) {
			for (SousaArg arg : sousaStroke.getArgList()) {
				SousaPoint p = getPointFromID(points, arg.getId());
				if (p != null)
					sousaStroke.getPointList().add(p);
			}
			sketch.getSousaStrokes().add(sousaStroke);
		}
	}

	private static SousaPoint getPointFromID(List<SousaPoint> points, UUID id) {
		for (SousaPoint sousaPoint : points) {
			if (id.compareTo(sousaPoint.getId())==0)
				return sousaPoint;
		}
		return null;
	}

	private static SousaStroke getStroke(Element stroke) {
		SousaStroke rvStroke = new SousaStroke(UUID.fromString(stroke.getAttribute("id")));
		NodeList argList = stroke.getChildNodes();
		for (int j = 0; j < argList.getLength(); j++) {
			Node argument = argList.item(j);
			
			if (argument.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) argument;
				SousaArg arg = new SousaArg();
				arg.setId(UUID.fromString(eElement.getTextContent()));
				arg.setType(eElement.getAttribute("type"));
				rvStroke.getArgList().add(arg);
			}
		}
		return rvStroke;
	}

	private static SousaPoint getPoint(Element point) {
		SousaPoint rvPoint = new SousaPoint();
		rvPoint.setId(UUID.fromString(point.getAttribute("id")));
		rvPoint.setxCoordinate(Double.parseDouble(point.getAttribute("x")));
		rvPoint.setyCoordinate(Double.parseDouble(point.getAttribute("y")));
		rvPoint.setTime(point.getAttribute("time"));
		return rvPoint;
	}

	private static List<SousaSketch> getSousaSketchFromDirectory(File dirName) throws ParserConfigurationException, IOException, SAXException {

		List<SousaSketch> rvList = new ArrayList<SousaSketch>();
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
			rvList.add(getSousaSketchFromfile(xmlfile));
		}

		return rvList;
	}
}
