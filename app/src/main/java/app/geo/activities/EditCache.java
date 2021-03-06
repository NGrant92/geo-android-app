package app.geo.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import app.geo.R;
import app.geo.main.GeoApp;
import app.geo.models.Cache;
import app.geo.models.CacheStore;

import static app.geo.helpers.MapHelper.getAddress;
/**
 * @author Niall Grant 05/11/2017
 *
 * This activity is used to edit an existing Cache.
 * When a user clicks on a Coffee Item in MyCache.java they are directed
 * to this activity. MyCache.java only displays the Caches a user has created
 * so they can only edit the ones they created
 *
 * Main reference source: 7(a) UI Design 1 & App Structure
 */

public class EditCache extends Base implements TextWatcher, CompoundButton.OnCheckedChangeListener {

  GeoApp app = GeoApp.getInstance();

  public EditText cacheName;
  public String cacheLocation;
  public double latitude;
  public double longitude;
  public EditText cacheDescription;
  public CheckBox checkSetLocation;
  public ImageView starIcon;
  public Boolean isFavourite;

  public CacheStore cacheStore;
  public Cache cache;

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_cache);

    cacheName = (EditText)findViewById(R.id.editCacheName);
    cacheName.addTextChangedListener(this);

    checkSetLocation = (CheckBox) findViewById(R.id.editCacheCheckBox);

    cacheDescription = (EditText)findViewById(R.id.editCacheDescription);
    cacheDescription.addTextChangedListener(this);

    GeoApp app = (GeoApp)getApplication();
    cacheStore = app.cacheStore;

    int resId = (int)getIntent().getExtras().getSerializable("cache_id");
    cache = cacheStore.getCache(resId);

     starIcon = (ImageView)findViewById(R.id.editCacheStar);

    if(cache != null){
      updateControls(cache);
    }
  }

  public void updateControls(Cache cache){
    cacheName.setText(cache.name);
    cacheDescription.setText(cache.description);

    if(cache.favourite){
      starIcon.setImageResource(R.drawable.star_green_full);
      isFavourite = true;
    }
    else{
      starIcon.setImageResource(R.drawable.star_green_empty);
      isFavourite = false;
    }
  }

  public void updateCacheButtonPressed(View view) {

    String newName = ((TextView) findViewById(R.id.editCacheName)).getText().toString();
    String newDescription = ((TextView) findViewById(R.id.editCacheDescription)).getText().toString();

    if (isNew(newName, cache.name)) {
      cache.name = newName;
    }
    if (isNew(newDescription, cache.description)) {
      cache.description = newDescription;
    }
    if(checkSetLocation.isChecked()){
      cache.location = getAddress(app.mCurrentLocation, this);
      cache.latitude = app.mCurrentLocation.getLatitude();
      cache.longitude = app.mCurrentLocation.getLongitude();
    }
    cacheStore.saveCaches();

    toastMessage("Cache updated!");
  }

  public void deleteCacheButtonPressed(View view){

    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setMessage("Are you sure you want to Delete the \'Cache\' " + cache.name + "?");
    builder.setCancelable(false);

    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface dialog, int id)
      {
        cacheStore.remCache(cache);
        cacheStore.saveCaches();
        toastMessage(cache.name + " removed!");
        finish();
      }
    }).setNegativeButton("No", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface dialog, int id)
      {
        dialog.cancel();
      }
    });
    AlertDialog alert = builder.create();
    alert.show();
  }

  public void toggle(View view){
    if(isFavourite){
      cache.favourite = false;
      isFavourite = false;
      toastMessage("Removed from favourites");

      starIcon.setImageResource(R.drawable.star_green_empty);
    }
    else{
      cache.favourite = true;
      isFavourite = true;
      toastMessage("Added to favourites");

      starIcon.setImageResource(R.drawable.star_green_full);
    }
    cacheStore.saveCaches();
  }

  public boolean isNew(String newString, String currString){
    return newString != null && !newString.equals(currString);
  }

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {

  }

  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {

  }

  @Override
  public void afterTextChanged(Editable s) {

  }

  @Override
  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

  }


  public String getAddress(Location location, Context context){
    Geocoder geocoder = new Geocoder(context);

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
