package app.geo.activities;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import app.geo.R;
import app.geo.fragments.MapsFragment;
import app.geo.main.GeoApp;

/**
 * @author Niall Grant 05/11/2017
 *
 * This activity is the main menu for the user when they log in.
 * They are given a list of buttons that will direct them to the appropriate activity
 *
 * Main reference source: 8 Multithreading & Networking Lab-05
 */

public class GeoHome extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener,
    MapsFragment.OnFragmentInteractionListener {

  public GeoApp app = GeoApp.getInstance();
  private ImageView googlePhoto;
  public ProgressDialog dialog;

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_geo_home);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    //SetUp GooglePhoto and Email for Drawer here
    googlePhoto = (ImageView)navigationView.getHeaderView(0).findViewById(R.id.googlephoto);

    //Ref: https://www.androidhive.info/2014/02/android-login-with-google-plus-account-1/
    Glide.with(getApplicationContext()).load(app.googlePhoto)
        .thumbnail(1.0f)
        .crossFade()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(googlePhoto);

    TextView googleName = (TextView)navigationView.getHeaderView(0).findViewById(R.id.googlename);
    googleName.setText(app.googleName);

    TextView googleMail = (TextView)navigationView.getHeaderView(0).findViewById(R.id.googlemail);
    googleMail.setText(app.googleMail);

    FragmentTransaction ft = getFragmentManager().beginTransaction();

    MapsFragment fragment = MapsFragment.newInstance();
    ft.replace(R.id.homeFrame, fragment);
    ft.commit();
  }

  public void openInfoDialog(Activity current) {
    Dialog dialog = new Dialog(current);
    dialog.setTitle("About CoffeeMate");
    dialog.setContentView(R.layout.info);

    dialog.setCancelable(true);
    dialog.setCanceledOnTouchOutside(true);
    dialog.show();
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    return false;
  }

  @Override
  public void toggle(View v) {

  }

  @Override
  public void update(View v) {

  }
}
