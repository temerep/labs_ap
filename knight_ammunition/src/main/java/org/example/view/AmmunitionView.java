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
