package com.example.android.finalproject.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.finalproject.Controller.DataAdapter;
import com.example.android.finalproject.Model.Question;
import com.example.android.finalproject.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ViewDelete extends AppCompatActivity {

    TextView txJson;
    TextView txQestion;
    ArrayList<Question> questions;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_delete);
        recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        questions = new ArrayList<>();

        sendGetRequest(new VolleyCallback() {
            String url = "https://finalproject-c6f51.firebaseio.com/questions.json";
            @Override
            public void onSuccess(String result) {
                Question question = new Question();
                Log.d("Result", result);
//                txJson.setText(result);

                Type targetClassType = new TypeToken<ArrayList<Question>>() { }.getType();
                questions = new Gson().fromJson(result, targetClassType);
                questions.remove(0);

                Log.d("Quest", questions.get(0).toString());

                // specify an adapter (see also next example)
                mAdapter = new DataAdapter(questions);
                recyclerView.setAdapter(mAdapter);
            }
        });
    }

    /**
     //     * Using Volley to handle Get request using a callback
     //     * @param callback
     //     */
    private void sendGetRequest(final VolleyCallback callback){

        String url = "https://finalproject-c6f51.firebaseio.com/questions.json";
        Log.d("STARTING GET......", "start");
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("COUNT FOR QUESTIONS:", response.toString());
                        callback.onSuccess(response);
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