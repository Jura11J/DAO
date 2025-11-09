package dao.jdbc;

import dao.StudentDAO;
import model.Course;
import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentJDBC implements StudentDAO {
    Connection connection;

    public StudentJDBC(Connection connection, boolean test) throws SQLException {
        this.connection = connection;
        if (test) {
            connection.setAutoCommit(false);
        }
    }

    @Override
    public void addStudent(Student student) {
        String insertSql = "INSERT INTO students(first_name, last_name, email) VALUES (?, ?, ?)";
        try (PreparedStatement insert = this.connection.prepareStatement(insertSql)) {
            insert.setString(1, student.getFirstName());
            insert.setString(2, student.getLastName());
            insert.setString(3, student.getEmail());
            int rows = insert.executeUpdate();
            System.out.println("Dodano studenta (wierszy: " + rows + ").");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student getStudentById(int id) {
        String selectSql = "SELECT id, first_name, last_name FROM students WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(selectSql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getInt("id"));
                    student.setFirstName(rs.getString("first_name"));
                    student.setLastName(rs.getString("last_name"));
                    student.setEmail(rs.getString("email"));
                    return student;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Błąd podczas pobierania studenta o id=" + id, e);
        }
    }


    @Override
    public Student getStudentByEmail(String email) {
        String selectSql = "SELECT id, first_name, last_name FROM students WHERE email = ?";

        try (PreparedStatement ps = connection.prepareStatement(selectSql)) {
            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getInt("id"));
                    student.setFirstName(rs.getString("first_name"));
                    student.setLastName(rs.getString("last_name"));
                    student.setEmail(rs.getString("email"));
                    return student;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Błąd podczas pobierania studenta o id=" + email, e);
        }
    }

    @Override
    public List<Student> getAllStudents() {
        String sql = "SELECT id, first_name FROM students";
        List<Student> students = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setFirstName(rs.getString("first_name"));
                s.setLastName(rs.getString("last_name"));
                s.setEmail(rs.getString("email"));
                students.add(s);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Błąd podczas pobierania listy studentów", e);
        }

        return students;
    }


    @Override
    public void updateStudent(Student student) {
        String sql = "UPDATE students SET first_name = ?, last_name = ?, email = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getEmail());
            ps.setInt(2, student.getId());

            int rows = ps.executeUpdate();

            if (rows == 0) {
                throw new RuntimeException("Brak studenta o id=" + student.getId());
            }

        } catch (SQLException e) {
            try {
                    connection.rollback();

            } catch (SQLException ignored) { }
            throw new RuntimeException("Błąd podczas aktualizacji studenta o id=" + student.getId(), e);
        }
    }


    @Override
    public void deleteStudent(int id) {
        String sql = "DELETE students WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("Brak studenta o id=" + id);
            }
        }
        catch (SQLException e) {
            try {
                connection.rollback();

            } catch (SQLException ignored) { }
            throw new RuntimeException("Błąd podczas aktualizacji studenta o id=" + id, e);
        }
        }


    @Override
    public List<Course> getEnrolledCourses(int studentId) {
        String sql =
                "SELECT c.id, c.name " +
                        "FROM courses c " +
                        "JOIN students e ON e.course_id = c.id " +
                        "WHERE e.id = ?";

        List<Course> courses = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Course course = new Course();
                    course.setId(rs.getInt("id"));
                    course.setCourseName(rs.getString("name"));
                    courses.add(course);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Błąd podczas pobierania kursów dla studenta o id=" + studentId, e);
        }

        return courses;
    }

}

