package com.sketchshape;

import java.util.UUID;

import edu.tamu.srl.sketch.core.object.SrlShape;
import edu.tamu.srl.sketch.core.tobenamedlater.SrlShapeConfig;

public class SrlShapeExtended extends SrlShape {
	private String primitiveType;

	public SrlShapeExtended() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SrlShapeExtended(long time, UUID uuid, boolean isUserCreated, SrlShapeConfig config, String description) {
		super(time, uuid, isUserCreated, config, description);
		// TODO Auto-generated constructor stub
	}

	public SrlShapeExtended(long time, UUID uuid, SrlShapeConfig config, String description) {
		super(time, uuid, config, description);
		// TODO Auto-generated constructor stub
	}

	public SrlShapeExtended(SrlShape arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public SrlShapeExtended(SrlShape original) {
		super(original);
		// TODO Auto-generated constructor stub
	}

	public String getPrimitiveType() {
		return primitiveType;
	}

	public void setPrimitiveType(String primitiveType) {
		this.primitiveType = primitiveType;
	}
}
