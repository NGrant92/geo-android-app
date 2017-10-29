package app.geo.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import app.geo.R;
import app.geo.main.GeoApp;
import app.geo.models.Cache;

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
  }
}
