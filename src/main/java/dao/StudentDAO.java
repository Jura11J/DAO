package dao;

import model.Course;
import model.Student;

import java.util.List;

public interface StudentDAO {
    void addStudent(Student student);
    Student getStudentById(int id);
    Student getStudentByEmail(String email);
    List<Student> getAllStudents();
    void updateStudent(Student student);
    void deleteStudent(int id);

    List<Course> getEnrolledCourses(int studentId);
}
