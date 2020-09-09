package com.kxdilbeck.gradeapp.Model.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.kxdilbeck.gradeapp.Model.Assignment;
import com.kxdilbeck.gradeapp.Model.Course;
import com.kxdilbeck.gradeapp.Model.Enrollment;
import com.kxdilbeck.gradeapp.Model.Grade;
import com.kxdilbeck.gradeapp.Model.GradeCategory;
import com.kxdilbeck.gradeapp.Model.User;

@Database(entities = {User.class, Assignment.class, Course.class, Enrollment.class,
        Grade.class, GradeCategory.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DBNAME = "db-grade-tracker";
    public static final String USER_TABLE = "User";
    public static final String ASSIGNMENT_TABLE = "Assignment";
    public static final String COURSE_TABLE = "Course";
    public static final String GRADE_TABLE = "Grade";
    public static final String GRADE_CATEGORY_TABLE = "Grade_Category";
    public static final String ENROLLMENT_TABLE = "Enrollment";

    public abstract UserDAO getUserDAO();
    public abstract AssignmentDAO getAssignmentDAO();
    public abstract CourseDAO getCourseDAO();
    public abstract EnrollmentDAO getEnrollmentDAO();
    public abstract GradeCategoryDAO getGradeCategoryDAO();
    public abstract GradeDAO getGradeDAO();
}
