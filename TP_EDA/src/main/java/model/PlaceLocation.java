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


  public List<String> levenshtein(String str, String elements[]){
    Levenshtein_Distance lev = new Levenshtein_Distance();
    List<String> results = new ArrayList<>();
    for(int i = 0; i< elements.length ; i++){
      String str2 = elements[i];

      if(lev.normalizedSimilarity(str,str2) >= 0.8){
        results.add(str2);
      }
    }
    results.sort(Comparator.reverseOrder());
    return results;
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
