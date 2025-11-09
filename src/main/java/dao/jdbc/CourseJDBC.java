package dao.jdbc;

import dao.CourseDAO;
import model.Course;
import model.Student;
import java.sql.*;

import java.util.List;

public class CourseJDBC implements CourseDAO {

    Connection connection;

    public CourseJDBC(Connection connection, boolean test) throws SQLException {
        this.connection = connection;
        if (test) {
            connection.setAutoCommit(false);
        }
    }

    @Override
    public void addCourse(Course course) {

    }

    @Override
    public Course getCourseById(int id) {
        return null;
    }

    @Override
    public Course getCourseByName(String name) {
        return null;
    }

    @Override
    public List<Course> getAllCourses() {
        return List.of();
    }

    @Override
    public void updateCourse(Course course) {

    }

    @Override
    public void deleteCourse(int id) {

    }

    @Override
    public List<Student> getEnrolledStudents(int courseId) {
        return List.of();
    }
}
