package org.example;

import org.example.command.*;
import org.example.controller.AmmunitionController;
import org.example.dao.AmmunitionDao;
import org.example.entity.AmmunitionFactory;
import org.example.entity.AmmunitionFactoryImpl;
import org.example.service.AmmunitionService;
import org.example.singleton.ScannerSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(AmmunitionService.class.getName());

    public static void main(String[] args) {
        ConsoleControl consoleControl = setupConsoleControl();
        start(consoleControl);

    }

    private static void start(ConsoleControl consoleControl) {
        Scanner scanner = ScannerSingleton.INSTANCE.getScanner();
        while (true) {
            consoleControl.buttonWasPushed(scanner.nextInt());
        }
    }

    public static ConsoleControl setupConsoleControl() {
        ConsoleControl consoleControl = new ConsoleControl();
        AmmunitionFactory ammunitionFactory = new AmmunitionFactoryImpl();
        AmmunitionController ammunitionController = new AmmunitionController(new AmmunitionService(new AmmunitionDao<>()));
        ExitProgramCommand exitProgramCommand = new ExitProgramCommand(ammunitionController);
        CraftAmmunitionCommand craftAmmunitionCommand = new CraftAmmunitionCommand(ammunitionController, ammunitionFactory);
        DeleteAmmunitionCommandById deleteAmmunitionCommandById = new DeleteAmmunitionCommandById(ammunitionController);
        PrintAllAmmunitionInCostRangeCommand printAllAmmunitionInCostRangeCommand = new PrintAllAmmunitionInCostRangeCommand(ammunitionController);
        PrintAllAmmunitionSortedByWeightCommand printAllAmmunitionSortedByWeightCommand = new PrintAllAmmunitionSortedByWeightCommand(ammunitionController);
        PrintTotalAmmunitionCommand printTotalAmmunitionCommand = new PrintTotalAmmunitionCommand(ammunitionController);
        PrintTotalAmmunitionCostCommand printTotalAmmunitionCostCommand = new PrintTotalAmmunitionCostCommand(ammunitionController);
        PrintAmmunitionByIdCommand printAmmunitionByIdCommand = new PrintAmmunitionByIdCommand(ammunitionController);
        consoleControl.setCommand(0, exitProgramCommand);
        consoleControl.setCommand(1, printTotalAmmunitionCommand);
        consoleControl.setCommand(2, deleteAmmunitionCommandById);
        consoleControl.setCommand(3, printAmmunitionByIdCommand);
        consoleControl.setCommand(4, craftAmmunitionCommand);
        consoleControl.setCommand(5, printTotalAmmunitionCostCommand);
        consoleControl.setCommand(6, printAllAmmunitionSortedByWeightCommand);
        consoleControl.setCommand(7, printAllAmmunitionInCostRangeCommand);
        ammunitionController.initView();
        return consoleControl;
    }
}