package com.kxdilbeck.gradeapp.Model;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseAdapt extends RecyclerView.Adapter<CourseAdapt.CourseViewHolder>{

    private List<Course> listofCourses;
    private List<Grade>  listofGrades;

    @NonNull
    @Override
    public CourseAdapt.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapt.CourseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder {
        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
