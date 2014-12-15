package com.doller;

public class OneDollerResult {

	String Name;
	float Score;
	float Ratio;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public float getScore() {
		return Score;
	}

	public void setScore(float score) {
		Score = score;
	}

	public float getRatio() {
		return Ratio;
	}

	public void setRatio(float ratio) {
		Ratio = ratio;
	}

	@Override
	public String toString() {
		return "Result [Name=" + Name + ", Score=" + Score + ", Ratio=" + Ratio + "]";
	}

	OneDollerResult(String name, float score, float ratio) {
		Name = name;
		Score = score;
		Ratio = ratio;
	}

}
