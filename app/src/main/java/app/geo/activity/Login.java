package app.geo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import app.geo.R;
import app.geo.main.GeoApp;

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
}

