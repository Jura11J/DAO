package dao.jdbc;

import dao.GradeDAO;
import model.Grade;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GradeJDBC implements GradeDAO {

    Connection connection;

    public GradeJDBC(Connection connection, boolean test) throws SQLException {
        this.connection = connection;
        if (test) {
            connection.setAutoCommit(false);
        }
    }

    @Override
    public void enrollStudent(int studentId, int courseId) {

    }

    @Override
    public void unenrollStudent(int studentId, int courseId) {

    }

    @Override
    public void updateGrade(int studentId, int courseId, int grade) {

    }

    @Override
    public int getGrade(int studentId, int courseId) {
        return 0;
    }

    @Override
    public List<Grade> getGradesForStudent(int studentId) {
        return List.of();
    }

    @Override
    public List<Grade> getGradesForCourse(int courseId) {
        return List.of();
    }
}
