package com.parser.mechanix;

import java.util.UUID;

public class MechanixPoint {
	
	private UUID id;
	private String time;
	private double x;
	private double y;
	private String simpl_id;
	private String simpl_ref;
	
	
	public MechanixPoint() {
		// TODO Auto-generated constructor stub
	}

	public MechanixPoint(double x, double y, String time) {
		this.x = x;
		this.y = y;
		this.time = time;
		// TODO Auto-generated constructor stub
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


	public double getX() {
		return x;
	}


	public void setX(double x) {
		this.x = x;
	}


	public double getY() {
		return y;
	}


	public void setY(double y) {
		this.y = y;
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
	
	
	
}