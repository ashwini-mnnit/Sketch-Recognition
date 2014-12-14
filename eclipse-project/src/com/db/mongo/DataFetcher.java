package com.db.mongo;

//shirsing
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.parser.sousa.SousaParser;
import com.parser.sousa.SousaSketch;
import com.sketchshape.SketchExtended;
import com.sketchshape.SrlShapeExtended;

public class DataFetcher {
	private String path;

	public DataFetcher(String path) {
		this.path = path;
	}

	public List<SrlShapeExtended> GetSouseData() {
		List<SousaSketch> sketchList = new ArrayList<SousaSketch>();
		try {
			sketchList = SousaParser.parse(path);
		} catch (ParserConfigurationException | IOException | SAXException e) {
			System.out.println("Exception:  " + e.getMessage());
			e.printStackTrace();
		}
		SketchExtended sketchExtended = new SketchExtended();
		sketchExtended.parseSouseSketch(sketchList);
		return sketchExtended.getSrlshapes();
	}
}
