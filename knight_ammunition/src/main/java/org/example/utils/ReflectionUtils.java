package org.example.utils;

public class ReflectionUtils {
    public static String getCurrentExecutingMethod() {
        return new Throwable().getStackTrace()[1].getMethodName();
    }
}
