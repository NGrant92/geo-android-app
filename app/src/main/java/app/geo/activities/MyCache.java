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
import app.geo.main.GeoApp;
import app.geo.models.Cache;

/**
 * Created by niall on 09/10/17.
 */

public class MyCache extends Base  implements AdapterView.OnItemClickListener{

  public ListView listView;
  public GeoApp app;

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_my_cache);

    listView = (ListView)findViewById(R.id.myCacheList);

    app = (GeoApp)getApplication();

    CacheAdapter adapter = new CacheAdapter(this, app.cacheStore.caches);
    listView.setAdapter(adapter);
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    startActivity(new Intent(this, EditCache.class));
  }
}

class CacheAdapter extends ArrayAdapter<Cache> {
  public Context context;
  public List<Cache> caches;

  public CacheAdapter(Context context, List<Cache> caches){
    super(context, R.layout.cache_row_layout, caches);
    this.context = context;
    this.caches = caches;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {

    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    ViewHolder holder;

    if(convertView == null && inflater != null){
      convertView = inflater.inflate(R.layout.cache_row_layout, parent, false);
      holder = new ViewHolder();
      holder.cache = caches.get(position);
      holder.name = (TextView) convertView.findViewById(R.id.cache_name);
      holder.location = (TextView) convertView.findViewById(R.id.cache_location);

      holder.name.setText(String.valueOf(holder.cache.name));
      holder.location.setText(String.valueOf(holder.cache.location));
    }
    else{
      holder = (ViewHolder) convertView.getTag();
    }
    return convertView;
  }

  static class ViewHolder {
    private Cache cache;
    private TextView name;
    private TextView location;
  }
}
