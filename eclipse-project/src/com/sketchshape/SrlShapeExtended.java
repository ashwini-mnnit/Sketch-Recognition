package com.sketchshape;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.parser.mechanix.MechanixAttribute;
import com.parser.mechanix.MechanixAttributes;

import edu.tamu.srl.sketch.core.object.SrlShape;
import edu.tamu.srl.sketch.core.tobenamedlater.SrlShapeConfig;

public class SrlShapeExtended extends SrlShape {
	private double averageSpeed;
	private double averagePressure;
	private double averageTiltX;
	private double averageTiltY;
	private double clusterId;
	private String authorName;
	private String shapeType;
	private String color;
	private String mechanixSimRef;
	private String mechanixSimId;
	private Map<String, Double> mInterpretations; 

	public String getMechanixSimId() {
		return mechanixSimId;
	}

	public void setMechanixSimId(String mechanixSimId) {
		this.mechanixSimId = mechanixSimId;
	}

	public String getMechanixSimRef() {
		return mechanixSimRef;
	}

	public void setMechanixSimRef(String mechanixSimRef) {
		this.mechanixSimRef = mechanixSimRef;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getShapeType() {
		return shapeType;
	}

	public void setShapeType(String shapeType) {
		this.shapeType = shapeType;
	}

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

	public SrlShapeExtended() {
		super();
		this.mInterpretations = new HashMap();
		// TODO Auto-generated constructor stub
	}

	public SrlShapeExtended(long time, UUID uuid, boolean isUserCreated, SrlShapeConfig config, String description) {
		super(time, uuid, isUserCreated, config, description);
		this.mInterpretations = new HashMap();
		// TODO Auto-generated constructor stub
	}

	public SrlShapeExtended(long time, UUID uuid, SrlShapeConfig config, String description) {
		super(time, uuid, config, description);
		this.mInterpretations = new HashMap();
		// TODO Auto-generated constructor stub
	}

	public SrlShapeExtended(SrlShape arg0, boolean arg1) {
		super(arg0, arg1);
		this.mInterpretations = new HashMap();
		// TODO Auto-generated constructor stub
	}

	public SrlShapeExtended(SrlShape original) {
		super(original);
		this.mInterpretations = new HashMap();
		// TODO Auto-generated constructor stub
	}
	
	public void setInterpretation(String key, Double value)
	  {
	    this.mInterpretations.put(key, value);
	  }
}
