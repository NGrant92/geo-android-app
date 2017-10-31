package app.geo.activities;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import app.geo.R;
import app.geo.fragments.CachesFragment;
import app.geo.main.GeoApp;

/**
 * Created by niall on 15/10/17.
 */

public class Base extends AppCompatActivity {

  public CachesFragment cacheFragment;

  protected void goToActivity(Activity current, Class<? extends Activity> activityClass, Bundle bundle) {
    Intent newActivity = new Intent(current, activityClass);

    if (bundle != null) newActivity.putExtras(bundle);

    current.startActivity(newActivity);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item){
    switch(item.getItemId()){
      case R.id.menuSettings:
        goToActivity(this, Settings.class, null);
        break;
      case R.id.menuHome:
        goToActivity(this, GeoMenu.class, null);
        break;
      case R.id.menuLogout:
        GeoApp app = (GeoApp)getApplication();
        app.currUser = null;
        goToActivity(this, Welcome.class, null);
        break;
    }
    return true;
  }

  protected void toastMessage(String s) {
    Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
  }
}
