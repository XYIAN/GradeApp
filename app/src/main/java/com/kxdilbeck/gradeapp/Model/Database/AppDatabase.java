package com.kxdilbeck.gradeapp.Model.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.kxdilbeck.gradeapp.Model.Assignment;
import com.kxdilbeck.gradeapp.Model.Course;
import com.kxdilbeck.gradeapp.Model.Enrollment;
import com.kxdilbeck.gradeapp.Model.Grade;
import com.kxdilbeck.gradeapp.Model.GradeCategory;
import com.kxdilbeck.gradeapp.Model.User;

/**
 * Database class
 */
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
    private static AppDatabase db;

    public abstract UserDAO getUserDAO();
    public abstract AssignmentDAO getAssignmentDAO();
    public abstract CourseDAO getCourseDAO();
    public abstract EnrollmentDAO getEnrollmentDAO();
    public abstract GradeCategoryDAO getGradeCategoryDAO();
    public abstract GradeDAO getGradeDAO();

    /**
     * Returns the instance of the db
     * @param context
     * @return
     */
    public static final AppDatabase getDb(Context context){
        if(db == null){
            db = Room.databaseBuilder(context, AppDatabase.class, AppDatabase.DBNAME).allowMainThreadQueries().build();
        }

        return db;
    }
}
