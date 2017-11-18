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

  public String firstName;
  public String lastName;
  public String email;

  private static final String JSON_FIRSTNAME = "firstName";
  private static final String JSON_LASTNAME = "lastName";
  private static final String JSON_EMAIL = "email";

  public User(String firstName, String lastName, String email){
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public User(JSONObject json) throws JSONException{
    firstName = json.getString(JSON_FIRSTNAME);
    lastName = json.getString(JSON_LASTNAME);
    email = json.getString(JSON_EMAIL);
  }

  public JSONObject toJSON() throws JSONException{
    JSONObject json = new JSONObject();
    json.put(JSON_FIRSTNAME, firstName);
    json.put(JSON_LASTNAME, lastName);
    json.put(JSON_EMAIL, email);

    return json;
  }
}
