package project.android.mapd713.college.centennial.com.mapd713application;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {


    static EditText etPassword;
    EditText etUserName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void logIn(View v)
    {
        etUserName = findViewById(R.id.r1editText);
        etPassword = (EditText) findViewById(R.id.r2editText);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("userName",etUserName.getText().toString());
        editor.apply();
        String userPassword = prefs.getString("password","mkmk");
        FetchLoginData process =  new FetchLoginData(Login.this);
        process.execute();


        Log.d("sifre", "bulcaz2: " + etPassword.getText().toString());
        Log.d("sifre", "bulcaz3: " + userPassword);

        if(userPassword.equals(etPassword.getText().toString())) {
            //instantiate intent class
            Intent intent = new Intent(Login.this, MainActivity.class);
            //start the activity
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(),"Invalid username or password!", Toast.LENGTH_LONG).show();
        }
    }

    public void signUp(View v)
    {
        //instantiate intent class
        Intent intent=new Intent(Login.this, AddPatient.class);

        //start the activity
        startActivity(intent);
    }
}
