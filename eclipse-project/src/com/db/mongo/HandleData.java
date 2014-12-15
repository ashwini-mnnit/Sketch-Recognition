package com.db.mongo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import com.classify.ClassifyData;
import com.google.gson.Gson;
import com.sketchMl.Sketch;
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

		public static void main(String[] args) throws JSONException {
			ProcessData dataObject = new ProcessData("localhost", 27017, "SketchRec");
			dataObject.removeSouseData("Souse", "0b8c1460-6919-45bf-b6e3-b3776b69a40b");
			dataObject.processSouseData("/home/shirsing/Downloads/17902.xml", "Souse");
			dataObject.insertMechanixData("/home/shirsing/Downloads/SketchData.xml", "Mechanix");
			RetrieveData r = new RetrieveData("localhost", 27017, "SketchRec");
			
			 r.getSketchMlforSouseData("Souse", "0b8c1460-6919-45bf-b6e3-b3776b69a40b");
			
			 ArrayList<Sketch> s =r.getSketchMlforMechanixData("Mechanix", "b589d96f-ec00-4156-9bc8-b6f26bf28cc5");
			
			for (Sketch it :s) {
				Gson gson = new Gson();
				System.out.println(gson.toJson(it));
			}
		}
	}
