package com.parser.sousa;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class SousaStroke {
	private UUID id;
	private List<SousaArg> argList;
	private ArrayList<SousaPoint> pointList;
	private Iterator<SousaPoint> pointListIterator;

	public SousaStroke(UUID id, ArrayList<SousaPoint> pointList) {
		super();
		this.id = id;
		this.pointList = pointList;
		this.pointListIterator = pointList.iterator();
		this.argList = new ArrayList<SousaArg>();
	}

	public SousaStroke(UUID id) {
		super();
		this.id = id;
		this.pointList = new ArrayList<SousaPoint>();
		this.pointListIterator = pointList.iterator();
		this.argList = new ArrayList<SousaArg>();
	}

	public Boolean isEmpty() {
		return (pointList.size() == 0);
	}

	public Boolean hasNextStroke() {
		return pointListIterator.hasNext();
	}

	public SousaPoint nextSousaStroke() {
		return pointListIterator.next();
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public List<SousaArg> getArgList() {
		return argList;
	}

	public void setArgList(List<SousaArg> argList) {
		this.argList = argList;
	}

	public ArrayList<SousaPoint> getPointList() {
		return pointList;
	}

	public void setPointList(ArrayList<SousaPoint> pointList) {
		this.pointList = pointList;
	}

	public UUID getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Stroke [id=" + id + ", pointList=" + pointList.toString() + "]";
	}

}
