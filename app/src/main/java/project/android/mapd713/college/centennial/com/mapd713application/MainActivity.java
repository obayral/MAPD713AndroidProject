package project.android.mapd713.college.centennial.com.mapd713application;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class MainActivity extends AppCompatActivity {

    //  TextView text;
    Button getPatients;
    Button getSinglePatient;
    EditText etID;

    public static TextView mTextViewResult;
    RequestQueue mQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //  text = (TextView)findViewById(R.id.text);
        mTextViewResult = (TextView) findViewById(R.id.text);
        mQueue = Volley.newRequestQueue(this);

    }
    public void getPatients(View v)
    {
        //instantiate intent class
        Intent intent=new Intent(MainActivity.this, GetPatients.class);

        //start the activity
        startActivity(intent);
    }
    public void getSinglePatient(View v)
    {
        etID = findViewById(R.id.editTextID);
        SharedPreferences sharedPref = getSharedPreferences("patientId", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("patientId",etID.getText().toString());
        editor.apply();

        String xx = sharedPref.getString("patientId","hayamk");
        Log.d("XX","DEGER" + xx);

        //instantiate intent class
        Intent intent=new Intent(MainActivity.this, GetSinglePatient.class);

        //start the activity
        startActivity(intent);
    }
    public void addPatient(View v)
    {
        //instantiate intent class
        Intent intent=new Intent(MainActivity.this, AddPatient.class);

        //start the activity
        startActivity(intent);
    }

}