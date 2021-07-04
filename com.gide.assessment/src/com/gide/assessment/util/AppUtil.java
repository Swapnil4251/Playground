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

	public static int compareFilesizes(Object e1, Object e2) {
		if (e1 == null || e2 == null) {
			return 0;
		}
		File o1 = (File) e1;
		File o2 = (File) e2;
		if (o1.isDirectory()) {
			return Integer.MIN_VALUE;
		}
		if (o2.isDirectory()) {
			return Integer.MAX_VALUE;
		}
		return (int) (o1.length() - o2.length());
	}
	
}
