package org.example.entity;

public class Sword extends Ammunition implements Damageable{
    private final double damage;

    public Sword(String name, double cost, double weight, double durability, double damage) {
        super(name, cost, weight, durability);
        this.damage = damage;
    }

    @Override
    public double getDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return super.toString()+ "damage=" + damage +
                ']';
    }
}
