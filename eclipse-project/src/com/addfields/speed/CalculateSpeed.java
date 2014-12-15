package com.addfields.speed;

import java.lang.ArithmeticException;
import java.util.ArrayList;
import java.util.List;

import com.parser.mechanix.MechanixPoint;
import com.parser.mechanix.MechanixShape;
import com.parser.mechanix.MechanixSketch;
import com.parser.mechanix.MechanixStroke;
import com.sketchshape.SrlShapeExtended;

import edu.tamu.srl.sketch.core.abstracted.SrlObject;
import edu.tamu.srl.sketch.core.object.SrlStroke;
import edu.tamu.srl.sketch.core.virtual.SrlPoint;

public class CalculateSpeed {

	private double getDistanceBetweenTwoPoints(double x1, double y1, double x2, double y2) {
		return Math.sqrt((Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
	}	
	
	public void populateSpeed(SrlShapeExtended srlShapeExtended) 
	{
		try {
		List<SrlStroke> subShapes;
		subShapes = (srlShapeExtended).getRecursiveStrokeList();
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
				if(currentTime != 0)
					shapeSpeed+=(currentDistance/currentTime);
			}
		}
		shapeSpeed = shapeSpeed / totalPoints;
		srlShapeExtended.setAverageSpeed(shapeSpeed);
		}
		catch(ArithmeticException e) {
			System.out.printf("There are no points in given Shape", e.getMessage());
			throw new ArithmeticException();
		}
	}

	public void populateSpeed(MechanixSketch mechanixSketch) 
	{
		try {
		List<MechanixShape> subShapes = new ArrayList<MechanixShape>();
		subShapes = mechanixSketch.getAllShapes();
		
			for(MechanixShape singleShape : subShapes) {
				List<MechanixStroke> strokeList = new ArrayList<MechanixStroke>();
				if(singleShape.getAllStroke() != null)
					strokeList.addAll(singleShape.getAllStroke());
				
				double shapeSpeed = 0;
				double totalPoints = 0;
				for(MechanixStroke mechanixStroke : strokeList)
				{
					List<MechanixPoint> pointList = mechanixStroke.getPoints();
					totalPoints+=(pointList.size() - 1);
					for(int i = 0; i < pointList.size() -1; i++)
					{
						double x1 = pointList.get(i).getX();
						double y1 = pointList.get(i).getY();
						double x2 = pointList.get(i+1).getX();
						double y2 = pointList.get(i+1).getY();
						
						double currentDistance = getDistanceBetweenTwoPoints(x1, y1, x2, y2);
						double currentTime = 0;
						if (pointList.get(i+1).getTime() != null && pointList.get(i).getTime() != null)
							currentTime = Double.parseDouble(pointList.get(i+1).getTime()) - Double.parseDouble(pointList.get(i).getTime());
						if(currentTime != 0)
							shapeSpeed+=(currentDistance/currentTime);
					}
				}
				if(totalPoints == 0)
					shapeSpeed = 0;
				else
					shapeSpeed = shapeSpeed / totalPoints;
				shapeSpeed = (double)(int)(shapeSpeed*100);
				singleShape.setAverageSpeed(shapeSpeed);
			}
		}
		catch(ArithmeticException e) {
			System.out.printf("There are no points in given Shape", e.getMessage());
			throw new ArithmeticException();
		}
	}
	
	public void populateSpeed(MechanixShape mechanixShape) 
	{
		try {
		
				List<MechanixStroke> strokeList = new ArrayList<MechanixStroke>();
				if(mechanixShape.getAllStroke() != null)
					strokeList.addAll(mechanixShape.getAllStroke());
				
				double shapeSpeed = 0;
				double totalPoints = 0;
				for(MechanixStroke mechanixStroke : strokeList)
				{
					List<MechanixPoint> pointList = mechanixStroke.getPoints();
					totalPoints+=(pointList.size() - 1);
					for(int i = 0; i < pointList.size() -1; i++)
					{
						double x1 = pointList.get(i).getX();
						double y1 = pointList.get(i).getY();
						double x2 = pointList.get(i+1).getX();
						double y2 = pointList.get(i+1).getY();
						
						double currentDistance = getDistanceBetweenTwoPoints(x1, y1, x2, y2);
						double currentTime = 0;
						if (pointList.get(i+1).getTime() != null && pointList.get(i).getTime() != null)
							currentTime = Double.parseDouble(pointList.get(i+1).getTime()) - Double.parseDouble(pointList.get(i).getTime());
						if(currentTime != 0)
							shapeSpeed+=(currentDistance/currentTime);
					}
				}
				if(totalPoints == 0)
					shapeSpeed = 0;
				else
					shapeSpeed = shapeSpeed / totalPoints;
				shapeSpeed = (double)(int)(shapeSpeed*100);
				mechanixShape.setAverageSpeed(shapeSpeed);
		}
		catch(ArithmeticException e) {
			System.out.printf("There are no points in given Shape", e.getMessage());
			throw new ArithmeticException();
		}
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
		srlShapeExtended.addAll(srlStrokes);
		calculateSpeed.populateSpeed(srlShapeExtended);
		System.out.println(srlShapeExtended.getAverageSpeed());
		
		MechanixSketch mechanixSketch = new MechanixSketch();
		MechanixShape mechanixShape = new MechanixShape();
		ArrayList<MechanixPoint> pointList3 = new ArrayList<MechanixPoint>();
		MechanixPoint mechanixPoint1 = new MechanixPoint(0, 0, "0");
		MechanixPoint mechanixPoint2 = new MechanixPoint(0, 1, "1");
		MechanixPoint mechanixPoint3 = new MechanixPoint(0, 3, "2");
		MechanixPoint mechanixPoint4 = new MechanixPoint(0, 6, "3");
		MechanixPoint mechanixPoint5 = new MechanixPoint(0, 10, "4");
		pointList3.add(mechanixPoint1);
		pointList3.add(mechanixPoint2);
		pointList3.add(mechanixPoint3);
		pointList3.add(mechanixPoint4);
		pointList3.add(mechanixPoint5);
		MechanixStroke mechanixStroke = new MechanixStroke();
		mechanixStroke.setPoints(pointList3);
		mechanixShape.setStroke(mechanixStroke);
		ArrayList<MechanixShape> mechanixShapes = new ArrayList<MechanixShape>();
		mechanixShapes.add(mechanixShape);
		mechanixSketch.setShapes(mechanixShapes);
		calculateSpeed.populateSpeed(mechanixSketch);
		List<MechanixShape> subShapes = new ArrayList<MechanixShape>();
		subShapes = mechanixSketch.getShapes();
		for (MechanixShape mechanixSingleShape : subShapes) {
			if(mechanixSingleShape.getAverageSpeed() != 0)
				System.out.println(mechanixSingleShape.getAverageSpeed());
		}
		
	}
}
