package app.geo.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by niall on 10/10/17.
 * A class that will hold information of a user.
 */

public class User {

  public static int autoid = 1;
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
    this.userId = autoid++;
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
}
