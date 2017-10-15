package app.geo.model;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import app.geo.R;
import app.geo.activity.Base;
import app.geo.activity.GeoMenu;
import app.geo.main.GeoApp;

/**
 * Created by niall on 09/10/17.
 */

public class Settings extends Base {
  User user;

  @Override
  protected void onCreate(Bundle savedInstanceState){
    GeoApp app = (GeoApp)getApplication();
    this.user = app.currUser;

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);

    ((EditText)findViewById(R.id.settingsFirstName)).setText(String.valueOf(user.firstName));
    ((EditText)findViewById(R.id.settingsLastName)).setText(String.valueOf(user.lastName));
    ((EditText)findViewById(R.id.settingsEmail)).setText(String.valueOf(user.email));
    ((EditText)findViewById(R.id.settingsPassword)).setText(String.valueOf(user.password));
  }

  public void updateButtonPressed(View view) {
    GeoApp app = (GeoApp) getApplication();

    String firstName = ((TextView) findViewById(R.id.settingsFirstName)).getText().toString();
    String lastName = ((TextView) findViewById(R.id.settingsLastName)).getText().toString();
    String email = ((TextView) findViewById(R.id.settingsEmail)).getText().toString();
    String password = ((TextView) findViewById(R.id.settingsPassword)).getText().toString();

    if(isValidEmail(email, user.userId)){
      if (isNew(firstName, user.firstName)) {
        user.firstName = firstName;
      }
      if (isNew(lastName, user.lastName)) {
        user.lastName = lastName;
      }
      if (isNew(email, user.email)) {
        user.email = email;
      }
      if (isNew(password, user.password)) {
        user.password = password;
      }
      toastMessage("Profile updated!");
      goToActivity(this, GeoMenu.class, null);
    }
    else{

      toastMessage("Invalid Email.");
    }

  }

  public boolean isValidEmail(String email, int userId){
    GeoApp app = (GeoApp) getApplication();
    List<User> users = app.users;
    for(User user : users){
      if(email.equals(user.email) && user.userId != userId){
        return false;
      }
    }
    return true;
  }


  public boolean isNew(String newString, String currString){
    return newString != null && !newString.equals(currString);
  }
}
