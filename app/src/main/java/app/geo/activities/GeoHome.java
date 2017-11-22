package app.geo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

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

public class GeoHome extends Base {

  public GeoApp app = GeoApp.getInstance();

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_geo_home);

    //String menuTitle = app.currUser.firstName + "'s Geo Menu";
    String menuTitle = "Geo Home Menu";
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
    Toast.makeText(this, "Under Development", Toast.LENGTH_LONG).show();
    //goToActivity(this, Settings.class, null);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu){
    getMenuInflater().inflate(R.menu.geo_home_menu, menu);
    return true;
  }

  public void menuSettings(MenuItem m){
    goToActivity(this, Settings.class, null);
  }

  //lab09 Google Services
  public void menuLogOut(MenuItem m) {

    //https://stackoverflow.com/questions/38039320/googleapiclient-is-not-connected-yet-on-logout-when-using-firebase-auth-with-g
    app.mGoogleApiClient.connect();
    app.mGoogleApiClient.registerConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
      @Override
      public void onConnected(@Nullable Bundle bundle) {

        //FirebaseAuth.getInstance().signOut();
        if(app.mGoogleApiClient.isConnected()) {
          Auth.GoogleSignInApi.signOut(app.mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
              if (status.isSuccess()) {
                Log.v("Geo", "User Logged out");
                Intent intent = new Intent(GeoHome.this, Login.class);
                startActivity(intent);
                finish();
              }
            }
          });
        }
      }

      @Override
      public void onConnectionSuspended(int i) {
        Log.d("Geo", "Google API Client Connection Suspended");
      }
    });
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
