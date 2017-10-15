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
    app.newUser(user);

    startActivity(new Intent(this, Login.class));
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
        startActivity(new Intent(this, Welcome.class));
        break;
      case R.id.menuLogin:
        startActivity(new Intent(this, Login.class));
        break;
      case R.id.menusignup:
        startActivity(new Intent(this, Signup.class));
        break;
    }
    return true;
  }
}

