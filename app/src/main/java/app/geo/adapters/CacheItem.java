package app.geo.adapters;


import app.geo.activities.Base;
import app.geo.activities.GeoHome;
import app.geo.fragments.CachesFragment;
import app.geo.main.GeoApp;
import app.geo.models.Cache;
import app.geo.R;
import app.geo.models.User;
import app.geo.models.UserStore;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;


import static app.geo.helpers.CameraHelper.showPhoto;

/**
 * @author Niall Grant 05/11/2017
 * This adapter is used as a template to display the information of a particular cache
 *
 * Main reference source: 7(a) UI Design 1 & App Structure
 */

public class CacheItem extends Base {
  View view;
  Context mContext;

  public CacheItem(Context context, ViewGroup parent, Cache cache){

    this.mContext = context;
    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    view = inflater.inflate(R.layout.cache_row, parent, false);
    view.setId(cache.cacheId);

    updateControls(cache);
  }

  public void updateControls(Cache cache){
    ((TextView) view.findViewById(R.id.cacheRowName)).setText(cache.name);
    ((TextView) view.findViewById(R.id.cacheRowLocation)).setText(cache.location);

    ImageView starIcon = (ImageView) view.findViewById(R.id.cacheRowStar);
    ImageView photo = (ImageView) view.findViewById(R.id.cacheRowPhoto);

    if(!cache.photo.equals("photo")){
      showPhoto((GeoHome)mContext, cache.photo, photo);
    }

    if(cache.favourite){
      starIcon.setImageResource(R.drawable.star_green_full);
    }
    else{
      starIcon.setImageResource(R.drawable.star_green_empty);
    }
  }
}
