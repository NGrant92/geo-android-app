package app.geo.adapters;


import app.geo.activities.Base;
import app.geo.main.GeoApp;
import app.geo.models.Cache;
import app.geo.R;
import app.geo.models.User;
import app.geo.models.UserStore;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class CacheItem extends Base {
  View view;

  public CacheItem(Context context, ViewGroup parent, Cache cache){

    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    view = inflater.inflate(R.layout.cache_row, parent, false);
    view.setId(cache.cacheId);

    updateControls(cache);
  }

  public void updateControls(Cache cache){
    ((TextView) view.findViewById(R.id.cacheRowName)).setText(cache.name);
    ((TextView) view.findViewById(R.id.cacheRowLocation)).setText(cache.location);

    UserStore currStore = GeoApp.getInstance().userStore;
    User owner = currStore.getUser(cache.ownerId);
    ((TextView) view.findViewById(R.id.cacheRowOwner)).setText(owner.firstName + " " + owner.lastName);
  }
}
