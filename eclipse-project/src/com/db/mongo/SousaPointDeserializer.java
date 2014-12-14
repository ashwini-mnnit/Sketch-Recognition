package com.db.mongo;
import java.lang.reflect.Type;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.parser.sousa.SousaPoint;


public class SousaPointDeserializer implements JsonDeserializer<SousaPoint> {
	

	  @Override
	  public SousaPoint deserialize(final JsonElement json, 
	        final Type typeOfT, 
	        final JsonDeserializationContext context) 
	        throws JsonParseException {

		  SousaPoint susaPoint = new SousaPoint();
	    // Parsing will be done here.
	    return susaPoint;
	  }
	}

