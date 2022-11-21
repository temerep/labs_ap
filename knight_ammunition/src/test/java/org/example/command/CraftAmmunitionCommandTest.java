package org.example.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CraftAmmunitionCommandTest extends AbstractCommandTest{

    @BeforeEach
    void setUp() {
        storage.add(helmet);
        storage.add(chestPlate);
    }

    @AfterEach
    void tearDown() {
        storage.clear();
        storage = null;
    }

    @Test
    void execute() {

    }
}