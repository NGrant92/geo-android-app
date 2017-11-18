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
 * @author Niall Grant 05/11/2017
 * This activity is used to add a new cache
 *
 * The user is required to fill in all 3 fields (name, location & description)
 * If fields are empty they are prompted with a toast message
 * If fields are full then the Cache is added and saved
 */

public class AddCache extends Base {
  public GeoApp app = GeoApp.getInstance();
  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_cache);
  }

  public void addCachePressed(View view){
    GeoApp app = (GeoApp)getApplication();
    User currUser = app.currUser;

    TextView name = (TextView)findViewById(R.id.addCacheName);
    TextView location = (TextView)findViewById(R.id.addCacheLocation);
    TextView description = (TextView)findViewById(R.id.addCacheDescription);

    if(name.length() == 0 || location.length() == 0 || description.length() == 0){
      toastMessage("Please ensure no empty fields");
    }
    else{
      Cache cache = new Cache(name.getText().toString(),
          location.getText().toString(),
          description.getText().toString(),
          app.googleMail,
          app.mCurrentLocation.getLatitude(),
          app.mCurrentLocation.getLongitude());

      app.cacheStore.addCache(cache);
      app.cacheStore.saveCaches();

      Toast.makeText(this, "Cache Added!", Toast.LENGTH_SHORT).show();
      //startActivity(new Intent(this, GeoHome.class));
    }
  }
}
