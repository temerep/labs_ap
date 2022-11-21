package com.repei.droidbattle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.repei.droidbattle.droid.BaseDroid;
import com.repei.droidbattle.droid.Bowman;
import com.repei.droidbattle.droid.Knight;
import com.repei.droidbattle.droid.Wizard;

public class App {
  public static void main(String[] args) throws InterruptedException {
    System.out.print("\033\143");
    Scanner sc = new Scanner(System.in);
    boolean menuChecker = false;
    int select;

    List<BaseDroid> units = new ArrayList<>();

    List<String> names = new ArrayList<>();
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader("names.txt"));
      String line = reader.readLine();
      while (line != null) {
        names.add(line);
        line = reader.readLine();
      }
      reader.close();
    } catch (Exception e) {
    }

    do {
      welcome();
      select = sc.nextInt();
      Random random = new Random();
      String name;
      int unitCount;
      switch (select) {
        case 1:
          menuChecker = false;
          System.out.print("\033\143");
          System.out.print(Font.BOLD + Font.WHITE + "👤 Select warrior:\n\n(1) Bowman\t" + Font.YELLOW
              + "(HP: 120 DAM: 25)" + Font.WHITE + "\n(2) Knight\t" + Font.YELLOW + "(HP: 240 DAM: 35)\n" + Font.WHITE
              + "(3) Wizard\t" + Font.YELLOW + "(HP: 180 DAM: 45)\n\n=> " + Font.RESET);
          select = sc.nextInt();
          name = names.get(random.nextInt(names.size()));
          units.add(select == 1 ? new Bowman(name) : select == 2 ? new Knight(name) : new Wizard(name));
          name = names.get(random.nextInt(names.size()));
          units.add(select == 1 ? new Bowman(name) : select == 2 ? new Knight(name) : new Wizard(name));
          new DroidBattle().battle(1, units);
          break;
        case 2:
          menuChecker = false;
          do {
            System.out.print("\033\143");
            System.out.print(Font.BOLD + Font.WHITE + "👥 Number of warriors in the team: " + Font.RESET);
            unitCount = sc.nextInt();
          } while (unitCount < 1 || unitCount > 15);
          System.out.print(Font.BOLD + Font.WHITE + "\n👤 Select warriors:\n\n(1) Bowman\t" + Font.YELLOW
              + "(HP: 120 DAM: 25)" + Font.WHITE + "\n(2) Knight\t" + Font.YELLOW + "(HP: 240 DAM: 35)\n" + Font.WHITE
              + "(3) Wizard\t" + Font.YELLOW + "(HP: 180 DAM: 45)\n " + Font.RESET);

          for (int i = 0; i < unitCount; i++) {
            do {
              System.out.printf(Font.BOLD + Font.YELLOW + "\n#%d" + Font.WHITE + " warrior => " + Font.RESET, i + 1);
              select = sc.nextInt();
              name = names.get(random.nextInt(names.size()));
              units.add(select == 1 ? new Bowman(name) : select == 2 ? new Knight(name) : new Wizard(name));
              name = names.get(random.nextInt(names.size()));
              units.add(select == 1 ? new Bowman(name) : select == 2 ? new Knight(name) : new Wizard(name));
            } while (select < 1 || select > 3);
          }
          new DroidBattle().battle(unitCount, units);
          break;
        case 3:
          menuChecker = false;
          System.out.print("\033\143");
          OutBattle out = new OutBattle();
          break;
        case 4:
          menuChecker = false;
          System.out.print("\033\143");
          break;
        default:
          System.out.print("\033\143");
          menuChecker = true;
      }
    } while (menuChecker);
  }

  public static void welcome() {
    System.out.println(
        "⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️");
    System.out.println(
        "⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬛⬛⬛⬛⬛⬛⬛⬛⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️");
    System.out.println(Font.BOLD + Font.WHITE
        + "⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬛🟫🟫🟫🟫🟫🟫🟫🟫⬛⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️    __  __           _ ______\t\t\t\t\t\t⬜️⬜️⬜️⬜️⬜️");
    System.out.println(
        "⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬛🟫🏼🏼🏼🏼🏼🏼🏼🟫🟫⬛⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️   |  \\/  |         | |  ____|\t\t\t\t\t⬜️⬜️⬜️⬜️⬜️");
    System.out.println(
        "⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬛🏼🏼🏼🏼🏼🏼🏼🏼🏼🟫⬛⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️   | \\  / | __ _  __| | |__ ___  _ __ ___ ___\t\t\t\t⬜️⬜️⬜️⬜️⬜️");
    System.out.println(
        "⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬛🏼🏼⬛🏼🏼🏼⬛🏼🏼🟫⬛⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️   | |\\/| |/ _` |/ _` |  __/ _ \\| '__/ __/ _ \\\t\t\t⬜️⬜️⬜️⬜️⬜️");
    System.out.println(
        "⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬛🏼🏼🏼🏼🏼🏼🏼🏼🏼🏼⬛⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️   | |  | | (_| | (_| | | | (_) | | | (_|  __/\t\t\t⬜️⬜️⬜️⬜️⬜️");
    System.out.println(
        "⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬛🏼🏼🏼⬛⬛⬛🏼🏼🏼🏼⬛⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️   |_|  |_|\\__,_|\\__,_|_|  \\___/|_|  \\___\\___|   _   _. ._ _   _  \t⬜️⬜️⬜️⬜️⬜️");
    System.out.println(
        "⬜️⬜️⬜️⬜️🟧⬜️⬜️⬛⬛🏼🏼🏼🏼🏼🏼🏼🏼⬛⬛⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️\t\t\t\t\t\t  (_| (_| | | | (/_ \t⬜️⬜️⬜️⬜️⬜️");
    System.out.println(
        "⬜️⬜️⬜️⬜️⬜️🟧⬜️⬜️⬜️⬛⬛⬛⬛⬛⬛⬛⬛⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️\tBattle terminal-game\t\t\t   _|               \t⬜️⬜️⬜️⬜️⬜️"
            + Font.RESET);
    System.out.println("⬜️⬜️⬜️⬜️⬜️🟧⬜️⬜️⬛🏽🟦🟨🟨🟨🏽🏽🏽⬛⬜️⬜️⬜️🟧⬜️⬜️⬜️⬜️⬜️⬜️⬜️\t" + Font.BLACK
        + "Author: @temerep\t\t\t\t\t\t⬜️⬜️⬜️⬜️⬜" + Font.RESET);
    System.out.println("⬜️⬜️⬜️🟧⬜️⬜️🟧⬛🏽⬛🟨🟦🟨🟨🟨🟨⬛🏽⬛⬜️🟧⬜⬜️⬜️⬜️⬜️⬜️⬜️⬜️\t\t\t\t\t\t\t\t\t⬜️⬜️⬜️⬜️⬜️");
    System.out.println("⬜️⬜️⬜️⬜️🟧🟧🟥⬛🏼⬛🟨🟨🏽🟦🟨🟨⬛🏼⬛🟧🟧⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️\t\t\t\t\t\t\t\t\t⬜️⬜️⬜️⬜️⬜️");
    System.out.println("⬜️⬜️🟧⬜️🟧🟥⬛🏼⬛⬛🟨🟨🟨🟨🟦🟦⬛🏼⬛🟥🟧🟧⬜️⬜️⬜️⬜️⬜️⬜️⬜️\t" + Font.BOLD + Font.YELLOW
        + "Menu:\t\t\t\t\t\t\t\t⬜️⬜️⬜️⬜️⬜️");
    System.out.println("⬜️⬜️⬜️🟧🟥🟥⬛⬛⬜️⬛🟧🟧🟦🟧🟧🟧⬛⬛🟥🟧🟧⬜️🟧⬜️⬜️⬜️⬜️⬜️⬜️\t\t\t\t\t\t\t\t\t⬜️⬜️⬜️⬜️⬜️");
    System.out.println("⬜️⬜️⬜️⬜️🟧⬜️🟧🟧🟥⬛⬜️🟦🟦🟦🟦🟦⬛🟥🟥🟧🟧⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️\t" + "1️⃣ Single Battle (1x1)"
        + "\t\t\t\t\t\t⬜️⬜️⬜️⬜️⬜️");
    System.out.println("⬜️⬜️⬜️🟧⬜️⬜️🟧⬜️⬜️⬛🏽🟦⬛⬛🏽🟦⬛🟧🟧⬜️⬜️🟧⬜️⬜️⬜️⬜️⬜️⬜️⬜️\t" + "2️⃣ Big Battle (Team x Team)"
        + "\t\t\t\t\t⬜️⬜️⬜️⬜️⬜️");
    System.out.println(
        "⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬛⬛⬛⬜️⬜️⬛⬛⬛🟧⬜️🟧⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️\t" + "3️⃣ Play Game From File" + "\t\t\t\t\t\t⬜️⬜️⬜️⬜️⬜️");
    System.out.println("⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️🟧⬜️⬜️🟧🟧⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️\t" + "4️⃣ Exit" + "\t\t\t\t\t\t\t\t⬜️⬜️⬜️⬜️⬜️");
    System.out.println("⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️\t\t\t\t\t\t\t\t\t⬜️⬜️⬜️⬜️⬜️");
    System.out.println("⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️\t\t\t\t\t\t\t\t\t⬜️⬜️⬜️⬜️⬜️");
    System.out.print("⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️\t=> " + Font.RESET);
  }
}
