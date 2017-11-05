package app.geo.models;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * @author Niall Grant 05/11/2017
 * A class used to save and store the list of caches
 *
 * Main reference source: 4(a) Navigation and LifeCycles (Siobhán) Lab 2
 */

public class CacheSerializer {
  private Context mContext;
  private String mFilename;

  public CacheSerializer(Context c, String f){
    this.mContext = c;
    this.mFilename = f;
  }

  public void saveCache(ArrayList<Cache> caches) throws JSONException, IOException{
    JSONArray array = new JSONArray();
    for(Cache c : caches){
      array.put(c.toJSON());
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

  public ArrayList<Cache> loadCache() throws JSONException, IOException{
    ArrayList<Cache> caches = new ArrayList<Cache>();
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
        caches.add(new Cache(array.getJSONObject(i)));
      }
    }
    catch(FileNotFoundException e){
      //ignored
    }
    finally{
      if(reader != null)
        reader.close();
    }
    return caches;
  }
}
