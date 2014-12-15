package com.parser.sousa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class SousaStroke {
	private UUID id;
	private List<SousaArg> argList;
	private ArrayList<SousaPoint> pointList;

	public SousaStroke(UUID id, ArrayList<SousaPoint> pointList) {
		super();
		this.id = id;
		this.pointList = pointList;
		this.argList = new ArrayList<SousaArg>();
	}

	public SousaStroke(UUID id) {
		super();
		this.id = id;
		this.pointList = new ArrayList<SousaPoint>();
		this.argList = new ArrayList<SousaArg>();
	}

	public Boolean isEmpty() {
		return (pointList.size() == 0);
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public List<SousaArg> getArgList() {
		return argList;
	}

	public void setArgList(List<SousaArg> argList) {
		this.argList = argList;
	}

	public ArrayList<SousaPoint> getPointList() {
		return pointList;
	}

	public void setPointList(ArrayList<SousaPoint> pointList) {
		this.pointList = pointList;
	}

	public UUID getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Stroke [id=" + id + ", pointList=" + pointList.toString() + "]";
	}

	public boolean isLine() {
		int pointCount = this.pointList.size();
		double oldslope = 0;
		boolean start = true;
		boolean isLine = true;
		for (int i = 1; i < pointCount; i++) {
			SousaPoint pointA = this.pointList.get(i - 1);
			SousaPoint pointB = this.pointList.get(i);
			double slope = findSlope(pointA, pointB);
			if (start) {
				start = false;
				oldslope = slope;
			} else {
				double diff = oldslope - slope;
				if (diff > 0.3) {
					isLine = false;
				}

			}
		}
		return isLine;
	}

	private Double findSlope(SousaPoint pointA, SousaPoint pointB) {
		if (pointA.getxCoordinate() - pointB.getxCoordinate() != 0) {
			double tanTheta = (pointA.getyCoordinate() - pointB.getyCoordinate()) / (pointA.getxCoordinate() - pointB.getxCoordinate());
			return tanTheta;

		}
		return Double.MAX_VALUE;// Tan 90

	}
}
