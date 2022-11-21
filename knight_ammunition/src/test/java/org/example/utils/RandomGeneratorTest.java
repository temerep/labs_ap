package org.example.utils;

import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomGeneratorTest {

    @Test
    void generateString() {
        RandomGenerator.generateString();
        assertTrue(true);
    }

    @Test
    void generateDouble() {
        RandomGenerator.generateDouble();
        assertTrue(true);
    }

    @Test
    void generateVisionLevel() {
        RandomGenerator.generateVisionLevel();
        assertTrue(true);
    }
}