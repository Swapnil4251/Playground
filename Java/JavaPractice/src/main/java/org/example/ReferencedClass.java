package org.example;

public class ReferencedClass {

    public static String referencedMethod(int number, String string, JavaType type) {
        System.out.println("Inside org.example.ReferencedClass.referencedMethod(String)");
        return "MethodCalled";
    }

    public static class JavaType {
        String field;
    }
}
