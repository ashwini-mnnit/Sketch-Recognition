package com.srlshapeextended;

import java.util.UUID;

import edu.tamu.srl.sketch.core.object.SrlShape;
import edu.tamu.srl.sketch.core.tobenamedlater.SrlShapeConfig;

public class SrlShapeExtended {
	
	SrlShape srlShape;
	double averageSpeed;
	double averagePressure;
	double averageTiltX;
	double averageTiltY;
	double clusterId;
	
	public SrlShapeExtended() {
		srlShape = new SrlShape();
	}

	public SrlShapeExtended(long time, UUID uuid,  SrlShapeConfig config, String description) {
		srlShape = new SrlShape(time, uuid,  config, description);
	}

	public double getClusterId() {
		return clusterId;
	}
	public void setClusterId(double clusterId) {
		this.clusterId = clusterId;
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
	public boolean equals(Object arg0) {
		return srlShape.equals(arg0);
	}
	public int hashCode() {
		return srlShape.hashCode();
	}
	public String toString() {
		return srlShape.toString();
	}
	public SrlShape getSrlShape() {
		return srlShape;
	}
	public void setSrlShape(SrlShape srlShape) {
		this.srlShape = srlShape;
	}	
}
