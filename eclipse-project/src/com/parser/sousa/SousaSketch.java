package com.parser.sousa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

public class SousaSketch {
	private UUID id;
	private String type;
	private String author;

	private ArrayList<SousaStroke> sousaStrokes;

	public ArrayList<SousaStroke> getSousaStrokes() {
		return sousaStrokes;
	}

	public void setSousaStrokes(ArrayList<SousaStroke> sousaStrokes) {
		this.sousaStrokes = sousaStrokes;
	}


	public SousaSketch() {
		super();
		sousaStrokes = new ArrayList<SousaStroke>();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Boolean isEmpty() {
		return (sousaStrokes.size() == 0);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPremitivetype() {
		boolean isLineSketch= true;
		for (SousaStroke stroke : this.sousaStrokes) {
			if (!stroke.isLine())
				isLineSketch=false;

		}
		if(isLineSketch)
			return "Line";
		return "Not Line";
	}
}
