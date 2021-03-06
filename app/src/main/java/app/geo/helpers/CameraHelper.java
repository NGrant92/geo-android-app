package app.geo.helpers;

import java.util.List;

import app.geo.activities.GeoHome;
import app.geo.fragments.AddCachesFragment;
import app.geo.models.Cache;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Camera.Size;
import android.view.Display;
import android.widget.ImageView;

public class CameraHelper {

  /**
   * Render the photo on the ImageView
   */
  public static void showPhoto(Activity activity, String photo, ImageView photoView)
  {
    String path = activity.getFileStreamPath(photo).getAbsolutePath();
    BitmapDrawable b = getScaledDrawable(activity, path);
    if (b != null)
      photoView.setImageDrawable(b);
  }

  /**
   * Get a BitmapDrawable from a local file that is scaled down to fit the
   * current Window size.
   */
  @SuppressWarnings("deprecation")
  public static BitmapDrawable getScaledDrawable(Activity a, String path)
  {
    Display display = a.getWindowManager().getDefaultDisplay();
    float destWidth = display.getWidth();
    float destHeight = display.getHeight();

    // read in the dimensions of the image on disk
    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(path, options);

    float srcWidth = options.outWidth;
    float srcHeight = options.outHeight;

    int inSampleSize = 1;
    if (srcHeight > destHeight || srcWidth > destWidth)
    {
      if (srcWidth > srcHeight)
      {
        inSampleSize = Math.round((float) srcHeight / (float) destHeight);
      }
      else
      {
        inSampleSize = Math.round((float) srcWidth / (float) destWidth);
      }
    }

    options = new BitmapFactory.Options();
    options.inSampleSize = inSampleSize;

    Bitmap bitmap = BitmapFactory.decodeFile(path, options);
    return new BitmapDrawable(a.getResources(), bitmap);
  }

}