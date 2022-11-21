package com.repei.train;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.repei.train.trainlist.Train;

public class App {
  public static void main(String[] args) throws Exception {
    List<Train> trains = new ArrayList<>();
    trains.add(new Train(24, "Uzhgorod", "03:40", 25, 32, 15));
    trains.add(new Train(36, "Lutsk", "08:55", 23, 60, 27));
    trains.add(new Train(33, "Kyiv", "11:15", 37, 41, 18));
    trains.add(new Train(131, "Lviv", "13:25", 0, 0, 0));
    trains.add(new Train(127, "Lviv", "21:20", 33, 75, 36));
    trains.add(new Train(121, "Odessa", "22:33", 34, 45, 32));
    Scanner sc = new Scanner(System.in);
    boolean checker = true;
    while (checker) {
      checker = false;
      System.out.print("\033\143");
      System.out.println(
          "Choose the item:\n1) List of trains to your destination\n2) List of trains to your destination and departure\n3) List of trains to your destination and have places\n4) Show all trains\n5) Add train to list\n6) Exit");
      System.out.print("=> ");

      int choice = sc.nextInt();
      switch (choice) {
        case 1:
          System.out.print("\033\143");
          System.out.print("Enter destination: ");
          sc.nextLine();
          String dest = sc.nextLine();
          System.out.println("________________________");
          for (Train t : trains) {
            if (dest.compareToIgnoreCase(t.getDestination()) == 0) {
              System.out.print("\n#" + t.getTrainNumber() + "\tDestination: " + t.getDestination() + "\tDeparture: "
                  + t.getStartTime() + "\tPlaces: " + t.getPlacesCount(0));
            }
          }
          System.out.print("\n\n");
          sc.nextLine();
          checker = true;
          break;
        case 2:
          System.out.print("\033\143");
          System.out.print("Enter destination: ");
          sc.nextLine();
          String dest1 = sc.nextLine();
          System.out.print("Enter time (HH:mm): ");
          String time = sc.nextLine();
          DateFormat sdf = new SimpleDateFormat("HH:mm");
          Date userTime = sdf.parse(time);
          System.out.println("________________________");
          for (Train t : trains) {
            Date train_time = sdf.parse(t.getStartTime());
            if (dest1.compareToIgnoreCase(t.getDestination()) == 0 && userTime.getTime() < train_time.getTime()) {
              System.out.print(t.toString());
            }
          }
          System.out.print("\n\n");
          sc.nextLine();
          checker = true;
          break;
        case 3:
          System.out.print("\033\143");
          System.out.print("Enter destination: ");
          sc.nextLine();
          String dest2 = sc.nextLine();
          System.out.println("________________________");
          for (Train t : trains) {
            if (dest2.compareToIgnoreCase(t.getDestination()) == 0 && t.getPlacesCount(0) > 0) {
              System.out.print(t.toString());
            }
          }
          System.out.print("\n\n");
          sc.nextLine();
          checker = true;
          break;
        case 4:
          System.out.print("\033\143");
          for (Train t : trains) {
            System.out.print(t.toString());
          }
          sc.nextLine();
          sc.nextLine();
          checker = true;
          break;
        case 5:
          System.out.print("\033\143");
          System.out.print("Enter # of train: ");
          int num_of_train = sc.nextInt();
          System.out.print("Enter destination: ");
          sc.nextLine();
          String train_dest = sc.nextLine();
          System.out.print("Enter departure: ");
          String train_dep = sc.nextLine();
          System.out.print("Enter count of compartment seats: ");
          int num_of_coupe = sc.nextInt();
          System.out.print("Enter count of econom-class seats: ");
          int num_of_platsk = sc.nextInt();
          System.out.print("Enter count of luxury seats: ");
          int num_of_lux = sc.nextInt();
          trains.add(new Train(num_of_train, train_dest, train_dep, num_of_coupe, num_of_platsk, num_of_lux));
          System.out.print("\nTrain added successfully!");
          sc.nextLine();
          sc.nextLine();
          checker = true;
          break;
        case 6:
          System.out.print("\033\143");
          checker = false;
          break;
        default:
          checker = true;
      }
    }
    sc.close();
  }
}