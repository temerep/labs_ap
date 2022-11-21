package org.example.command;

import org.example.controller.AmmunitionController;

public class PrintAllAmmunitionSortedByWeightCommand extends AbstractCommand {

    public PrintAllAmmunitionSortedByWeightCommand(AmmunitionController ammunitionController) {
        super(ammunitionController);
    }

    @Override
    public void execute() {
        super.execute();
        ammunitionController.getAmmunitionListSortedByWeight();
    }
}
