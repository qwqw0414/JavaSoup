package com.java.soup.component;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesComp {

	private static final Properties prop = new Properties();

	public PropertiesComp() {
		String fileName = PropertiesComp.class.getResource("/properties/configurer.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String get(String key) throws RuntimeException {
		
		String result = prop.getProperty(key);
//		System.out.println("# getProperties : "+ key + "=" + result);
		
		return result;
	}
	
}
