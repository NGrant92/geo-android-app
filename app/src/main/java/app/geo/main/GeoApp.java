package app.geo.main;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import app.geo.model.Cache;
import app.geo.model.User;

/**
 * Created by niall on 14/10/17.
 */

public class GeoApp extends Application{

  public List<Cache> caches = new ArrayList<>();
  public List<User> users = new ArrayList<>();

  public void newUser(User user){
    users.add(user);
  }
  public void newGeo(Cache cache){
    caches.add(cache);
  }

  public boolean validUser(String email, String password){
    for(User user : users){
      if(user.email.equals(email) && user.password.equals(password)){
        return true;
      }
    }
    return false;
  }
}
