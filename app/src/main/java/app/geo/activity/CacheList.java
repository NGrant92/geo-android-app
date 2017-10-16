package app.geo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import app.geo.R;
import app.geo.main.GeoApp;
import app.geo.model.Cache;

/**
 * Created by niall on 09/10/17.
 */

public class CacheList extends Base {

  public ListView listView;
  public GeoApp app;

  @Override
  protected void onCreate(Bundle savedInstanceState){

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cache_list);

    app = (GeoApp)getApplication();

    listView = (ListView)findViewById(R.id.cache_list);
    CacheAdapter adapter = new CacheAdapter(this, app.cacheStore.caches);
    listView.setAdapter(adapter);
  }
}

class CacheAdapter extends ArrayAdapter<Cache>{
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
