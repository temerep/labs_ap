package org.example.command;

import org.example.controller.AmmunitionController;

public class ExitProgramCommand extends AbstractCommand {


    public ExitProgramCommand(AmmunitionController ammunitionController) {
        super(ammunitionController);
    }

    @Override
    public void execute() {
        super.execute();
        scanner.close();
        System.exit(0);
    }
}
