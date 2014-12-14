package com.parser.json;
import java.io.Reader;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class jsonParser {

	public static void main(String[] args)  throws Exception {
	   {
	      JSONParser parser=new JSONParser();
	      String s = "[0,{\"1\":{\"2\":{\"3\":{\"4\":[5,{\"6\":7}]}}}}]";
	      try{
	         Object obj = parser.parse();
	         JSONArray array = (JSONArray)obj;
	         System.out.println("The 2nd element of array");
	         System.out.println(array.get(1));
	         System.out.println();

	         JSONObject obj2 = (JSONObject)array.get(1);
	         System.out.println("Field \"1\"");
	         System.out.println(obj2.get("1"));    

	         s = "{}";
	         obj = parser.parse(s);
	         System.out.println(obj);

	         s= "[5,]";
	         obj = parser.parse(s);
	         System.out.println(obj);

	         s= "[5,,2]";
	         obj = parser.parse(s);
	         System.out.println(obj);
	      }catch(ParseException pe){
	         System.out.println("position: " + pe.getPosition());
	         System.out.println(pe);
	      }
	   }	
	   
	   public void ViewBean(byte[] buffer) throws ParseException {
		   JSONParser parser=new JSONParser();
		   Reader s;
		   try {
		     JSONObject json=(JSONObject)parser.parse(s);
		     if (json.containsKey("description")) {
		       this.description=(String)json.get("description");
		     }
		  else {
		       this.description="";
		     }
		     this.owner=(String)json.get("owner");
		     this.name=(String)json.get("name");
		     this.permissionType=(String)json.get("permissionType");
		     int size=((JSONArray)json.get("pages")).size();
		     PagesBean[] pages=new PagesBean[size];
		     JSONArray pagesArray=(JSONArray)json.get("pages");
		     for (int i=0; i < size; i++) {
		       pages[i]=new PagesBean((JSONObject)pagesArray.get(i));
		     }
		     this.pages=pages;
		   }
		  catch (  Exception e) {
		     log.error(ExceptionUtil.getStackTrace(e));
		     throw new ParseException(ExceptionUtil.getStackTrace(e),0);
		   }
		 }
		  
}
