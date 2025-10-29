package org.example.service;

import org.example.dao.StudentDAO;
import org.example.model.StudentRecord;
import org.example.util.CSVUtil;
import org.example.util.CreateFileUtil;
import org.example.util.ReadInputUtil;

import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private static List<StudentRecord> studentRecords = new ArrayList<>();

    private static final StudentDAO dao = new StudentDAO();
    private static final CreateFileUtil cf = new CreateFileUtil();

    public void fileLauncher() {
        cf.createFile();
    }

    public void addStudentRecord() {
        int id = ReadInputUtil.readIntInput("ID: ");
        String name = ReadInputUtil.readStringInput("Name: ");
        String subject = ReadInputUtil.readStringInput("Subject: ");
        int grade = ReadInputUtil.readIntInput("Grade: ");
        StudentRecord sr = new StudentRecord(id, name, subject, grade);
        dao.addStudentRecord(sr);
        studentRecords = dao.fetchStudentsRecords();
        CSVUtil.writeToCsv(studentRecords, cf.path);
    }

    public void deleteStudentRecord() {
        int id = ReadInputUtil.readIntInput("ID: ");
        String subject = ReadInputUtil.readStringInput("Subject: ");
        dao.deleteStudentRecord(id, subject);
        studentRecords = dao.fetchStudentsRecords();
        CSVUtil.writeToCsv(studentRecords, cf.path);
    }

    public void totalAvgGrade() {
        int id = ReadInputUtil.readIntInput("ID: ");
        StudentRecord sr = dao.totalAvgGrade(id);
        System.out.printf("%nID: %d%nName: %s%nTotal Average: %.2f%n%n", sr.getId(), sr.getName(), sr.getAvg());
    }

    public void showStudentRecords() {
        studentRecords = CSVUtil.readFromCsv(cf.path);
        if(studentRecords.isEmpty()) {
            System.out.println("CSV is empty");
            studentRecords = dao.fetchStudentsRecords();
        }
        for (StudentRecord sr : studentRecords) {
            System.out.printf("%nID: %d%nName: %s%nSubject: %s%nGrade: %d%n%n",
                    sr.getId(),
                    sr.getName(),
                    sr.getSubject(),
                    sr.getGrade());
        }
    }
}
