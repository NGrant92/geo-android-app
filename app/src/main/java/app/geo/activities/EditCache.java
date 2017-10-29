package app.geo.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CompoundButton;
import android.widget.EditText;

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

    if(cache != null){
      updateControls(cache);
    }
  }

  public void updateControls(Cache cache){
    cacheName.setText(cache.name);
    cacheLocation.setText(cache.location);
    cacheDescription.setText(cache.description);
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
