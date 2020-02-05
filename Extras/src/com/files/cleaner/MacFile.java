package com.files.cleaner;

import java.io.File;

public class MacFile {
	
	private File file;
	
	private String humanReadableSizeCount;
	
	public MacFile(File file) {
		this.file = file;
	}
	public MacFile(File file, String size) {
		this.file = file;
		this.humanReadableSizeCount = size;
	}

	public File getFile() {
		return file;
	}

	public String getHumanReadbleSizeCount() {
		if (humanReadableSizeCount == null) {
			humanReadableSizeCount = humanReadableByteCount(this.file.length(), true);
		}
		return humanReadableSizeCount;
	}
	
	public String getAbsolutePath() {
		return this.file.getAbsolutePath();
	}
	
	public long sizeInBytes() {
		return this.file.length();
	}
	
	private String humanReadableByteCount(long bytes, boolean si) {
	    int unit = si ? 1000 : 1024;
	    if (bytes < unit) return bytes + " B";
	    int exp = (int) (Math.log(bytes) / Math.log(unit));
	    String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + "";//(si ? "" : "i");
	    return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}
}
