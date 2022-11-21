package com.repei.droidbattle.droid;

import java.util.Random;

import com.repei.droidbattle.Font;

public class BaseDroid {
  private final String name;
  private final int damage;
  private String warrior = "BaseDroid";
  private int energy;
  private int health;

  public BaseDroid(int health, int damage, String name, String warrior) {
    this.health = health;
    this.damage = damage;
    this.name = name;
    this.warrior = warrior;
    this.energy = 0;
  }

  public int getDamage() {
    if (energy >= 25) {
      energy -= 25;
      System.out.println("\n" + Font.BOLD + Font.BLUE +  "ULT:\t" + Font.RESET + Font.BOLD + Font.BLUE + Font.CYCLONE + this.getWarrior() + " " + this.getName()  + Font.RESET + Font.BLUE + " used x2 damage!" + Font.RESET);
      return damage*2;
    }
    return damage;
  }

  public String getWarrior() {
    return warrior;
  }

  public int getEnergy() {
    return energy;
  }

  public void setEnergy(int energy) {
    this.energy = energy;
  }

  public int getHealth() {
    return health;
  }

  public String getName() {
    return name;
  }

  public boolean isAlive() {
    return health > 0;
  }

  public int getHit(int damage) {
    Random random = new Random();

    int actualDamage = random.nextInt(damage);
    this.health -= actualDamage;
    this.energy += actualDamage;
    if (health < 0) {
      health = 0;
    }

    return actualDamage;
  }

  @Override
  public String toString() {
    return warrior + " " + name + "\t\t" + Font.HEART + " " + health +"\t|";
  }
}
