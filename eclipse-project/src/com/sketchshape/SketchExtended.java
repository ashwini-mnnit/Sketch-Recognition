package com.sketchshape;

import java.util.ArrayList;
import java.util.List;

import com.doller.OneDollerResult;
import com.parser.sousa.SousaPoint;
import com.parser.sousa.SousaSketch;
import com.parser.sousa.SousaStroke;

import edu.tamu.srl.sketch.core.virtual.SrlPoint;

public class SketchExtended {
	private List<SrlShapeExtended> srlshapes;

	public SketchExtended() {
		super();
		srlshapes = new ArrayList<SrlShapeExtended>();
	}

	void parseSouseSketch(List<SousaSketch> souseSketchs) {
		for (SousaSketch sousaSketch : souseSketchs) {
			SrlShapeExtended srlshape = new SrlShapeExtended(0, sousaSketch.getId(), null, sousaSketch.getType());
			for (SousaStroke sousaStroke : sousaSketch.getSousaStrokes()) {

				SrlStrokeExtended sriStroke = new SrlStrokeExtended(0, sousaStroke.getId(), false);
				for (SousaPoint point : sousaStroke.getPointList()) {
					SrlPoint srlPoint = new SrlPoint(point.getxCoordinate(), point.getyCoordinate(), Long.parseLong(point.getTime()), point.getId());
					sriStroke.addPoint(srlPoint);
				}
				OneDollerResult recResult = sousaStroke.getDollarRecognizerType();
				sriStroke.setPremitiveType(new PremitiveStrokeType(recResult.getName(), recResult.getScore(), recResult.getRatio()));
				srlshape.add(sriStroke);
			}
			srlshapes.add(srlshape);
		}
	}

	public List<SrlShapeExtended> getSrlshapes() {
		return srlshapes;
	}

	public void setSrlshapes(List<SrlShapeExtended> srlshapes) {
		this.srlshapes = srlshapes;
	}
}
