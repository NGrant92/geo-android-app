package app.geo.adapters;


import app.geo.activities.Base;
import app.geo.main.GeoApp;
import app.geo.models.Cache;
import app.geo.R;
import app.geo.models.User;
import app.geo.models.UserStore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Niall Grant 05/11/2017
 * This adapter is used as a template to display the information of a particular cache
 *
 * Main reference source: 7(a) UI Design 1 & App Structure
 */

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

    ImageView starIcon = (ImageView) view.findViewById(R.id.cacheRowStar);

    if(cache.favourite){
      starIcon.setImageResource(R.drawable.star_green_full);
    }
    else{
      starIcon.setImageResource(R.drawable.star_green_empty);
    }
  }
}
