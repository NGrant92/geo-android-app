package app.geo.activities;

import app.geo.R;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

/**
 * @author Niall Grant 05/11/2017
 * An activity class used to take and save a photo of the cache object
 *
 * Main reference source: https://wit-hdip-computer-science.github.io/semester-2-mobile-app-dev/topic11-a/book-a-myrent-11%20(Camera)/index.html
 */

public class Camera extends AppCompatActivity implements OnClickListener
{

  private Button savePhoto;
  private Button takePhoto;
  private ImageView cacheImage;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_photo);

    cacheImage = (ImageView) findViewById(R.id.residenceImage);
    takePhoto = (Button)findViewById(R.id.takePhoto);
    savePhoto = (Button)findViewById(R.id.savePhoto);
    savePhoto.setEnabled(false);

    savePhoto.setOnClickListener(this);
    takePhoto.setOnClickListener(this);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  @Override
  public void onClick(View v)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    }
    else {
      super.onBackPressed();
    }
  }

}
