package com.sketchshape;

import java.util.UUID;

import edu.tamu.srl.sketch.core.virtual.SrlPoint;

public class SrlPointExtended extends SrlPoint {

	private String mechanixSimRef;
	private String mechanixSimId;
	
	public String getMechanixSimRef() {
		return mechanixSimRef;
	}

	public void setMechanixSimRef(String mechanixSimRef) {
		this.mechanixSimRef = mechanixSimRef;
	}

	public String getMechanixSimId() {
		return mechanixSimId;
	}

	public void setMechanixSimId(String mechanixSimId) {
		this.mechanixSimId = mechanixSimId;
	}

	public SrlPointExtended(double x, double y, long time, UUID uuid, double tiltX, double tiltY, double pressure) {
		super(x, y, time, uuid, tiltX, tiltY, pressure);
		// TODO Auto-generated constructor stub
	}

	public SrlPointExtended(double x, double y, long time, UUID uuid) {
		super(x, y, time, uuid);
		// TODO Auto-generated constructor stub
	}

	public SrlPointExtended(double x, double y, long time) {
		super(x, y, time);
		// TODO Auto-generated constructor stub
	}

	public SrlPointExtended(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public SrlPointExtended(SrlPoint arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public SrlPointExtended(SrlPoint original) {
		super(original);
		// TODO Auto-generated constructor stub
	}

	
}
