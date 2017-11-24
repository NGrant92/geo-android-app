package app.geo.fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import app.geo.R;

import static app.geo.helpers.ContactHelper.getContact;
import static app.geo.helpers.ContactHelper.getEmail;
import static app.geo.helpers.ContactHelper.sendEmail;

/**
 * @author Niall Grant 05/11/2017
 *
 * This activity allows the user to write and send an email from their contact list
 *
 * Main reference source: 5a (SUPPLEMENTAL) Persistence and Communicaton
 */

public class MailFragment extends Fragment implements TextWatcher, CompoundButton.OnCheckedChangeListener, View.OnClickListener{

  private String emailAddress;
  private Button mailSendToButton;
  private EditText mailSubject;
  private EditText mailText;
  private Button sendMailButton;
  private static final int REQUEST_CONTACT = 1;

  public MailFragment(){}

  public static MailFragment newInstance(){
    return new MailFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

    View view = inflater.inflate(R.layout.fragment_mail, container, false);

    mailSendToButton = (Button)view.findViewById(R.id.mailSendToButton);
    mailSendToButton.setOnClickListener(this);

    mailSubject = (EditText)view.findViewById(R.id.mailSubject);
    mailSubject.addTextChangedListener(this);

    mailText = (EditText)view.findViewById(R.id.mailText);
    mailText.addTextChangedListener(this);

    sendMailButton = (Button)view.findViewById(R.id.sendMailButton);
    sendMailButton.setOnClickListener(this);

    return view;
  }

  @Override
  public void onClick(View view)
  {
    switch (view.getId()) {
      case R.id.mailSendToButton:
        //Ref: https://stackoverflow.com/a/32890529
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, REQUEST_CONTACT);
        break;

      case R.id.sendMailButton:
        sendEmail(getActivity(), emailAddress, mailSubject.getText().toString(), mailText.getText().toString());
        break;
    }
  }

  @SuppressLint("SetTextI18n")
  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    switch (requestCode) {
      case REQUEST_CONTACT:
        if(data != null){
          String name = getContact(getActivity(), data);
          emailAddress = getEmail(getActivity(), data);
          mailSendToButton.setText(name + ": " + emailAddress);
        }
        else {
          Toast.makeText(getActivity(), "Contact not found", Toast.LENGTH_SHORT).show();
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
