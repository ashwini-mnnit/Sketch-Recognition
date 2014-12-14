/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sketchMl;

import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author shirsing
 */
public class Shape {
    private ArrayList<Arg> arg;
    private ArrayList<Alias> alias;
    private UUID shapeId;
    private String shpeName;
    private String shapeType;
    private long time;
    private UUID authorId;
    private float color;
    private float height;
    private float area;
    private boolean render;
    private float orientation;
    private String penTip;
    private String raster;
    private UUID parentId;
    
    public Shape() {
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
		this.shapeId = shapeId;
		this.shpeName = shpeName;
		this.shapeType = shapeType;
		this.time = time;
		this.authorId = authorId;
		this.color = color;
		this.height = height;
		this.area = area;
		this.render = render;
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
	public void setArg(String argType, UUID argId) {
		
		Arg a= new Arg(argType, argId);
		this.arg.add(a);
	}
	
	public void setArg(ArrayList<Arg> arg) {
		this.arg = arg;
	}
	
	public ArrayList<Alias> getAlias() {
		return alias;
	}
	
	public void setAlias(ArrayList<Alias> alias) {
		this.alias = alias;;
	}
	
	public void setAlias(String aliasType, String aliasName, UUID aliasId) {
		Alias a = new Alias(aliasType,aliasName,aliasId);
		this.alias.add(a);
	}
	public float getArea() {
		return area;
	}
	public void setArea(float area) {
		this.area = area;
	}
	public UUID getShapeId() {
		return shapeId;
	}
	public void setShapeId(UUID shapeId) {
		this.shapeId = shapeId;
	}
	public String getShpeName() {
		return shpeName;
	}
	public void setShpeName(String shpeName) {
		this.shpeName = shpeName;
	}
	public String getShapeType() {
		return shapeType;
	}
	public void setShapeType(String shapeType) {
		this.shapeType = shapeType;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public UUID getAuthorId() {
		return authorId;
	}
	public void setAuthorId(UUID authorId) {
		this.authorId = authorId;
	}
	public float getColor() {
		return color;
	}
	public void setColor(float color) {
		this.color = color;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public boolean isRender() {
		return render;
	}
	public void setRender(boolean render) {
		this.render = render;
	}
	public float getOrientation() {
		return orientation;
	}
	public void setOrientation(float orientation) {
		this.orientation = orientation;
	}
	public String getPenTip() {
		return penTip;
	}
	public void setPenTip(String penTip) {
		this.penTip = penTip;
	}
	public String getRaster() {
		return raster;
	}
	public void setRaster(String raster) {
		this.raster = raster;
	}
	public UUID getParentId() {
		return parentId;
	}
	public void setParentId(UUID parentId) {
		this.parentId = parentId;
	}
	public String getP1() {
		return p1;
	}
	public void setP1(String p1) {
		this.p1 = p1;
	}
	public String getP2() {
		return p2;
	}
	public void setP2(String p2) {
		this.p2 = p2;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public float getLeftx() {
		return leftx;
	}
	public void setLeftx(float leftx) {
		this.leftx = leftx;
	}
	public float getTopy() {
		return topy;
	}
	public void setTopy(float topy) {
		this.topy = topy;
	}
	public String getControl1() {
		return control1;
	}
	public void setControl1(String control1) {
		this.control1 = control1;
	}
	public String getControl2() {
		return control2;
	}
	public void setControl2(String control2) {
		this.control2 = control2;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
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
}
