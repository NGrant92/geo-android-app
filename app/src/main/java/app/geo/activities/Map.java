package app.geo.activities;

import android.os.Bundle;
import android.util.Log;

import app.geo.R;

/**
 * Created by niall on 09/10/17.
 */

public class Map extends Base {
  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);

    Log.v("Geo", "Map oncreate");
    setContentView(R.layout.fragment_map);
  }
}
