package com.parser.mechanix;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

public class MechanixStroke {
	
	private UUID id;
	private String time;
	private String draw_color;
	private MechanixElementStroke elem_stroke;
	private ArrayList<MechanixPoint> points;
	private MechanixStroke parent;
	
	public UUID getId() {
		return id;
	}

	public String getDraw_color() {
		return draw_color;
	}

	public MechanixElementStroke getElem_stroke() {
		return elem_stroke;
	}

	public ArrayList<MechanixPoint> getPoints() {
		return points;
	}

	public MechanixStroke getParent() {
		return parent;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public MechanixStroke(UUID id, String time, String draw_color,
			MechanixElementStroke elem_stroke, ArrayList<MechanixPoint> points,
			MechanixStroke parent) {
		super();
		this.id = id;
		this.time = time;
		this.draw_color = draw_color;
		this.elem_stroke = elem_stroke;
		this.points = points;
		this.parent = parent;
	}

	public MechanixStroke(UUID id, String time, String draw_color,
			MechanixElementStroke elem_stroke, ArrayList<MechanixPoint> points) {
		super();
		this.id = id;
		this.time = time;
		this.draw_color = draw_color;
		this.elem_stroke = elem_stroke;
		this.points = points;
	}

	public MechanixStroke() {
		// TODO Auto-generated constructor stub
	}

	public String getTime() {
		return time;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setDraw_color(String draw_color) {
		this.draw_color = draw_color;
	}

	public void setElem_stroke(MechanixElementStroke elem_stroke) {
		this.elem_stroke = elem_stroke;
	}

	public void setPoints(ArrayList<MechanixPoint> points) {
		this.points = points;
	}

	public void setParent(MechanixStroke parent) {
		this.parent = parent;
	}

	
	
	
}