package service;

import dao.jdbc.CourseJDBC;
import dao.jdbc.GradeJDBC;
import dao.jdbc.StudentJDBC;
import model.Course;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DatabaseServiceTest {

    DatabaseService databaseService;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        try {
            Connection connection = new DatabaseConnection().getConnection();
            StudentJDBC studentJDBC = new StudentJDBC(connection, true);
            GradeJDBC gradeJDBC = new GradeJDBC(connection, true);
            CourseJDBC courseJDBC = new CourseJDBC(connection, true);
            databaseService = new DatabaseService(studentJDBC, courseJDBC, gradeJDBC);
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to database");
        }
    }

    @org.junit.jupiter.api.Test
    void registerStudent() {
        databaseService.registerStudent("test", "test", "test");
        assertEquals("test", databaseService.getStudent(4).getFirstName());
    }

    @org.junit.jupiter.api.Test
    void calculateStudentGpa() {
        assertEquals(3, databaseService.calculateStudentGpa(3), 0.01);
    }

    @org.junit.jupiter.api.Test
    void getEnrolledCourses() {
        assertEquals(2, databaseService.getEnrolledCourses(1).size());
    }

    @org.junit.jupiter.api.Test
    void removeStudent() {
        databaseService.removeStudent(1);
        assertEquals(2, databaseService.getAllStudents().size());
    }

    @org.junit.jupiter.api.Test
    void addCourse() {
        databaseService.addCourse("test");
        assertEquals(4, databaseService.getAllCourses().size());
    }

    @org.junit.jupiter.api.Test
    void getNumberOfEnrolledStudents() {
        assertEquals(2, databaseService.getNumberOfEnrolledStudents(1));
    }

    @org.junit.jupiter.api.Test
    void getEnrolledStudents() {
        assertEquals(2, databaseService.getEnrolledStudents(1).size());
    }

    @org.junit.jupiter.api.Test
    void removeCourse() {
        databaseService.removeCourse(1);
        assertEquals(2, databaseService.getAllCourses().size());
    }

    @org.junit.jupiter.api.Test
    void calculateCourseGpa() {
        assertEquals(2, databaseService.calculateCourseGpa(3), 0.01);
    }

    @org.junit.jupiter.api.Test
    void enrollStudentInCourse() {
        databaseService.enrollStudentInCourse(1, 3);
        assertEquals(3, databaseService.getEnrolledCourses(1).size());
    }

    @org.junit.jupiter.api.Test
    void updateGrade() {
        databaseService.updateGrade(1,2,5);
        assertEquals(5, databaseService.calculateStudentGpa(1), 0.01);
    }

    @org.junit.jupiter.api.Test
    void getGrade() {
        assertEquals(5, databaseService.getGrade(1,1),0.01);
    }

    @org.junit.jupiter.api.Test
    void unenrollStudent() {
        databaseService.unenrollStudent(1, 1);
        assertEquals(1, databaseService.getEnrolledCourses(1).size());
    }
}