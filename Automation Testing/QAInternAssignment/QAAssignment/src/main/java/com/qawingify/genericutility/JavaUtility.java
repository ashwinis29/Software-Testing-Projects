package com.qawingify.genericutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class JavaUtility {
	
	/**
	 * This method will be used to read the common data from the property file
	 * @author Ashwini Saravannavar
	 * @param key
	 * @return value
	 * @throws FileNotFoundException
	 * @throws IOException
	 * 
	 */
	public String propertyData(String key) throws FileNotFoundException, IOException
	{
		Properties pobj = new Properties();
		pobj.load(new FileInputStream("./src/test/resources/data.properties"));
		String value = pobj.getProperty(key);
		return value;
	}
}
