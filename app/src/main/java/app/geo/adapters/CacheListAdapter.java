package app.geo.adapters;

import app.geo.models.Cache;
import app.geo.R;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * @author Niall Grant 05/11/2017
 * This adapter is used to format the list of caches
 *
 * Main reference source: 7(a) UI Design 1 & App Structure
 */

public class CacheListAdapter extends ArrayAdapter<Cache> {
  public Context context;
  public List<Cache> caches;

  public CacheListAdapter(Context context, List<Cache> caches){
    super(context, R.layout.cache_row, caches);
    this.context = context;
    this.caches = caches;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {

    CacheItem item = new CacheItem(context, parent, caches.get(position));
    return item.view;
  }

  public int getCount(){
    return caches.size();
  }

  @Override
  public Cache getItem(int position) {
    return caches.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public int getPosition(Cache c) {
    return caches.indexOf(c);
  }
}