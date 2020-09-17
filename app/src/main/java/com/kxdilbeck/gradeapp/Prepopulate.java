package com.kxdilbeck.gradeapp;

import android.content.Context;

import androidx.room.Room;

import com.kxdilbeck.gradeapp.Model.Assignment;
import com.kxdilbeck.gradeapp.Model.Course;
import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;
import com.kxdilbeck.gradeapp.Model.Database.AssignmentDAO;
import com.kxdilbeck.gradeapp.Model.Database.CourseDAO;
import com.kxdilbeck.gradeapp.Model.Database.EnrollmentDAO;
import com.kxdilbeck.gradeapp.Model.Database.GradeCategoryDAO;
import com.kxdilbeck.gradeapp.Model.Database.GradeDAO;
import com.kxdilbeck.gradeapp.Model.Database.UserDAO;
import com.kxdilbeck.gradeapp.Model.Enrollment;
import com.kxdilbeck.gradeapp.Model.Grade;
import com.kxdilbeck.gradeapp.Model.GradeCategory;
import com.kxdilbeck.gradeapp.Model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Prepopulate inserts default values into the database.
 */
public class Prepopulate {
    private CourseDAO mCourseDao;
    private static List<Integer> userIds;
    private static List<Integer> courseIds;
    private static List<Integer> gradeIds;
    private static List<Integer> assignmentIds;
    private static List<Integer> gradeCategoryIds;

    /**
     * Constructor for Prepopulate.
     * @param context
     */
    public Prepopulate(Context context){

        mCourseDao = Room.databaseBuilder(context, AppDatabase.class, AppDatabase.DBNAME)
                .allowMainThreadQueries()
                .build().getCourseDAO();
    }

    /**
     * Given a db it inserts prepopulated values into it.
     * @param appDatabase
     */
    public static void prepopulate(AppDatabase appDatabase){
           userIds = new ArrayList<>();
           courseIds = new ArrayList<>();
           gradeIds = new ArrayList<>();
           gradeCategoryIds = new ArrayList<>();
           assignmentIds = new ArrayList<>();

           addCourses(appDatabase);
           addUsers(appDatabase);
           addEnrollments(appDatabase);
           addGradeCategories(appDatabase);
           addGrades(appDatabase);
           addAssignments(appDatabase);

           userIds = null;
           courseIds = null;
           gradeIds = null;
           gradeCategoryIds = null;
           assignmentIds = null;
    }

    /**
     * Inserts prepopulated courses.
     * @param appDatabase
     */
    private static void addCourses(AppDatabase appDatabase){
        CourseDAO courseDAO;

        courseDAO = appDatabase.getCourseDAO();

        if(courseDAO.getAllCourses().size() == 0){
            courseIds.add(courseDAO.insert(new Course("Dr. teacher", "BIO", "title here", "12/12/12", "12/13/12")).get(0).intValue());
            courseIds.add(courseDAO.insert(new Course("Dr. guy", "SCIENCE", "title here", "12/12/12", "12/13/12")).get(0).intValue());
            courseIds.add(courseDAO.insert(new Course("Dr. professor", "HISTORY", "title here", "12/12/12", "12/13/12")).get(0).intValue());
            courseIds.add(courseDAO.insert(new Course("Dr. pro", "ANATOMY", "title here", "12/12/15", "12/13/12")).get(0).intValue());
            courseIds.add(courseDAO.insert(new Course("Dr. it", "CS", "title here", "12/12/15", "12/13/12")).get(0).intValue());
            courseIds.add(courseDAO.insert(new Course("Dr. llama", "CS", "title here", "12/12/16", "12/13/12")).get(0).intValue());
            courseIds.add(courseDAO.insert(new Course("Dr. Evil", "BIO", "title here", "12/12/15", "12/13/12")).get(0).intValue());
        }
    }

