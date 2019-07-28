package com.jhallat.codeviewide.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class CodeViewProperties {

	public static final String LAST_PROJECT_DIRECTORY = "last.project";
	
	private static final String APPLICATION_PROPERTIES = "codeview.properties";
	private Properties applicationProperties = new Properties();
	private boolean loaded;
	private static CodeViewProperties singleton = new CodeViewProperties();
	
	private CodeViewProperties() {}
	
	public static CodeViewProperties getInstance() {
		return singleton;
	}
	
	public String get(String propertyName) {
		if (!loaded) {
			File file = new File(APPLICATION_PROPERTIES);
			try {
				if (!file.exists()) {
					file.createNewFile();
				}
				FileInputStream input = new FileInputStream(file);
				applicationProperties.load(input);
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			}
		}
		return applicationProperties.getProperty(propertyName, "");
	}
	
	public void set(String propertyName, String propertyValue) {
		applicationProperties.setProperty(propertyName, propertyValue);
		File file = new File(APPLICATION_PROPERTIES);
		try {
			FileOutputStream output = new FileOutputStream(file);
			applicationProperties.store(output, "");
			output.close();
		} catch (IOException e) {
			//TODO replace with logger
			e.printStackTrace();
		}
		
	}
}
