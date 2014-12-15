package com.parser.mechanix;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.doller.OneDollerPoint;
import com.doller.OneDollerRecognizer;
import com.doller.OneDollerResult;
import com.parser.sousa.SousaPoint;
import com.sketchshape.PremitiveStrokeType;

public class MechanixStroke {
	
	private UUID id;
	private String time;
	private String draw_color;
	private MechanixElementStroke elem_stroke;
	private ArrayList<MechanixPoint> points;
	private MechanixStroke parent;
	
	public PremitiveStrokeType getPrimitiveType() {
		return primitiveType;
	}

	public void setPrimitiveType(PremitiveStrokeType primitiveType) {
		this.primitiveType = primitiveType;
	}

	private PremitiveStrokeType primitiveType;
	
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

	public void updatePrimitiveTypes() {
		if (this.points==null)
			return;
		OneDollerRecognizer r = new OneDollerRecognizer();
		List<OneDollerPoint> dollerPoint = new ArrayList<OneDollerPoint>();
		for (MechanixPoint mexPoint : this.points) {
			OneDollerPoint p = new OneDollerPoint((float)mexPoint.getX(),(float) mexPoint.getY());
			dollerPoint.add(p);
		}
		OneDollerPoint[] rvPoint = new OneDollerPoint[dollerPoint.size()];
		for (int j = 0; j < dollerPoint.size(); j++) {
			rvPoint[j] = dollerPoint.get(j);
		}
		OneDollerResult result = r.Recognize(rvPoint);
		//System.out.println(result.toString());
		this.primitiveType = new PremitiveStrokeType(result.getName(), result.getScore(), result.getRatio());
	}
	
	
}