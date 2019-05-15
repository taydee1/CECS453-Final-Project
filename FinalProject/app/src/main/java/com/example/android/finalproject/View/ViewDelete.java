package com.example.android.finalproject.View;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.android.finalproject.Controller.BaseActivity;
import com.example.android.finalproject.Controller.DataAdapter;
import com.example.android.finalproject.Model.Question;
import com.example.android.finalproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ViewDelete extends BaseActivity {

    ArrayList<Question> questions;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_delete);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mDatabase = FirebaseDatabase.getInstance().getReference("/questions");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                questions = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Question que = snapshot.getValue(Question.class);
                    Log.d("key: ", snapshot.getKey().toString());
                    Log.d("value", snapshot.getValue().toString());
                    Log.d("Firebase question: ", que.toString());
                    que.setId(snapshot.getKey());
                    questions.add(que);

                }
                mAdapter = new DataAdapter(questions);
                recyclerView.setAdapter(mAdapter);
                Log.d("FIREBASE COUNT: ", "" + dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

}
