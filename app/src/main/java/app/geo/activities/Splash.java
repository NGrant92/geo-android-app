package app.geo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import app.geo.R;

public class Splash extends Activity {
  // used to know if the back button was pressed in the splash screen activity
  // and avoid opening the next activity
  private boolean 			mIsBackButtonPressed;
  private static final int 	SPLASH_DURATION = 2000; // 2 seconds

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    Handler handler = new Handler();
    // run a thread after 2 seconds to start the home screen
    handler.postDelayed(new Runnable() {

      @Override
      public void run() {
        // make sure we close the splash screen so the user
        // won't come back when it presses back key
        finish();

        if (!mIsBackButtonPressed) {
          // start the home screen if the back button wasn't pressed already
          Intent intent = new Intent(Splash.this, Login.class);
          Splash.this.startActivity(intent);
        }
      }
    }, SPLASH_DURATION); // time in milliseconds to delay call to run()
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