    /**
     * Inserts prepopulate users.
     * @param appDatabase
     */
    private static void addUsers(AppDatabase appDatabase){
        UserDAO userDAO = appDatabase.getUserDAO();

        if(userDAO.getAllUsers().size() == 0) {
            userIds.add(userDAO.insert(new User("Admin2", "Password2", "First", "Last", 0)).get(0).intValue());
            userIds.add(userDAO.insert(new User("JohnS2020", "Password2", "John", "Smith", 1)).get(0).intValue());
            userIds.add(userDAO.insert(new User("JaneD2020", "Password2", "Jane", "Doe", 1)).get(0).intValue());
            userIds.add(userDAO.insert(new User("MorganF2020", "Password2", "Morgan", "Freemen", 1)).get(0).intValue());
            userIds.add(userDAO.insert(new User("Hacker2", "2Drowssap", "None", "None", 0)).get(0).intValue());
        }
    }

    /**
     * Inserts prepopulated enrollments.
     * @param appDatabase
     */
    private static void addEnrollments(AppDatabase appDatabase){
        EnrollmentDAO enrollmentDAO = appDatabase.getEnrollmentDAO();

        if(enrollmentDAO.getAllEnrollments().size() == 0) {
            enrollmentDAO.insert(new Enrollment(userIds.get(0), courseIds.get(0), "09/09/2021"));
            enrollmentDAO.insert(new Enrollment(userIds.get(0), courseIds.get(2), "09/09/2021"));
            enrollmentDAO.insert(new Enrollment(userIds.get(0), courseIds.get(3), "09/09/2021"));
            enrollmentDAO.insert(new Enrollment(userIds.get(1), courseIds.get(0), "09/09/2021"));
            enrollmentDAO.insert(new Enrollment(userIds.get(1), courseIds.get(2), "09/09/2021"));
            enrollmentDAO.insert(new Enrollment(userIds.get(2), courseIds.get(0), "09/09/2021"));
            enrollmentDAO.insert(new Enrollment(userIds.get(2), courseIds.get(4), "09/09/2021"));
            enrollmentDAO.insert(new Enrollment(userIds.get(2), courseIds.get(5), "09/09/2021"));
            enrollmentDAO.insert(new Enrollment(userIds.get(3), courseIds.get(0), "09/09/2021"));
            enrollmentDAO.insert(new Enrollment(userIds.get(3), courseIds.get(1), "09/09/2021"));
            enrollmentDAO.insert(new Enrollment(userIds.get(3), courseIds.get(4), "09/09/2021"));
        }
    }

    /**
     * Inserts prepopulated grade categories.
     * @param appDatabase
     */
    private static void addGradeCategories(AppDatabase appDatabase){
        GradeCategoryDAO gradeCategoryDAO = appDatabase.getGradeCategoryDAO();

        if(gradeCategoryDAO.getAllCategories().size() == 0){
            gradeCategoryIds.add(gradeCategoryDAO.insert(new GradeCategory("Test", 40.0)).get(0).intValue());
            gradeCategoryIds.add(gradeCategoryDAO.insert(new GradeCategory("Quiz", 10.0)).get(0).intValue());
            gradeCategoryIds.add(gradeCategoryDAO.insert(new GradeCategory("Homework", 10.0)).get(0).intValue());
            gradeCategoryIds.add(gradeCategoryDAO.insert(new GradeCategory("Midterm", 20.0)).get(0).intValue());
            gradeCategoryIds.add(gradeCategoryDAO.insert(new GradeCategory("Final", 20.0)).get(0).intValue());
        }
    }

