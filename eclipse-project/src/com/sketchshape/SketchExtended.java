package com.sketchshape;

import java.util.ArrayList;
import java.util.List;

import com.parser.sousa.SousaSketch;
import com.parser.sousa.SousaStroke;

import edu.tamu.srl.sketch.core.object.SrlShape;
import edu.tamu.srl.sketch.core.object.SrlStroke;

public class SketchExtended {
	private List<SrlShape> srlshapes;

	public SketchExtended() {		
		super();
		srlshapes= new ArrayList<SrlShape>();
	}
	void parseSouseSketch(List<SousaSketch> souseSketchs)
	{
		for (SousaSketch sousaSketch : souseSketchs) {
			SrlShape shape= new SrlShape(0,sousaSketch.getId(),null,sousaSketch.getType());
			for (SousaStroke stroke : sousaSketch.getSousaStrokes()) {
				SrlStroke sriStroke = new SrlStroke(0, stroke.getId(), false);
				shape.add(sriStroke);
				
			}
			srlshapes.add(shape);
		}
	}
}
