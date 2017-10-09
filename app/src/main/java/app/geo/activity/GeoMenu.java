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
}
