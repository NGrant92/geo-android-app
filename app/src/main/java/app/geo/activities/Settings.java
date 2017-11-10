package app.geo.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import app.geo.R;
import app.geo.main.GeoApp;
import app.geo.models.User;
import app.geo.models.UserStore;

/**
 * @author Niall Grant 05/11/2017
 *
 * This activity allows the user to edit their personal information
 *
 * Main reference source: 7(a) UI Design 1 & App Structure
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
    UserStore userStore = app.userStore;

    String firstName = ((TextView) findViewById(R.id.settingsFirstName)).getText().toString();
    String lastName = ((TextView) findViewById(R.id.settingsLastName)).getText().toString();
    String email = ((TextView) findViewById(R.id.settingsEmail)).getText().toString();
    String password = ((TextView) findViewById(R.id.settingsPassword)).getText().toString();

    if(userStore.updateEmailCheck(email, user.userId)){
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
      userStore.saveUsers();

      toastMessage("Profile updated!");
      goToActivity(this, GeoHome.class, null);
    }
    else{
      toastMessage("Invalid Email.");
    }
  }


  public boolean isNew(String newString, String currString){
    return newString != null && !newString.equals(currString);
  }
}
