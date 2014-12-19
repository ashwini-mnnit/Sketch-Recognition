package com.db.mongo;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.addfields.speed.CalculateSpeed;
import com.cluster.ClusterDataSet;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.parser.mechanix.MechanixSketch;
import com.parser.sousa.SousaSketch;
import com.parser.sousa.SousaStroke;
import com.sketchshape.SrlShapeExtended;

public class ProcessData {
	private MongoConnect mongoConnect;
	
	public ProcessData(String serverAddr, int port, String dbName) {
		 mongoConnect = new MongoConnect(serverAddr, port, dbName);
    }
	
	public void insertSouseData(String path, String collectionName) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println(path);
		processSouseData(path, collectionName);
	}
	
	public void insertMechanixData(String path, String collectionName) {
		processMechanixData(path, collectionName);
	}
	
	public void insertSrlData(String path, String collectionName) throws JsonGenerationException, JsonMappingException, IOException {
		processSouseDataSrl(path, collectionName);
	}
	
	public void processMechanixData(String path, String collectionName) {
		DBCollection collection = mongoConnect.getCollection(collectionName);
		CalculateSpeed calculateSpeed = new CalculateSpeed(); 
		ClusterDataSet clusterDataSet = new ClusterDataSet();
		
		DataFetcher df = new DataFetcher(path);
		List<MechanixSketch> sketchList = df.GetMechanixData();
		
		for (MechanixSketch mechanixSketch : sketchList)
			calculateSpeed.populateSpeed(mechanixSketch);
		clusterDataSet.setClusterIdMechanixShapeList(sketchList);
    	for (MechanixSketch mSketch : sketchList) {
    		mSketch.updatePrimitiveTypes();
    		Gson gson = new Gson();
    		String jsonString = gson.toJson(mSketch);
            DBObject dbObject = (DBObject)JSON.parse(jsonString);
            collection.insert(dbObject);
    	}
	}
	
	
	public void processSouseData(String path, String collectionName) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("1");
		DBCollection collection = mongoConnect.getCollection(collectionName);
		ObjectMapper mapper = new ObjectMapper();
		
		DataFetcher df = new DataFetcher(path);
		File resultFile = new File("C:\\Users\\shirsing\\Desktop\\Referral\\abc.json");
		List<SousaSketch> sketchList = df.GetSouseDatas();
		mapper.defaultPrettyPrintingWriter().writeValue(resultFile, sketchList.get(0));
    	for (SousaSketch sousaSketch : sketchList) {
    		System.out.println("2");
    		sousaSketch.updatePrimitiveTypes();
    		Gson gson = new Gson();
    		
    		String jsonString = gson.toJson(sousaSketch);
    		System.out.println(jsonString);
            DBObject dbObject = (DBObject)JSON.parse(jsonString);
            collection.insert(dbObject);
    	}
	}
	
public void processSouseDataSrl(String path, String collectionName) throws JsonGenerationException, JsonMappingException, IOException {
		
		DBCollection collection = mongoConnect.getCollection(collectionName);
		ObjectMapper mapper = new ObjectMapper();
		DataFetcher df = new DataFetcher(path);
		List<SrlShapeExtended> sketchList = df.GetSouseData();
		
		File resultFile = new File("C:\\Users\\shirsing\\Desktop\\Referral\\abc.json");
		System.out.println(sketchList.get(0).getPrimitiveType());
		mapper.defaultPrettyPrintingWriter().writeValue(resultFile, sketchList.get(0));
		
		SrlShapeExtended srl = mapper.readValue(resultFile, SrlShapeExtended.class);
		System.out.println(srl.getPrimitiveType());
		
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
    	
    	public void removeAllSouseData(String collectionName) {
    		DBCollection collection = mongoConnect.getCollection(collectionName);
	    	collection.drop();
	    		    	
    	}
    	
    	public void removeAllMechanixData(String collectionName) {
    		DBCollection collection = mongoConnect.getCollection(collectionName);
	    	collection.drop();
	    		    	
    	}

}
