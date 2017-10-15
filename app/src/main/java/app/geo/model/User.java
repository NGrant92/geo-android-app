package app.geo.model;

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

  public User(String firstName, String lastName, String email, String password){
    this.userId = autoid++;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
  }
}
