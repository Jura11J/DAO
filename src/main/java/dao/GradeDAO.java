package dao;

import model.Grade;
import model.Student;

import java.util.List;

public interface GradeDAO {
    void enrollStudent(int studentId, int courseId);
    void unenrollStudent(int studentId, int courseId);
    void updateGrade(int studentId, int courseId, int grade);

    int getGrade(int studentId, int courseId);
    List<Grade> getGradesForStudent(int studentId);
    List<Grade> getGradesForCourse(int courseId);
}
