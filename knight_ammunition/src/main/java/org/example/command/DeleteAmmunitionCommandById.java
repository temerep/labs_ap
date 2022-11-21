package org.example.command;

import org.example.controller.AmmunitionController;

public class DeleteAmmunitionCommandById extends AbstractCommand {

    public DeleteAmmunitionCommandById(AmmunitionController ammunitionController) {
        super(ammunitionController);
    }

    @Override
    public void execute() {
        super.execute();
        System.out.println("Enter ammunition ID to be deleted: ");
        int nextInt = scanner.nextInt();
        ammunitionController.delete(nextInt);
    }
}
