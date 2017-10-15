package app.geo.model;

/**
 * Created by niall on 10/10/17.
 * A class that will be used to hold information about a single Geocache
 */

public class Cache {

  public static int autoid = 1;
  public int cacheId;
  public String name;
  public String location;
  public String description;
  //public String owner;

  public Cache(String name, String location, String description){
    this.cacheId = autoid++;
    this.name = name;
    this.location = location;
    this.description = description;
  }
}
