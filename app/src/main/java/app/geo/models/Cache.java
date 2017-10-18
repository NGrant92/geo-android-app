package app.geo.models;

import org.json.JSONException;
import org.json.JSONObject;

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
  public int ownerId;

  private static final String JSON_CACHEID = "cacheId";
  private static final String JSON_CACHENAME = "name";
  private static final String JSON_CACHELOCATION = "location";
  private static final String JSON_CACHEDESCRIPTION = "description";
  private static final String JSON_CACHEOWNERID = "ownerId";

  public Cache(String name, String location, String description, int ownerId){
    this.cacheId = autoid++;
    this.name = name;
    this.location = location;
    this.description = description;
    this.ownerId = ownerId;
  }

  public Cache(JSONObject json) throws JSONException {
    cacheId = json.getInt(JSON_CACHEID);
    name = json.getString(JSON_CACHENAME);
    location = json.getString(JSON_CACHELOCATION);
    description = json.getString(JSON_CACHEDESCRIPTION);
    ownerId = json.getInt(JSON_CACHEOWNERID);
  }

  public JSONObject toJSON() throws JSONException{
    JSONObject json = new JSONObject();
    json.put(JSON_CACHEID, String.valueOf(cacheId));
    json.put(JSON_CACHENAME, name);
    json.put(JSON_CACHELOCATION, location);
    json.put(JSON_CACHEDESCRIPTION, description);
    json.put(JSON_CACHEOWNERID, String.valueOf(ownerId));

    return json;
  }
}
