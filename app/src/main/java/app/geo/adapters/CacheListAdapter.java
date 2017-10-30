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

public class CacheListAdapter extends ArrayAdapter<Cache> {
  public Context context;
  public List<Cache> caches;

  public CacheListAdapter(Context context, List<Cache> caches){
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

  public int getCount(){
    return caches.size();
  }

  @Override
  public Cache getItem(int position) {
    return caches.get(position);
  }

  @Override
  public long getItemId(int position) {
    //return coffeeList.get(position).coffeeId;
    return position;
  }

  @Override
  public int getPosition(Cache c) {
    return caches.indexOf(c);
  }
}