package com.repei.train.trainlist;

public class Train {
    private int trainNumber;
    private int[] placesCount = new int[4];
    private String startTime;
    private String destination;
  
    public Train(int trainNumber, String destination, String startTime, int coupe, int platsk, int lux) {
      this.trainNumber = trainNumber;
      this.destination = destination;
      this.startTime = startTime;
      this.placesCount[0] = coupe + platsk + lux;
      this.placesCount[1] = coupe;
      this.placesCount[2] = platsk;
      this.placesCount[3] = lux;
    }
  
    public int getTrainNumber() {
      return trainNumber;
    }
  
    public int getPlacesCount(int i) {
      return placesCount[i];
    }
  
    public String getStartTime() {
      return startTime;
    }
  
    public String getDestination() {
      return destination;
    }
  
    public void setTrainNumber(int trainNumber) {
      this.trainNumber = trainNumber;
    }
  
    public void setDestination(String destination) {
      this.destination = destination;
    }
  
    public void setStartDate(String startTime) {
      this.startTime = startTime;
    }
  
    public void setPlacesCount(int coupe, int platsk, int lux) {
      this.placesCount[0] = coupe + platsk + lux;
      this.placesCount[1] = coupe;
      this.placesCount[2] = platsk;
      this.placesCount[3] = lux;
    }
    @Override
    public String toString() {
      return ("\n#" + trainNumber + "\tDestination: " + destination + "\tDeparture: " + startTime + "\tPlaces: " + placesCount[0]);
    }
  }