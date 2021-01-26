package com.practice.hackerrank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

class Result {

    /*
     * Complete the 'countingValleys' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER steps
     *  2. STRING path
     */

    public static int countingValleys(int steps, String path) {
        int positionOfHiker = 0;
        boolean atSeaLevel = true;
        int numberOfValleys = 0;
        for (int i = 0; i < steps; i++) {
            if (path.charAt(i) == 'U') {
                positionOfHiker++;
            } else if (path.charAt(i) == 'D') {
                positionOfHiker--;
            }
            
            if (positionOfHiker < 0 && atSeaLevel) numberOfValleys++;
                
            atSeaLevel = positionOfHiker == 0;
        }
        return numberOfValleys;
    }

}

public class CountingValleys {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int steps = Integer.parseInt(bufferedReader.readLine().trim());

        String path = bufferedReader.readLine();

        int result = Result.countingValleys(steps, path);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
