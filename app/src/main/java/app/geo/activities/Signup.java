package app.geo.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import app.geo.R;
import app.geo.main.GeoApp;
import app.geo.models.User;
import app.geo.models.UserStore;

/**
 * @author Niall Grant 05/11/2017
 *
 * This activity allows the user to register an account if wish to do so.
 * Email input is checked to ensure that it is registered to one account only
 * If a matching email is found then a toast message is displayed
 * If no matching email is found then they are brought to the home screen
 *
 * Main reference source: 2(b). Activities and Layouts (Siobhán)
 */

public class Signup extends Base {

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_signup);
  }

  public void registerPressed(View view){

    TextView firstName = (TextView)findViewById(R.id.signupFirstName);
    TextView lastName = (TextView)findViewById(R.id.signupLastName);
    TextView email = (TextView)findViewById(R.id.signupEmail);
    TextView password = (TextView)findViewById(R.id.signupPassword);

    User user = new User(firstName.getText().toString(), lastName.getText().toString(), email.getText().toString(), password.getText().toString());
    GeoApp app = (GeoApp)getApplication();
    UserStore userStore  = app.userStore;

    if(userStore.isEmailFree(user.email)){
      userStore.addUser(user);
      userStore.saveUsers();
      goToActivity(this, Login.class, null);
    }
    else{
      toastMessage("Email is used by another account");
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu){
    //to inflate the menu items for use in the action bar
    getMenuInflater().inflate(R.menu.menu_welcome, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item){
    switch(item.getItemId()){
      case R.id.menuWelcome:
        goToActivity(this, Welcome.class, null);
        break;
      case R.id.menuLogin:
        goToActivity(this, Login.class, null);
        break;
      case R.id.menusignup:
        toastMessage("Already on Sign Up Page");
        break;
    }
    return true;
  }
}

