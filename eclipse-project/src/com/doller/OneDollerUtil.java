package com.doller;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class OneDollerUtil {

	public static OneDollerPoint[] RotateToZero(OneDollerPoint[] points) {
		OneDollerPoint c = Centroid(points);
		float theta = (float) Math.atan2(c.Y - points[0].Y, c.X - points[0].X);
		return RotateBy(points, -theta);
	}

	public static OneDollerPoint Centroid(OneDollerPoint[] points) {
		OneDollerPoint centriod = new OneDollerPoint((float) 0.0, (float) 0.0);
		for (int i = 1; i < points.length; i++) {
			centriod.X += points[i].X;
			centriod.Y += points[i].Y;
		}
		centriod.X /= points.length;
		centriod.Y /= points.length;
		return centriod;
	}

	public static OneDollerPoint[] RotateBy(OneDollerPoint[] points, float theta) {
		OneDollerPoint c = Centroid(points);
		float Cos = (float) Math.cos(theta);
		float Sin = (float) Math.sin(theta);

		OneDollerPoint[] newpoints = new OneDollerPoint[points.length];
		for (int i = 0; i < points.length; i++) {
			float qx = (points[i].X - c.X) * Cos - (points[i].Y - c.Y) * Sin + c.X;
			float qy = (points[i].X - c.X) * Sin + (points[i].Y - c.Y) * Cos + c.Y;
			newpoints[i] = new OneDollerPoint(qx, qy);
		}
		return newpoints;
	}

	public static float PathDistance(OneDollerPoint[] pts1, OneDollerPoint[] pts2) {
		if (pts1.length != pts2.length) {
			//System.out.println("Lengths differ. " + pts1.length + " != " + pts2.length);
			return Float.MAX_VALUE;
		}
		float d = (float) 0.0;
		for (int i = 0; i < pts1.length; i++) {
			d += pts1[i].distance(pts2[i]);
		}
		return d / (float) pts1.length;
	}

	public static float PathLength(OneDollerPoint[] points) {
		float d = (float) 0.0;
		for (int i = 1; i < points.length; i++) {
			d += points[i - 1].distance(points[i]);
		}
		return d;
	}

	public static OneDollerRectangle BoundingBox(OneDollerPoint[] points) {
		float minX = Float.MAX_VALUE;
		float maxX = Float.MIN_VALUE;
		float minY = Float.MAX_VALUE;
		float maxY = Float.MIN_VALUE;

		for (int i = 1; i < points.length; i++) {
			minX = Math.min(points[i].X, minX);
			maxX = Math.max(points[i].X, maxX);
			minY = Math.min(points[i].Y, minY);
			maxY = Math.max(points[i].Y, maxY);
		}
		return new OneDollerRectangle(minX, minY, maxX - minX, maxY - minY);
	}

	public static OneDollerPoint[] ScaleToSquare(OneDollerPoint[] points, float sz) {
		OneDollerRectangle B = BoundingBox(points);
		OneDollerPoint[] newpoints = new OneDollerPoint[points.length];
		for (int i = 0; i < points.length; i++) {
			float qx = points[i].X * (sz / B.Width);
			float qy = points[i].Y * (sz / B.Height);
			newpoints[i] = new OneDollerPoint(qx, qy);
		}
		return newpoints;
	}

	public static float DistanceAtBestAngle(OneDollerPoint[] points, Template T, float a, float b, float threshold) {
		float Phi = (float) (0.5 * (-1.0 + Math.sqrt(5.0))); // Golden Ratio
		float x1 = (float) (Phi * a + (1.0 - Phi) * b);
		float f1 = DistanceAtAngle(points, T, x1);
		float x2 = (float) ((1.0 - Phi) * a + Phi * b);
		float f2 = DistanceAtAngle(points, T, x2);
		while (Math.abs(b - a) > threshold) {
			if (f1 < f2) {
				b = x2;
				x2 = x1;
				f2 = f1;
				x1 = (float) (Phi * a + (1.0 - Phi) * b);
				f1 = DistanceAtAngle(points, T, x1);
			} else {
				a = x1;
				x1 = x2;
				f1 = f2;
				x2 = (float) ((1.0 - Phi) * a + Phi * b);
				f2 = DistanceAtAngle(points, T, x2);
			}
		}
		return Math.min(f1, f2);
	}

	public static float DistanceAtAngle(OneDollerPoint[] points, Template T, float theta) {
		OneDollerPoint[] newpoints = OneDollerUtil.RotateBy(points, theta);
		return OneDollerUtil.PathDistance(newpoints, T.Points);
	}

	public static OneDollerPoint[] TranslateToOrigin(OneDollerPoint[] points) {
		OneDollerPoint c = OneDollerUtil.Centroid(points);
		OneDollerPoint[] newpoints = new OneDollerPoint[points.length];
		for (int i = -0; i < points.length; i++) {
			float qx = points[i].X - c.X;
			float qy = points[i].Y - c.Y;
			newpoints[i] = new OneDollerPoint(qx, qy);
		}
		return newpoints;
	}

	public static OneDollerPoint[] Resample(OneDollerPoint[] points, int numPoints2) {
		float I = (float) (PathLength(points) / ((float) numPoints2 - 1.0));
		float D = (float) 0.0;
		List<OneDollerPoint> newpoints = new ArrayList<OneDollerPoint>();
		Stack<OneDollerPoint> stack = new Stack<OneDollerPoint>();
		for (int i = 0; i < points.length; i++) {
			stack.push(points[points.length - 1 - i]);
		}

		while (!stack.empty()) {
			OneDollerPoint pt1 = (OneDollerPoint) stack.pop();

			if (stack.empty()) {
				newpoints.add(pt1);
				continue;
			}
			OneDollerPoint pt2 = (OneDollerPoint) stack.peek();
			float d = pt1.distance(pt2);
			if ((D + d) >= I) {
				float qx = pt1.X + ((I - D) / d) * (pt2.X - pt1.X);
				float qy = pt1.Y + ((I - D) / d) * (pt2.Y - pt1.Y);
				OneDollerPoint q = new OneDollerPoint(qx, qy);
				newpoints.add(q);
				stack.push(q);
				D = (float) 0.0;
			} else {
				D += d;
			}
		}

		if (newpoints.size() == (numPoints2 - 1)) {
			newpoints.add(points[points.length - 1]);
		}
		OneDollerPoint[] rvPoint = new OneDollerPoint[newpoints.size()];
		for (int j = 0; j < newpoints.size(); j++) {
			rvPoint[j] = newpoints.get(j);
		}

		return rvPoint;

	}
}
