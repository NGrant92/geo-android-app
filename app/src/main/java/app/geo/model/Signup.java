package app.geo.model;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import app.geo.R;
import app.geo.activity.Login;
import app.geo.main.GeoApp;

/**
 * Created by niall on 10/10/17.
 */

public class Signup extends AppCompatActivity {
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
}

