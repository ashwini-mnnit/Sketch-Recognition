package com.db.mongo;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.sketchMl.Sketch;

public class SketchMltoXmlConverter {
	 	
	 		public static void sketchMltoXml(Sketch sketch) {
		  
			  try {
	 
				  File file = new File("/home/shirsing/shirsing.xml");
				  JAXBContext jaxbContext = JAXBContext.newInstance(Sketch.class);
				  Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
			// output pretty printed
				  jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	 
				  jaxbMarshaller.marshal(sketch, file);
				  jaxbMarshaller.marshal(sketch, System.out);
	 
			  	} catch (JAXBException e) {
			  		e.printStackTrace();
			  	}
	 
	 		}
}
