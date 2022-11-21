package org.example.dao;

import org.example.entity.Ammunition;
import org.example.entity.Helmet;
import org.example.entity.Shield;
import org.example.entity.VisionLevel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AmmunitionDaoTest {
    private static AmmunitionDao<Ammunition> ammunitionDao;
    Helmet helmet = new Helmet("h1", 1, 1, 1, VisionLevel.HALF_FACE);

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        ammunitionDao = new AmmunitionDao<>();
        ammunitionDao.storage.add(helmet);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        ammunitionDao.storage.clear();
        ammunitionDao.storage = null;
    }

    @org.junit.jupiter.api.Test
    void testFindAll() throws Exception {
        assertEquals(List.of(helmet), ammunitionDao.findAll());
    }

    @org.junit.jupiter.api.Test
    void testFindEntityById() throws Exception {
        assertEquals(helmet, ammunitionDao.findEntityById(0));
    }

    @org.junit.jupiter.api.Test
    void testDeleteById() throws Exception {
        ammunitionDao.delete(0);
        assertEquals(0, ammunitionDao.storage.size());
    }

    @org.junit.jupiter.api.Test
    void testDeleteByEntity() throws Exception {
        ammunitionDao.delete(helmet);
        assertEquals(0, ammunitionDao.storage.size());
    }

    @org.junit.jupiter.api.Test
    void testCreate() throws Exception {
        ammunitionDao.create(new Shield("sh", 1, 1, 1, 1));
        assertEquals(2,ammunitionDao.storage.size());
    }
}