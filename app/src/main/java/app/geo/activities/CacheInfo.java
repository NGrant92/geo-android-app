package app.geo.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import app.geo.R;
import app.geo.main.GeoApp;
import app.geo.models.Cache;
import app.geo.models.CacheStore;

/**
 * Created by niall on 09/10/17.
 */

public class CacheInfo extends Base implements TextWatcher, CompoundButton.OnCheckedChangeListener {

  public TextView cacheName;
  public TextView cacheLocation;
  public TextView cacheDescription;
  public ImageView starIcon;
  public Boolean isFavourite;

  public CacheStore cacheStore;
  public Cache cache;

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_cache);

    cacheName = (TextView)findViewById(R.id.cacheInfoName);

    cacheLocation = (TextView)findViewById(R.id.cacheInfoLocation);

    cacheDescription = (TextView)findViewById(R.id.cacheInfoDescription);

    GeoApp app = (GeoApp)getApplication();
    cacheStore = app.cacheStore;

    int resId = (int)getIntent().getExtras().getSerializable("cache_id");
    cache = cacheStore.getCache(resId);

     starIcon = (ImageView)findViewById(R.id.cacheInfoStar);

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
