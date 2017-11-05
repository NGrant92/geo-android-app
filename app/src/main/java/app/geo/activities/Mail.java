package app.geo.activities;

import android.os.Bundle;

import app.geo.R;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;

import static app.geo.helpers.ContactHelper.getContact;
import static app.geo.helpers.ContactHelper.getEmail;
import static app.geo.helpers.ContactHelper.sendEmail;
import static app.geo.helpers.IntentHelper.selectContact;
import android.content.Intent;

/**
 * @author Niall Grant 05/11/2017
 *
 * This activity allows the user to write and send an email from their contact list
 *
 * Main reference source: 5a (SUPPLEMENTAL) Persistence and Communicaton
 */

public class Mail extends Base implements TextWatcher, CompoundButton.OnCheckedChangeListener, View.OnClickListener{

  private String emailAddress;

  private Button mailSendToButton;
  private EditText mailSubject;
  private EditText mailText;
  private Button sendMailButton;

  private static final int REQUEST_CONTACT = 1;

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mail);

    mailSendToButton = (Button) findViewById(R.id.mailSendToButton);
    mailSendToButton.setOnClickListener(this);

    mailSubject = (EditText)findViewById(R.id.mailSubject);
    mailSubject.addTextChangedListener(this);

    mailText = (EditText)findViewById(R.id.mailText);
    mailText.addTextChangedListener(this);

    sendMailButton = (Button)findViewById(R.id.sendMailButton);
    sendMailButton.setOnClickListener(this);
  }

  @Override
  public void onClick(View view)
  {
    switch (view.getId()) {
      case R.id.mailSendToButton:
        selectContact(this, REQUEST_CONTACT);
        break;

      case R.id.sendMailButton :
        sendEmail(this, emailAddress, mailSubject.getText().toString(), mailText.getText().toString());
        break;
    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data)
  {
    switch (requestCode)
    {
      case REQUEST_CONTACT:
        if(data != null){
          String name = getContact(this, data);
          emailAddress = getEmail(this, data);
          mailSendToButton.setText(name + ": " + emailAddress);
        }
        break;
    }
  }

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {

  }

  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {

  }

  @Override
  public void afterTextChanged(Editable s) {

  }

  @Override
  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

  }
}
