package app.geo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import app.geo.R;
import app.geo.main.GeoApp;
import app.geo.model.Signup;
import app.geo.model.UserStore;
import app.geo.model.Welcome;

/**
 * Created by niall on 10/10/17.
 */

public class Login extends Base {

  public UserStore userStore;

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
  }

  public void loginPressed(View view){
    GeoApp app = (GeoApp)getApplication();
    this.userStore = app.userStore;

    TextView email = (TextView)findViewById(R.id.loginEmail);
    TextView password = (TextView)findViewById(R.id.loginPassword);

    if(userStore.validUser(email.getText().toString(), password.getText().toString())){
      app.currUser = userStore.getUser(email.getText().toString());
      startActivity(new Intent(this, GeoMenu.class));
    }
    else{
      toastMessage("Invalid Credentials");
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
        toastMessage("Already on Login Page");
        break;
      case R.id.menusignup:
        goToActivity(this, Signup.class, null);
        break;
    }
    return true;
  }
}

