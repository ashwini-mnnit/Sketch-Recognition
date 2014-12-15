package com.db.mongo;
import java.util.List;

import com.cluster.ClusterDataSet;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.parser.mechanix.MechanixSketch;
import com.parser.sousa.SousaSketch;
import com.sketchshape.SrlShapeExtended;

public class ProcessData {
	private MongoConnect mongoConnect;
	
	public ProcessData(String serverAddr, int port, String dbName) {
		 mongoConnect = new MongoConnect(serverAddr, port, dbName);
    }
	
	public void insertSouseData(String path, String collectionName) {
		processSouseData(path, collectionName);
	}
	
	public void insertMechanixData(String path, String collectionName) {
		processMechanixData(path, collectionName);
	}
	
	public void processMechanixData(String path, String collectionName) {
		
		DBCollection collection = mongoConnect.getCollection(collectionName);
		ClusterDataSet clusterDataSet = new ClusterDataSet();
		DataFetcher df = new DataFetcher(path);
		List<MechanixSketch> sketchList = df.GetMechanixData();
		for (MechanixSketch mechanixSketch : sketchList)
			clusterDataSet.setClusterIdMechanixShape(mechanixSketch);
    	for (MechanixSketch sousaSketch : sketchList) {
    		Gson gson = new Gson();
    		String jsonString = gson.toJson(sousaSketch);
            DBObject dbObject = (DBObject)JSON.parse(jsonString);
            collection.insert(dbObject);
    	}
	}
	
	
	public void processSouseData(String path, String collectionName) {
		
		DBCollection collection = mongoConnect.getCollection(collectionName);
		
		DataFetcher df = new DataFetcher(path);
		
		List<SousaSketch> sketchList = df.GetSouseDatas();
    	for (SousaSketch sousaSketch : sketchList) {
    		Gson gson = new Gson();
    		String jsonString = gson.toJson(sousaSketch);
            DBObject dbObject = (DBObject)JSON.parse(jsonString);
            collection.insert(dbObject);
    	}
	}
	
public void processSouseDataSrl(String path, String collectionName) {
		
		DBCollection collection = mongoConnect.getCollection(collectionName);
		
		DataFetcher df = new DataFetcher(path);
		List<SrlShapeExtended> sketchList = df.GetSouseData();
		ClusterDataSet clusterDataSet = new ClusterDataSet();
		clusterDataSet.setClusterId(sketchList);
    	for (SrlShapeExtended sousaSketch : sketchList) {
    		Gson gson = new Gson();
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
    	
    	public void removeSouseData(String collectionName, String id) {
    		DBCollection collection = mongoConnect.getCollection(collectionName);
	    	BasicDBObject searchQuery = new BasicDBObject();
	    	searchQuery.containsField(id);
	    	collection.remove(searchQuery);
	    		    	
    	}

}
