package com.self.practice.report;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableList;
import com.practice.zensar.AppUtil;
import com.self.practice.report.constants.ExamResult;

public class PredictQualificationRange {

	public static final String FILE_PATTERN = "/Users/swapnilsarwade/Desktop/Java Programs/Agri_scoreList_{0}.pdf";
	public static final String NEW_LINE_PATTERN = "\\r?\\n";
	public static final int OUT_OF_MARKS = 150;
	
	public static void main(String[] args) {
		
		String docText = "";
		for (int i = 1; i <= 4; i++) {
			docText = docText + NEW_LINE_PATTERN + AppUtil.readPdfDocument(MessageFormat.format(FILE_PATTERN, i));
		}
		
		List<AgriApplicant> listOfApplicants = prepareObtainedMarksMap(docText);

		Optional<AgriApplicant> topper = getHighestMarks(listOfApplicants);
		System.out.println(topper);
		
		System.out.println("++++++++++++++++++++++");
		List<AgriApplicant> top10 = getTopResults(listOfApplicants, 18);
		top10.forEach(t -> System.out.println(t));
		System.out.println("++++++++++++++++++++++");
		
		Double possibility = possibilityOfGettingQualified(listOfApplicants, "AGRICLR_KS_0041106");
		
		System.out.println("Probability that you will get qualified : " + possibility + " i.e. " + Math.round(possibility * 100) + "%");
	}
	
	public static AgriApplicant getApplicantById(List<AgriApplicant> listOfApplicants, String applicantId) {
		return listOfApplicants.stream().filter(apl -> applicantId.equals(apl.getId())).findFirst().orElse(null);
	}
	
	public static Double possibilityOfGettingQualified(List<AgriApplicant> listOfApplicants, String applicantId) {
		
		AgriApplicant applicant = getApplicantById(listOfApplicants, applicantId);
		
		double sum = listOfApplicants.stream().mapToDouble(apl -> apl.getPercent()).sum();
		double mean = sum / listOfApplicants.size();
		
		double standardDeviation = calculateStandardDeviation(listOfApplicants);
		
		return (applicant.getPercent() - mean) / standardDeviation;
	}
	
	public static double calculateStandardDeviation(List<AgriApplicant> listOfApplicants) {
		
		System.out.println("Calculating Standard deviation...");
		
		final int count = listOfApplicants.size();
		
		// Step 1 : Calculate mean (μ greek letter "mu")
		double sum = listOfApplicants.stream().mapToDouble(apl -> apl.getPercent()).sum();
		final double mean = sum / count;
		
		System.out.println("Step 1 : calculate mean (sum / count) i.e. (" + sum + " / " + count + ") = " + mean );
		
		// Step 2 : Then for each number: subtract the Mean and square the result
		// This is the part of the formula that says:(X(i) - μ)^2 (sutract mean from each element and square) 
		double sumOfPowers = listOfApplicants.stream().mapToDouble(apl -> {
			return Math.pow((apl.getPercent() - mean), 2);
		})
		// Step 3 : To work out the mean, add up all the values then divide by how many.
		.sum();
		
		double meanOfSquaredDifferences = sumOfPowers / count;

		System.out.println("Step 2  & 3 : Subtract the mean and square the result then add up all values and divide by count.\n"
				+ " SUM(( Xi - μ ) ^ 2) / count) i.e. (" + sumOfPowers + " / " + count + ") = " + meanOfSquaredDifferences );
		
		// Step 4 : Take the square root of Mean of squared differences.
		double standardDeviation = Math.sqrt(meanOfSquaredDifferences);
		
		System.out.println("Step 4 : Take the square root of Mean of squared differences. Math.sqrt(" + meanOfSquaredDifferences + ") = " + standardDeviation);
		System.out.println("Result : Standard deviation = " + standardDeviation);
		
		return standardDeviation;
	}
	
	public static List<AgriApplicant> getTopResults(List<AgriApplicant> listOfApplicants, int limit) {
		Stream<AgriApplicant> sorted = listOfApplicants.stream().sorted((obj1, obj2) -> obj2.getMarks() - obj1.getMarks());
		return sorted.limit(limit).collect(Collectors.toList());
	}

	public static Optional<AgriApplicant> getHighestMarks(List<AgriApplicant> listOfApplicants) {
		return listOfApplicants.stream().max((obj1, obj2) -> obj1.getMarks() - obj2.getMarks());
	}

	public static List<AgriApplicant> prepareObtainedMarksMap(String docText) {
		ImmutableList.Builder<AgriApplicant> builder = ImmutableList.<AgriApplicant> builder();
		// split by whitespace
		String lines[] = docText.split(NEW_LINE_PATTERN);
		for (String line : lines) {
			if (!AppUtil.isNullOrEmpty(line) && line.startsWith("AGRICLR")) {
				String[] arr = line.split(" ");
				String id = arr[0];
				String result = arr[1].trim();
				int marks = 0;
				if (AppUtil.isNumber(result)) {
					marks = Integer.parseInt(result);
					result = "PASSED";
				}
				builder.add(new AgriApplicant(id.trim(), marks, OUT_OF_MARKS, ExamResult.valueOf(result)));
			}
		}
		
		return builder.build();
	}
}
