package app.geo.helpers;

import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;

/**
 * @author Niall Grant 05/11/2017
 *
 * This is used to allow the user to access their phone contact list from within the app
 *
 * Main reference source: 5a (SUPPLEMENTAL) Persistence and Communicaton
 */

public class IntentHelper {

  public static void selectContact(Activity parent, int id) {
    Intent selectContactIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
    parent.startActivityForResult(selectContactIntent, id);
  }
}
