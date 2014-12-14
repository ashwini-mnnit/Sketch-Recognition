package com.db.mongo;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.parser.sousa.SousaSketch;
import com.sketchMl.Sketch;

public class RetrieveData {
	private MongoConnect mongoConnect;
	
	public RetrieveData(String serverAddr, int port, String dbName) {
		 mongoConnect = new MongoConnect(serverAddr, port, dbName);
    }
	
	public Sketch getSketchMlforSouseData(String collectionName, String id) {
		DBCollection collection = mongoConnect.getCollection(collectionName);
    	BasicDBObject searchQuery = new BasicDBObject();
    	searchQuery.containsField(id);
    	DBCursor cursor = collection.find(searchQuery);
    	while (cursor.hasNext()) {
    		System.out.print("Singh");
    		DBObject dbobj = cursor.next();
    		SousaSketch sousaSketch = (new Gson()).fromJson(dbobj.toString(), SousaSketch.class);
    		System.out.println(dbobj.toString());
    	}	    	
		return null;
		
	} 
	
	public static void main(String[] args) {
		RetrieveData dataObject = new RetrieveData("localhost", 27017, "SketchRec");
		dataObject.getSketchMlforSouseData("Souse", "0b8c1460-6919-45bf-b6e3-b3776b69a40b");
	}
}
