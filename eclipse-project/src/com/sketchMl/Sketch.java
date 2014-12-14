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
public class Sketch {
 private UUID sketchId;
 private String units;
 private ArrayList<Sketcher> sketcher;
 private Study study;
 private Domain domain;
 private ArrayList<Point> point;
 private ArrayList<Shape> shape;
 private ArrayList<Edit> edit;
 private ArrayList<Speech> speech;
 private ArrayList<MediaInfo> mediainfo;
 
 
public Sketch(UUID sketchId, String units, ArrayList<Sketcher> sketcher, Study study,
		Domain domain, ArrayList<Point> point, ArrayList<Shape> shape, ArrayList<Edit> edit, ArrayList<Speech> speech,
		ArrayList<MediaInfo> mediainfo) {
	this.sketchId = sketchId;
	this.units = units;
	this.sketcher = sketcher;
	this.study = study;
	this.domain = domain;
	this.point = point;
	this.shape = shape;
	this.edit = edit;
	this.speech = speech;
	this.mediainfo = mediainfo;
}

public Sketch() {
	this.sketcher = new ArrayList<Sketcher>();
	this.point = new ArrayList<Point>();
	this.shape = new ArrayList<Shape>();
	this.edit = new ArrayList<Edit>();
	this.speech = new ArrayList<Speech>();
	this.mediainfo = new ArrayList<MediaInfo>();
}

public UUID getSketchId() {
	return sketchId;
}
public void setSketchId(UUID sketchId) {
	this.sketchId = sketchId;
}
public String getUnits() {
	return units;
}
public void setUnits(String units) {
	this.units = units;
}
public ArrayList<Sketcher> getSketcher() {
	return sketcher;
}
public void setSketcher(UUID id, float XAxis, float YAxis, String nickName) {
	Sketcher s = new Sketcher(id, XAxis, YAxis, nickName);
	this.sketcher.add(s);
}

public void setSketcher(ArrayList<Sketcher> sketcher) {
	this.sketcher = sketcher;
}

public Study getStudy() {
	return study;
}
public void setStudy(Study study) {
	this.study = study;
}

public void setStudy(String studyName) {
	this.study = new Study(studyName);
}

public Domain getDomain() {
	return domain;
}
public void setDomain(Domain domain) {
	this.domain = domain;
}

public void setDomain(String domain) {
	this.domain = new Domain(domain);
}
public ArrayList<Point> getPoint() {
	return point;
}
public void setPoint(ArrayList<Point> point) {
	this.point = point;
}

public void setPoint(float xCordinate, float yCordinate, float pressure, 
        String pointName, UUID pointID, long time) {
		Point point = new Point(xCordinate,yCordinate,pressure,pointName,pointID,time);
		this.point.add(point);
}

public ArrayList<Shape> getShape() {
	return shape;
}
public void setShape(ArrayList<Shape> shape) {
	this.shape = shape;
}
public ArrayList<Edit> getEdit() {
	return edit;
}
public void setEdit(ArrayList<Edit> edit) {
	this.edit = edit;
}

public void setEdit(Trigger trigger, ArrayList<Arg> arg, UUID editId, long time, 
		String editType) {
		Edit edit = new Edit(trigger, arg,editId,time,editType);
		this.edit.add(edit);
}

public ArrayList<Speech> getSpeech() {
	return speech;
}

public void setSpeech(ArrayList<Speech> speech) {
	this.speech = speech;
}

public ArrayList<MediaInfo> getMediainfo() {
	return mediainfo;
}
public void setMediainfo(ArrayList<MediaInfo> mediainfo) {
	this.mediainfo = mediainfo;
}

public void setMediainfo(Id mediaInfoId, Arg arg, long startTime, String mediaType) {
	MediaInfo mediainfo = new MediaInfo(mediaInfoId,arg,startTime,mediaType); 
	this.mediainfo.add(mediainfo);
}
 
}
