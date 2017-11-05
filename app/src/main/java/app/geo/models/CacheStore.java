package app.geo.models;

import android.util.Log;

import java.util.ArrayList;

/**
 * @author Niall Grant 05/11/2017
 * A class that loads the Cache data from the CacheSerializer on app start up
 *
 * Main reference source: 4(a) Navigation and LifeCycles (Siobhán) Lab 2
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

  public void remCache(Cache cache){
    caches.remove(cache);
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

  public Cache getCache(int id){
    for(Cache cache : caches){
      if(id == cache.cacheId){
        return cache;
      }
    }
    return null;
  }

  public ArrayList<Cache> getCaches(){
    return caches;
  }
}
