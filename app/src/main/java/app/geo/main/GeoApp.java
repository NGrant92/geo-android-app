package app.geo.main;

import android.app.Application;
import android.util.Log;

import app.geo.models.CacheSerializer;
import app.geo.models.CacheStore;
import app.geo.models.User;
import app.geo.models.UserSerializer;
import app.geo.models.UserStore;

/**
 * Created by niall on 14/10/17.
 */

public class GeoApp extends Application{

  public User currUser;
  public UserStore userStore;
  public CacheStore cacheStore;
  private static final String USERSTORE = "userstore.json";
  private static final String CACHESTORE = "cachestore.json";

  @Override
  public void onCreate(){
    super.onCreate();
    UserSerializer serializer = new UserSerializer(this, USERSTORE);
    userStore = new UserStore(serializer);

    CacheSerializer cacheSerializer = new CacheSerializer(this, CACHESTORE);
    cacheStore = new CacheStore(cacheSerializer);

    Log.v("Geo", "Geo App Launched");
  }
}