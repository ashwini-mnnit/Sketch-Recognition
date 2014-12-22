package com.db.mongo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.sketchMl.Dpi;
import com.sketchMl.Point;
import com.sketchMl.Sketch;
import com.sketchMl.Sketcher;

import edu.tamu.srl.sketch.core.object.SrlShape;
import edu.tamu.srl.sketch.core.object.SrlStroke;
import edu.tamu.srl.sketch.core.virtual.SrlPoint;

public class ProcessData {
	private MongoConnect mongoConnect;
	
	public ProcessData(String serverAddr, int port, String dbName) {
		 mongoConnect = new MongoConnect(serverAddr, port, dbName);
    }
	
	
	public void insertMechanixData(String path, String collectionName) throws JsonGenerationException, JsonMappingException, IOException {
		processMechanixDataSrl(path, collectionName);
	}
	
	public void insertSrlData(String path, String collectionName) throws JsonGenerationException, JsonMappingException, IOException {
		processSouseDataSrl(path, collectionName);
	}
	
	
	
public void processMechanixDataSrl(String path, String collectionName) throws JsonGenerationException, JsonMappingException, IOException {
		
		DBCollection collection = mongoConnect.getCollection(collectionName);
		DataFetcher df = new DataFetcher(path);
		List<SrlShape> srlList = df.GetMechanixData();
		ArrayList<Sketch> sketchList= getSketchMlList(srlList);
		
		for (Sketch sketch : sketchList) {
			Gson gson = new Gson();
    		String jsonString = gson.toJson(sketch);
    		System.out.println(jsonString);
    		DBObject dbObject = (DBObject)JSON.parse(jsonString);
            collection.insert(dbObject);
		}	
}

	
public ArrayList<Sketch> getSketchMlList(List<SrlShape> srlShapeList) {
	ArrayList<Sketch> sketchList = new ArrayList<Sketch>();
	for (SrlShape srlShape: srlShapeList) {
		Sketch sketch = new Sketch();
		sketch.setId(srlShape.getId());
		List<SrlStroke> srlStroke = srlShape.getRecursiveStrokeList();
		ArrayList<Sketcher> sketcherList = getAllSketcher(srlStroke);
		sketch.setSketcher(sketcherList);
		ArrayList<Point> pointList = getAllPoints(srlStroke); 
		sketch.setPoint(pointList);
		sketchList.add(sketch);
	}
	
	return sketchList;
	
	
}
	
private ArrayList<Point> getAllPoints(List<SrlStroke> srlStroke) {
	ArrayList<Point> pointList = new ArrayList<Point>();
	for (SrlStroke it : srlStroke) { 
		List<SrlPoint> srlPoint = it.getPoints();
		for (SrlPoint srlpoint : srlPoint) {
			Point point = new Point();
			point.setId(srlpoint.getId());
			point.setX((float) srlpoint.getX());
			point.setY((float) srlpoint.getY());
			point.setPressure((float) srlpoint.getPressure());
			pointList.add(point);
		}
	}
	return pointList;
}


private ArrayList<Sketcher> getAllSketcher(List<SrlStroke> srlStroke) {
	ArrayList<Sketcher> sketcher = new ArrayList<Sketcher>();
	
	for (SrlStroke it : srlStroke) {
		Sketcher sk = new Sketcher();
		if (it.getAuthor() != null) {
			sk.setId(it.getAuthor().getId());
			sk.setNickname(it.getAuthor().getName());
		}
		Dpi dpi = new Dpi();
		
		if (it.getDevice() != null) {
			dpi.setX((float) it.getDevice().getDpiX());
			dpi.setY((float) it.getDevice().getDpiY());
		}
		sk.setDpi(dpi);
		sketcher.add(sk);
	}
	return sketcher;
}


public void processSouseDataSrl(String path, String collectionName) throws JsonGenerationException, JsonMappingException, IOException {
		
		DBCollection collection = mongoConnect.getCollection(collectionName);
		DataFetcher df = new DataFetcher(path);
		List<SrlShape> srlList = df.GetSouseDatas();
		ArrayList<Sketch> sketchList= getSketchMlList(srlList);
		
		for (Sketch sketch : sketchList) {
			Gson gson = new Gson();
    		String jsonString = gson.toJson(sketch);
    		System.out.println(jsonString);
    		DBObject dbObject = (DBObject)JSON.parse(jsonString);
            collection.insert(dbObject);
		}	
}
    	
    	public void displaySouseData(String collectionName, String id) {
    		DBCollection collection = mongoConnect.getCollection(collectionName);
	    	BasicDBObject searchQuery = new BasicDBObject();
	    	searchQuery.containsField(id);
	    	DBCursor cursor = collection.find(searchQuery);
	    	while (cursor.hasNext()) {
	    		System.out.println(cursor.next());
	    	}	    	
    	}
    	
    	
    	public void removeSouseData(String collectionName, String id) {
    		DBCollection collection = mongoConnect.getCollection(collectionName);
	    	BasicDBObject searchQuery = new BasicDBObject();
	    	searchQuery.containsField(id);
	    	collection.remove(searchQuery);
	    		    	
    	}
    	
    	public void removeAllSouseData(String collectionName) {
    		DBCollection collection = mongoConnect.getCollection(collectionName);
	    	collection.drop();
	    		    	
    	}
    	
    	public void removeAllMechanixData(String collectionName) {
    		DBCollection collection = mongoConnect.getCollection(collectionName);
	    	collection.drop();
	    		    	
    	}

}
