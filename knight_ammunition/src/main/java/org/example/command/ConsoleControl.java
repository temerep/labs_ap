package org.example.command;

import ch.qos.logback.classic.Logger;
import org.example.service.AmmunitionService;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConsoleControl {
    public static final int NUMBER_OF_COMMANDS = 10;
    List<AbstractCommand> commands;
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ConsoleControl.class.getName());

    public ConsoleControl() {
        commands = new ArrayList<>(Collections.nCopies(NUMBER_OF_COMMANDS, new EmptyCommand(null)));
    }

    public void setCommand(int slot, AbstractCommand command) {
        commands.set(slot, command);
    }

    public void buttonWasPushed(int slot) {
        try {
            commands.get(slot).execute();
            commands.get(slot).ammunitionController.updateView();
        } catch (Exception e) {
            LOGGER.warn("Incorrect input. Try again");
        }

    }
}
