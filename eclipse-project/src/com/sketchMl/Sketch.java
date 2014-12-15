/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sketchMl;

import java.util.ArrayList;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author shirsing
 */
@XmlRootElement
@XmlType(propOrder={"sketcher", "study", "domain", "point" , "shape", "edit", "speech","mediaInfo" })
public class Sketch {
 private UUID id;
 private String units;
 private ArrayList<Sketcher> sketcher;
 private Study study;
 private Domain domain;
 private ArrayList<Point> point;
 private ArrayList<Shape> shape;
 private ArrayList<Edit> edit;
 private ArrayList<Speech> speech;
 private ArrayList<MediaInfo> mediaInfo;
 
 
public Sketch(UUID sketchId, String units, ArrayList<Sketcher> sketcher, Study study,
		Domain domain, ArrayList<Point> point, ArrayList<Shape> shape, ArrayList<Edit> edit, ArrayList<Speech> speech,
		ArrayList<MediaInfo> mediainfo) {
	this.id = sketchId;
	this.units = units;
	this.sketcher = sketcher;
	this.study = study;
	this.domain = domain;
	this.point = point;
	this.shape = shape;
	this.edit = edit;
	this.speech = speech;
	this.mediaInfo = mediainfo;
}

public Sketch() {
	this.id = UUID.randomUUID();
	this.units = "himetric";
	this.study = new Study();
	this.domain = new Domain();
	this.sketcher = new ArrayList<Sketcher>();
	this.point = new ArrayList<Point>();
	this.shape = new ArrayList<Shape>();
	this.edit = new ArrayList<Edit>();
	this.speech = new ArrayList<Speech>();
	this.mediaInfo = new ArrayList<MediaInfo>();
}

public UUID getId() {
	return id;
}
@XmlAttribute
public void setId(UUID id) {
	this.id = id;
}

public String getUnits() {
	return units;
}
@XmlAttribute
public void setUnits(String units) {
	this.units = units;
}

public ArrayList<Sketcher> getSketcher() {
	return sketcher;
}
@XmlElement
public void setSketcher(ArrayList<Sketcher> sketcher) {
	this.sketcher = sketcher;
}

public Study getStudy() {
	return study;
}
@XmlElement
public void setStudy(Study study) {
	this.study = study;
}

public Domain getDomain() {
	return domain;
}
@XmlElement
public void setDomain(Domain domain) {
	this.domain = domain;
}

public ArrayList<Point> getPoint() {
	return point;
}
@XmlElement
public void setPoint(ArrayList<Point> point) {
	this.point = point;
}

public ArrayList<Shape> getShape() {
	return shape;
}
@XmlElement
public void setShape(ArrayList<Shape> shape) {
	this.shape = shape;
}

public ArrayList<Edit> getEdit() {
	return edit;
}
@XmlElement
public void setEdit(ArrayList<Edit> edit) {
	this.edit = edit;
}

public ArrayList<Speech> getSpeech() {
	return speech;
}
@XmlElement
public void setSpeech(ArrayList<Speech> speech) {
	this.speech = speech;
}

public ArrayList<MediaInfo> getMediaInfo() {
	return mediaInfo;
}
@XmlElement
public void setMediaInfo(ArrayList<MediaInfo> mediaInfo) {
	this.mediaInfo = mediaInfo;
}


 
}
