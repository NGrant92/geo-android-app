package app.geo.activity;

import android.os.Bundle;

import app.geo.R;

/**
 * Created by niall on 09/10/17.
 */

public class Map extends Base {
  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.fragment_map);
  }
}
