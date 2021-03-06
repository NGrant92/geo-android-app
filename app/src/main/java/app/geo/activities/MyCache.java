package app.geo.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AbsListView.MultiChoiceModeListener;
import app.geo.adapters.CacheListAdapter;

import java.util.List;

import app.geo.R;
import app.geo.main.GeoApp;
import app.geo.models.Cache;
import app.geo.fragments.CachesFragment;

/**
 * @author Niall Grant 05/11/2017
 *
 * This activity is used to display the list of the user's existing caches
 * From this activity if the user clicks on a Cache Item they are
 * directed to EditCache.java and given the ability to edit the information
 *
 * Main reference source: 7(a) UI Design 1 & App Structure
 */

public class MyCache extends Base implements AdapterView.OnItemClickListener{

  public GeoApp app;
  public CacheListAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_my_cache);

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
  public void onResume()
  {
    super.onResume();
    adapter.notifyDataSetChanged();

    if(app.cacheStore.getCaches() != null){
      cacheFragment = CachesFragment.newInstance();
      getFragmentManager().beginTransaction().add(R.id.fragment_layout, cacheFragment).commit();
    }
  }

  private void deleteCaches(ActionMode mode) {
    toastMessage("deleteCaches pressed");
  }
}


