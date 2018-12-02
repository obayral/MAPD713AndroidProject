package project.android.mapd713.college.centennial.com.mapd713application;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FetchLoginData extends AsyncTask<Void,Void,Void> {

    String data = "";
    String dataParsed = "";
    String singleParsed = "";
    JSONObject myObject;
    private Context ctx;

    public FetchLoginData(Context ctx) {
        this.ctx = ctx;
    }


    @Override
    protected Void doInBackground(Void... voids) {


        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(ctx);
        //String patientId = prefs.getString("patientId", "");
        String xx = sharedPref.getString("userName","mkmk");
        Log.d("ZOZO","OLLEY" + xx);


        Log.i("fonksiyon","ICINE GIRDI");

        try {
            URL url = new URL("https://mapd713prjct.herokuapp.com/login/" + xx);
            Log.i("URL","URL ICINE GIRDI");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null) {
                line = bufferedReader.readLine();
                data = data + line;

            }
            myObject = new JSONObject(data);
            myObject.getString("password");
            Log.d("DOKTOR BU NE","hmm" + myObject.getString("password"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.print("SIKINTI 1: " + e);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("SIKINTI 2: " + e);
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.print("SIKINTI 3: " + e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        System.out.println("girdi");
        Log.i("onPostExecute","GIRDI");
        try {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("password",myObject.getString("password"));
            editor.apply();
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.print("KILLATMA: " + e);
        }
    }
}
