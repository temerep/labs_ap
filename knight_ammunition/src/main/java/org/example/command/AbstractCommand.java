package org.example.command;



import org.example.controller.AmmunitionController;
import org.example.singleton.ScannerSingleton;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.util.Scanner;


public abstract class AbstractCommand implements ICommand {
    protected final static Logger LOGGER = LoggerFactory.getLogger(AbstractCommand.class.getSimpleName());
    protected final Scanner scanner = ScannerSingleton.getInstance().getScanner();
    protected AmmunitionController ammunitionController;

    public AbstractCommand(AmmunitionController ammunitionController) {
        this.ammunitionController = ammunitionController;
    }

    @Override
    public void execute() {
        LOGGER.info(this.getClass().getSimpleName() + " is executed");
    }
}
