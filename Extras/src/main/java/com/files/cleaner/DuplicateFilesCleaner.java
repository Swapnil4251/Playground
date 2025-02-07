package com.files.cleaner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.app.util.FilesUtil;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;

public class DuplicateFilesCleaner {
	
	private static boolean moveFiles = false;
	private static final String REPORT_FILE_NAME = "DuplicateFilesReport.txt";
	public static final String BASE_PATH = "/Users/swapnilsarwade/Downloads/Redmi Note 4/";
	public static final ImmutableSet<String> EXPECTED_DUPLICATE_FOLDERS = ImmutableSet.<String> builder()
			.add(BASE_PATH + "20180305/")
			.add(BASE_PATH + "BackUp/")
			.build();
	
	public static void main(String[] args) {
		try {
				
			Map<String, List<File>> result = new HashMap<String, List<File>>();
			FilesUtil.identifyDuplicateFiles(result, BASE_PATH);
			System.out.println("Unique files: " + result.size());
			
			StringBuilder reportContent = new StringBuilder();
			reportContent.append("--------------------------------------------------------------------------------------------------\n");
			reportContent.append("---------------------------------> DUPLICATE FILES SUMMARY <--------------------------------------\n");
			reportContent.append("--------------------------------------------------------------------------------------------------\n");
			Builder<MacFile> builder = ImmutableSet.<MacFile> builder();
			Builder<MacFile> builder2 = ImmutableSet.<MacFile> builder();
			Builder<MacFile> builder3 = ImmutableSet.<MacFile> builder();
			
			int totalFiles = result.values().stream().mapToInt(l -> l.size()).sum();
			System.out.println("Total fies :  " + totalFiles);
			
			result.forEach((s, l) -> {
				if (l.size() > 1) {
					MacFile fileToKeep = new MacFile(getPossibleFileToKeep(l).get());
					builder.add(fileToKeep);
					List<MacFile> filesToRemove = l.stream().filter(f -> !f.getAbsolutePath().equals(fileToKeep.getAbsolutePath())).map(f -> new MacFile(f)).collect(Collectors.toList());
					builder2.addAll(filesToRemove);
					reportContent.append("\nFile name : " + s + " \n");
					reportContent.append("\tFile to keep : " + fileToKeep.getAbsolutePath() + "; Size -> " + fileToKeep.getHumanReadbleSizeCount() + "\n");
					reportContent.append("\tDuplicate files : " + filesToRemove.size() + "\n" );
					filesToRemove.forEach(f -> reportContent.append("\t\t" + f.getAbsolutePath() + "; Size -> " + f.getHumanReadbleSizeCount() + "\n"));
				} else {
					builder3.add(new MacFile(l.get(0)));
				}
			});
			
			ImmutableSet<MacFile> filesToKeep = builder.build();
			ImmutableSet<MacFile> filesToRemove = builder2.build();
			ImmutableSet<MacFile> singleFiles = builder3.build();
			
			long keptBytes = filesToKeep.stream().mapToLong(f -> f.sizeInBytes()).sum();
			long removedBytes = filesToRemove.stream().mapToLong(f -> f.sizeInBytes()).sum();
			long noDuplicateBytes = singleFiles.stream().mapToLong(f -> f.sizeInBytes()).sum();
			long totalBytes = keptBytes + removedBytes + noDuplicateBytes;
			
			reportContent.append("\n\n");
			reportContent.append("Total files present : " + totalFiles + " (" + humanReadableByteCount(totalBytes, true) + ")\n\n");
			reportContent.append("\tNo duplicate files : " + singleFiles.size() + " (" + humanReadableByteCount(noDuplicateBytes, true) + ")\n");
			reportContent.append("\tTotal files to keep : " + filesToKeep.size() + " (" + humanReadableByteCount(keptBytes, true) + ")\n");
			reportContent.append("\tTotal files to delete : " + filesToRemove.size() + " (" + humanReadableByteCount(removedBytes, true) + ")\n\n");
			
			if (moveFiles) {
				//moveFiles(reportContent, filesToRemove);
			}
			reportContent.append("--------------------------------------------------------------------------------------------------\n");
			reportContent.append("------------------------------> DUPLICATE FILES SUMMARY - End <-----------------------------------\n");
			reportContent.append("--------------------------------------------------------------------------------------------------\n");
			
			writeReportToFile(reportContent.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static void moveFiles(StringBuilder reportContent, ImmutableSet<MacFile> filesToRemove)
			throws IOException, InterruptedException {
		for (MacFile f : filesToRemove) {
			try {
				java.nio.file.Path source = f.getFile().toPath();
				java.nio.file.Path target = Paths.get("/Users/swapnilsarwade/Downloads/ToBeDeleted/" + f.getFile().getName());
				Files.move(source, target);
				Thread.sleep(100);
				reportContent.append("File moved from Source : " + source + " -> Target : " + target + "\n");
				System.out.println("File moved from Source : " + source + " -> Target : " + target);
			} catch (FileAlreadyExistsException e) {
				java.nio.file.Path source = f.getFile().toPath();
				java.nio.file.Path target = Paths.get("/Users/swapnilsarwade/Downloads/ToBeDeleted/" + Calendar.getInstance().getTimeInMillis() + f.getFile().getName());
				Files.move(source, target);
				Thread.sleep(100);
				reportContent.append("File moved wih Source : " + source + " -> Target : " + target + "\n");
				System.out.println("File moved with Source : " + source + " -> Target : " + target);
			} catch (Exception e) {
				reportContent.append("Failed to delete : " + f.getAbsolutePath() + "\n");
				System.out.println("Failed to delete : " + f.getAbsolutePath());
				e.printStackTrace();
			}
		}
	}
	
	public static void writeReportToFile(String reportContent) throws IOException {
		FileWriter writer = null;
		File report = new File(BASE_PATH + REPORT_FILE_NAME);
		if (!report.exists()) {
			if (!report.createNewFile())
				return;
		}
		try {
			writer = new FileWriter(report);
			writer.write(reportContent);
			
			System.out.println("Report file written successfully");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
	
	public static Optional<File> getPossibleFileToKeep(List<File> files) {
		if (files.size() > 1) {
			if (files.stream().anyMatch(getPredicateToMatchFolder("Camera"))) 
				return files.stream().filter(getPredicateToMatchFolder("Camera")).findFirst();
			if (files.stream().anyMatch(getPredicateToMatchFolder("BackUp"))) 
				return files.stream().filter(getPredicateToMatchFolder("BackUp")).findFirst();
		}
		return Optional.of(files.get(0));
	}

	private static Predicate<File> getPredicateToMatchFolder(String folder) {
		return f -> {
			String filePath = f.getAbsolutePath().replace(BASE_PATH, "");
			int i = filePath.indexOf("/");
			if (i > 0) {
				filePath = filePath.substring(0, i);
			}
			return folder.equalsIgnoreCase(filePath);
		};
	}
	
	public static String humanReadableByteCount(long bytes, boolean si) {
	    int unit = si ? 1000 : 1024;
	    if (bytes < unit) return bytes + " B";
	    int exp = (int) (Math.log(bytes) / Math.log(unit));
	    String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + "";//(si ? "" : "i");
	    return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}
}
