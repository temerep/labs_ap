package org.example.command;

import org.example.controller.AmmunitionController;
import org.example.dao.AmmunitionDao;
import org.example.entity.Ammunition;
import org.example.entity.ChestPlate;
import org.example.entity.Helmet;
import org.example.entity.VisionLevel;
import org.example.service.AmmunitionService;
import org.example.singleton.ScannerSingleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AbstractCommandTest {
    protected List<Ammunition> storage= new ArrayList<>();
    protected final Scanner scanner = ScannerSingleton.getInstance().getScanner();
    protected AmmunitionController ammunitionController = new AmmunitionController(new AmmunitionService(new AmmunitionDao<Ammunition>(storage)));
    Helmet helmet = new Helmet("h1", 1, 1, 1, VisionLevel.HALF_FACE);
    ChestPlate chestPlate = new ChestPlate("cp2", 2, 2, 2, 2);
}