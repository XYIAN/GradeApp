package com.kxdilbeck.gradeapp.Model;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.kxdilbeck.gradeapp.R;

import java.util.List;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.AssignmentViewHolder> {
    private List<String[]> mData;
    private Context mContext;
    private View.OnClickListener mButtonAction;

    public class AssignmentViewHolder extends RecyclerView.ViewHolder {
        public Button mActionButton;

        public AssignmentViewHolder( View itemView) {
            super(itemView);
            mActionButton = itemView.findViewById(R.id.selectCourseButton);
            mActionButton.setOnClickListener(mButtonAction);
        }

    }

    public AssignmentAdapter(List<String[]> data, Context context, View.OnClickListener buttonAction){
        mData = data;
        mContext = context;
        mButtonAction = buttonAction;
    }

    @Override
    public AssignmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment_item, parent, false);
        return new AssignmentViewHolder(v);
    }

    public void clear(){
        int size = mData.size();
        mData.clear();
        notifyItemRangeRemoved(0, size);
    }

    @Override
    public void onBindViewHolder(AssignmentViewHolder holder, int position){
        String[] data = mData.get(position);

        if(data[1].equals("-1")) {
            SpannableString spanString = new SpannableString(data[0]);
            spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, spanString.length(), 0);
            holder.mActionButton.setTextSize(16);
            holder.mActionButton.setText(spanString);
        }else{
            holder.mActionButton.setText(data[0]);
        }

        holder.mActionButton.setTag(Integer.parseInt(data[1]));
    }

    @Override
    public int getItemCount(){
        return mData.size();
    }

}