package app.geo.model;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by niall on 16/10/17.
 */

public class CacheStore {

  public ArrayList<Cache> caches;
  private CacheSerializer serializer;

  public CacheStore(CacheSerializer serializer){
    this.serializer = serializer;
    try{
      caches = serializer.loadCache();
    }
    catch(Exception e){
      caches = new ArrayList<Cache>();
    }
  }

  public void addCache(Cache cache){
    caches.add(cache);
  }

  public boolean saveCaches(){
    try{
      serializer.saveCache(caches);
      Log.v("Geo", "Cache saved!");
      return true;
    }
    catch(Exception e){
      Log.v("Geo", "Cache Save Error!");
      return false;
    }
  }
}
