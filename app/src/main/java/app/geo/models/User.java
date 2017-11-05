package app.geo.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import app.geo.main.GeoApp;

/**
 * @author Niall Grant 05/11/2017
 * A model class that defines what should be stored in the user object
 *
 * Main reference source: 4(a) Navigation and LifeCycles (Siobhán) Lab 2
 */

public class User {

  public int userId;
  public String firstName;
  public String lastName;
  public String email;
  public String password;

  private static final String JSON_USERID = "userId";
  private static final String JSON_FIRSTNAME = "firstName";
  private static final String JSON_LASTNAME = "lastName";
  private static final String JSON_EMAIL = "email";
  private static final String JSON_PASSWORD = "password";

  public User(String firstName, String lastName, String email, String password){
    this.userId = newid();
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
  }

  public User(JSONObject json) throws JSONException{
    userId = json.getInt(JSON_USERID);
    firstName = json.getString(JSON_FIRSTNAME);
    lastName = json.getString(JSON_LASTNAME);
    email = json.getString(JSON_EMAIL);
    password = json.getString(JSON_PASSWORD);
  }

  public JSONObject toJSON() throws JSONException{
    JSONObject json = new JSONObject();
    json.put(JSON_USERID, String.valueOf(userId));
    json.put(JSON_FIRSTNAME, firstName);
    json.put(JSON_LASTNAME, lastName);
    json.put(JSON_EMAIL, email);
    json.put(JSON_PASSWORD, password);

    return json;
  }

  private int newid(){
    ArrayList<User> users = GeoApp.getInstance().userStore.users;
    if(users.size() > 0) {
      User user = users.get(users.size() - 1);
      return user.userId + 1;
    }
    else{
      return 0;
    }
  }
}
