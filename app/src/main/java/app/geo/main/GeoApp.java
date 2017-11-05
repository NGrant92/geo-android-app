package app.geo.main;

import android.app.Application;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;

import app.geo.models.CacheSerializer;
import app.geo.models.CacheStore;
import app.geo.models.User;
import app.geo.models.UserSerializer;
import app.geo.models.UserStore;

/**
 * @author Niall Grant 05/11/2017
 * The main entry point for the application and is responsible for
 * loading the persisted data into the app
 *
 * Main reference source: 3(b) Navigation and App Structure (Siobhán)
 */

public class GeoApp extends Application{

  public User currUser;
  public UserStore userStore;
  public CacheStore cacheStore;
  public Location mCurrentLocation;
  public GoogleApiClient mGoogleApiClient;

  private static GeoApp mInstance;
  private static final String USERSTORE = "userstore.json";
  private static final String CACHESTORE = "cachestore.json";

  @Override
  public void onCreate(){
    super.onCreate();
    UserSerializer serializer = new UserSerializer(this, USERSTORE);
    userStore = new UserStore(serializer);

    CacheSerializer cacheSerializer = new CacheSerializer(this, CACHESTORE);
    cacheStore = new CacheStore(cacheSerializer);

    mInstance = this;

    Log.v("Geo", "Geo App Launched");
  }

  public static synchronized GeoApp getInstance(){
    return mInstance;
  }
}