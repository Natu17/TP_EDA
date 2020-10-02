package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class PlaceLocation {

  private double lat;
  private double lng;
  private String name;

  public PlaceLocation(String name, double lat, double lng) {

    this.name = name;
    this.lat = lat;
    this.lng = lng;

  }


  public double getLat() {
    return lat;
  }

  public double getLng() {
    return lng;
  }

  public String getName() {
    return name;
  }
}
