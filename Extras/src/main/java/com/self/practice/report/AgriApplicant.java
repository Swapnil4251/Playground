package com.self.practice.report;

import com.self.practice.report.constants.ExamResult;

public class AgriApplicant extends Applicant {
	
	public AgriApplicant(String id, int marks) {
		super(id, marks);
	}
	
	public AgriApplicant(String id, int marks, int totalMarks, ExamResult result) {
		super(id, marks, totalMarks, result);
	}

}
