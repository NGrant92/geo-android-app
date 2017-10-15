package app.geo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import app.geo.R;
import app.geo.model.Settings;

/**
 * Created by niall on 15/10/17.
 */

public class Base extends AppCompatActivity {

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

  public void menuSettings(MenuItem m) {
    goToActivity(this, Settings.class, null);
  }

  public void menuHome(MenuItem m) {
    goToActivity(this, GeoMenu.class, null);
  }

  protected void toastMessage(String s) {
    Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
  }
}
