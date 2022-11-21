package org.example.singleton;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ScannerSingletonTest {
    ScannerSingleton scannerSingleton= ScannerSingleton.INSTANCE;
    @Test
    void getInstance() {
        Scanner scanner =ScannerSingleton.getInstance().getScanner();
        assertNotNull(scanner);
    }

    @Test
    void getScanner() {
        Scanner scanner =ScannerSingleton.getInstance().getScanner();
        assertNotNull(scanner);
    }
}