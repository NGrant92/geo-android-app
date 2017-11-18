package app.geo.activities;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

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

    TextView name = (TextView)findViewById(R.id.addCacheName);
    TextView description = (TextView)findViewById(R.id.addCacheDescription);

    if(name.length() == 0 || description.length() == 0){
      toastMessage("Please ensure no empty fields");
    }
    else{

      Cache cache = new Cache(name.getText().toString(),
          getAddress(app.mCurrentLocation),
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

  public String getAddress(Location location){
    Geocoder geocoder = new Geocoder(this);

    String strAddress = "";
    Address address;

    try{
      address = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1).get(0);
      strAddress = address.getAddressLine(0);

      if(address.getAddressLine(1) != null){
        strAddress += " " + address.getAddressLine(1);
      }
      if(address.getAddressLine(2) != null){
        strAddress += " " + address.getAddressLine(2);
      }
    }
    catch (IOException err){
      Log.v("Geo", String.valueOf(err));
    }
    Log.v("Geo", "getAddress(): " + strAddress);
    return strAddress;
  }
}
