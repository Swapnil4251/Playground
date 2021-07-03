package com.gide.assessment.messages;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class Messages {

	private static String BUNDLE_NAME = "com.gide.assessment.messages.Messages";
	private static ResourceBundle bundle = PropertyResourceBundle.getBundle(BUNDLE_NAME);
	private static final Logger logger = Logger.getLogger(Messages.class);
	
	public static String getProperty(String property) {
		try {
			return bundle.getString(property);
		} catch (MissingResourceException e) {
			logger.warn("Missing resource property: " + property);
			return property;
		}
	}
	
	public static String getProperty(String property, Object...values) {
		try {
			return MessageFormat.format(bundle.getString(property), values);
		} catch (MissingResourceException e) {
			logger.warn("Missing resource property: " + property);
			return property;
		}
	}
}
