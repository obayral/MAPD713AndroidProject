package project.android.mapd713.college.centennial.com.mapd713application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetSinglePatient extends AppCompatActivity {


    public static TextView mTextViewResult;
    RequestQueue mQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_single_patient);
        //  text = (TextView)findViewById(R.id.text);
        mTextViewResult = (TextView) findViewById(R.id.text);
        mQueue = Volley.newRequestQueue(this);
        fetchSinglePatientData process =  new fetchSinglePatientData(GetSinglePatient.this);
        process.execute();
    }


    public void getPatientRecord(View v)
    {
        //instantiate intent class
        Intent intent=new Intent(GetSinglePatient.this, GetPatientRecords.class);
        //start the activity
        startActivity(intent);
    }


}