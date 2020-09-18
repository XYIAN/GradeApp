package com.kxdilbeck.gradeapp.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kxdilbeck.gradeapp.R;

import java.util.List;

public class CourseAdapt extends RecyclerView.Adapter<CourseAdapt.CourseViewHolder>{
    private List<Course> mCourses;
    private List<String[]>  mGrades;
    private View.OnClickListener mButtonAction;

    public CourseAdapt(List<Course> courses, List<String[]> grades, View.OnClickListener buttonAction){
        mCourses = courses;
        mGrades = grades;
        mButtonAction = buttonAction;
    }

    @NonNull
    @Override
    public CourseAdapt.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.courseitem, parent, false);
        return new CourseViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapt.CourseViewHolder holder, int position) {
        Course course = mCourses.get(position);
        String[] grade = mGrades.get(position);

        holder.mActionButton.setText(course.getTitle() + "\t" + grade[0] + ": " + grade[1]);
        holder.mActionButton.setTag(course);
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder {
        public Button mActionButton;

        public CourseViewHolder( View itemView) {
            super(itemView);
            mActionButton = itemView.findViewById(R.id.courseRecyclerButton);
            mActionButton.setOnClickListener(mButtonAction);
        }

    }
}
