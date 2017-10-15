package app.geo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import app.geo.R;
import app.geo.main.GeoApp;
import app.geo.model.Cache;

/**
 * Created by niall on 09/10/17.
 */

public class AddCache extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_cache);
  }

  public void addGeoPressed(View view){

    TextView name = (TextView)findViewById(R.id.addCacheName);
    TextView location = (TextView)findViewById(R.id.addCacheLocation);
    TextView description = (TextView)findViewById(R.id.addCacheDescription);

    Cache cache = new Cache(name.getText().toString(), location.getText().toString(), description.getText().toString());
    GeoApp app = (GeoApp)getApplication();
    app.newGeo(cache);

    Toast.makeText(this, "Cache Added!", Toast.LENGTH_SHORT).show();
    startActivity(new Intent(this, GeoMenu.class));
  }
}
