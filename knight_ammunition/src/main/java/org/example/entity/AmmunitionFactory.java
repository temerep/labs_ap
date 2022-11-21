package org.example.entity;

public interface AmmunitionFactory {
    Shield createShield();

    Sword createSword();

    ChestPlate createChestPlate();

    Helmet createHelmet();

    Ammunition createRandomAmmunition();
}
