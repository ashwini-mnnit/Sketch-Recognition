package com.db.mongo;

import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONException;

import com.google.gson.Gson;
import com.sketchMl.Sketch;

public class HandleData {

		ProcessData dataObject;
		
		HandleData() {
			dataObject = new ProcessData("localhost", 27017, "SketchRec");
		}

		public static void main(String[] args) throws JSONException, JsonGenerationException, JsonMappingException, IOException {
			ProcessData dataObject = new ProcessData("localhost", 27017, "SketchRec");
			dataObject.removeAllSouseData("Souse");
			dataObject.insertSrlData("C:\\Users\\shirsing\\Desktop\\Referral\\1839\\17902.xml", "Souse");
			
			RetrieveData r = new RetrieveData("localhost", 27017, "SketchRec");
			ArrayList<Sketch> sketchMlList = r.getSketchMlDataFromMongo("Souse", "0b8c1460-6919-45bf-b6e3-b3776b69a40b");
			for (Sketch it : sketchMlList) {
				    Gson g = new Gson();
				    System.out.println(g.toJson(it));
					SketchMltoXmlConverter.sketchMltoXml(it, it.getId().toString(),"XML");
			}
		}
	}
