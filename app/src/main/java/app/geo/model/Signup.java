package app.geo.model;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import app.geo.R;
import app.geo.activity.Base;
import app.geo.activity.Login;
import app.geo.main.GeoApp;

/**
 * Created by niall on 10/10/17.
 */

public class Signup extends Base {

  public UserStore userStore;

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
    userStore = app.userStore;
    Log.v("Geo", "USERSTORE: " + userStore);

    if(app.emailExists(user.email)){
      toastMessage("Email is used by another account");
    }
    else{
      userStore.addUser(user);
      Log.v("Geo", "added to user store");
      app.newUser(user);
      goToActivity(this, Login.class, null);
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

