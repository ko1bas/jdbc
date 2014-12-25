package com.simbirsoft.utils.jdbc;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class JDBCConfig {
	final String DB_DRIVER = "db.driver";
	final String DB_ADDRESS = "db.url";
	final String DB_USERNAME = "db.username";
	final String DB_PASSWORD = "db.password";
	private static JDBCConfig instance;
	private Properties properties;

	private JDBCConfig(String propertiesFile){
		properties = new Properties();
		InputStream inputStream;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(propertiesFile));
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static JDBCConfig load(String propertiesFile){
		if (instance == null) {
			instance = new JDBCConfig(propertiesFile);
		}
		return instance;
	}

	public JDBCParams getJDBCParams() {
		JDBCParams params = new JDBCParams();
		params.setDbDriver(properties.getProperty(DB_DRIVER));
		params.setUsername(properties.getProperty(DB_USERNAME));
		params.setPassword(properties.getProperty(DB_PASSWORD));
		params.setDbUrl(properties.getProperty(DB_ADDRESS));
		return params;
	}
}



