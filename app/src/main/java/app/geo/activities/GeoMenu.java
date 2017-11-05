package app.geo.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import app.geo.R;
import app.geo.main.GeoApp;

/**
 * @author Niall Grant 05/11/2017
 *
 * This activity is the main menu for the user when they log in.
 * They are given a list of buttons that will direct them to the appropriate activity
 *
 * Main reference source: 2(b). Activities and Layouts (Siobhán)
 */

public class GeoMenu extends Base {

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_geo_menu);

    GeoApp app = (GeoApp)getApplication();
    String menuTitle = app.currUser.firstName + "'s Geo Menu";
    ((TextView)findViewById(R.id.menuTitle)).setText(menuTitle);
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

  /**
   * Once logged in, if they hit the back button, trying to get back to the login menu,
   * they are exited from the app
   *
   * Reference: https://stackoverflow.com/a/26492794
   */
  @Override
  public void onBackPressed() {
    moveTaskToBack(true);
  }
}
