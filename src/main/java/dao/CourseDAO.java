package dao;

import model.Course;
import model.Student;

import java.util.List;

public interface CourseDAO {
    void addCourse(Course course);
    Course getCourseById(int id);
    Course getCourseByName(String name);
    List<Course> getAllCourses();
    void updateCourse(Course course);
    void deleteCourse(int id);

    List<Student> getEnrolledStudents(int courseId);
}
