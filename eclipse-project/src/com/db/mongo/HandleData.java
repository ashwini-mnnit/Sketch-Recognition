package com.db.mongo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONException;

import com.classify.ClassifyData;
import com.google.gson.Gson;
import com.parser.mechanix.MechanixShape;
import com.parser.mechanix.MechanixSketch;
import com.sketchMl.Sketch;
import com.sketchshape.SrlShapeExtended;

public class HandleData {

		ProcessData dataObject;
		
		HandleData() {
			dataObject = new ProcessData("localhost", 27017, "SketchRec");
		}

		public static void main(String[] args) throws JSONException {
			ProcessData dataObject = new ProcessData("localhost", 27017, "SketchRec");
			dataObject.removeSouseData("Souse", "0b8c1460-6919-45bf-b6e3-b3776b69a40b");
			dataObject.insertSouseData("/home/shirsing/Downloads/17902.xml", "Souse");
			dataObject.insertMechanixData("/home/shirsing/Downloads/SketchData.xml", "Mechanix");
			RetrieveData r = new RetrieveData("localhost", 27017, "SketchRec");
			
			ArrayList<Sketch> s =r.getSketchMlforSouseData("Souse", "0b8c1460-6919-45bf-b6e3-b3776b69a40b");
			
			r.queryOnPrimitiveTypes("Souse", "0b8c1460-6919-45bf-b6e3-b3776b69a40b","Line");
			//ArrayList<Sketch> s = r.getSketchMlforMechanixData("Mechanix", "b589d96f-ec00-4156-9bc8-b6f26bf28cc5");
			
			for (Sketch it :s) {
				//System.out.println(it.getId());
				SketchMltoXmlConverter.sketchMltoXml(it);
				Gson gson = new Gson();
				System.out.println(gson.toJson(it));
			}
		}
	}
