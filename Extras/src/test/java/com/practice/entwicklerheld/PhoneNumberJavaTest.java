package com.practice.entwicklerheld;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PhoneNumberJavaTest {

    @Test
    public void testPhoneNumber() {
        PhoneNumberJava phoneNumber = new PhoneNumberJava("+1 (223) 256-7890");
        assertThat(phoneNumber.getNumber(), equalTo("2232567890"));
    }

    @Test
    public void testInvalidPhoneNumber() {
        PhoneNumberJava phoneNumber = new PhoneNumberJava("123456789");
        Throwable exception = assertThrows(IllegalArgumentException.class, phoneNumber::getNumber);
        assertThat(exception.getMessage(), equalTo("must not be fewer than 10 digits"));

    }
}
