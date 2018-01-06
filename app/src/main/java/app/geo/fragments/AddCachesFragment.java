package app.geo.fragments;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.InflateException;
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
import app.geo.activities.Camera;
import app.geo.helpers.ContactHelper;
import app.geo.main.GeoApp;
import app.geo.models.Cache;
import static app.geo.helpers.CameraHelper.showPhoto;
import android.widget.ImageView;

import static app.geo.helpers.MapHelper.getAddress;

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

  private static final int REQUEST_PHOTO = 0;
  private static View v;

  private EditText name, description;
  public String photo = "photo";
  private ImageView cameraButton;
  private ImageView photoView;

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

    //Ref: https://stackoverflow.com/a/14695397
    if(v != null){
      ViewGroup parent = (ViewGroup) v.getParent();
      if(parent != null){
        parent.removeView(v);
      }
    }

    try{
      v = inflater.inflate(R.layout.fragment_add_cache, container, false);
    }
    catch(InflateException err){
      Log.v("Geo", "ADD CACHE ON CREATE VIEW ERROR: " + err);
    }

    Button addCacheButton = (Button) v.findViewById(R.id.addCacheButton);
    name = (EditText) v.findViewById(R.id.addCacheName);
    description = (EditText) v.findViewById(R.id.addCacheDescription);

    photoView = (ImageView) v.findViewById(R.id.addCacheImage);
    cameraButton = (ImageView) v.findViewById(R.id.addCacheCameraButton);
    cameraButton.setOnClickListener(this);

    addCacheButton.setOnClickListener(this);

    return v;
  }

  @Override
  public void onClick(View v) {

    switch(v.getId()){
      case R.id.addCacheCameraButton:
        Intent ic = new Intent(getActivity(), Camera.class);
        startActivityForResult(ic, REQUEST_PHOTO);
        break;

      case R.id.addCacheButton:
        if(name.length() == 0 || description.length() == 0){
          Toast.makeText(getActivity(), "Please ensure no empty fields", Toast.LENGTH_SHORT).show();
        }
        else{
          Cache cache = new Cache(name.getText().toString(),
              getAddress(app.mCurrentLocation, getActivity()),
              description.getText().toString(),
              app.googleToken,
              app.googleName,
              app.googleMail,
              photo,
              app.mCurrentLocation.getLatitude(),
              app.mCurrentLocation.getLongitude());

          app.cacheStore.addCache(cache);
          app.cacheStore.saveCaches();

          Toast.makeText(getActivity(), "Cache Added!", Toast.LENGTH_SHORT).show();
          //startActivity(new Intent(this, GeoHome.class));
        }
    }
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

  @Override
  public void onStart(){
    super.onStart();

    showPhoto(getActivity(), this, photoView);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data)
  {
    if (resultCode != Activity.RESULT_OK)
    {
      return;
    }
    switch (requestCode)
    {
//      case REQUEST_CONTACT:
//        String name = ContactHelper.getContact(getActivity(), data);
//        residence.tenant = name;
//        tenantButton.setText(name);
//        break;
      case REQUEST_PHOTO:
        String filename = data.getStringExtra(Camera.EXTRA_PHOTO_FILENAME);
        if (filename != null)
        {
          photo = filename;
          showPhoto(getActivity(), this, photoView );
        }
        break;
    }
  }
}
