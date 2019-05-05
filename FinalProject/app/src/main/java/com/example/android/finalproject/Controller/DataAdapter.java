package com.example.android.finalproject.Controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.android.finalproject.Model.Question;
import com.example.android.finalproject.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.RouteViewHolder> {

    public interface  OnItemClickListener{
        void onItemClicked(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
    private final ArrayList<Question> mQuestions;
    private LayoutInflater mInflater;
    private int currentPosition;
    private OnItemClickListener mListener;
    private DatabaseReference mDatabase;

    public DataAdapter(ArrayList<Question> questions){
        this.mQuestions = questions;
    }

    class RouteViewHolder extends RecyclerView.ViewHolder{
        public TextView questionItemView;
        public TextView answerItemView;
        public TextView answerAItemView;
        public TextView answerBItemView;
        public TextView answerCItemView;
        public TextView id_view;
        public ImageButton btnDelete;


        public RouteViewHolder(View v){
            super(v);
            questionItemView = v.findViewById(R.id.textView);
            answerItemView = v.findViewById(R.id.answerView);
            answerAItemView = v.findViewById(R.id.answerA);
            answerBItemView = v.findViewById(R.id.answerB);
            answerCItemView = v.findViewById(R.id.answerC);
            id_view = v.findViewById(R.id.id_);
            btnDelete = v.findViewById(R.id.btnDelete);

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    mListener.onItemClicked(getAdapterPosition());
                    Log.d("CLICKED", ""+getAdapterPosition() + " "+ id_view.getText());
                    mDatabase = FirebaseDatabase.getInstance().getReference("/questions/"+id_view.getText());
                    mDatabase.removeValue();
                }
            });
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
        holder.id_view.setText(curr.getId());
        currentPosition = position;

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mQuestions.size();
    }

}
