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
			dataObject.removeAllMechanixData("Mechanix");
			//dataObject.insertSrlData("C:\\Users\\shirsing\\Desktop\\Referral\\1839\\17902.xml", "Souse");
			dataObject.insertMechanixData("C:\\Users\\shirsing\\Downloads\\MechanixDataFolder", "Mechanix");
			
			RetrieveData r = new RetrieveData("localhost", 27017, "SketchRec");
			ArrayList<Sketch> sketchMlList = r.getSketchMlDataFromMongo("Mechanix", "00000004-3b88-469e-9284-3ce61b470fc0");
			for (Sketch it : sketchMlList) {
				    Gson g = new Gson();
				    System.out.println(g.toJson(it));
					SketchMltoXmlConverter.sketchMltoXml(it, it.getId().toString(),"XML");
			}
		}
	}
