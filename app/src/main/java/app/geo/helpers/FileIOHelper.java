package app.geo.helpers;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import android.util.Log;

import android.content.Context;
import android.graphics.Bitmap;

public class FileIOHelper
{
  public static boolean write(Context context, String filename, byte[] data)
  {
    FileOutputStream os = null;
    boolean success = true;
    try
    {
      os = context.openFileOutput(filename, Context.MODE_PRIVATE);
      os.write(data);
    }
    catch (Exception e)
    {
      Log.v("Geo","Error writing to file " + filename + " " + e.getMessage());
      success = false;
    }
    finally
    {
      try
      {
        if (os != null)
          os.close();
      }
      catch (Exception e)
      {
        Log.v("Geo", "Error closing file " + filename + " " + e.getMessage());
        success = false;
      }
    }
    return success;
  }

  public static byte[] byteArray(Bitmap bmp)
  {
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
    return stream.toByteArray();
  }

  public static boolean writeBitmap(Context context, String filename, Bitmap bmp)
  {
    return write(context, filename, byteArray(bmp));
  }
}
