package com.example.android.finalproject.Controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.finalproject.Model.Question;
import com.example.android.finalproject.R;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.RouteViewHolder> {

    private final ArrayList<Question> mQuestions;
    private LayoutInflater mInflater;
    private int currentPosition;
    private Context mContext;

    public DataAdapter(ArrayList<Question> questions){
        this.mQuestions = questions;
    }

    class RouteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView questionItemView;
        public TextView answerItemView;
        public TextView answerAItemView;
        public TextView answerBItemView;
        public TextView answerCItemView;

        public RouteViewHolder(View v){
            super(v);
            questionItemView = v.findViewById(R.id.textView);
            answerItemView = v.findViewById(R.id.answerView);
            answerAItemView = v.findViewById(R.id.answerA);
            answerBItemView = v.findViewById(R.id.answerB);
            answerCItemView = v.findViewById(R.id.answerC);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // Prepend "Clicked! " to the text in the view.
//            questionItemView.setText ("Clicked! "+ getPosition());
            Log.d("CLICKED", ""+getPosition());
//            clickListener.onRecyclerClickListener(v,getPosition());
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DataAdapter.RouteViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_item, parent, false);

        return new RouteViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RouteViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Question curr = mQuestions.get(position);
        String mCurrent =
                "Question:" + curr.getQuestion();
        String mAnswer = "Answer:" + curr.getAnswer();

        holder.questionItemView.setText(mCurrent);
        holder.answerAItemView.setText("A: "+curr.getA());
        holder.answerBItemView.setText("B: "+curr.getB());
        holder.answerCItemView.setText("C: "+curr.getC());
        holder.answerItemView.setText(mAnswer);
        currentPosition = position;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mQuestions.size();
    }

}
