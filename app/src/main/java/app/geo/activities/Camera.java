package app.geo.activities;

import app.geo.R;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.content.Intent;
import android.widget.Toast;
import android.graphics.Bitmap;

import java.util.UUID;

import static app.geo.helpers.FileIOHelper.writeBitmap;

/**
 * @author Niall Grant 05/11/2017
 * An activity class used to take and save a photo of the cache object
 *
 * Main reference source: https://wit-hdip-computer-science.github.io/semester-2-mobile-app-dev/topic11-a/book-a-myrent-11%20(Camera)/index.html
 */


public class Camera extends AppCompatActivity implements OnClickListener
{
  private static  final int CAMERA_RESULT = 5;
  public static final String EXTRA_PHOTO_FILENAME = "app.geo.photo.filename";

  private Button savePhoto;
  private Button takePhoto;
  private ImageView cacheImage;
  private Bitmap cachePhoto;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_photo);

    cacheImage = (ImageView) findViewById(R.id.cacheImage);
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
    switch(v.getId())
    {
      case R.id.takePhoto: onTakePhotoClicked(v);
        break;

      case R.id.savePhoto: onPictureTaken(cachePhoto);
        break;
    }
  }

  public void onTakePhotoClicked(View v)
  {
    // Check for presence of device camera. If not present advise user and quit method.
    PackageManager pm = getPackageManager();
    if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
      Toast.makeText(this, "Camera app not present on this device", Toast.LENGTH_SHORT).show();
      return;
    }
    // The device has a camera app ... so use it.
    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
    startActivityForResult(cameraIntent,CAMERA_RESULT);
    savePhoto.setEnabled(true);
  }

  private void onPictureTaken(Bitmap data) {
    String filename = UUID.randomUUID().toString() + ".png";
    if (writeBitmap(this, filename, data) == true) {
      Intent intent = new Intent();
      intent.putExtra(EXTRA_PHOTO_FILENAME, filename);
      setResult(Activity.RESULT_OK, intent);
    }
    else {
      setResult(Activity.RESULT_CANCELED);
    }
    finish();
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
