package com.cisco.ca.cstg.pdi.utils;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.ca.cstg.pdi.utils.PdiConfig;

public final class PdiConfig {

	public static final String CONFIG_FILE_NAME = "pdi.properties";
	private static Configuration config = null;
	private static boolean configLoaded = false;

	private static final Logger LOGGER = LoggerFactory.getLogger(PdiConfig.class);

	/**
	 * This is a utility class, so constructor is private.
	 */
	private PdiConfig() {
		super();
	}
	
	static {
		loadproperties();
	}

	public static String getProperty(String prop) {
		if (configLoaded) {
			return (config.getString(prop));
		} else {
			return null;
		}
	}

	public static String getProperty(String prop, String def) {
		if (configLoaded) {
			return (config.getString(prop, def));
		} else {
			return def;
		}
	}

	public static void loadproperties() {
		try {
			config = new PropertiesConfiguration(CONFIG_FILE_NAME);
			configLoaded = true;
		} catch (ConfigurationException e) {
			LOGGER.error("Loading configuration information", e);
			configLoaded = false;
		}
	}

	public static Configuration getConfig() {
		return config;
	}

	public static void setConfig(Configuration config) {
		PdiConfig.config = config;
	}

	public static boolean saveConfig() throws ConfigurationException {
		boolean returnValue = true;
		if (configLoaded && config instanceof PropertiesConfiguration) {
			FileWriter fileWriter = null;
			try {
				fileWriter = new FileWriter(CONFIG_FILE_NAME);
				((PropertiesConfiguration) config).save(fileWriter);
			} catch (IOException e) {
				LOGGER.error(e.getMessage(), e);
				returnValue = false;
			} finally {
				if(fileWriter != null) {
					try {
						fileWriter.close();
					} catch (IOException e) {
						LOGGER.info("exception in saveConfig method.",e);
					}
				}
			}
		}
		return returnValue;
	}

}
