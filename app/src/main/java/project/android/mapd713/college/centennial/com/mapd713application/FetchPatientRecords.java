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

public class FetchPatientRecords extends AsyncTask<Void,Void,Void> {

    String data = "";
    String dataParsed = "";
    String singleParsed = "";
    private Context ctx;

    public  FetchPatientRecords(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(ctx);
        //String patientId = prefs.getString("patientId", "");
        String xx = sharedPref.getString("patientId","rerore");
        Log.d("XX3","DEGE3R" + xx);


        try {
            URL url = new URL("https://mapd713prjct.herokuapp.com/patients/"  + xx + "/records");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null) {
                line = bufferedReader.readLine();
                data = data + line;

            }

            JSONArray JA = new JSONArray(data);

            for (int i = 0; i <JA.length(); i++) {
                JSONObject JO = (JSONObject) JA.get(i);

                singleParsed = "Patient Id:        " + JO.get("patient_id" ) +  "\n" +
                        "Pulse:        " + JO.get("pulse") +  "\n" +
                        "Nurse Name:            " + JO.get("nurse_name") +  "\n" +
                        "Allergy:     " + JO.get("allergy") +  "\n" +
                        "BMI:  " + JO.get("BMI") +  "\n" +
                        "Surgery:      " + JO.get("surgery") +  "\n" ;
                dataParsed = dataParsed + singleParsed + "\n";


            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.print("HATA 1: " + e);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("HATA 2: " + e);
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.print("HATA 3: " + e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        GetPatientRecords.mTextViewResult.setText(this.dataParsed);
    }
}
