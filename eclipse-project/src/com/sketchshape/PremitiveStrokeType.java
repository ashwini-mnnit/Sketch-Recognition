package com.sketchshape;

public class PremitiveStrokeType {
	private String Name;
	private float srore;
	private float Ratio;

	public PremitiveStrokeType(String name, float srore, float ratio) {
		super();
		Name = name;
		this.srore = srore;
		Ratio = ratio;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public float getSrore() {
		return srore;
	}

	public void setSrore(float srore) {
		this.srore = srore;
	}

	public float getRatio() {
		return Ratio;
	}

	public void setRatio(float ratio) {
		Ratio = ratio;
	}

}
