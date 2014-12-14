package com.db.mongo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.parser.sousa.SousaParser;
import com.parser.sousa.SousaSketch;

public class DataFetcher {
	private String path;

	public DataFetcher(String path) {
		this.path = path;
	}

	public List<SousaSketch> GetSouseData() {
		List<SousaSketch> sketchList = new ArrayList<SousaSketch>();
		try {
			sketchList = SousaParser.parse(path);
		} catch (ParserConfigurationException | IOException | SAXException e) {
			System.out.println("Exception:  " + e.getMessage());
			e.printStackTrace();
		}
		return sketchList;
	}
}
