package com.github.netty.http.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
public class ResourceHelper {
	private static Properties prop;
	private static InputStream inputStream;
	
	static {
		try {
			inputStream = ResourceHelper.class
					.getResourceAsStream("/db.properties");
			prop = new Properties();
			prop.load(inputStream);
			
		} catch (Exception ex) {
			try {
				if (inputStream!=null) {
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			ex.printStackTrace();
		}
	}
	public static String get(String key){
	    return prop.getProperty(key);
	}
	public static String get(String key,String defaultValue) {
	    String value = prop.getProperty(key);
	    value = value == null ? defaultValue : value;
		return value;
	}
	public static int getInt(String key,int defaultValue) {
	    String value = prop.getProperty(key);
	    if(value != null){
	        return Integer.parseInt(value);
	    }else{
	        return defaultValue;
	    }
	}
}
