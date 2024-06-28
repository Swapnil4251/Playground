package com.practice.patterns;

public class HorizontalDiamondPrint {

    public static void main(String[] args) {
        int n = 10;

        for (int i = 0, spaces = 0, direction = 1; i < n; i++, spaces += direction) {
            System.out.println(" ".repeat(spaces) + "*");
            if (spaces == 2 || (spaces == 0 && i != 0)) direction *= -1;
        }

        //printHorizontalDiamond(n);
    }

    private static void printHorizontalDiamond(int n) {
        int spaces = 0;
        boolean isIncreasing = true;
        for (int i = 0; i < n; i++) {
            System.out.println(" ".repeat(spaces) + "*");
            if (spaces == 2) {
                isIncreasing = false;
            } else if (spaces == 0) {
                isIncreasing = true;
            }
            spaces += isIncreasing ? 1 : -1;
        }
    }
}
