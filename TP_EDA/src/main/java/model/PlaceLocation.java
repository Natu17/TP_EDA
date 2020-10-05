package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class PlaceLocation implements Comparable<PlaceLocation>{
  private double similarity;
  private double lat;
  private double lng;
  private String name;

  public PlaceLocation(String name, double lat, double lng) {
    this.name = name;
    this.lat = lat;
    this.lng = lng;

  }
  public void similarity(double similarity){
    this.similarity = similarity;
  }

  public double getSimilarity() {
    return similarity;
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

  @Override
  public int compareTo(PlaceLocation o) {
    return Double.compare(similarity,o.similarity);
  }
}
