package org.example.entity;

import java.util.Random;

import static org.example.utils.RandomGenerator.*;

public class AmmunitionFactoryImpl implements AmmunitionFactory {

    @Override
    public Shield createShield() {
        return new Shield(generateString(), generateDouble(), generateDouble(), generateDouble(), generateDouble());
    }

    @Override
    public Sword createSword() {
        return new Sword(generateString(), generateDouble(), generateDouble(), generateDouble(), generateDouble());
    }

    @Override
    public ChestPlate createChestPlate() {
        return new ChestPlate(generateString(), generateDouble(), generateDouble(), generateDouble(), generateDouble());

    }

    @Override
    public Helmet createHelmet() {
        return new Helmet(generateString(), generateDouble(), generateDouble(),generateDouble(), generateVisionLevel());
    }

    @Override
    public Ammunition createRandomAmmunition() {
        Random random = new Random();
        switch (random.nextInt(4)) {
            case 0:
                return createSword();
            case 1:
                return createHelmet();
            case 2:
                return createShield();
            case 3:
                return createChestPlate();
            default:
                throw new RuntimeException();
        }
    }
}
