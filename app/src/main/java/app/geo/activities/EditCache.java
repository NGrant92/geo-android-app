package app.geo.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import app.geo.R;
import app.geo.main.GeoApp;
import app.geo.models.Cache;
import app.geo.models.CacheStore;

/**
 * Created by niall on 09/10/17.
 */

public class EditCache extends Base implements TextWatcher, CompoundButton.OnCheckedChangeListener {

  public EditText cacheName;
  public EditText cacheLocation;
  public EditText cacheDescription;
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

    cacheLocation = (EditText)findViewById(R.id.editCacheLocation);
    cacheLocation.addTextChangedListener(this);

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
    cacheLocation.setText(cache.location);
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
    String newLocation = ((TextView) findViewById(R.id.editCacheLocation)).getText().toString();
    String newDescription = ((TextView) findViewById(R.id.editCacheDescription)).getText().toString();

    if (isNew(newName, cache.name)) {
      cache.name = newName;
    }
    if (isNew(newLocation, cache.location)) {
      cache.location = newLocation;
    }
    if (isNew(newDescription, cache.description)) {
      cache.description = newDescription;
    }
    cacheStore.saveCaches();

    toastMessage("Cache updated!");
    goToActivity(this, MyCache.class, null);
  }

  public void deleteCacheButtonPressed(View view){

    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setMessage("Are you sure you want to Delete the \'Coffee\' " + cache.name + "?");
    builder.setCancelable(false);

    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface dialog, int id)
      {
        cacheStore.remCache(cache);
        cacheStore.saveCaches();
        toastMessage(cache.name + " removed!");
        goToActivity(EditCache.this, MyCache.class, null);
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
}
