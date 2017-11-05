package app.geo.activities;

import android.os.Bundle;
import android.util.Log;

import app.geo.R;

/**
 * @author Niall Grant 05/11/2017
 *
 * This activity is the main activity that displays the integrated google maps api
 *
 * Main reference source: 9 Google Services
 */

public class Map extends Base {
  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);

    Log.v("Geo", "Map oncreate");
    setContentView(R.layout.fragment_map);
  }
}
