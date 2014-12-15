package com.doller;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Template {
	String Name;
	OneDollerPoint[] Points;
	private final int NumPoints = 64;
	private final float SquareSize = (float) 250.0;

	Template(String name, OneDollerPoint[] points) {
		Name = name;
		Points = OneDollerUtil.Resample(points, NumPoints);
		Points = OneDollerUtil.RotateToZero(Points);
		Points = OneDollerUtil.ScaleToSquare(Points, SquareSize);
		Points = OneDollerUtil.TranslateToOrigin(Points);
	}


}
