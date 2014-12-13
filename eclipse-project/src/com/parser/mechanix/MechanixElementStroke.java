package com.parser.mechanix;

import java.util.UUID;

public class MechanixElementStroke {
	
	private double cap;
	private double width;
	private double miter_limit;
	
	public double getCap() {
		return cap;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getMiterLimit() {
		return miter_limit;
	}
	
	public MechanixElementStroke(double cap,double width,double miter_limit){
		this.cap = cap;
		this.width = width;
		this.miter_limit = miter_limit;
	}

	public MechanixElementStroke() {
		// TODO Auto-generated constructor stub
	}

	public void setCap(double cap) {
		this.cap = cap;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public void setMiter_limit(double miter_limit) {
		this.miter_limit = miter_limit;
	}
	
}