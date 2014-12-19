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

import edu.tamu.srl.sketch.core.object.SrlShape;

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

	public List<SrlShape> GetSouseDatas() {
		List<SrlShape> sketchList = new ArrayList<SrlShape>();
		try {
			sketchList = SousaParser.parse(path);
		}catch (ParserConfigurationException | IOException | SAXException e) {
			System.out.println("Exception:  " + e.getMessage());
			e.printStackTrace();
		}
		
		return sketchList;
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