    /**
     * Inserts prepopulated grades.
     * @param appDatabase
     */
    private static void addGrades(AppDatabase appDatabase){
        GradeDAO gradeDAO = appDatabase.getGradeDAO();

        if(gradeDAO.getAllGrades().size() == 0){
            gradeIds.add(gradeDAO.insert(new Grade(25.0, userIds.get(0), gradeCategoryIds.get(0))).get(0).intValue());
            gradeIds.add(gradeDAO.insert(new Grade(15.0, userIds.get(1), gradeCategoryIds.get(0))).get(0).intValue());
            gradeIds.add(gradeDAO.insert(new Grade(40.0, userIds.get(0), gradeCategoryIds.get(1))).get(0).intValue());
            gradeIds.add(gradeDAO.insert(new Grade(25.0, userIds.get(1), gradeCategoryIds.get(1))).get(0).intValue());
            gradeIds.add(gradeDAO.insert(new Grade(9.0, userIds.get(0), gradeCategoryIds.get(2))).get(0).intValue());
            gradeIds.add(gradeDAO.insert(new Grade(12.0, userIds.get(1), gradeCategoryIds.get(2))).get(0).intValue());
            gradeIds.add(gradeDAO.insert(new Grade(25.0, userIds.get(0), gradeCategoryIds.get(3))).get(0).intValue());
            gradeIds.add(gradeDAO.insert(new Grade(25.0, userIds.get(1), gradeCategoryIds.get(3))).get(0).intValue());
            gradeIds.add(gradeDAO.insert(new Grade(35.0, userIds.get(0), gradeCategoryIds.get(4))).get(0).intValue());
            gradeIds.add(gradeDAO.insert(new Grade(50.0, userIds.get(1), gradeCategoryIds.get(4))).get(0).intValue());
        }

    }

    /**
     * Inserts prepopulated assignments.
     * @param appDatabase
     */
    private static void addAssignments(AppDatabase appDatabase){
        AssignmentDAO assignmentDAO = appDatabase.getAssignmentDAO();

        if(assignmentDAO.getAllAssignments().size() == 0){
            assignmentIds.add(assignmentDAO.insert(new Assignment(courseIds.get(0), gradeIds.get(0), "09/12/2021", "09/09/2021", 25.0,
                    30.0, "Done")).get(0).intValue());
            assignmentIds.add(assignmentDAO.insert(new Assignment(courseIds.get(0), gradeIds.get(1), "09/12/2021", "09/09/2021", 15.0,
                    30.0, "Done")).get(0).intValue());
            assignmentIds.add(assignmentDAO.insert(new Assignment(courseIds.get(0), gradeIds.get(2), "09/12/2021", "09/09/2021", 40.0,
                    40.0, "Done")).get(0).intValue());
            assignmentIds.add(assignmentDAO.insert(new Assignment(courseIds.get(0), gradeIds.get(3), "09/12/2021", "09/09/2021", 25.0,
                    50.0, "Done")).get(0).intValue());
            assignmentIds.add(assignmentDAO.insert(new Assignment(courseIds.get(2), gradeIds.get(4), "09/12/2021", "09/09/2021", 9.0,
                    10.0, "Done")).get(0).intValue());
            assignmentIds.add(assignmentDAO.insert(new Assignment(courseIds.get(2), gradeIds.get(5), "09/12/2021", "09/09/2021", 12.0,
                    15.0, "Done")).get(0).intValue());
            assignmentIds.add(assignmentDAO.insert(new Assignment(courseIds.get(2), gradeIds.get(6), "09/12/2021", "09/09/2021", 25.0,
                    30.0, "Done")).get(0).intValue());
            assignmentIds.add(assignmentDAO.insert(new Assignment(courseIds.get(2), gradeIds.get(7), "09/12/2021", "09/09/2021", 25.0,
                    30.0, "Done")).get(0).intValue());
            assignmentIds.add(assignmentDAO.insert(new Assignment(courseIds.get(2), gradeIds.get(8), "09/12/2021", "09/09/2021", 35.0,
                    45.0, "Done")).get(0).intValue());
            assignmentIds.add(assignmentDAO.insert(new Assignment(courseIds.get(2), gradeIds.get(9), "09/12/2021", "09/09/2021", 50.0,
                    50.0, "Done")).get(0).intValue());
        }

    }
}
