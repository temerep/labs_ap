package com.repei.droidbattle;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.repei.droidbattle.controller.BattleArena;
import com.repei.droidbattle.droid.*;

public class DroidBattle {
  public File log;
  PrintWriter pw;
  public void battle(int cnt, List<BaseDroid> units) throws InterruptedException {
    int logNum = 1;
   try{
    log = new File("battle" + Integer.toString(logNum) + ".txt");
    while (log.exists()) {
      ++logNum;
      log = new File("battle" + Integer.toString(logNum) + ".txt");
    }
    log.createNewFile();
    pw = new PrintWriter(log);
  } catch (Exception e) {
    System.out.println(e);
  }

    List<BaseDroid> me = new ArrayList<>();
    List<BaseDroid> enemies = new ArrayList<>();
    for (int i = 0; i < cnt; i++) {
      me.add(units.get(i));
      enemies.add(units.get(cnt + i));
    }

    System.out.print("\033\143");
    BattleArena arena = new BattleArena(me, enemies, cnt, pw);
    BaseDroid winner = arena.startFight();

    System.out.println("\n" + Font.BOLD + Font.GREEN
        + "╔═══════════════════════════════════════════════════════════════════════╗" + Font.RESET);
    pw.println("\n" + Font.BOLD + Font.GREEN
    + "╔═══════════════════════════════════════════════════════════════════════╗" + Font.RESET);
    System.out.println("\n" + Font.BOLD + Font.GREEN + "║\t\t      " + Font.GREETING + " The winner is "
        + winner.getName() + "'s Team " + Font.GREETING + "\t\t\t║" + Font.RESET);
    pw.println("\n" + Font.BOLD + Font.GREEN + "║\t\t      " + Font.GREETING + " The winner is "
        + winner.getName() + "'s Team " + Font.GREETING + "\t\t\t║" + Font.RESET);
    System.out.println("\n" + Font.GREEN + "║\t\t\tBattle saved to \"" + log.getName()  + "\"\t\t\t║" + Font.RESET);
    System.out.println("\n" + Font.BOLD + Font.GREEN
        + "╚═══════════════════════════════════════════════════════════════════════╝" + Font.RESET);
    pw.println("\n" + Font.BOLD + Font.GREEN
    + "╚═══════════════════════════════════════════════════════════════════════╝" + Font.RESET);
    pw.close();
  }
}
