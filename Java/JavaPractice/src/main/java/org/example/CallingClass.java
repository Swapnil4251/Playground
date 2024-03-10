package org.example;

public class CallingClass {

    public void callMethod() {
        ReferencedClass.referencedMethod(1, "hello", new ReferencedClass.JavaType());
    }

}
