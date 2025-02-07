package com.app.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AppUtil {

	public static Object getObjectFromPath(Object json, String path) {
		Object value = null;
		if (json instanceof JSONObject) {
			value = getValueFromJson((JSONObject) json, path);
		} else if (json instanceof JSONArray) {
			value = getValueFromJsonArray((JSONArray) json, path);
		}
		return value;
	}

	public static Object getValueFromJsonArray(JSONArray json, String path) {

		Object value = null;

		if (!isNullOrEmpty(path)) {
			if (path.contains(".") || (path.contains("[") && path.contains("]"))) {
				String index = path.substring(0, path.indexOf("."));
				value = getObjectFromPath(json.get(Integer.parseInt(index)), path.substring(path.indexOf(".") + 1));
			} else {
				value = json.get(Integer.parseInt(path));
			}
		}
		return value;
	}

	public static Object getValueFromJson(JSONObject json, String path) {
		Object value = null;
		if (!isNullOrEmpty(path)) {
			if (path.contains(".") || (path.contains("[") && path.contains("]"))) {

				String key, valuePath = path;

				if (path.contains("[") && (!path.contains(".") || path.indexOf("[") < path.indexOf("."))) {
					key = path.substring(0, path.indexOf("["));
					valuePath = path.substring(path.indexOf("[") + 1, path.indexOf("]"))
							+ path.substring(path.indexOf("]") + 1);
				} else {
					key = path.substring(0, path.indexOf("."));
					valuePath = path.substring(path.indexOf(".") + 1);
				}
				value = getObjectFromPath(json.get(key), valuePath);
			} else {
				value = json.get(path);
			}
		}
		return value;
	}

	public static boolean isNullOrEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}

	public static boolean isNumber(String value) {
		try {
			Integer.parseInt(value);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public static String readPdfDocument(String filePath) {

		try (PDDocument document = PDDocument.load(new File(filePath))) {

			document.getClass();

			if (!document.isEncrypted()) {

				PDFTextStripperByArea stripper = new PDFTextStripperByArea();
				stripper.setSortByPosition(true);
				
				return new PDFTextStripper().getText(document);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}
	
	public static String formatMessage(String messageText, Map<String, String> params) {
		if (messageText.contains("{") && messageText.contains("}")) {
			for (int i = 0; i < messageText.length();) {
				int start = messageText.indexOf('{', i);
				int end = messageText.indexOf('}', start);
				if (start >= 0 && end > start) {
					String key = messageText.substring(start+1, end);
					messageText = messageText.replaceAll("\\{" + key + "\\}", params.get(key));
					i = ++end;
				} else {
					break;
				}
			}
		}
		return messageText;
	}
	
	public static Map<?, ?> emptyMap() {
		return new HashMap<>();
	}
}
