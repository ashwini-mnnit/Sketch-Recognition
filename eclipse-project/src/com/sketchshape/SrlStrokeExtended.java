package com.sketchshape;

import java.util.List;
import java.util.UUID;

import edu.tamu.srl.sketch.core.object.SrlStroke;
import edu.tamu.srl.sketch.core.tobenamedlater.SrlAuthor;
import edu.tamu.srl.sketch.core.tobenamedlater.SrlDevice;
import edu.tamu.srl.sketch.core.tobenamedlater.SrlPen;
import edu.tamu.srl.sketch.core.virtual.SrlPoint;

public class SrlStrokeExtended extends SrlStroke {
	private PremitiveStrokeType  premitiveType;

	public PremitiveStrokeType getPremitiveType() {
		return premitiveType;
	}

	public void setPremitiveType(PremitiveStrokeType premitiveType) {
		this.premitiveType = premitiveType;
	}

	public SrlStrokeExtended() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SrlStrokeExtended(boolean isUserCreated, SrlAuthor author, SrlPen pen, SrlDevice device) {
		super(isUserCreated, author, pen, device);
		// TODO Auto-generated constructor stub
	}

	public SrlStrokeExtended(List<SrlPoint> arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public SrlStrokeExtended(long time, UUID uuid, boolean isUserCreated, SrlAuthor author, SrlPen pen, SrlDevice device) {
		super(time, uuid, isUserCreated, author, pen, device);
		// TODO Auto-generated constructor stub
	}

	public SrlStrokeExtended(long time, UUID uuid, boolean isUserCreated) {
		super(time, uuid, isUserCreated);
		// TODO Auto-generated constructor stub
	}

	public SrlStrokeExtended(SrlStroke arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public SrlStrokeExtended(SrlStroke original) {
		super(original);
		// TODO Auto-generated constructor stub
	}
}
