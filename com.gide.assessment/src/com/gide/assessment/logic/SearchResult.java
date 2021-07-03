package com.gide.assessment.logic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SearchResult {

	private File file;
	private List<String> lines;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public List<String> getLines() {
		if (lines == null) {
			lines = new ArrayList<>();
		}
		return lines;
	}

	public void setLines(List<String> lines) {
		this.lines = lines;
	}
}
