package app.geo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import app.geo.R;
import app.geo.activity.AddGeo;
import app.geo.activity.GeoList;
import app.geo.activity.Mail;
import app.geo.activity.Map;
import app.geo.activity.MyGeos;
import app.geo.model.Settings;

/**
 * Created by niall on 08/10/17.
 */

public class GeoMenu extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_geomenu);
  }

  public void mapButtonPressed(View view){
    startActivity(new Intent(this, Map.class));
  }

  public void geoListButtonPressed(View view){
    startActivity(new Intent(this, GeoList.class));
  }

  public void myGeosButtonPressed(View view){
    startActivity(new Intent(this, MyGeos.class));
  }

  public void addGeoButtonPressed(View view){
    startActivity(new Intent(this, AddGeo.class));
  }

  public void mailButtonPressed(View view){
    startActivity(new Intent(this, Mail.class));
  }

  public void settingsButtonPressed(View view){
    startActivity(new Intent(this, Settings.class));
  }
}
