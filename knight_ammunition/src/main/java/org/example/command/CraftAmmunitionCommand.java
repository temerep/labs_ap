package org.example.command;

import org.example.controller.AmmunitionController;
import org.example.entity.AmmunitionFactory;

public class CraftAmmunitionCommand extends AbstractCommand {
    AmmunitionFactory ammunitionFactory;

    public CraftAmmunitionCommand(AmmunitionController ammunitionController, AmmunitionFactory ammunitionFactory) {
        super(ammunitionController);
        this.ammunitionFactory = ammunitionFactory;
    }


    @Override
    public void execute() {
        super.execute();
        ammunitionController.create(ammunitionFactory.createRandomAmmunition());
    }
}
