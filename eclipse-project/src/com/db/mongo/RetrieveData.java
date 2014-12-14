package com.db.mongo;

import java.util.ArrayList;
import java.util.UUID;

import org.json.JSONException;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.parser.mechanix.MechanixPoint;
import com.parser.mechanix.MechanixShape;
import com.parser.mechanix.MechanixSketch;
import com.parser.mechanix.MechanixStroke;
import com.sketchMl.Point;
import com.sketchMl.Shape;
import com.sketchMl.Sketch;


//shirsing

public class RetrieveData {
	private MongoConnect mongoConnect;
	
	public RetrieveData(String serverAddr, int port, String dbName) {
		 mongoConnect = new MongoConnect(serverAddr, port, dbName);
    }
	
	public ArrayList<Sketch> getSketchMlforSouseData(String collectionName, String id) throws JSONException {
		DBCollection collection = mongoConnect.getCollection(collectionName);
    	BasicDBObject searchQuery = new BasicDBObject();
    	searchQuery.containsField(id);
    	DBCursor cursor = collection.find(searchQuery);
    	ArrayList<Sketch> sketchML = new ArrayList<Sketch>();
    	while (cursor.hasNext()) {
    		Sketch sketch = new Sketch();
    		DBObject dbobj = cursor.next();
    		String uid = (String) dbobj.get( "mId");
    		sketch.setSketchId(UUID.fromString(uid));
    		sketchML.add(sketch);
    	}	    	
		return sketchML;
	} 
	
	public Sketch getSketchMlObject(MechanixSketch mSketch) {
		Sketch sk = new Sketch();
		sk.setSketchId(mSketch.getId());
		ArrayList<Shape> skShape= new ArrayList<Shape>();
		ArrayList<Point> skPoint= new ArrayList<Point>();
		ArrayList<MechanixShape> mShape = mSketch.getShapes();
		for (MechanixShape it : mShape) {
			Shape shape = new Shape();
			shape.setShapeId(it.getId());
			shape.setTime(Long.parseLong(it.getTime()));
			MechanixStroke mStroke = it.getStroke();
			shape.setColor(Float.parseFloat(mStroke.getDraw_color()));
			skShape.add(shape);
			ArrayList<MechanixPoint> mPoint = mStroke.getPoints();
			
			for (MechanixPoint it1 : mPoint) {
				Point p = new Point();
				p.setPointID(it1.getId());
				p.setTime(Long.parseLong(it1.getTime()));
				p.setXCordinate((float)(it1.getX()));
				p.setYCordinate((float)(it1.getY()));
				skPoint.add(p);
			}
		}
		sk.setShape(skShape);
		sk.setPoint(skPoint);
		return sk;
	}
	
	public ArrayList<Sketch> getSketchMlforMechanixData(String collectionName, String id) throws JSONException {
		DBCollection collection = mongoConnect.getCollection(collectionName);
    	BasicDBObject searchQuery = new BasicDBObject();
    	searchQuery.containsField(id);
    	DBCursor cursor = collection.find(searchQuery);
    	ArrayList<Sketch> sketchML = new ArrayList<Sketch>();
    	while (cursor.hasNext()) {
    		DBObject dbobj = cursor.next();
    		Gson gson = new Gson();
    		String json = dbobj.toString();
    		MechanixSketch mSketch = gson.fromJson(json, MechanixSketch.class);
    		Sketch sketch = getSketchMlObject(mSketch);
    		sketchML.add(sketch);
    	}	    	
		return sketchML;
	}
	
}
