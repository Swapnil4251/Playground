package com.self.practice.report;

public class Applicant implements Comparable<Integer> {

	private String id;
	private int marks;
	private int totalMarks;
	private double percent;
	private ExamResult result;
	
	public Applicant(String id, int marks) {
		this.id = id;
		this.marks = marks;
	}
	
	public Applicant(String id, int marks, int totalMarks, ExamResult result) {
		this.id = id;
		this.marks = marks;
		this.totalMarks = totalMarks;
		this.result = result;
		computePercentage();
	}
	
	private double computePercentage() {
		double percentage = (this.getMarks() * 100) / this.getTotalMarks();
		this.setPercent(percentage);
		return percentage;
	}
	
	public enum ExamResult {
		PASSED,
		DISQUALIFIED,
		QUALIFIED,
		FAILED;
	}
	
	@Override
	public int compareTo(Integer arg0) {
		return this.getMarks() - arg0;
	}

	@Override
	public String toString() {
		return this.getId() + "@" + this.getMarks();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	public ExamResult getExamResult() {
		return result;
	}

	public void setExamResult(ExamResult result) {
		this.result = result;
	}
}
