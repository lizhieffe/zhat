package com.zhat.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * read the src/main/resources/application.properties file
 */
public class AppProperties {
	
	private static AppProperties instance;
	private Map<String, String> map = new HashMap<String, String>();
	
	private AppProperties() {
		init();
	}
	
	synchronized public static AppProperties getInstance() {
		if (instance == null)
			instance = new AppProperties();
		return instance;
	}
	
	private void init() {
		String fileName = "application.properties";
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream in = classLoader.getResourceAsStream(fileName);
		Scanner scanner = new Scanner(in);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] parts = line.split("=");
			if (parts.length != 2)
				continue;
			map.put(parts[0].replace(" ", ""), parts[1].replace(" ", ""));
		}
		scanner.close();
	}
	
	public static String get(String key) {
		return AppProperties.getInstance().map.get(key);
	}
}
