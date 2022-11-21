package org.example.entity;

public abstract class Ammunition extends Entity implements Costable, Weightable, Durable {
    private String name;
    private final double cost;
    private final double weight;

    private final double durability;

    public Ammunition(String name, double cost, double weight, double durability) {
        this.name = name;
        this.cost = cost;
        this.weight = weight;
        this.durability = durability;
    }

    public String getName() {
        return name;
    }

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public double getDurability() {
        return durability;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.getClass().getSimpleName())
                .append(" ")
                .append(name)
                .append(" [id=")
                .append(id)
                .append(", cost=")
                .append(String.format("%.2f", cost))
                .append(", weight=")
                .append(String.format("%.2f", weight))
                .append(", ")
                .toString();
    }
}
