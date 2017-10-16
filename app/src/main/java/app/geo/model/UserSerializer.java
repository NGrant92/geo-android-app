package app.geo.model;

import android.content.Context;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import android.content.Context;

/**
 * Created by niall on 16/10/17.
 */

public class UserSerializer {
  private Context mContext;
  private String mFilename;

  public UserSerializer(Context c, String f){
    this.mContext = c;
    this.mFilename = f;
  }

  public void saveUsers(ArrayList<User> users) throws JSONException, IOException{
    JSONArray array = new JSONArray();
    for(User u : users){
      array.put(u.toJSON());
    }

    Writer writer = null;
    try{
      OutputStream out = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
      writer = new OutputStreamWriter(out);
      writer.write(array.toString());
    }
    finally{
      if(writer != null){
        writer.close();
      }
    }
  }

  public ArrayList<User> loadUsers() throws JSONException, IOException{
    ArrayList<User> users = new ArrayList<User>();
    BufferedReader reader = null;

    try{
      InputStream in = mContext.openFileInput(mFilename);
      reader = new BufferedReader(new InputStreamReader(in));
      StringBuilder jsonString = new StringBuilder();
      String line = null;

      while((line = reader.readLine()) != null){
        jsonString.append(line);
      }
      JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();

      for(int i = 0; i < array.length(); i++){
        users.add(new User(array.getJSONObject(i)));
      }
    }
    catch(FileNotFoundException e){
      //ignored
    }
    finally{
      if(reader != null)
        reader.close();
    }
    return users;
  }
}
