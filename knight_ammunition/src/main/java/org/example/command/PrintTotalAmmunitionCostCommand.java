package org.example.command;

import org.example.controller.AmmunitionController;

public class PrintTotalAmmunitionCostCommand extends AbstractCommand {

    public PrintTotalAmmunitionCostCommand(AmmunitionController ammunitionController) {
        super(ammunitionController);
    }

    @Override
    public void execute() {
        super.execute();
        ammunitionController.computeTotalEquippedAmmunitionCost();
    }
}
