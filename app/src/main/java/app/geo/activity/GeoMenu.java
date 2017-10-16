package app.geo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import app.geo.R;
import app.geo.main.GeoApp;
import app.geo.model.Settings;
import app.geo.model.Signup;
import app.geo.model.Welcome;

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
    goToActivity(this, Map.class, null);
  }

  public void cacheListButtonPressed(View view){
    goToActivity(this, CacheList.class, null);
  }

  public void myCacheButtonPressed(View view){
    goToActivity(this, MyCache.class, null);
  }

  public void addCacheButtonPressed(View view){
    goToActivity(this, AddCache.class, null);
  }

  public void mailButtonPressed(View view){
    goToActivity(this, Mail.class, null);
  }

  public void settingsButtonPressed(View view){
    goToActivity(this, Settings.class, null);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu){
    //to inflate the menu items for use in the action bar
    getMenuInflater().inflate(R.menu.geo_home_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item){
    switch(item.getItemId()){
      case R.id.menuSettings:
        goToActivity(this, Settings.class, null);
        break;
      case R.id.menuLogout:
        GeoApp app = (GeoApp)getApplication();
        app.currUser = null;
        goToActivity(this, Welcome.class, null);
        break;
    }
    return true;
  }
}
