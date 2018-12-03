package project.android.mapd713.college.centennial.com.mapd713application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }


    public void submit(View v){
        //Intent intent=new Intent(BMICheck.this, BMIResults.class);

        EditText etFirstName = findViewById(R.id.r1editText);
        EditText etLastName = findViewById(R.id.r2editText);
        EditText etMailAddress = findViewById(R.id.r3editText);
        EditText etPhone = findViewById(R.id.r4editText);
        EditText etDepartment = findViewById(R.id.r5editText);
        EditText etPosition = findViewById(R.id.r6editText);
        EditText etUserName = findViewById(R.id.r7editText);
        EditText etPassword = findViewById(R.id.r8editText);


        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String URL = "https://mapd713prjct.herokuapp.com/register";
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("first_name", etFirstName.getText().toString());
            jsonBody.put("last_name", etLastName.getText().toString());
            jsonBody.put("mail_address", etMailAddress.getText().toString());
            jsonBody.put("phone", etPhone.getText().toString());
            jsonBody.put("department", etDepartment.getText().toString());
            jsonBody.put("position", etPosition.getText().toString());
            jsonBody.put("username", etUserName.getText().toString());
            jsonBody.put("password", etPassword.getText().toString());

            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("VOLLEY", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue.add(stringRequest);
            Toast.makeText(getApplicationContext(),"User Registered Successfully!", Toast.LENGTH_LONG).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
