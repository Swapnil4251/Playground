/**
 * 
 */
package com.practice.zensar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author swapnilsarwade
 *
 */
public class AccountSummary {

	public static final String NEW_LINE = "\n";
	public static final String START_CHAR = "03";
	public static final String END_CHAR = "49";
	public static final String BASE_PATH = "/Users/swapnilsarwade/Desktop/Java Programs/";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try (BufferedReader numBr = new BufferedReader(new FileReader(BASE_PATH + "numbers.csv")); 
				BufferedReader accBr = new BufferedReader(new FileReader(BASE_PATH + "test.csv"))) {
			
			String line = numBr.readLine();
			if (line != null) {
				String[] accNums = line.split(",");
				
				String accLine = accBr.readLine();
				while (accLine != null) {
					for (String num : accNums) {
						if (accLine.contains(num) && accLine.startsWith(START_CHAR)) {
							captureRecords(accBr, accLine);
						}
					}
					
					accLine = accBr.readLine();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	public static void captureRecords(BufferedReader accBr, String startLine) throws Exception {
		
		File file = new File(BASE_PATH + "result.csv");
		
		if (!file.exists()) {
			file.createNewFile();
		}
		
		try (FileWriter writer = new FileWriter(BASE_PATH + "result.csv", true)) {
			
			writer.append(startLine).append(NEW_LINE);
			String line = accBr.readLine();
			
			while(line != null) {
				writer.append(line).append(NEW_LINE);
				if (line.startsWith(END_CHAR)) {
					line = null;
				} else {
					line = accBr.readLine();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
