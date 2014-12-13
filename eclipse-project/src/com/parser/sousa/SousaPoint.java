package com.parser.sousa;
import java.util.UUID;

public class SousaPoint {
	private UUID id;
	private Double xCoordinate;
	private Double yCoordinate;
	private String time;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public SousaPoint(UUID id) {
		super();
		this.id = id;
		this.xCoordinate = 0.0;
		this.yCoordinate = 0.0;
	}

	public SousaPoint(UUID id, Double xCoordinate, Double yCoordinate) {
		super();
		this.id = id;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Double getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(Double xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public Double getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(Double yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	@Override
	public String toString() {
		return "Point [id=" + id + ", xCoordinate=" + xCoordinate + ", yCoordinate=" + yCoordinate + "]";
	}
}
