package com.db.mongo;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.parser.sousa.SousaPoint;
import com.parser.sousa.SousaSketch;
import com.parser.sousa.SousaStroke;

public class ProcessData {
	private MongoConnect mongoConnect;
	
	public ProcessData(String serverAddr, int port, String dbName) {
		 mongoConnect = new MongoConnect(serverAddr, port, dbName);
    }
	
	public void processSouseData(String path, String collectionName) {
		
		DBCollection collection = mongoConnect.getCollection(collectionName);
		DataFetcher df = new DataFetcher(path);
    	List<SousaSketch> sketchList = df.GetSouseData();
    	for (SousaSketch sousaSketch : sketchList) {
    		/*System.out.println("Shireesh");
    		ArrayList<SousaStroke> strk = sousaSketch.getSousaStrokes();
    		for (SousaStroke sousaStroke : strk) {
    			System.out.println("Singh");
    			ArrayList<SousaPoint> point = sousaStroke.getPointList();
    			
    			for (SousaPoint sousaPoint : point) {
    				System.out.println("point");
    				System.out.println(sousaPoint.getxCoordinate());
    			}
    		}*/
    		GsonBuilder builder = new GsonBuilder();
    		builder.setPrettyPrinting().serializeNulls();
    		Gson gson = builder.create();
    		String jsonString = gson.toJson(sousaSketch);
         
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

}
