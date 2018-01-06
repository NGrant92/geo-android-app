package app.geo.activities;

/**
 * Created by niall on 06/01/18.
 */
import app.geo.R;
import app.geo.fragments.AddCachesFragment;
import app.geo.main.GeoApp;
import app.geo.models.Cache;
import app.geo.models.CacheStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import static app.geo.helpers.CameraHelper.showPhoto;

public class Gallery extends AppCompatActivity
{

  public static final String  EXTRA_PHOTO_FILENAME = "app.geo.photo.filename";
  private ImageView photoView;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_gallery);
    photoView = (ImageView) findViewById(R.id.cacheGalleryImage);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    //showPicture();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item)
  {
    switch (item.getItemId())
    {
      case android.R.id.home: onBackPressed();
        return true;
      default: return super.onOptionsItemSelected(item);
    }
  }

//  private void showPicture()
//  {
//    Long resId = (Long)getIntent().getSerializableExtra(AddCachesFragment.EXTRA_ID);
//    GeoApp app = (GeoApp) getApplication();
//    CacheStore cacheStore = app.cacheStore;
//    Cache cache = cacheStore.getCache(resId);
//    showPhoto(this, cache,  photoView);
//  }
}
