package com.db.mongo;

import java.util.ArrayList;

import org.json.JSONException;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sketchMl.Sketch;

public class RetrieveData {
	private MongoConnect mongoConnect;
	
	
	public RetrieveData(String serverAddr, int port, String dbName) {
		 mongoConnect = new MongoConnect(serverAddr, port, dbName);
    }
	
	
	public ArrayList<Sketch> getSimilarSketchMlforMechanixData(String collectionName, int clusterId) throws JSONException {
		ArrayList<Sketch> sketchML = new ArrayList<Sketch>();
		DBCollection collection = mongoConnect.getCollection(collectionName);
    	BasicDBObject searchQuery = new BasicDBObject();
    	searchQuery.put("shapes.Shapes.clusterId", clusterId);
    	DBCursor cursor = collection.find(searchQuery);
    	while (cursor.hasNext()) {
    		DBObject dbobj = cursor.next();
    		Gson gson = new Gson();
    		String json = dbobj.toString();
    		Sketch sketch = gson.fromJson(json,Sketch.class);
    		sketchML.add(sketch);
    	}	    	
		return sketchML;
	}
	
	
	public ArrayList<Sketch> queryOnPrimitiveTypes(String collectionName, String id,String primitiveType) {
		ArrayList<Sketch> sketchML = new ArrayList<Sketch>();
		DBCollection collection = mongoConnect.getCollection(collectionName);
    	BasicDBObject searchQuery = new BasicDBObject();
    	searchQuery.put("sousaStrokes.primitiveType.Name", primitiveType);
    	DBCursor cursor = collection.find(searchQuery);
    	while (cursor.hasNext()) {
    		DBObject dbobj = cursor.next();
    		Gson gson = new Gson();
    		String json = dbobj.toString();
    		Sketch sketch = gson.fromJson(json, Sketch.class);
    		sketchML.add(sketch);
    	}	
    	return sketchML;
	}
	
	public ArrayList<Sketch> getSketchMlDataFromMongo(String collectionName, String id) {
		DBCollection collection = mongoConnect.getCollection(collectionName);
		BasicDBObject searchQuery = new BasicDBObject();
    	searchQuery.containsField(id);
    	DBCursor cursor = collection.find(searchQuery);
    	ArrayList<Sketch> sketchML = new ArrayList<Sketch>();
    	while (cursor.hasNext()) {
    		DBObject dbobj = cursor.next();
    		Gson gson = new Gson();
    		String json = dbobj.toString();
    		Sketch sketch = gson.fromJson(json, Sketch.class);
    		sketchML.add(sketch);
    	}	    	
		return sketchML;
		
	}
}
