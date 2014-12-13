package com.parser.mechanix;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class MechanixShape {
	private UUID id;
	private String time;
	private String simpl_id;
	private String simpl_ref;
	
	private MechanixInterpretations Interpretations;
	private MechanixAttributes Attributes;
	private ArrayList<MechanixShape> Shapes;
	private MechanixStroke stroke;
	
	
	
	public MechanixShape() {
		super();
	}



	public UUID getId() {
		return id;
	}



	public void setId(UUID id) {
		this.id = id;
	}



	public String getTime() {
		return time;
	}



	public void setTime(String time) {
		this.time = time;
	}



	public String getSimpl_id() {
		return simpl_id;
	}



	public void setSimpl_id(String simpl_id) {
		this.simpl_id = simpl_id;
	}



	public String getSimpl_ref() {
		return simpl_ref;
	}



	public void setSimpl_ref(String simpl_ref) {
		this.simpl_ref = simpl_ref;
	}



	public MechanixInterpretations getInterpretations() {
		return Interpretations;
	}



	public void setInterpretations(MechanixInterpretations interpretations) {
		Interpretations = interpretations;
	}



	public MechanixAttributes getAttributes() {
		return Attributes;
	}



	public void setAttributes(MechanixAttributes attributes) {
		Attributes = attributes;
	}



	public ArrayList<MechanixShape> getShapes() {
		return Shapes;
	}



	public void setShapes(ArrayList<MechanixShape> shapes) {
		Shapes = shapes;
	}



	public MechanixStroke getStroke() {
		return stroke;
	}



	public void setStroke(MechanixStroke stroke) {
		this.stroke = stroke;
	}
	
	
	
	
	
	
	
	
	
}