package app.geo.fragments;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import app.geo.R;
import app.geo.main.GeoApp;

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

  }
  @Override
  public void onMapReady(GoogleMap googleMap) {

  }
}
