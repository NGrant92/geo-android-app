package app.geo.adapters;


import android.widget.Filter;

import java.io.FilterReader;
import java.util.ArrayList;

import app.geo.main.GeoApp;
import app.geo.models.Cache;

public class CacheFilter extends Filter{
  private ArrayList<Cache> caches;
  private String filter;
  private CacheListAdapter adapter;
  private GeoApp app;

  public CacheFilter(ArrayList<Cache> caches, String filter, CacheListAdapter adapter){
    super();
    this.caches = caches;
    this.filter = filter;
    this.adapter = adapter;

    app = GeoApp.getInstance();
  }

  public void setFilter(String filter){
    this.filter = filter;
  }

  @Override
  protected FilterResults performFiltering(CharSequence prefix){
    FilterResults results = new FilterResults();

    if(caches == null){
      caches = new ArrayList<Cache>();
    }
    if(prefix == null || prefix.length() == 0){
      ArrayList<Cache> newCaches = new ArrayList<Cache>();
      if (filter.equals("all")){
        results.values = caches;
        results.count = caches.size();
      }
      else{
        if(filter.equals("mycache")){
          int userid = app.currUser.userId;
          for (Cache c : caches){
            if(c.ownerId == userid){
              newCaches.add(c);
            }
          }
        }
        results.values = newCaches;
        results.count = newCaches.size();
      }
    }
    else{
      String prefixString = prefix.toString().toLowerCase();
      final ArrayList<Cache> newCaches = new ArrayList<Cache>();

      for(Cache c : caches){
        final String cacheName = c.name.toLowerCase();
        if(cacheName.contains(prefixString)){
          if(filter.equals("all")){
            newCaches.add(c);
          }
          else if(c.favourite){
            newCaches.add(c);
          }
        }
      }
      results.values = newCaches;
      results.count = newCaches.size();
    }
    return results;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void publishResults(CharSequence prefix, FilterResults results) {

    adapter.caches = (ArrayList<Cache>) results.values;

    if (results.count >= 0)
      adapter.notifyDataSetChanged();
    else {
      adapter.notifyDataSetInvalidated();
      adapter.caches = caches;
    }
  }
}
