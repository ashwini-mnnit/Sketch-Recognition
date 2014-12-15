package com.doller;

public class OneDollerPoint {
	float X;
	float Y;

	public OneDollerPoint(float x, float y) {
		X = x;
		Y = y;
	}

	public float distance(OneDollerPoint other) {
		return (float) Math.sqrt(((X-other.X)*(X-other.X)+(Y-other.Y)*(Y-other.Y)));
	}
}
