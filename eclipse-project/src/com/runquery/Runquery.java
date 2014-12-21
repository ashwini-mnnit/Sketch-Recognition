package com.runquery;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import com.addfields.speed.CalculateSpeed;
import com.classify.ClassifyData;
import com.db.mongo.DataFetcher;
import com.db.mongo.RetrieveData;
import com.db.mongo.SketchMltoXmlConverter;
import com.parser.mechanix.MechanixShape;
import com.parser.mechanix.MechanixSketch;
import com.sketchMl.Sketch;

import edu.tamu.srl.sketch.core.object.SrlShape;

public class Runquery {
	RetrieveData retrieveData;
	ClassifyData classifyData;
	CalculateSpeed calculateSpeed;
	
	Runquery(String serverAddr, int port, String dbName, List<MechanixSketch> mechanixSketchList) {
		retrieveData = new RetrieveData(serverAddr, port, dbName);
		calculateSpeed = new CalculateSpeed();
		for(MechanixSketch mechanixSketch : mechanixSketchList)
			calculateSpeed.populateSpeed(mechanixSketch);
		classifyData = new ClassifyData(5);
		classifyData.learnClassifierMechanixSketch(mechanixSketchList);
		
	}

	public ArrayList<Sketch> executeQuery(MechanixShape mechanixShape) throws JSONException {
		CalculateSpeed calculateSpeed = new CalculateSpeed();
		calculateSpeed.populateSpeed(mechanixShape);
		Double clusterId = classifyData.getClusterId(mechanixShape);
		ArrayList<Sketch> sketchList = retrieveData.getSimilarSketchMlforMechanixData("Mechanix", clusterId);
		return sketchList;
	}

	public static void main(String[] args) {
		DataFetcher df = new DataFetcher("/home/shirsing/Downloads/SketchData.xml");
		List<SrlShape> sketchList = df.GetMechanixData();
		List<MechanixShape> mechanixShapeList = new ArrayList<MechanixShape>();
		for(SrlShape mechanixSketch : sketchList) {
			//mechanixShapeList.addAll(mechanixSketch.getAllShapes());
		}
		MechanixShape mechanixShape = new MechanixShape();
		for(MechanixShape mechanixShapeTemp : mechanixShapeList) {
			if(mechanixShapeTemp.getStroke() != null && mechanixShapeTemp.getStroke().getPoints() != null && mechanixShapeTemp.getStroke().getPoints().size() != 1) {
				mechanixShape = mechanixShapeTemp;
				break;
			}
		}
		
	/*//Runquery runQuery = new Runquery("localhost", 27017, "SketchRec", sketchList);
	try {
		//ArrayList<Sketch> clusterIdToQuery = runQuery.executeQuery(mechanixShape);
		for (Sketch it :  clusterIdToQuery) {
			SketchMltoXmlConverter.sketchMltoXml(it, it.getId().toString(),"ClusteredXML");
	}
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	
	}

}
