package org.example.service;

import org.example.dao.AmmunitionDao;
import org.example.entity.Ammunition;
import org.example.entity.ChestPlate;
import org.example.entity.Helmet;
import org.example.entity.VisionLevel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AmmunitionServiceTest {
    AmmunitionDao<Ammunition> ammunitionDao;
    Helmet helmet = new Helmet("h1", 1, 1, 1, VisionLevel.HALF_FACE);
    ChestPlate chestPlate = new ChestPlate("cp2", 2, 2, 2, 2);
    AmmunitionService ammunitionService;
    @BeforeEach
    void setUp() throws Exception {
        AmmunitionDao<Ammunition> dao = new AmmunitionDao<>();
        dao.create(helmet);
        dao.create(chestPlate);
        ammunitionService = new AmmunitionService(dao);
    }

    @AfterEach
    void tearDown() {
        ammunitionService = null;
        ammunitionDao = null;
    }

    @Test
    void findAll() throws Exception {
        assertEquals(List.of(helmet, chestPlate), ammunitionService.findAll());
    }

    @Test
    void findEntityById() throws Exception {
        assertEquals(chestPlate,ammunitionService.findEntityById(1));
    }

    @Test
    void deleteById() throws Exception {
        ammunitionService.delete(0);
        assertEquals(1, ammunitionService.findAll().size());
    }

    @Test
    void deleteByEntity() throws Exception {
        ammunitionService.delete(0);
        assertEquals(1, ammunitionService.findAll().size());
    }

    @Test
    void create() throws Exception {
        ammunitionService.create(new Helmet("h2",1,1,1,VisionLevel.HALF_FACE));
        assertEquals(3, ammunitionService.findAll().size());
    }

    @Test
    void computeTotalEquippedAmmunitionCost() throws Exception {
        assertEquals(3, ammunitionService.computeTotalEquippedAmmunitionCost());
    }

    @Test
    void getAmmunitionListSortedByWeight() throws Exception {
        List<Ammunition> ammunitionListSortedByWeight = ammunitionService.getAmmunitionListSortedByWeight();
        assertEquals(List.of(helmet, chestPlate),ammunitionListSortedByWeight);
    }

    @Test
    void getAmmunitionListInCostRange() throws Exception {
        List<Ammunition> ammunitionListInCostRange = ammunitionService.getAmmunitionListInCostRange(0, 1);
        assertEquals(List.of(helmet),ammunitionListInCostRange);
    }
}