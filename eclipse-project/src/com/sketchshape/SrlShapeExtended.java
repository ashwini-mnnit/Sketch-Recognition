package com.sketchshape;

import java.util.UUID;

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
		// TODO Auto-generated constructor stub
	}

	public SrlShapeExtended(long time, UUID uuid, boolean isUserCreated, SrlShapeConfig config, String description) {
		super(time, uuid, isUserCreated, config, description);
		// TODO Auto-generated constructor stub
	}

	public SrlShapeExtended(long time, UUID uuid, SrlShapeConfig config, String description) {
		super(time, uuid, config, description);
		// TODO Auto-generated constructor stub
	}

	public SrlShapeExtended(SrlShape arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public SrlShapeExtended(SrlShape original) {
		super(original);
		// TODO Auto-generated constructor stub
	}
}
