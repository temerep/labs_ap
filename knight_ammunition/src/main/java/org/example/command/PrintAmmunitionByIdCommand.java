package org.example.command;

import org.example.controller.AmmunitionController;

public class PrintAmmunitionByIdCommand extends AbstractCommand {

    public PrintAmmunitionByIdCommand(AmmunitionController ammunitionController) {
        super(ammunitionController);
    }

    @Override
    public void execute() {
        super.execute();
        System.out.println("Enter ammunition ID to be printed: ");
        int nextInt = scanner.nextInt();
        ammunitionController.findEntityById(nextInt);
    }
}
