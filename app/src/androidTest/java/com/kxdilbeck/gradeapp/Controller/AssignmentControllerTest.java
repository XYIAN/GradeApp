package com.kxdilbeck.gradeapp.Controller;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import com.kxdilbeck.gradeapp.Model.Assignment;
import com.kxdilbeck.gradeapp.Model.Course;
import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;
import com.kxdilbeck.gradeapp.Model.Database.AssignmentDAO;
import com.kxdilbeck.gradeapp.Model.Database.CourseDAO;
import com.kxdilbeck.gradeapp.Model.Database.GradeCategoryDAO;
import com.kxdilbeck.gradeapp.Model.Database.GradeDAO;
import com.kxdilbeck.gradeapp.Model.Database.UserDAO;
import com.kxdilbeck.gradeapp.Model.Grade;
import com.kxdilbeck.gradeapp.Model.GradeCategory;
import com.kxdilbeck.gradeapp.Model.User;
import com.kxdilbeck.gradeapp.Prepopulate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AssignmentControllerTest {
    private AppDatabase db;
    private AssignmentController assignmentController;
    private UserDAO userDAO;
    private CourseDAO courseDAO;
    private AssignmentDAO assignmentDAO;
    private GradeCategoryDAO gradeCategoryDAO;
    private GradeDAO gradeDAO;

    @Before
    public void setUp() throws Exception {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db =  Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries()
                .build();
        Prepopulate.prepopulate(db);
        userDAO = db.getUserDAO();
        courseDAO = db.getCourseDAO();
        gradeCategoryDAO = db.getGradeCategoryDAO();
        assignmentDAO = db.getAssignmentDAO();
        gradeDAO = db.getGradeDAO();

        assignmentController = new AssignmentController(db);
    }


    @After
    public void tearDown() throws Exception {
        db.close();
        assignmentController = null;
        userDAO = null;
        courseDAO = null;
        gradeCategoryDAO = null;
        assignmentDAO = null;
        gradeDAO = null;
    }

    @Test
    public void getCategoryTitle() {
        User user =  userDAO.getAllUsers().get(0);
        Course course = courseDAO.getAllEnrolledCourses(user.getUserId()).get(0);
        List<Integer> categoryId = gradeDAO.getGradeCategoriesForCourse(course.getCourseId(), user.getUserId());

        assertEquals("Test", assignmentController.getCategoryTitle(categoryId.get(0).intValue()));
        assertEquals("Quiz", assignmentController.getCategoryTitle(categoryId.get(1).intValue()));
    }

    @Test
    public void checkScore() {
        assertTrue(assignmentController.checkScore("5"));
        assertFalse(assignmentController.checkScore(""));
    }

    @Test
    public void checkMaxScore() {
        assertTrue(assignmentController.checkMaxScore("5"));
        assertFalse(assignmentController.checkMaxScore(""));
    }

    @Test
    public void checkDate() {
        assertTrue(assignmentController.checkDate("08/08/08"));
        assertFalse(assignmentController.checkScore(""));
    }

    @Test
    public void checkCategory() {
        assertTrue(assignmentController.checkCategory("Test"));
        assertFalse(assignmentController.checkScore(""));
    }

    @Test
    public void checkWeight() {
        assertTrue(assignmentController.checkWeight("5"));
        assertFalse(assignmentController.checkWeight(""));
        assertFalse(assignmentController.checkWeight("101"));
    }

    /**
     * Tests adding an assignment with the AssignmentController. If an assignment is successfully
     *  added then the size of the assignment table will change.
     */
    @Test
    public void addAssignment() {
        User user =  userDAO.getAllUsers().get(0);
        Course course = courseDAO.getAllEnrolledCourses(user.getUserId()).get(0);
        List<Integer> categoryId = gradeDAO.getGradeCategoriesForCourse(course.getCourseId(), user.getUserId());

        GradeCategory gradeCategory = gradeCategoryDAO.getCategory(categoryId.get(0));

        int sizeBefore = assignmentDAO.getAllAssignments().size();
        assignmentController.addAssignment(course.getCourseId(), user.getUserId(), 25, 25, gradeCategory.getWeight(), "08/08/08",
                "08/08/09", gradeCategory.getTitle(), "NONE");
        int sizeAfter = assignmentDAO.getAllAssignments().size();

        assertNotEquals(sizeAfter, sizeBefore);
    }

    /**
     * Tests whether that the recycler view being supplied the right amount of data, and right
     * assignments.
     */
    @Test
    public void getDataForRecyclerView() {
        User user =  userDAO.getAllUsers().get(0);
        Course course = courseDAO.getAllEnrolledCourses(user.getUserId()).get(1);

        List<String[]> data = assignmentController.getDataForRecyclerView(course.getCourseId(), user.getUserId());
        List<Integer> categoryIds = gradeDAO.getGradeCategoriesForCourse(course.getCourseId(), user.getUserId());

        List<Integer> assignmentIds = new ArrayList<>();
        List<Integer> expectedAssignmentIds = new ArrayList<>();

        // collects assignment ids from data.
        for(String[] d : data){
            if(!d[1].equals("-1")){
                assignmentIds.add(Integer.parseInt(d[1]));
            }
        }

        int count = 0;
        // counts amount items that are expected, and gets assignmentIds.
        for(int i = 0; i < categoryIds.size(); i++){
            count++; // adds 1 for the category

            List<Assignment> assignments = assignmentDAO.getAssignmentsByCategory(course.getCourseId(), user.getUserId(), categoryIds.get(i).intValue());
            for(int j = 0; j < assignments.size(); j++){
                expectedAssignmentIds.add(assignments.get(j).getAssignmentId());
            }

            count+= assignments.size(); // adds 1 for each assignment
        }

        assertEquals(count, data.size());
        assertEquals(expectedAssignmentIds, assignmentIds);
    }
}