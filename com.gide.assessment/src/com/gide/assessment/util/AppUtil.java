package com.gide.assessment.util;

import java.io.File;

public class AppUtil {

	public static String getFileExtension(File file) {
		String extension = "";
		String fileName = file.getName();
		int index = fileName.lastIndexOf('.');
		if (index > 0) {
			extension = fileName.substring(index + 1);
		}
		return extension;
	}
	
}
