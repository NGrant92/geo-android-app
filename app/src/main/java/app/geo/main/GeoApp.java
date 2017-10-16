package app.geo.main;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import app.geo.model.Cache;
import app.geo.model.User;
import app.geo.model.UserSerializer;
import app.geo.model.UserStore;

/**
 * Created by niall on 14/10/17.
 */

public class GeoApp extends Application{

  public List<Cache> caches = new ArrayList<>();
  public List<User> users = new ArrayList<>();
  public User currUser;
  public UserStore userStore;
  private static final String FILENAME = "userstore.json";

  @Override
  public void onCreate(){
    super.onCreate();
    UserSerializer serializer = new UserSerializer(this, FILENAME);
    userStore = new UserStore(serializer);

    Log.v("Geo", "Geo App Launched");
  }

  public void newUser(User user){
    users.add(user);
  }
  public void newCache(Cache cache){
    caches.add(cache);
  }

  public boolean validUser(String email, String password){
    for(User user : users){
      if(user.email.equals(email) && user.password.equals(password)){
        this.currUser = user;
        return true;
      }
    }
    return false;
  }

  public boolean emailExists(String regEmail){
    for(User user : users){
      if(user.email.equals(regEmail)){
        return true;
      }
    }
    return false;
  }
}