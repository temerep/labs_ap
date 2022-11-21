package org.example.entity;

public class ChestPlate extends Ammunition implements Protectable {
    private final double protection;

    public ChestPlate(String name, double cost, double weight, double durability, double protection) {
        super(name, cost, weight, durability);
        this.protection = protection;
    }

    @Override
    public double getProtection() {
        return protection;
    }

    @Override
    public String toString() {
        return super.toString() +
                "protection=" + String.format("%.2f",protection)  +
                ']';
    }
}
