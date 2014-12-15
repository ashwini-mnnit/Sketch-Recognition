package com.db.mongo;

import java.util.ArrayList;
import java.util.List;
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
import com.parser.sousa.SousaArg;
import com.parser.sousa.SousaPoint;
import com.parser.sousa.SousaSketch;
import com.parser.sousa.SousaStroke;
import com.sketchMl.Arg;
import com.sketchMl.NickName;
import com.sketchMl.Point;
import com.sketchMl.Shape;
import com.sketchMl.Sketch;
import com.sketchMl.Sketcher;
import com.sketchshape.PremitiveStrokeType;
import com.sketchshape.SrlShapeExtended;

public class RetrieveData {
	private MongoConnect mongoConnect;
	ClassifyData classifyData;
	
	public RetrieveData(String serverAddr, int port, String dbName) {
		 classifyData = new ClassifyData(5);
		 mongoConnect = new MongoConnect(serverAddr, port, dbName);
    }

	public RetrieveData(String serverAddr, int port, String dbName, List<MechanixShape> mechanixShapeList) {
		classifyData = new ClassifyData(5);
		classifyData.learnClassifierMechanixShape(mechanixShapeList);
		mongoConnect = new MongoConnect(serverAddr, port, dbName);
   }
	
	public ArrayList<Sketch> getSketchMlforSouseDataSrl(String collectionName, String id) throws JSONException {
		DBCollection collection = mongoConnect.getCollection(collectionName);
    	BasicDBObject searchQuery = new BasicDBObject();
    	searchQuery.containsField(id);
    	DBCursor cursor = collection.find(searchQuery);
    	ArrayList<Sketch> sketchML = new ArrayList<Sketch>();
    	while (cursor.hasNext()) {
    		Sketch sketch = new Sketch();
    		DBObject dbobj = cursor.next();
    		String uid = (String) dbobj.get( "mId");
    		sketch.setId(UUID.fromString(uid));
    		sketchML.add(sketch);
    	}	    	
		return sketchML;
	} 
	
	public void addSketcher(ArrayList<Sketcher> sketcher,SousaSketch sSketch ) {
			Sketcher s = new Sketcher();
			NickName name = new NickName();
			name.setNickname(sSketch.getAuthor());
			s.setNickname(sSketch.getAuthor());
			sketcher.add(s);
	}
	
	public void addArg(ArrayList<Arg> arg, List<SousaArg> argList) {
		for (SousaArg it : argList) {
			Arg a = new Arg(it.getType(),it.getId());
			arg.add(a);
		}
	}
	
	public void addPoint(ArrayList<Point> point ,ArrayList<SousaStroke> sousaStrokes) {
		for (SousaStroke it : sousaStrokes) {
			ArrayList<SousaPoint> pointList = it.getPointList();
			
			for (SousaPoint it1 : pointList) {
				Point p = new Point();
				p.setId(it1.getId());
				p.setTime(Long.parseLong(it1.getTime()));
				Double d = it1.getxCoordinate();
				p.setX(d.floatValue());
				d = it1.getyCoordinate();
				p.setY(d.floatValue());
				point.add(p);
			}
		}
	}
	public void addShape(ArrayList<Shape> shape, ArrayList<SousaStroke> sousaStrokes){
		for (SousaStroke it : sousaStrokes) {
			Shape s = new Shape();
			PremitiveStrokeType ptype = it.getPrimitiveType();
			if (ptype != null) {
				s.setType(ptype.getName());
			}
			ArrayList<Arg> arg = new ArrayList<Arg>();
			addArg(arg, it.getArgList());
			s.setArg(arg);
			s.setId(it.getId());
			shape.add(s);
		}
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
    		Gson gson = new Gson();
    		String json = dbobj.toString();
    		SousaSketch sSketch = gson.fromJson(json, SousaSketch.class);
    		sketch.setId(sSketch.getId());
    		
    		ArrayList<Sketcher> sketcher= new ArrayList<Sketcher> ();
    		addSketcher(sketcher,sSketch);
    		sketch.setSketcher(sketcher);
    		
    		ArrayList<Shape> shape= new ArrayList<Shape>();
    		addShape(shape,sSketch.getSousaStrokes());
    		sketch.setShape(shape);
    		
    		ArrayList<Point> point = new ArrayList<Point>();
    		addPoint(point, sSketch.getSousaStrokes());
    		sketch.setPoint(point);
    		sketchML.add(sketch);
    	}	    	
		return sketchML;
	} 
	
	public Sketch getSketchMlObject(MechanixSketch mSketch) {
		Sketch sk = new Sketch();
		sk.setId(mSketch.getId());
		ArrayList<Shape> skShape= new ArrayList<Shape>();
		ArrayList<Point> skPoint= new ArrayList<Point>();
		ArrayList<MechanixShape> mShape = mSketch.getShapes();
		for (MechanixShape it : mShape) {
			Shape shape = new Shape();
			shape.setId(it.getId());
			if (it.getTime() != null) {
				double t = Double.parseDouble(it.getTime()); 
				shape.setTime((long)(t));
			}
			MechanixStroke mStroke = it.getStroke();
			/*if(mStroke.getDraw_color() != null) {
				float c = Float.parseFloat(mStroke.getDraw_color());
				shape.setColor(c);
			}*/
			if (mStroke != null) {
				PremitiveStrokeType ptype = mStroke.getPrimitiveType();
				if (ptype != null) {
					shape.setType(ptype.getName());
				}
			}
			skShape.add(shape);
			if (mStroke != null) {
				ArrayList<MechanixPoint> mPoint = mStroke.getPoints();
				if (mPoint != null) {
					for (MechanixPoint it1 : mPoint) {
						Point p = new Point();
						p.setId(it1.getId());
						if (it1.getTime() != null) {
							p.setTime(Long.parseLong(it1.getTime()));
						}
						p.setX((float)(it1.getX()));
						p.setY((float)(it1.getY()));
						skPoint.add(p);
					}
				}
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
	
	public ArrayList<Sketch> getSimilarSketchMlforMechanixData(String collectionName, MechanixShape mechanixShape) throws JSONException {
		CalculateSpeed calculateSpeed = new CalculateSpeed();
		calculateSpeed.populateSpeed(mechanixShape);
		double clusterId = classifyData.getClusterId(mechanixShape);
		
		DBCollection collection = mongoConnect.getCollection(collectionName);
    	BasicDBObject searchQuery = new BasicDBObject();
    	searchQuery.put("clusterId", clusterId);
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
