package app.geo.model;

import java.io.Serializable;
import java.lang.reflect.Array;

/**
 * Created by niall on 10/10/17.
 * A class that will be used to hold information about a single Geocache
 */

public class Geo{

  public static int autoid = 1;
  public int geoId;
  public String name;
  public String location;
  public String description;
  //public String owner;

  public Geo(String name, String location, String description){
    this.geoId = autoid++;
    this.name = name;
    this.location = location;
    this.description = description;
  }
}
