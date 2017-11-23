package app.geo.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import app.geo.activities.Mail;

/**
 * Created by niall on 23/11/17.
 */

public class MailFragment extends Fragment implements View.OnClickListener{

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
    View view = super.onCreateView(inflater, container, savedInstanceState);



    return view;
  }

  @Override
  public void onClick(View v) {

  }
}
