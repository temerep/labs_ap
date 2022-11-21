package org.example.singleton;

import java.util.Scanner;

public enum ScannerSingleton {
    INSTANCE;
    private final Scanner scanner;
    ScannerSingleton() {
        scanner = new Scanner(System.in);
    }
    public static ScannerSingleton getInstance()
    {
        return INSTANCE;
    }

    public Scanner getScanner() {
        return scanner;
    }
}
