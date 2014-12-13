package com.parser.mechanix;

public class MechanixInterpretation {
	
	private double confidence;
	private String label;
	
	public MechanixInterpretation(double confidence, String label) {
		super();
		this.confidence = confidence;
		this.label = label;
	}

	public MechanixInterpretation() {
		// TODO Auto-generated constructor stub
	}

	public double getConfidence() {
		return confidence;
	}

	public String getLabel() {
		return label;
	}

	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	
	
	
	
}