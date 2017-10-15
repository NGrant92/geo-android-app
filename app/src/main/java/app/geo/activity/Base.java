package app.geo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.geo.R;
import app.geo.model.Cache;
import app.geo.model.Settings;
import app.geo.model.Signup;
import app.geo.model.User;
import app.geo.model.Welcome;

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

  @Override
  public boolean onOptionsItemSelected(MenuItem item){
    switch(item.getItemId()){
      case R.id.menuSettings:
        goToActivity(this, Settings.class, null);
        break;
      case R.id.menuHome:
        goToActivity(this, GeoMenu.class, null);
        break;
    }
    return true;
  }

  protected void toastMessage(String s) {
    Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
  }
}