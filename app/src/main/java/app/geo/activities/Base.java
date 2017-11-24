package app.geo.activities;

import android.app.Activity;
import android.content.Context;
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
 * @author Niall Grant 05/11/2017
 *
 * This is a Base class that is extended by all activity classes
 * It stores general methods that are used by a number of classes such as the top menu
 *
 * Main reference source: 7(a) UI Design 1 & App Structure
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
      case R.id.menuHome:
        goToActivity(this, GeoHome.class, null);
        break;
    }
    return true;
  }

  protected void toastMessage(String s) {
    Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
  }
}
