//App.java

package org.example;

import org.example.command.*;
import org.example.controller.AmmunitionController;
import org.example.dao.AmmunitionDao;
import org.example.entity.AmmunitionFactory;
import org.example.entity.AmmunitionFactoryImpl;
import org.example.service.AmmunitionService;
import org.example.singleton.ScannerSingleton;

import java.util.Scanner;

public class App {

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

//AmmunitionController.java

package org.example.controller;

import org.example.entity.Ammunition;
import org.example.service.AmmunitionService;
import org.example.utils.ReflectionUtils;
import org.example.view.AmmunitionView;

import java.util.List;

public class AmmunitionController {
    AmmunitionService ammunitionService;
    AmmunitionView ammunitionView;

    public AmmunitionController(AmmunitionService ammunitionService) {
        this.ammunitionService = ammunitionService;
        ammunitionView = new AmmunitionView(this, ammunitionService);
    }

    public void initView() {
        LOGGER.info("App is started");
        ammunitionView.initView();
    }

    public void updateView() {
        ammunitionView.updateView();
    }


    public void findAll()  {
        try {
            ammunitionView.printAmmunition(ammunitionService.findAll());
        } catch (Exception e) {
           // throw new RuntimeException(e);
        }
    }

    public void findEntityById(int id)  {
        try {
            Ammunition ammunitionById = ammunitionService.findEntityById(id);
            ammunitionView.printAmmunitionById(id, ammunitionById);
        } catch (java.lang.Exception e) {
            // throw new RuntimeException(e);
        }
    }

    public void delete(Ammunition ammunition){
        try {
            ammunitionService.delete(ammunition);
            ammunitionView.printDeleted(ammunition);
        } catch (Exception e) {
            // throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try {
            Ammunition entityById = ammunitionService.findEntityById(id);
            ammunitionService.delete(id);
            ammunitionView.printDeleted(entityById);
        } catch (java.lang.Exception e) {
            // throw new RuntimeException(e);
        }
    }

    public void create(Ammunition ammunition) {
        try {
            ammunitionService.create( ammunition);
            ammunitionView.printCreated(ammunition);
        } catch (Exception e) {
            // throw new RuntimeException(e);
        }
    }


    public void computeTotalEquippedAmmunitionCost() {
        try {
            double l = ammunitionService.computeTotalEquippedAmmunitionCost();
            ammunitionView.printTotalEquippedAmmunitionCost(l);
        } catch (java.lang.Exception e) {
            // throw new RuntimeException(e);
        }
    }

    public void getAmmunitionListSortedByWeight() {
        try {
            List<Ammunition> ammunitionListSortedByWeight = ammunitionService.getAmmunitionListSortedByWeight();
            ammunitionView.printAmmunitionSortedByWeight(ammunitionListSortedByWeight);
        } catch (java.lang.Exception e) {
            // throw new RuntimeException(e);
        }
    }

    public void getAmmunitionListInCostRange(long inclusiveStart, long inclusiveEnd) {
        try {
            List<Ammunition> ammunitionListInCostRange = ammunitionService.getAmmunitionListInCostRange(inclusiveStart, inclusiveEnd);
            ammunitionView.printAmmunitionInCostRange(ammunitionListInCostRange, inclusiveStart,inclusiveEnd);
        } catch (java.lang.Exception e) {
            // throw new RuntimeException(e);
        }
    }
}

//AmmunitionView.java

package org.example.view;

import org.example.command.ICommand;
import org.example.controller.AmmunitionController;
import org.example.entity.Ammunition;
import org.example.service.AmmunitionService;

import java.util.List;

public class AmmunitionView {
    AmmunitionController ammunitionController;
    AmmunitionService ammunitionService;

    public AmmunitionView(AmmunitionController ammunitionController, AmmunitionService ammunitionService) {
        this.ammunitionController = ammunitionController;
        this.ammunitionService = ammunitionService;
        //initView();
    }

    public void initView() {
        System.out.println("<<< Knight equipment management system >>>");
        updateView();
    }

    public void updateView() {
        StringBuilder stringBuilder = new StringBuilder()
                .append("1 -> Print all ammunition\n")
                .append("2 -> Delete ammunition by ID\n")
                .append("3 -> Print ammunition by ID\n")
                .append("4 -> Craft ammunition\n")
                .append("5 -> Print total ammunition cost\n")
                .append("6 -> Print all ammunition sorted by weight\n")
                .append("7 -> Print all ammunition in cost range\n")
                .append("0 -> Exit program\n");
        System.out.println(stringBuilder);
    }

    public void printAmmunition(List<Ammunition> ammunitionList) {
        System.out.println("All equipped ammunition: " + ammunitionList);
    }

    public void executeCommand(ICommand command) throws Exception {
        command.execute();
    }

    public void printDeleted(Ammunition ammunition) {
        System.out.println(ammunition + "is deleted");
    }

    public void printAmmunitionById(int id, Ammunition ammunitionById) {
        System.out.println("Found " + ammunitionById + " by id=" + id);
    }

    public void printCreated(Ammunition ammunition) {
        System.out.println(ammunition + " is created");
    }

    public void printTotalEquippedAmmunitionCost(double cost) {
        System.out.println("Total equipped ammunition cost = " + cost);
    }

    public void printAmmunitionSortedByWeight(List<Ammunition> ammunitionListSortedByWeight) {
        System.out.println("All ammunition sorted by weight: " + ammunitionListSortedByWeight);
    }

    public void printAmmunitionInCostRange(List<Ammunition> ammunitionListInCostRange, long inclusiveStart, long inclusiveEnd) {
        String stringBuilder = "All ammunition in range [" +
                inclusiveStart +
                ":" +
                inclusiveEnd +
                "] by cost value:\n" +
                ammunitionListInCostRange;
        System.out.println(stringBuilder);
    }
}

