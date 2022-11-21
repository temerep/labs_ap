package org.example.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmmunitionFactoryImplTest {
    AmmunitionFactory ammunitionFactory = new AmmunitionFactoryImpl();
    @Test
    void createShield() {
        assertEquals("Shield",ammunitionFactory.createShield().getClass().getSimpleName());
    }

    @Test
    void createSword() {
        assertEquals("Sword",ammunitionFactory.createSword().getClass().getSimpleName());
    }

    @Test
    void createChestPlate() {
        assertEquals("ChestPlate",ammunitionFactory.createChestPlate().getClass().getSimpleName());
    }

    @Test
    void createHelmet() {
        assertEquals("Helmet",ammunitionFactory.createHelmet().getClass().getSimpleName());
    }

    @Test
    void createRandomAmmunition() {
        assertTrue(ammunitionFactory.createHelmet() instanceof Ammunition);
    }
}