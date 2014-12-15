package com.parser.mechanix;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.doller.OneDollerPoint;
import com.doller.OneDollerRecognizer;
import com.doller.OneDollerResult;
import com.sketchshape.PremitiveStrokeType;

public class MechanixShape {
	private UUID id;
	private String time;
	private String simpl_id;
	private String simpl_ref;

	private MechanixInterpretations Interpretations;
	private MechanixAttributes Attributes;
	private ArrayList<MechanixShape> Shapes;
	private MechanixStroke stroke;

	private double averageSpeed;
	private double averagePressure;
	private double averageTiltX;
	private double averageTiltY;
	private double clusterId;

	public double getAverageSpeed() {
		return averageSpeed;
	}

	public void setAverageSpeed(double averageSpeed) {
		this.averageSpeed = averageSpeed;
	}

	public double getAveragePressure() {
		return averagePressure;
	}

	public void setAveragePressure(double averagePressure) {
		this.averagePressure = averagePressure;
	}

	public double getAverageTiltX() {
		return averageTiltX;
	}

	public void setAverageTiltX(double averageTiltX) {
		this.averageTiltX = averageTiltX;
	}

	public double getAverageTiltY() {
		return averageTiltY;
	}

	public void setAverageTiltY(double averageTiltY) {
		this.averageTiltY = averageTiltY;
	}

	public double getClusterId() {
		return clusterId;
	}

	public void setClusterId(double clusterId) {
		this.clusterId = clusterId;
	}

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

	public void updatePrimitiveTypes() {
		if (this.stroke != null) {
			stroke.updatePrimitiveTypes();
		}
		if (Shapes==null)
		{
			return;
		}
		for (MechanixShape mexShape : Shapes) {
			mexShape.updatePrimitiveTypes();
		}
	}
}