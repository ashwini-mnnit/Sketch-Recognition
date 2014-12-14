package com.db.mongo;

public class HandleData {

	public static void main(String[] args) {
		ProcessData dataObject = new ProcessData("localhost", 27017, "SketchRec");
		dataObject.removeSouseData("Souse", "0b8c1460-6919-45bf-b6e3-b3776b69a40b");
		dataObject.processSouseData("/home/shirsing/Downloads/17902.xml", "Souse");
		dataObject.displaySouseData("Souse", "0b8c1460-6919-45bf-b6e3-b3776b69a40b");
	}

}
