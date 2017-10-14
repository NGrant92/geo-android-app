package app.geo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import app.geo.R;
import app.geo.main.GeoApp;
import app.geo.model.Geo;

/**
 * Created by niall on 09/10/17.
 */

public class GeoList extends AppCompatActivity {

  public ListView listView;
  public GeoApp app;

  @Override
  protected void onCreate(Bundle savedInstanceState){

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_geolist);

    app = (GeoApp)getApplication();
    Log.v("Geo", "GEOS: " + app.geos.toString());

    listView = (ListView)findViewById(R.id.geos_List);
    GeoAdapter adapter = new GeoAdapter(this, app.geos);
    listView.setAdapter(adapter);
  }
}

class GeoAdapter extends ArrayAdapter<Geo>{
  public Context context;
  public List<Geo> geos;

  public GeoAdapter(Context context, List<Geo> geos){
    super(context, R.layout.geo_row_layout, geos);
    this.context = context;
    this.geos = geos;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {

    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    ViewHolder holder;

    if(convertView == null && inflater != null){
      Log.v("Geo", "convertView == null && inflater != null === true");
      convertView = inflater.inflate(R.layout.geo_row_layout, parent, false);
      holder = new ViewHolder();
      holder.geo = geos.get(position);
      holder.name = (TextView) convertView.findViewById(R.id.geo_name);
      holder.location = (TextView) convertView.findViewById(R.id.geo_location);

      holder.name.setText(String.valueOf(holder.geo.name));
      holder.location.setText(String.valueOf(holder.geo.location));
    }
    else{
      Log.v("Geo", "convertView == null && inflater != null === false");
      holder = (ViewHolder) convertView.getTag();
    }
    return convertView;
  }

  static class ViewHolder {
    private Geo geo;
    private TextView name;
    private TextView location;
  }
}
