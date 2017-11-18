package app.geo.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import app.geo.main.GeoApp;

/**
 * @author Niall Grant 05/11/2017
 * A model class that defines what should be stored in the cache object
 *
 * Main reference source: 4(a) Navigation and LifeCycles (Siobhán) Lab 2
 */

public class Cache {

  public int cacheId;
  public String name;
  public String location;
  public String description;
  public boolean favourite;
  public String ownerId;
  public double latitude;
  public double longitude;

  private static final String JSON_CACHEID = "cacheId";
  private static final String JSON_CACHENAME = "name";
  private static final String JSON_CACHELOCATION = "location";
  private static final String JSON_CACHEDESCRIPTION = "description";
  private static final String JSON_CACHEDFAVOURITE= "favourite";
  private static final String JSON_CACHEOWNERID = "ownerId";
  private static final String JSON_CACHELATITUDE = "latitude";
  private static final String JSON_CACHELONGITUDE = "longitude";

  public Cache(String name, String location, String description, String ownerId, double latitude, double longitude){
    this.cacheId = newid();
    this.name = name;
    this.location = location;
    this.description = description;
    this.favourite = false;
    this.ownerId = ownerId;

    this.latitude = latitude;
    this.longitude = longitude;
  }

  public Cache(JSONObject json) throws JSONException {
    cacheId = json.getInt(JSON_CACHEID);
    name = json.getString(JSON_CACHENAME);
    location = json.getString(JSON_CACHELOCATION);
    description = json.getString(JSON_CACHEDESCRIPTION);
    favourite = json.getBoolean(JSON_CACHEDFAVOURITE);
    ownerId = json.getString(JSON_CACHEOWNERID);
    latitude = json.getDouble(JSON_CACHELATITUDE);
    longitude = json.getDouble(JSON_CACHELONGITUDE);
  }

  public JSONObject toJSON() throws JSONException{
    JSONObject json = new JSONObject();
    json.put(JSON_CACHEID, String.valueOf(cacheId));
    json.put(JSON_CACHENAME, name);
    json.put(JSON_CACHELOCATION, location);
    json.put(JSON_CACHEDESCRIPTION, description);
    json.put(JSON_CACHEDFAVOURITE, String.valueOf(favourite));
    json.put(JSON_CACHEOWNERID, ownerId);
    json.put(JSON_CACHELATITUDE, String.valueOf(latitude));
    json.put(JSON_CACHELONGITUDE, String.valueOf(longitude));

    return json;
  }

  private int newid(){
    ArrayList<Cache> caches = GeoApp.getInstance().cacheStore.caches;
    if(caches.size() > 0) {
      Cache cache = caches.get(caches.size() - 1);
      return cache.cacheId + 1;
    }
    else{
      return 0;
    }
  }
}
