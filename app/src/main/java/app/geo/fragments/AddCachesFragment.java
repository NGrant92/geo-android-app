package app.geo.fragments;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.geo.R;
import app.geo.main.GeoApp;
import app.geo.models.Cache;

/**
 * @author Niall Grant 05/11/2017
 * This activity is used to add a new cache
 *
 * The user is required to fill in all 2 fields (name & description)
 * If fields are empty they are prompted with a toast message
 * If fields are full then the Cache is added and saved
 * The app also takes the user's current location and adds it to the cache
 */
public class AddCachesFragment extends Fragment implements View.OnClickListener, OnMapReadyCallback {
  private EditText name, description;

  public GeoApp app = GeoApp.getInstance();


  public AddCachesFragment() {
    // Required empty public constructor
  }

  public static AddCachesFragment newInstance() {
    AddCachesFragment fragment = new AddCachesFragment();
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

    View v = inflater.inflate(R.layout.fragment_add_cache, container, false);
    //View v = super.onCreateView(inflater, container, savedInstanceState);

    Button addCacheButton = (Button) v.findViewById(R.id.addCacheButton);
    name = (EditText) v.findViewById(R.id.addCacheName);
    description = (EditText) v.findViewById(R.id.addCacheDescription);

    addCacheButton.setOnClickListener(this);

    return v;
  }

  @Override
  public void onClick(View v) {

    if(name.length() == 0 || description.length() == 0){
      Toast.makeText(getActivity(), "Please ensure no empty fields", Toast.LENGTH_SHORT).show();
    }
    else{

      Cache cache = new Cache(name.getText().toString(),
          getAddress(app.mCurrentLocation),
          description.getText().toString(),
          app.googleMail,
          app.mCurrentLocation.getLatitude(),
          app.mCurrentLocation.getLongitude());

      app.cacheStore.addCache(cache);
      app.cacheStore.saveCaches();

      Toast.makeText(getActivity(), "Cache Added!", Toast.LENGTH_SHORT).show();
      //startActivity(new Intent(this, GeoHome.class));
    }
  }

  public String getAddress(Location location){
    Geocoder geocoder = new Geocoder(getActivity());

    String strAddress = "";
    Address address;

    try{
      address = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1).get(0);
      strAddress = address.getAddressLine(0);

      if(address.getAddressLine(1) != null){
        strAddress += " " + address.getAddressLine(1);
      }
      if(address.getAddressLine(2) != null){
        strAddress += " " + address.getAddressLine(2);
      }
    }
    catch (IOException err){
      Log.v("Geo", String.valueOf(err));
    }
    Log.v("Geo", "getAddress(): " + strAddress);
    return strAddress;
  }


  @Override
  public void onMapReady(GoogleMap googleMap) {
    googleMap.clear();
    addCaches(app.cacheStore.caches, googleMap);
  }

  public void addCaches(ArrayList<Cache> list, GoogleMap googleMap){
    for(Cache c : list) {
      googleMap.addMarker(new MarkerOptions()
          .position(new LatLng(c.latitude, c.longitude))
          .title(c.name)
          .snippet(c.location)
          .icon(BitmapDescriptorFactory.fromResource(android.R.drawable.star_off)));
    }
  }
}
