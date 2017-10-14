package app.geo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import app.geo.R;
import app.geo.main.GeoApp;
import app.geo.model.Geo;

/**
 * Created by niall on 09/10/17.
 */

public class AddGeo extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_addgeo);
  }

  public void addGeoPressed(View view){

    TextView name = (TextView)  findViewById(R.id.addGeoName);
    TextView location = (TextView)  findViewById(R.id.addGeoLocation);
    TextView description = (TextView)  findViewById(R.id.addGeoDescription);

    Geo geo = new Geo(name.getText().toString(), location.getText().toString(), description.getText().toString());
    GeoApp app = (GeoApp)getApplication();
    app.newGeo(geo);

    Toast.makeText(this, "Geo Added!", Toast.LENGTH_SHORT).show();
    startActivity(new Intent(this, GeoMenu.class));
  }
}
