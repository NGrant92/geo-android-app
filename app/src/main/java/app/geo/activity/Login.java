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
import app.geo.model.Welcome;

/**
 * Created by niall on 10/10/17.
 */

public class Login extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
  }

  public void loginPressed(View view){
    GeoApp app = (GeoApp)getApplication();

    TextView email = (TextView)findViewById(R.id.loginEmail);
    TextView password = (TextView)findViewById(R.id.loginPassword);

    if(app.validUser(email.getText().toString(), password.getText().toString())){
      startActivity(new Intent(this, GeoMenu.class));
    }
    else{
      Toast toast = Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT);
      toast.show();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu){
    //to inflate the menu items for use in the action bar
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_welcome, menu);
    return super.onCreateOptionsMenu(menu);
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

