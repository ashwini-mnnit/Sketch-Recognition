package com.sketchshape;

import java.util.ArrayList;
import java.util.List;

import com.parser.sousa.SousaPoint;
import com.parser.sousa.SousaSketch;
import com.parser.sousa.SousaStroke;

import edu.tamu.srl.sketch.core.object.SrlShape;
import edu.tamu.srl.sketch.core.object.SrlStroke;
import edu.tamu.srl.sketch.core.virtual.SrlPoint;

//new
public class SketchExtended {
	private List<SrlShapeExtended> srlshapes;	
    private String id;
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SketchExtended() {		
		super();
		srlshapes= new ArrayList<SrlShapeExtended>();
		
	}
	
	public List<SrlShapeExtended> getSrlshapes() {
		return srlshapes;
	}

	public void setSrlshapes(List<SrlShapeExtended> srlshapes) {
		this.srlshapes = srlshapes;
	}


	public void parseSouseSketch(List<SousaSketch> souseSketchs)
	{
		for (SousaSketch sousaSketch : souseSketchs) {
			SrlShapeExtended srlshape= new SrlShapeExtended(0,sousaSketch.getId(),null,sousaSketch.getType());
			for (SousaStroke sousaStroke : sousaSketch.getSousaStrokes()) {
				
				srlshape.setPrimitiveType(sousaSketch.getPremitivetype());
				
				SrlStroke sriStroke = new SrlStroke(0, sousaStroke.getId(), false);
				for (SousaPoint point: sousaStroke.getPointList()) {
					SrlPoint srlPoint = new SrlPoint(point.getxCoordinate(),point.getyCoordinate(),Long.parseLong(point.getTime()),point.getId());
					sriStroke.addPoint(srlPoint);
				}
				srlshape.add(sriStroke);				
			}
			srlshapes.add(srlshape);
		}
	}
	
	
}
