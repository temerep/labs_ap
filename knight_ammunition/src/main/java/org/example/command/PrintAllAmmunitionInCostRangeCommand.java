package org.example.command;

import org.example.controller.AmmunitionController;

public class PrintAllAmmunitionInCostRangeCommand extends AbstractCommand {

    public PrintAllAmmunitionInCostRangeCommand(AmmunitionController ammunitionController) {
        super(ammunitionController);
    }

    @Override
    public void execute() {
        super.execute();
        System.out.println("Enter inclusive start and end boundaries for ammunition cost to be printed: ");
        int start = scanner.nextInt();
        int end = scanner.nextInt();
        ammunitionController.getAmmunitionListInCostRange(start, end);
    }
}
