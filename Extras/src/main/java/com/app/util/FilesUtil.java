package com.app.util;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FilesUtil {

	public static Map<String, List<File>> identifyDuplicateFiles(Map<String, List<File>> result, String sourcePath) {
		try {
			File source = new File(sourcePath);
			if (!source.exists()) {
				return result;
			}
			
			if (source.isFile()) {
				if (!result.containsKey(source.getName())) {
					List<File> list = new ArrayList<File>();
					result.put(source.getName(), list);
				}
				result.get(source.getName()).add(source);
			} else if (source.isDirectory()) {
				DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(sourcePath));
				directoryStream.forEach(path -> identifyDuplicateFiles(result, path.toFile().getAbsolutePath()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;	
	}
}
