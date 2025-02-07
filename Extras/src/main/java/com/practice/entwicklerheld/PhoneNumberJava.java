package com.practice.entwicklerheld;

public class PhoneNumberJava {

    public static void main(String[] args) {
        PhoneNumberJava phoneNumber = new PhoneNumberJava("+1 (223) 256-7890");
        System.out.println(phoneNumber.getNumber());
    }

    private final String number;

    public PhoneNumberJava(String phoneNumber) {
        this.number = phoneNumber;
    }

    public String getNumber() {
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException("must not be fewer than 10 digits");
        }

        String numericString = extractNumericString(number);

        validateLengths(numericString);

        if (numericString.length() == 11) {
            numericString = numericString.substring(1);
        }

        validateAreaCode(numericString);
        validateExchangeCode(numericString);

        return numericString;
    }

    String extractNumericString(String number) {
        StringBuilder numericString = new StringBuilder();
        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (!isNumericCharacter(ch)) {
                if ((i == 0 || (numericString.length() == 0)) && ch == '+') {
                    continue;
                }
                if (!isValidCharacter(ch)) {
                    if (isLetter(ch)) {
                        throw new IllegalArgumentException("letters not permitted");
                    } else {
                        throw new IllegalArgumentException("punctuations not permitted");
                    }
                }
                continue;
            }
            numericString.append(ch);
        }
        return numericString.toString();
    }

    void validateLengths(String numericString) {
        if (numericString.length() < 10) {
            throw new IllegalArgumentException("must not be fewer than 10 digits");
        }

        if (numericString.length() == 11 && !numericString.startsWith("1")) {
            throw new IllegalArgumentException("11 digits must start with 1");
        }

        if (numericString.length() > 11) {
            throw new IllegalArgumentException("must not be greater than 11 digits");
        }
    }

    void validateAreaCode(String numericString) {
        char areaCode = numericString.charAt(0);
        if (areaCode == '0' || areaCode == '1') {
            throw new IllegalArgumentException(
                    "area code cannot start with " + (areaCode == '0' ? "zero" : "one"));
        }
    }

    void validateExchangeCode(String numericString) {
        char exchangeCode = numericString.charAt(3);
        if (exchangeCode == '0' || exchangeCode == '1') {
            throw new IllegalArgumentException(
                    "exchange code cannot start with " + (exchangeCode == '0' ? "zero" : "one"));
        }
    }

    boolean isNumericCharacter(char ch) {
        return ch >= 48 && ch <= 57;
    }

    boolean isValidCharacter(char ch) {
        return ch == ' ' // 32
                || ch == '(' // 40
                || ch == ')' // 41
                || ch == '-' // 45
                || ch == '.'; // 46
    }

    boolean isLetter(char ch) {
        return (ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122);
    }
}
