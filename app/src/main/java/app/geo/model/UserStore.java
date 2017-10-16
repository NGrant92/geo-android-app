package app.geo.model;

import java.util.ArrayList;
import android.util.Log;

/**
 * Created by niall on 16/10/17.
 */

public class UserStore {

  public ArrayList<User> users;
  private UserSerializer serializer;

  public UserStore(UserSerializer serializer){
    this.serializer = serializer;
    try{
      users = serializer.loadUsers();
    }
    catch(Exception e){
      users = new ArrayList<User>();
    }
  }

  public void addUser(User user){
    users.add(user);
  }

  public User getUser(int id){
    for(User user : users){
      if(id == user.userId){
        return user;
      }
    }
    return null;
  }

  public boolean saveUsers(){
    try{
      serializer.saveUsers(users);
      Log.v("Geo", "Users saved!");
      return true;
    }
    catch(Exception e){
      Log.v("Geo", "Save Error!");
      return false;
    }
  }

  public boolean validUser(String email, String password){
    for(User user : users){
      if(user.email.equals(email) && user.password.equals(password)){
        return true;
      }
    }
    return false;
  }

  public boolean updateEmailCheck(String email, int userId){
    for(User user : users){
      if(email.equals(user.email) && userId != user.userId){
        return false;
      }
    }
    return true;
  }

  public boolean isEmailFree(String email){
    for(User user : users){
      if(email.equals(user.email)){
        return false;
      }
    }
    return true;
  }

  public User getUser(String email){
    for(User user : users){
      if(user.email.equals(email)){
        return user;
      }
    }
    return null;
  }
}