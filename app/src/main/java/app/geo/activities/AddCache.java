package app.geo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import app.geo.R;
import app.geo.main.GeoApp;
import app.geo.models.Cache;
import app.geo.models.User;

/**
 * Created by niall on 09/10/17.
 */

public class AddCache extends Base {
  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_cache);
  }

  public void addGeoPressed(View view){
    GeoApp app = (GeoApp)getApplication();
    User currUser = app.currUser;

    TextView name = (TextView)findViewById(R.id.addCacheName);
    TextView location = (TextView)findViewById(R.id.addCacheLocation);
    TextView description = (TextView)findViewById(R.id.addCacheDescription);

    Cache cache = new Cache(name.getText().toString(), location.getText().toString(), description.getText().toString(), currUser.userId);
    app.cacheStore.addCache(cache);
    app.cacheStore.saveCaches();

    Toast.makeText(this, "Cache Added!", Toast.LENGTH_SHORT).show();
    startActivity(new Intent(this, GeoMenu.class));
  }
}