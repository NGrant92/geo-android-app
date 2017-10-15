package app.geo.model;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import app.geo.R;
import app.geo.activity.Base;
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

  public void updateButtonPressed(View view){
    String firstName = ((TextView)findViewById(R.id.settingsFirstName)).getText().toString();
    String lastName = ((TextView)findViewById(R.id.settingsLastName)).getText().toString();;
    String email = ((TextView)findViewById(R.id.settingsEmail)).getText().toString();;
    String password = ((TextView)findViewById(R.id.settingsPassword)).getText().toString();;

    if(isNew(firstName, user.firstName)){
      user.firstName = firstName;
    }
    if(isNew(lastName, user.lastName)){
      user.lastName = lastName;
    }
    if(isNew(email, user.email)){
      user.email = email;
    }
    if(isNew(password, user.password)){
      user.password = password;
    }

  }

  public boolean isNew(String newString, String currString){

    return newString != null && !newString.equals(currString);
  }
}
