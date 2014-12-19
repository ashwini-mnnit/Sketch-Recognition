package com.db.mongo;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoConnect {
	private MongoClient mongoClient;
	private DB mongoDb;

	public MongoConnect(String serverAddr, int port, String dbName) {
		try {
			mongoClient = new MongoClient(serverAddr, port);
			mongoDb = mongoClient.getDB(dbName);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public MongoClient getMongoClient() {
		return mongoClient;
	}

	public void setMongoClient(String serverAddr, int port) {
		try {
			mongoClient = new MongoClient(serverAddr, port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DB getMongoDb() {
		return mongoDb;
	}

	public void setMongoDb(String dbName) {
		mongoDb = mongoClient.getDB(dbName);
	}

	public DBCollection getCollection(String collectionName) {
		DBCollection collection = mongoDb.getCollection(collectionName);
		return collection;
	}

}