package app.geo.helpers;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;

import java.io.IOException;

/**
 * Created by niall on 24/11/17.
 */

public class MapHelper {
  public static String getAddress(Location location, Context context){
    Geocoder geocoder = new Geocoder(context);

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

}
