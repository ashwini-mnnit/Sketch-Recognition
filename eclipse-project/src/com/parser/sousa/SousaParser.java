package com.parser.sousa;

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

import com.sketchshape.SrlShapeExtended;
import com.sketchshape.SrlStrokeExtended;

import edu.tamu.srl.sketch.core.object.SrlShape;
import edu.tamu.srl.sketch.core.object.SrlStroke;
import edu.tamu.srl.sketch.core.virtual.SrlPoint;

public class SousaParser {

	public static List<SrlShape> parse(String filename) throws ParserConfigurationException, IOException, SAXException {
		List<SrlShape> rvSketchList = new ArrayList<SrlShape>();
		File f = new File(filename);
		if (!f.exists()) {
			throw new FileNotFoundException("Filename: " + filename + " does not exist. !!!");
		}

		if (f.isDirectory()) {
			rvSketchList = getSousaSketchFromDirectory(f);
		} else {
			SrlShapeExtended sketchFormFile = getSousaSketchFromfile(f);
			rvSketchList.add(sketchFormFile);
		}
		return rvSketchList;
	}

	private static SrlShapeExtended getSousaSketchFromfile(File filename) throws ParserConfigurationException, IOException, SAXException {
		HashMap<UUID, List<SousaArg>> tempArgMap = new HashMap<UUID, List<SousaArg>>();

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(filename);
		doc.getDocumentElement().normalize();

		SrlShapeExtended sketch = new SrlShapeExtended(0, UUID.fromString(doc.getDocumentElement().getAttribute("id")), null, doc.getDocumentElement().getAttribute("type"));
		sketch.setAuthorName(doc.getDocumentElement().getAttribute("author"));

		List<SrlPoint> points = new ArrayList<SrlPoint>();
		List<SrlStroke> strokes = new ArrayList<SrlStroke>();

		NodeList sketchNodes = doc.getDocumentElement().getChildNodes();
		for (int i = 0; i < sketchNodes.getLength(); i++) {
			Node sketchNode = sketchNodes.item(i);
			if (sketchNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) sketchNode;
				switch (eElement.getNodeName()) {
				case "point":
					SrlPoint point = getPoint(eElement);
					points.add(point);
					break;
				case "stroke":
					SrlStrokeExtended stroke = getStroke(eElement, tempArgMap);
					strokes.add(stroke);
					break;
				}

			}

		}
		updateSketch(sketch, points, strokes, tempArgMap);
		return sketch;
	}

	private static void updateSketch(SrlShapeExtended sketch, List<SrlPoint> points, List<SrlStroke> strokes, HashMap<UUID, List<SousaArg>> tempArgMap) {

		for (SrlStroke srlStroke : strokes) {
			for (SousaArg arg : tempArgMap.get(srlStroke.getId())) {

				SrlPoint p = getPointFromID(points, arg.getId());
				if (p != null)
					srlStroke.addPoint(p);
			}
			sketch.add(srlStroke);
			;
		}
	}

	private static SrlPoint getPointFromID(List<SrlPoint> points, UUID id) {
		for (SrlPoint sousaPoint : points) {
			if (id.compareTo(sousaPoint.getId()) == 0)
				return sousaPoint;
		}
		return null;
	}

	private static SrlStrokeExtended getStroke(Element stroke, HashMap<UUID, List<SousaArg>> tempArgMap) {
		SrlStrokeExtended rvStroke = new SrlStrokeExtended(0, UUID.fromString(stroke.getAttribute("id")), false);

		List<SousaArg> sousaArg = new ArrayList<SousaArg>();
		NodeList argList = stroke.getChildNodes();
		for (int j = 0; j < argList.getLength(); j++) {
			Node argument = argList.item(j);

			if (argument.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) argument;
				SousaArg arg = new SousaArg();
				arg.setId(UUID.fromString(eElement.getTextContent()));
				arg.setType(eElement.getAttribute("type"));
				sousaArg.add(arg);
			}
		}
		tempArgMap.put(rvStroke.getId(), sousaArg);
		return rvStroke;
	}

	private static SrlPoint getPoint(Element point) {
		SrlPoint rvPoint = new SrlPoint(Double.parseDouble(point.getAttribute("x")), Double.parseDouble(point.getAttribute("y")), Long.parseLong(point.getAttribute("time")), UUID.fromString(point.getAttribute("id")));
		return rvPoint;
	}

	private static List<SrlShape> getSousaSketchFromDirectory(File dirName) throws ParserConfigurationException, IOException, SAXException {

		List<SrlShape> rvList = new ArrayList<SrlShape>();
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

	public static void main(String[] args) {
		try {
			List<SrlShape> sketchList = SousaParser.parse("C:\\Users\\Owner\\Dropbox\\Courses\\Skech Recognition\\Project\\sousa-108-[2014-11-04-21-22-43.956834]-decision-graphic-sub-study\\108\\1839\\17902.xml");
			for (SrlShape sousaSketch : sketchList) {
				for (SrlStroke sousaStroke : sousaSketch.getRecursiveStrokeList()) {
					((SrlStrokeExtended) sousaStroke).updatePrimitiveTypes();
				}
			}
			System.out.println("");
		} catch (ParserConfigurationException | IOException | SAXException e) {
			System.out.println("Exception:  " + e.getMessage());
			e.printStackTrace();
		}

	}

}
