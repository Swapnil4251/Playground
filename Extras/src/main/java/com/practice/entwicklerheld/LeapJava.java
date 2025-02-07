package com.practice.entwicklerheld;

public class LeapJava {

    static boolean isLeapYear(int year) {
        boolean isLeap = false;
        boolean isDivisibleBy4 = year % 4 == 0;
        boolean isDivisibleBy100 = year % 100 == 0;
        boolean isDivisibleBy400 = year % 400 == 0;

        if (isDivisibleBy4) {
            if (isDivisibleBy100 && isDivisibleBy400) {
                isLeap = true;
            } else {
                isLeap = true;
            }
        }
        return isLeap;
    }

    public static void main(String[] args) {
        Integer year = Integer.valueOf(args[0]);
        System.out.println(isLeapYear(year));
    }
}