package com.parser.mechanix;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MechanixSketch {

	private UUID id;
	private String time;
	private String draw_color;
	private MechanixAttributes attributes;
	private ArrayList<MechanixShape> shapes;

	public MechanixSketch() {
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

	public String getDraw_color() {
		return draw_color;
	}

	public void setDraw_color(String draw_color) {
		this.draw_color = draw_color;
	}

	public MechanixAttributes getAttributes() {
		return attributes;
	}

	public void setAttributes(MechanixAttributes attributes) {
		this.attributes = attributes;
	}

	public ArrayList<MechanixShape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<MechanixShape> shapes) {
		this.shapes = shapes;
	}
	
	public void updatePrimitiveTypes() {
		for (MechanixShape shape : this.shapes) {
			shape.updatePrimitiveTypes();
		}
	}
	
	public List<MechanixShape> getAllShapes()
	{
		List<MechanixShape> rvStrokes = new ArrayList<MechanixShape>();
		
		if (shapes==null)
		{
			return rvStrokes;
		}
		for (MechanixShape mexShape : shapes) {
			rvStrokes.add(mexShape);
			rvStrokes.addAll(mexShape.getAllShapes());
		}
		return rvStrokes;
	}

}
