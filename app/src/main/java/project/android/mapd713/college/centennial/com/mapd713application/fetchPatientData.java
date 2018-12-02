package project.android.mapd713.college.centennial.com.mapd713application;

import android.os.AsyncTask;

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

public class fetchPatientData extends AsyncTask<Void,Void,Void> {

    String data = "";
    String dataParsed = "";
    String singleParsed = "";

    @Override
    protected Void doInBackground(Void... voids) {


        try {
            URL url = new URL("https://mapd713prjct.herokuapp.com/patients");

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

         singleParsed = "First Name:        " + JO.get("first_name" ) +  "\n" +
                        "Last Name:        " + JO.get("last_name") +  "\n" +
                        "Blood Group:     " + JO.get("blood_group") +  "\n" +
                        "Address:            " + JO.get("address") +  "\n" +
                        "Date of birth:     " + JO.get("date_of_birth") +  "\n" +
                        "Date admitted:  " + JO.get("date_admitted") +  "\n" +
                        "Department:      " + JO.get("department") +  "\n" +
                        "Doctor:               " + JO.get("doctor") +  "\n" +
                        "Ailment:             " + JO.get("ailment") +  "\n" ;
                dataParsed = dataParsed + singleParsed + "\n";


            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        GetPatients.mTextViewResult.setText(this.dataParsed);
    }
}
