package app.geo.main;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import app.geo.model.Geo;
import app.geo.model.User;

/**
 * Created by niall on 14/10/17.
 */

public class GeoApp extends Application{

  public List<Geo> geos = new ArrayList<>();
  public List<User> users = new ArrayList<>();

  public void newUser(User user){
    users.add(user);
  }
  public void newGeo(User user){
    users.add(user);
  }
}
