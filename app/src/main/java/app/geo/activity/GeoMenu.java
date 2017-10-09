package app.geo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import app.geo.R;

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
    Toast.makeText(this, "Map Pressed!", Toast.LENGTH_SHORT).show();

    startActivity(new Intent(this, Map.class));
  }

  public void geoListButtonPressed(View view){
    Toast.makeText(this, "Geo List Pressed!", Toast.LENGTH_SHORT).show();

    startActivity(new Intent(this, GeoList.class));
  }

  public void myGeosButtonPressed(View view){
    Toast.makeText(this, "My Geo Pressed!", Toast.LENGTH_SHORT).show();

    startActivity(new Intent(this, MyGeos.class));
  }

  public void addGeoButtonPressed(View view){
    Toast.makeText(this, "Add Geo Pressed!", Toast.LENGTH_SHORT).show();

    startActivity(new Intent(this, AddGeo.class));
  }

  public void mailButtonPressed(View view){
    Toast.makeText(this, "Mail Pressed!", Toast.LENGTH_SHORT).show();

    startActivity(new Intent(this, Mail.class));
  }

  public void settingsButtonPressed(View view){
    Toast.makeText(this, "Settings Pressed!", Toast.LENGTH_SHORT).show();

    startActivity(new Intent(this, Settings.class));
  }
}
