/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sketchMl;

import java.util.ArrayList;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author shirsing
 */
@XmlRootElement
public class Shape {
    private ArrayList<Arg> arg;
    private ArrayList<Alias> alias;
    private UUID id;
    private String name;
    private String type;
    private long time;
    private UUID author;
    private float color;
    private float height;
    private float area;
    private boolean laysInk;
    private float orientation;
    private String penTip;
    private String raster;
    private UUID parentId;
    private String p1;
    private String p2;
    private float x;
    private float y;
    private String text;
    private float leftx;
    private float topy;
    private String control1;
    private String control2;
    private String start;
    private String end;
    private String source;
    
    public Shape() {
    	id = UUID.randomUUID();
    	name = "";
    	author = UUID.randomUUID();
    	type = "";
    	time = 0;
    	color = 0;
    	area = 0;
    	laysInk = false;
    	height = 0;
    	raster = "";
    	penTip = "Ball";
    	arg = new ArrayList<Arg>();
    	alias = new ArrayList<Alias>();
    }
    
    public Shape(ArrayList<Arg> arg, ArrayList<Alias> alias, UUID shapeId, String shpeName,
			String shapeType, long time, UUID authorId, float color,
			float height, float area, boolean render, float orientation,
			String penTip, String raster, UUID parentId, String p1, String p2,
			float x, float y, String text, float leftx, float topy,
			String control1, String control2, String start, String end,
			String source) {
		this.arg = arg;
		this.alias = alias;
		this.id = shapeId;
		this.name = shpeName;
		this.type = shapeType;
		this.time = time;
		this.author = authorId;
		this.color = color;
		this.height = height;
		this.area = area;
		this.laysInk = render;
		this.orientation = orientation;
		this.penTip = penTip;
		this.raster = raster;
		this.parentId = parentId;
		this.p1 = p1;
		this.p2 = p2;
		this.x = x;
		this.y = y;
		this.text = text;
		this.leftx = leftx;
		this.topy = topy;
		this.control1 = control1;
		this.control2 = control2;
		this.start = start;
		this.end = end;
		this.source = source;
	}

	public ArrayList<Arg> getArg() {
		return arg;
	}
	 @XmlElement
	public void setArg(ArrayList<Arg> arg) {
		this.arg = arg;
	}

	public ArrayList<Alias> getAlias() {
		return alias;
	}
	 @XmlElement
	public void setAlias(ArrayList<Alias> alias) {
		this.alias = alias;
	}

	public UUID getId() {
		return id;
	}
	@XmlAttribute
	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	@XmlAttribute
	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}
	@XmlAttribute
	public void setType(String type) {
		this.type = type;
	}

	public long getTime() {
		return time;
	}
	@XmlAttribute
	public void setTime(long time) {
		this.time = time;
	}

	public UUID getAuthor() {
		return author;
	}
	@XmlAttribute
	public void setAuthor(UUID author) {
		this.author = author;
	}

	public float getColor() {
		return color;
	}
	@XmlAttribute
	public void setColor(float color) {
		this.color = color;
	}

	public float getHeight() {
		return height;
	}
	@XmlAttribute
	public void setHeight(float height) {
		this.height = height;
	}

	public float getArea() {
		return area;
	}
	@XmlAttribute
	public void setArea(float area) {
		this.area = area;
	}

	public boolean isLaysInk() {
		return laysInk;
	}
	@XmlAttribute
	public void setLaysInk(boolean laysInk) {
		this.laysInk = laysInk;
	}

	public float getOrientation() {
		return orientation;
	}
	@XmlAttribute
	public void setOrientation(float orientation) {
		this.orientation = orientation;
	}
	
	public String getPenTip() {
		return penTip;
	}
	@XmlAttribute
	public void setPenTip(String penTip) {
		this.penTip = penTip;
	}

	public String getRaster() {
		return raster;
	}
	@XmlAttribute
	public void setRaster(String raster) {
		this.raster = raster;
	}

    
}
