package com.example.android.finalproject.View;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.finalproject.Model.Question;
import com.example.android.finalproject.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class NewQuestionActivity extends AppCompatActivity {

    EditText enterQuestion;
    EditText choiceA;
    EditText choiceB;
    EditText choiceC;
    Button submitButton;
    String answer;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newquestion);
//        getSupportActionBar().setTitle("Enter a new question");

        enterQuestion = findViewById(R.id.enterQuestion);
        choiceA = findViewById(R.id.choiceA);
        choiceB = findViewById(R.id.choiceB);
        choiceC = findViewById(R.id.choiceC);
        submitButton = findViewById(R.id.submit);
        answer = null;

        /**
         * Submitting a new question results in:
         *  1- requesting a count of total questions to the counter object
         *  2- requesting a PUT
         *  3- incrementing the counter and overwriting theq current count with new count
         *
         *  The counter is its own json object since firebase doesn't keep a counter?
         */
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendGetRequest(new VolleyCallback() {

                    @Override
                    public void onSuccess(String result) {
                        Question question = new Question();

                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("question", enterQuestion.getText().toString());
                            jsonObject.put("a", choiceA.getText().toString());
                            jsonObject.put("b", choiceB.getText().toString());
                            jsonObject.put("c", choiceC.getText().toString());
                            jsonObject.put("answer", answer);
                        } catch (JSONException e) {
                            Log.e("JSONObject Error", "sendGetRequest Error");
                        }
                        int count =  Integer.parseInt(result);
                        count++;
                        String url = "https://finalproject-c6f51.firebaseio.com/questions/"+count+".json";
                        sendPutRequest(jsonObject,url);


                        JSONObject jsonCount = new JSONObject();
                        try{
                            jsonCount.put("count", count);
                        }catch (JSONException e){
                            Log.e("JSONObject Error", "sendGetRequest Error");
                        }
                        url = "https://finalproject-c6f51.firebaseio.com/counter.json";
                        sendPutRequest(jsonCount,url);
                    }
                });
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.optionA:
                if (checked)
                    answer = "a";
                break;
            case R.id.optionB:
                if (checked)
                    answer = "b";
                break;
            case R.id.optionC:
                if (checked)
                    answer = "c";
                break;
        }
    }

    /**
     * Using Volley to handle a PUT request
     * @param jsonObject
     * @param url
     */
    private void sendPutRequest(JSONObject jsonObject, String url) {
        RequestQueue queue = Volley.newRequestQueue(this);

        final JSONObject jsonObject_ = jsonObject;

        JsonObjectRequest putRequest = new JsonObjectRequest(Request.Method.PUT, url, jsonObject_,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // response
                        Log.d("Response", response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                        Toast.makeText(getApplicationContext(),"Entry Error!",Toast.LENGTH_LONG).show();
                    }
                }
        ) {

            @Override
            public Map<String, String> getHeaders()
            {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Accept", "application/json");
                return headers;
            }

            @Override
            public byte[] getBody() {

                try {
                    Log.i("json", jsonObject_.toString());
                    return jsonObject_.toString().getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        queue.add(putRequest);
    }

    /**
     * Using Volley to handle Get request using a callback
     * @param callback
     */
    private void sendGetRequest(final VolleyCallback callback){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://finalproject-c6f51.firebaseio.com/counter/count.json";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("COUNT FOR QUESTIONS:", response.toString());
                        callback.onSuccess(response);
                        Toast.makeText(getApplicationContext(),"Entry added!",Toast.LENGTH_LONG).show();
                        Intent register = new Intent(NewQuestionActivity.this, AdminMenu.class);
                        startActivity(register);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error Respose", "Volley Error");
                    }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

}


