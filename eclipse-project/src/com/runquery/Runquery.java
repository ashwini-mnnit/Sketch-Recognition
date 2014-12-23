package com.runquery;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import com.addfields.speed.CalculateSpeed;
import com.classify.ClassifyData;
import com.db.mongo.DataFetcher;
import com.db.mongo.RetrieveData;
import com.db.mongo.SketchMltoXmlConverter;
import com.google.gson.Gson;
import com.sketchMl.Sketch;

import edu.tamu.srl.sketch.core.abstracted.SrlObject;
import edu.tamu.srl.sketch.core.object.SrlShape;

public class Runquery {
	RetrieveData retrieveData;
	ClassifyData classifyData;
	CalculateSpeed calculateSpeed;
	
	Runquery(String serverAddr, int port, String dbName, List<SrlShape> srlShapeList) {
		List<SrlShape> srlShapeListTemp = new ArrayList<>();
		for(SrlShape srlShape1 : srlShapeList) {
			for(SrlObject srlShape : (((SrlShape) srlShape1).getRecursiveSubObjectList())) {
				srlShapeListTemp.add((SrlShape) srlShape);
			}
		}
		retrieveData = new RetrieveData(serverAddr, port, dbName);
		calculateSpeed = new CalculateSpeed();
		for(SrlShape srlShape : srlShapeListTemp)
			calculateSpeed.populateSpeed(srlShape);
		classifyData = new ClassifyData(5);
		classifyData.learnClassifier(srlShapeListTemp);
	}

	public ArrayList<Sketch> executeQuery(SrlShape srlShape) throws JSONException {
		CalculateSpeed calculateSpeed = new CalculateSpeed();
		calculateSpeed.populateSpeed(srlShape);
		int clusterId = classifyData.getClusterId(srlShape);
		ArrayList<Sketch> sketchList = retrieveData.getSimilarSketchMlforMechanixData("Mechanix", 0);
		return sketchList;
	}

	public static void main(String[] args) {
		DataFetcher df = new DataFetcher("C:\\Users\\shirsing\\Downloads\\MechanixDataFolder\\MechanixInputFile1.xml");
		//DataFetcher df = new DataFetcher("C:\\Users\\chunkygupta\\Downloads\\SketchData.xml");
		List<SrlShape> srlShapeList = df.GetMechanixData();
		List<SrlShape> srlShapeListTemp = new ArrayList<>();
		for(SrlShape srlShape1 : srlShapeList) {
			for(SrlObject srlShape : (((SrlShape) srlShape1).getRecursiveSubObjectList())) {
				srlShapeListTemp.add((SrlShape) srlShape);
			}
		}
		
		SrlShape srlShape = new SrlShape();
		for(SrlShape srlShapeTemp : srlShapeListTemp) {
			if(srlShapeTemp.getLastStroke() != null && srlShapeTemp.getLastStroke().getPoints() != null && srlShapeTemp.getLastStroke().getPoints().size() != 1) {
				srlShape = srlShapeTemp;
				break;
			}
		}
		
	Runquery runQuery = new Runquery("localhost", 27017, "SketchRec", srlShapeList);
	try {
		ArrayList<Sketch> clusterIdToQuery = runQuery.executeQuery(srlShape);
		if (!clusterIdToQuery.isEmpty()) {
			Sketch s = clusterIdToQuery.get(0);
			Gson g = new Gson();
			System.out.println(g.toJson(s));
		}
		for (Sketch it :  clusterIdToQuery) {
			SketchMltoXmlConverter.sketchMltoXml(it, it.getId().toString(),"ClusteredXML");
	}
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}

}
