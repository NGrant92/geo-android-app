package app.geo.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import app.geo.R;
import app.geo.adapters.CacheListAdapter;
import app.geo.fragments.CachesFragment;
import app.geo.main.GeoApp;
import app.geo.models.Cache;

/**
 * Created by niall on 09/10/17.
 */

public class CacheList extends Base implements AdapterView.OnItemClickListener {

  public GeoApp app;
  public CacheListAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState){

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cache_list);
    app = (GeoApp)getApplication();

    adapter = new CacheListAdapter(this, app.cacheStore.caches);
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    Cache cache = adapter.getItem(position);
    Intent intent = new Intent(this, EditCache.class);
    intent.putExtra("cache_id", cache.cacheId);

    startActivity(intent);
  }

  @Override
  public void onResume() {
    super.onResume();
    adapter.notifyDataSetChanged();

    if(app.cacheStore.getCaches() != null){
      cacheFragment = CachesFragment.newInstance();
      getFragmentManager().beginTransaction().add(R.id.fragment_layout_full, cacheFragment).commit();
    }
  }
}
