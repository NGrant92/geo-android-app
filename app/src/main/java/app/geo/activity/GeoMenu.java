package app.geo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import app.geo.R;
import app.geo.model.Settings;

/**
 * Created by niall on 08/10/17.
 */

public class GeoMenu extends Base {

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_geo_menu);
  }

  public void mapButtonPressed(View view){
    startActivity(new Intent(this, Map.class));
  }

  public void cacheListButtonPressed(View view){
    startActivity(new Intent(this, CacheList.class));
  }

  public void myCacheButtonPressed(View view){
    startActivity(new Intent(this, MyCache.class));
  }

  public void addCacheButtonPressed(View view){
    startActivity(new Intent(this, AddCache.class));
  }

  public void mailButtonPressed(View view){
    startActivity(new Intent(this, Mail.class));
  }

  public void settingsButtonPressed(View view){
    startActivity(new Intent(this, Settings.class));
  }
}
