package org.example.dao;

import org.example.model.StudentRecord;
import org.example.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private static Connection conn;

    public static void createConnection() {
        try{
            if(conn == null || conn.isClosed()) conn = DBUtil.getConnection();
        } catch (SQLException ex) {
            System.out.println("Error connecting to db: " + ex.getMessage());
        }
    }

    public void addStudentRecord(StudentRecord sr) {
        String q = "INSERT INTO csv_students(id, name, subject, grade) VALUES(?, ?, ?, ?);";
        createConnection();

        try(PreparedStatement ps = conn.prepareStatement(q)) {
            ps.setInt(1, sr.getId());
            ps.setString(2, sr.getName());
            ps.setString(3, sr.getSubject());
            ps.setInt(4, sr.getGrade());
            ps.executeUpdate();
            System.out.println("Student record added");
        } catch (SQLException ex) {
            System.out.println("Error adding student record: " + ex.getMessage());
        }
    }

    public void deleteStudentRecord(int id, String subject) {
        String q = "DELETE FROM csv_students WHERE id = ? AND subject = ?;";
        createConnection();
        try(PreparedStatement ps = conn.prepareStatement(q)) {
            ps.setInt(1, id);
            ps.setString(2, subject);
            ps.executeUpdate();
            System.out.println("Student record deleted");
        } catch (SQLException ex) {
            System.out.println("Error deleting student record: " + ex.getMessage());
        }
    }

    public List<StudentRecord> fetchStudentsRecords() {

        List<StudentRecord> studentsRecords = new ArrayList<>();
        String q = "SELECT * FROM csv_students;";
        createConnection();

        try(Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(q);
            while(rs.next()) {
                StudentRecord sr = new StudentRecord(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4));
                studentsRecords.add(sr);
            }
        } catch (SQLException ex) {
            System.out.println("Error fetching students' records: " + ex.getMessage());
        }
        return studentsRecords;
    }

    public StudentRecord totalAvgGrade(int id) {
        StudentRecord sr = null;
        String q = "SELECT id, name, ROUND(AVG(grade), 2) FROM csv_students WHERE id = ? GROUP BY id, name;";
        createConnection();

        try(PreparedStatement ps = conn.prepareStatement(q)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                sr = new StudentRecord(rs.getInt(1), rs.getString(2), rs.getDouble(3));
            }
        } catch (SQLException ex) {
            System.out.println("Error calculating average grade: " + ex.getMessage());
        }
        return sr;
    }
}
