package com.practice.entwicklerheld;

import java.util.Map;

final class WordScore {
    private final String word;
    private static final Map<String, Integer> SCORES = Map.of("aeioulnrst", 1,
    "dg", 2, "bcmp", 3, "fhvwy", 4, "k", 5, "jx", 8, "qz", 10);

    WordScore(String word) {
        this.word = word;
    }

    public static void main(String[] args) {
        WordScore ws = new WordScore("Hello");
        System.out.println(ws.getScore());
        WordScore ws2 = new WordScore("aeioulnrstqz");
        System.out.println(ws2.getScore());
    }

    int getScore() {
        int score = 0;
        char[] ch_array = word.toLowerCase().toCharArray();
        for (char ch: ch_array)  {
            score += SCORES.entrySet().stream()
                .filter(e -> e.getKey().contains(String.valueOf(ch)))
                .map(e -> e.getValue()).findFirst().orElse(0);
        }
        return score;
    }
}