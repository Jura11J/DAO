package service;

import dao.CourseDAO;
import dao.GradeDAO;
import dao.StudentDAO;
import model.Course;
import model.Student;

import java.util.List;

public class DatabaseService {
    private StudentDAO studentDAO;
    private CourseDAO courseDAO;
    private GradeDAO gradeDAO;

    public DatabaseService(StudentDAO studentDAO, CourseDAO courseDAO, GradeDAO gradeDAO) {
        this.studentDAO = studentDAO;
        this.courseDAO = courseDAO;
        this.gradeDAO = gradeDAO;
    }

    // Student

    public void registerStudent(String firstName, String lastName, String email) {
        int id=0;
        Student student = new Student(id,firstName,lastName,email);
        studentDAO.addStudent(student);

    }

    public double calculateStudentGpa(int studentId) {
        return 0.0;
    }

    public List<Course> getEnrolledCourses(int studentId) {
        return studentDAO.getEnrolledCourses(studentId);
    }

    public void removeStudent(int studentId) {
        studentDAO.deleteStudent(studentId);
    }

    public Student getStudent(int studentId) {
        return studentDAO.getStudentById(studentId);
    }

    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    // Course

    public void addCourse(String courseName) {}

    public int getNumberOfEnrolledStudents(int courseId) {
        return 0;
    }

    public List<Student> getEnrolledStudents(int courseId) {
        return null;
    }

    public void removeCourse(int courseId) {
    }

    public double calculateCourseGpa(int courseId) {
        return 0;
    }

    public Course getCourse(int courseId) {
        return null;
    }

    public List<Course> getAllCourses() {
        return null;
    }

    // Grade

    public void enrollStudentInCourse(int studentId, int courseId) { }

    public void updateGrade(int studentId, int courseId, int grade) {}

    public int getGrade(int studentId, int courseId) {
        return 0;
    }

    public void unenrollStudent(int studentId, int courseId) {}
}
