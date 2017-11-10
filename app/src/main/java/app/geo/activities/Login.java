package app.geo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import app.geo.R;
import app.geo.main.GeoApp;
import app.geo.models.UserStore;

/**
 * @author Niall Grant 05/11/2017
 *
 * This activity is where the user is asked to log in using their email and password
 * If they do not enter the correct credentials then a toast message is displayed
 * If credentials are corrct then they are brought to the GeoMenu.java activity
 *
 * Main reference source: Lab 09 Google Services
 */

public class Login extends FragmentActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

  public UserStore userStore;
  public GeoApp app = GeoApp.getInstance();

  /* Request code used to invoke sign in user interactions. */
  private static final int RC_SIGN_IN = 0;
  private static final String TAG = "geoapp";

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);

    // [START configure_signin]
    // Configure sign-in to request the user's ID, email address, and basic
    // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
    app.mGoogleSignInOptions = new GoogleSignInOptions
        .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .requestProfile()
        .build();
    // [END configure_signin]

    app.mGoogleApiClient = new GoogleApiClient.Builder(this)
        .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
        .addApi(LocationServices.API)
        .addApi(Auth.GOOGLE_SIGN_IN_API, app.mGoogleSignInOptions)
        .build();


    setContentView(R.layout.activity_login);
    findViewById(R.id.sign_in_button).setOnClickListener(this);
    findViewById(R.id.disconnect_button).setOnClickListener(this);
  }

  @Override
  public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

  }

  @Override
  public void onClick(View v) {

  }

  /**
   * Once logged out, if they hit the back button, trying to get back to the main logged in menu,
   * they are exited from the app
   *
   * Reference: https://stackoverflow.com/a/26492794
   */
  @Override
  public void onBackPressed() {
    moveTaskToBack(true);
  }
}

