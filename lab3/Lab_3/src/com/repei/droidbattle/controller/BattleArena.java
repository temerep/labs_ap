package com.repei.droidbattle.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.repei.droidbattle.Font;
import com.repei.droidbattle.droid.BaseDroid;

public class BattleArena {
  private final List<BaseDroid> me;
  private final List<BaseDroid> enemies;
  private BaseDroid attacker;
  private BaseDroid defender;
  private int teamCount = 0;
  private int currentRound = 0;
  private PrintWriter pw;

  public BattleArena(List<BaseDroid> me, List<BaseDroid> enemies, int cnt, PrintWriter pw) {
    this.me = new ArrayList<>(me);
    this.enemies = new ArrayList<>(enemies);
    this.teamCount = cnt;
    this.pw = pw;
  }

  public boolean isAlive(List<BaseDroid> team) {
    int sum = 0;
    for (int i = 0; i < team.size(); i++) {
      sum += team.get(i).getHealth();
    }
    return sum > 0;
  }

  public BaseDroid startFight() throws InterruptedException {
    do {
      prepareRound();
      int actualDamage = doFight();
      printRoundInfo(actualDamage, attacker, defender);

      TimeUnit.MILLISECONDS.sleep(100);
    } while (isAlive(me) && isAlive(enemies));

    return attacker;
  }

  private void prepareRound() {
    initFighters(teamCount);
    currentRound++;
    System.out.println(Font.BOLD
        + "\n◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎\n◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎\n"
        + Font.RESET);
    System.out.println(Font.BOLD + Font.YELLOW + "Round " + currentRound + Font.RESET);
    pw.println(Font.BOLD
        + "\n◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎\n◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎\n"
        + Font.RESET);
    pw.println(Font.BOLD + Font.YELLOW + "Round " + currentRound + Font.RESET);
  }

  private int doFight() {
    return defender.getHit(attacker.getDamage());
  }

  private void printRoundInfo(int actualDamage, BaseDroid attacker, BaseDroid defender) {
    System.out.println("\n" + Font.RED + Font.BOLD + "ACTION: " + Font.BLOOD + defender.getWarrior() + " "
        + defender.getName() + Font.RESET
        + Font.RED + " get " + Font.BOLD + "-" + actualDamage + " " + Font.HEART + Font.RESET + Font.RED
        + " of damage from "
        + Font.GREEN + Font.BOLD + Font.AXE + attacker.getWarrior() + " " + attacker.getName() + Font.RESET + "\n");
    pw.println("\n" + Font.RED + Font.BOLD + "ACTION: " + Font.BLOOD + defender.getWarrior() + " " + defender.getName()
        + Font.RESET
        + Font.RED + " get " + Font.BOLD + "-" + actualDamage + " " + Font.HEART + Font.RESET + Font.RED
        + " of damage from "
        + Font.GREEN + Font.BOLD + Font.AXE + attacker.getWarrior() + " " + attacker.getName() + Font.RESET + "\n");
    System.out
        .println(Font.BOLD + "-------------------------------------------------------------------------" + Font.RESET);
    pw.println(Font.BOLD + "-------------------------------------------------------------------------" + Font.RESET);
    System.out.println(Font.BOLD + Font.CYAN + "\t   Your Team" + "\t\t|" + Font.RESET + Font.BOLD + Font.YELLOW
        + "\t\tRival Team\t\t|" + Font.RESET);
    pw.println(Font.BOLD + Font.CYAN + "\t   Your Team" + "\t\t|" + Font.RESET + Font.BOLD + Font.YELLOW
        + "\t\tRival Team\t\t|" + Font.RESET);
    System.out.println(Font.BOLD + Font.CYAN + "Name" + "\t\t\tHealth\t|" + Font.RESET + Font.BOLD + Font.YELLOW
        + "\tName\t\t\tHealth\t|" + Font.RESET);
    pw.println(Font.BOLD + Font.CYAN + "Name" + "\t\t\tHealth\t|" + Font.RESET + Font.BOLD + Font.YELLOW
        + "\tName\t\t\tHealth\t|" + Font.RESET);
    System.out
        .println(Font.BOLD + "-------------------------------------------------------------------------" + Font.RESET);
    pw.println(Font.BOLD + "-------------------------------------------------------------------------" + Font.RESET);
    for (int i = 0; i < teamCount; i++) {
      System.out.println((attacker == me.get(i)
          ? Font.GREEN + Font.BOLD + Font.AXE
          : defender == me.get(i)
              ? Font.RED + Font.BOLD + Font.BLOOD
              : me.get(i).getHealth() == 0 ? Font.BLACK : Font.CYAN)
          + me.get(i) + Font.RESET + "\t"
          + (attacker == enemies.get(i)
              ? Font.GREEN + Font.BOLD + Font.AXE
              : defender == enemies.get(i)
                  ? Font.RED + Font.BOLD + Font.BLOOD
                  : enemies.get(i).getHealth() == 0 ? Font.BLACK : Font.YELLOW)
          + enemies.get(i) + Font.RESET);
      pw.println((attacker == me.get(i)
          ? Font.GREEN + Font.BOLD + Font.AXE
          : defender == me.get(i)
              ? Font.RED + Font.BOLD + Font.BLOOD
              : me.get(i).getHealth() == 0 ? Font.BLACK : Font.CYAN)
          + me.get(i) + Font.RESET + "\t"
          + (attacker == enemies.get(i)
              ? Font.GREEN + Font.BOLD + Font.AXE
              : defender == enemies.get(i)
                  ? Font.RED + Font.BOLD + Font.BLOOD
                  : enemies.get(i).getHealth() == 0 ? Font.BLACK : Font.YELLOW)
          + enemies.get(i) + Font.RESET);
      System.out.println(
          Font.BOLD + "-------------------------------------------------------------------------" + Font.RESET);
      pw.println(Font.BOLD + "-------------------------------------------------------------------------" + Font.RESET);
    }

  }

  private void initFighters(int teamCount) {
    Random random = new Random();
    int rand;
    if (random.nextBoolean()) {
      do {
        rand = random.nextInt(me.size());
        attacker = me.get(rand);
      } while (attacker.getHealth() == 0);
      do {
        rand = random.nextInt(enemies.size());
        defender = enemies.get(rand);
      } while (defender.getHealth() == 0);
    } else {
      do {
        rand = random.nextInt(enemies.size());
        attacker = enemies.get(rand);
      } while (attacker.getHealth() == 0);
      do {
        rand = random.nextInt(me.size());
        defender = me.get(rand);
      } while (defender.getHealth() == 0);
    }
  }
}
