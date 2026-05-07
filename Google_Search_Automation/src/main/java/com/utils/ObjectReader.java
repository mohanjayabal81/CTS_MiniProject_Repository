package com.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ObjectReader {
	private Properties properties;
	
	public ObjectReader()  {
		
		//initializing the Properties reference variable 
		properties = new Properties();
		
		//Getting the path of the object.properties
		String path = System.getProperty("user.dir")+"\\src\\main\\java\\object.properties";
		
		//creating FileInputStream object 
		FileInputStream fis;
		try {
			fis = new FileInputStream(path);
			
			//loading the FileInputStream object to properties
			properties.load(fis);
			
			//closing the FileInputStream
			fis.close();
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
	}
	
	//Gets the properties from the object.properties file
	public String getProperty(String prop) {
		
		//reading properties from object.properties file
		return properties.getProperty(prop);
	}
	
}
