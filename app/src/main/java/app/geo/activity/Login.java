package app.geo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import app.geo.R;

/**
 * Created by niall on 10/10/17.
 */

public class Login extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
  }

  public void loginPressed(View view){
    startActivity(new Intent(this, GeoMenu.class));
  }
}

