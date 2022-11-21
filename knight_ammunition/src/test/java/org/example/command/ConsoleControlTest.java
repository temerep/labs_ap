package org.example.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleControlTest{
    ConsoleControl consoleControl = new ConsoleControl();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void setCommand() {
        consoleControl.setCommand(0,new CraftAmmunitionCommand(null, null));
        assertTrue(consoleControl.commands.get(0) instanceof CraftAmmunitionCommand);
    }

    @Test
    void buttonWasPushed() {
    }
}