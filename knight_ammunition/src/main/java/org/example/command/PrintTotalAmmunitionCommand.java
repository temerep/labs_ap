package org.example.command;

import org.example.controller.AmmunitionController;

public class PrintTotalAmmunitionCommand extends AbstractCommand {

    public PrintTotalAmmunitionCommand(AmmunitionController ammunitionController) {
        super(ammunitionController);
    }

    @Override
    public void execute() {
        super.execute();
        ammunitionController.findAll();
    }
}
