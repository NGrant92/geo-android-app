package app.geo.main;

import android.app.Application;
import android.util.Log;

/**
 * Created by niall on 08/10/17.
 */

public class GeoMenu extends Application{

  @Override
  public void onCreate(){
    super.onCreate();
    Log.v("Geo", "Main GeoMenu Created");
  }
}
