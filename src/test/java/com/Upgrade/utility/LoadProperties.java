package com.Upgrade.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class LoadProperties {

	public static Properties readPropertyFile(String path) {
		try {
			Properties prop = new Properties();

			String fullPath = System.getProperty("user.dir") +"\\Resources"+ path;
			System.out.println("Reading the input vlaues from :  " + fullPath);
			try (InputStream stream = new FileInputStream(new File(fullPath))) {
			
				prop.load(stream);
				return prop;
			}
		} catch (Exception e) {
			return null;
		}
	}
}
