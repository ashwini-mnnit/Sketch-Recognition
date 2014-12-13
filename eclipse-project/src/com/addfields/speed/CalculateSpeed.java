package com.addfields.speed;

import java.util.ArrayList;
import java.util.List;

import com.srlshapeextended.SrlShapeExtended;

import edu.tamu.srl.sketch.core.abstracted.SrlObject;
import edu.tamu.srl.sketch.core.object.SrlStroke;
import edu.tamu.srl.sketch.core.virtual.SrlPoint;

public class CalculateSpeed {

	double getDistanceBetweenTwoPoints(double x1, double y1, double x2, double y2) {
		return Math.sqrt((Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
	}	
	
	void populateSpeed(SrlShapeExtended srlShapeExtended)
	{
		List<SrlStroke> subShapes;
		subShapes = (srlShapeExtended).getSrlShape().getRecursiveStrokeList();
		double shapeSpeed = 0;
		double totalPoints = 0;
		for(SrlStroke stroke : subShapes)
		{
			List<SrlPoint> pointList = stroke.getPoints();
			totalPoints+=(pointList.size() - 1);
			for(int i = 0; i < pointList.size() -1; i++)
			{
				double x1 = pointList.get(i).getX();
				double y1 = pointList.get(i).getY();
				double x2 = pointList.get(i+1).getX();
				double y2 = pointList.get(i+1).getY();
				
				double currentDistance = getDistanceBetweenTwoPoints(x1, y1, x2, y2);
				double currentTime = pointList.get(i+1).getTime() - pointList.get(i).getTime();
				shapeSpeed+=(currentDistance/currentTime);
			}
		}
		shapeSpeed = shapeSpeed / totalPoints;
		srlShapeExtended.setAverageSpeed(shapeSpeed);
	}

	public static void main(String[] args) {
		
		CalculateSpeed calculateSpeed = new CalculateSpeed();
		SrlShapeExtended srlShapeExtended = new SrlShapeExtended();
		List<SrlPoint> pointList = new ArrayList<SrlPoint>();
		SrlPoint srlPoint1 = new SrlPoint(0, 0, 0);
		SrlPoint srlPoint2 = new SrlPoint(0, 1, 1);
		SrlPoint srlPoint3 = new SrlPoint(0, 3, 2);
		SrlPoint srlPoint4 = new SrlPoint(0, 6, 3);
		SrlPoint srlPoint5 = new SrlPoint(0, 10, 4);
		pointList.add(srlPoint1);
		pointList.add(srlPoint2);
		pointList.add(srlPoint3);
		pointList.add(srlPoint4);
		pointList.add(srlPoint5);
		SrlStroke srlStroke = new SrlStroke();
		srlStroke.addPoints(pointList);
		List<SrlObject> srlStrokes = new ArrayList<SrlObject>();
		srlStrokes.add(srlStroke);
		srlShapeExtended.getSrlShape().addAll(srlStrokes);
		calculateSpeed.populateSpeed(srlShapeExtended);
		System.out.println(srlShapeExtended.getAverageSpeed());
	}
}
