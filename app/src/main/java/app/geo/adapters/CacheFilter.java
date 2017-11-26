package app.geo.adapters;


import android.widget.Filter;

import java.io.FilterReader;
import java.util.ArrayList;

import app.geo.main.GeoApp;
import app.geo.models.Cache;

/**
 * @author Niall Grant 05/11/2017
 *
 * This adapter is used to filter the list of caches in the CachesFragment.java
 * This is used to ensure only the User's caches are displayed in MyCache
 *
 * Main reference source: 7(b) UI Design 2
 */

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
      switch (filter) {
        case "all":
          results.values = caches;
          results.count = caches.size();
          break;
        case "mycache":
          for (Cache c : caches) {
            if (c.ownerId.equals(app.googleToken)) {
              newCaches.add(c);
            }
          }
          results.values = newCaches;
          results.count = newCaches.size();
          break;
        case "favourites":
          for (Cache c : caches) {
            if (c.favourite) {
              newCaches.add(c);
            }
          }
          results.values = newCaches;
          results.count = newCaches.size();
          break;
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
