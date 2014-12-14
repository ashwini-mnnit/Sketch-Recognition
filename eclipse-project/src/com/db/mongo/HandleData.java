package com.db.mongo;

import java.util.List;

import com.classify.ClassifyData;
import com.sketchshape.SrlShapeExtended;

public class HandleData {

	ClassifyData classifyData;
	ProcessData dataObject;
	
	HandleData() {
		classifyData = new ClassifyData(5);
		dataObject = new ProcessData("localhost", 27017, "SketchRec");
	}

	HandleData(List<SrlShapeExtended> srlShapeExtendedList) {
		dataObject = new ProcessData("localhost", 27017, "SketchRec");
		classifyData = new ClassifyData(5);
		classifyData.learnClassifier(srlShapeExtendedList);
	}
	
	void printSimilarShapeObjects(SrlShapeExtended srlShapeExtended) {
		Double clusterId = classifyData.getClusterId(srlShapeExtended);
		dataObject.displaySouseData("Souse", Double.toString(clusterId));		
	}

	public static void main(String[] args) {
		ProcessData dataObject = new ProcessData("localhost", 27017, "SketchRec");
		dataObject.removeSouseData("Souse", "0b8c1460-6919-45bf-b6e3-b3776b69a40b");
		dataObject.processSouseData("/home/shirsing/Downloads/17902.xml", "Souse");
		dataObject.displaySouseData("Souse", "0b8c1460-6919-45bf-b6e3-b3776b69a40b");
	}
}
