package com.gide.assessment.logic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.gide.assessment.util.AppUtil;

public class FileBrowsingModel extends ModelObject {

	private String rootDirectory;
	private List<File> files;
	private String searchText;
	private static final Logger logger = Logger.getLogger(FileBrowsingModel.class);
	
	public FileBrowsingModel(String rootDirectory) {
		this.rootDirectory = rootDirectory;
		init();
	}

	private void init() {
		try {
			this.files = Files.list(Paths.get(getRootDirectory())).map(p -> p.toFile()).collect(Collectors.toList());
		} catch (IOException e) {
			logger.error("Error loading files from path : " + this.rootDirectory);
		} 
	}
	
	public Object[] browseFiles(String folderPath) {
		return browseFiles(folderPath, this.getSearchText());
	}
	
	public Object[] browseFiles(String folderPath, String filterPattern) {
		Object[] tFiles = null;
		try {
			Stream<File> stream = Files.list(Paths.get(folderPath)).map(p -> p.toFile());
			if (StringUtils.isNotBlank(filterPattern)) {
				stream = stream.filter(f -> f.getName().contains(filterPattern));
			}
			stream = stream.sorted((o1, o2) -> AppUtil.compareFilesizes(o1, o2));
			this.files = stream.collect(Collectors.toList());
			tFiles = this.files.toArray();
		} catch (Exception e) {
			logger.error("Error loading files from the path : " + folderPath);
		}
		return tFiles;
	}
	
	public Object[] filterFiles(String filter) {
		return this.browseFiles(getRootDirectory(), filter);
	}
	
	public List<File> getFiles() {
		return files;
	}
	
	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
		firePropertyChange("searchText", getSearchText(), searchText);
	}
	
	public String getRootDirectory() {
		return rootDirectory;
	}

	public void setRootDirectory(String rootDirectory) {
		this.rootDirectory = rootDirectory;
	}
}
