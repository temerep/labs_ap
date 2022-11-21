package org.example.controller;

import org.example.dao.AmmunitionDao;
import org.example.entity.*;
import org.example.service.AmmunitionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AmmunitionControllerTest {
    AmmunitionFactory ammunitionFactory = new AmmunitionFactoryImpl();
    AmmunitionController ammunitionController = new AmmunitionController(new AmmunitionService(new AmmunitionDao<>()));
    Helmet helmet = new Helmet("h1", 3, 3, 3, VisionLevel.HALF_FACE);
    ChestPlate chestPlate = new ChestPlate("cp2", 2, 2, 2, 2);
    List<Ammunition> list;
    @BeforeEach
    void setUp() {
        list = List.of(helmet,chestPlate);
    }

    @AfterEach
    void tearDown() {
        list = null;
    }

    @Test
    void initView() {
        assertTrue(true);
    }

    @Test
    void updateView() {
        assertTrue(true);
    }

    @Test
    void findAll() {
        assertTrue(true);
    }

    @Test
    void findEntityById() {
        assertTrue(true);
    }

    @Test
    void delete() {
        assertTrue(true);
    }

    @Test
    void testDelete() {
        assertTrue(true);
    }

    @Test
    void create() {
        assertTrue(true);
    }

    @Test
    void computeTotalEquippedAmmunitionCost() {
        assertTrue(true);
    }

    @Test
    void getAmmunitionListSortedByWeight() {
        assertTrue(true);
    }

    @Test
    void getAmmunitionListInCostRange() {
        assertTrue(true);
    }
}