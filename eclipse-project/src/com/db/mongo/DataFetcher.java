package com.db.mongo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.parser.mechanix.MechanixParser;
import com.parser.mechanix.MechanixSketch;
import com.parser.sousa.SousaParser;
import com.sketchshape.SrlShapeExtended;

public class DataFetcher {
	private String path;

	public DataFetcher(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<SousaSketch> GetSouseDatas() {
		List<SousaSketch> sketchList = new ArrayList<SousaSketch>();
		try {
			sketchList = SousaParser.parse(path);
		}catch (ParserConfigurationException | IOException | SAXException e) {
			System.out.println("Exception:  " + e.getMessage());
			e.printStackTrace();
		}
		return sketchList;
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
	
	public List<MechanixSketch> GetMechanixData() {
		List<MechanixSketch> sketchList = new ArrayList<MechanixSketch>();
		try {
			MechanixParser MechanixSketch = new MechanixParser();
			MechanixSketch.parse(path);
			sketchList = MechanixSketch.getMechanixSketchList(); 
		} catch (ParserConfigurationException | IOException | SAXException e) {
			System.out.println("Exception:  " + e.getMessage());
			e.printStackTrace();
		}	
		return sketchList;
	}
}
