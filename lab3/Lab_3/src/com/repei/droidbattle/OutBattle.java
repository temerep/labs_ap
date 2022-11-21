package com.repei.droidbattle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class OutBattle {
  BufferedReader br;
  public OutBattle() {
    Scanner sc = new Scanner(System.in);
    System.out.print(Font.BOLD + Font.WHITE + "🖋 Type name of battle (ex. battle1.txt): " + Font.RESET);
    String name = sc.nextLine();
    System.out.print("\033\143");
    try{
      br = new BufferedReader(new FileReader(name));
      String line;
      while ((line = br.readLine()) != null) {
        System.out.println(line);
      }
  } catch (Exception e) {
    System.out.println("File not exist!");
  }
  }
}
