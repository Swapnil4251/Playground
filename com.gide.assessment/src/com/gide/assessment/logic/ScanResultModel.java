package com.gide.assessment.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;

import com.gide.assessment.util.AppUtil;

public class ScanResultModel {

	private String scanDirectory;
	private String searchText;
	private Map<String, SearchResult> scanResult = new HashMap<>();
	private static final Logger logger = Logger.getLogger(ScanResultModel.class);
	
	public ScanResultModel(String root) {
		this.scanDirectory = root;
	}
	
	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getScanDirectory() {
		return scanDirectory;
	}

	public void setScanDirectory(String scanDirectory) {
		this.scanDirectory = scanDirectory;
	}

	public Map<String, SearchResult> scanFiles(IProgressMonitor monitor, String searchText) {
		File rootFolder = new File(scanDirectory);
		this.searchText = searchText;
		int totalWork = rootFolder.list().length;
		monitor.beginTask("Scanning files...", totalWork);
		search(rootFolder, searchText, monitor);
		monitor.worked(totalWork);
		return scanResult;
	}

	private void search(File file, String searchText, IProgressMonitor monitor) {
		if (file.isDirectory()) {
			String[] files = file.list();
			monitor.subTask("Searching " + searchText + " in subfolder : " + file);
			
			for (int i = 0; i < files.length; i++) {
				try {
					File tempFile = new File(file, files[i]);
					monitor.subTask("Searching '" + searchText + "' in " + tempFile);
					search(tempFile, searchText, monitor);
					monitor.worked(i);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		} else if ("txt".equalsIgnoreCase(AppUtil.getFileExtension(file))) {
			searchInFile(file, searchText);
		}
	}
	
	private void searchInFile(File file, String searchText) {
		try (FileReader fr = new FileReader(file)) {
			BufferedReader br = new BufferedReader(fr);
			String line;
			String[] words = null;
			while ((line = br.readLine()) != null) {
				words = line.split(" ");
				for (String word : words) {
					if (word.equals(searchText)) {
						if (scanResult.containsKey(file.getAbsolutePath())) {
							scanResult.get(file.getAbsolutePath()).getLines().add(line);
						} else {
							SearchResult result = new SearchResult();
							result.setFile(file);
							result.getLines().add(line);
							scanResult.put(file.getAbsolutePath(), result);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public Map<String, SearchResult> getScanResult() {
		return scanResult;
	}
}
