package org.example.app;

import org.example.service.StudentService;
import org.example.util.ReadInputUtil;

public class App {
    public static void main(String[] args) {
        StudentService ss = new StudentService();
        boolean r = true;

        ss.fileLauncher();
        while (r) {
            System.out.println("1. Add Student Record");
            System.out.println("2. Delete Student Record");
            System.out.println("3. List All Student' Records");
            System.out.println("4. Calculate Total Average Grade");
            System.out.println("5. Exit");

            int option = ReadInputUtil.readIntInput("Option: ");

            switch (option) {
                case 1 -> ss.addStudentRecord();
                case 2 -> ss.deleteStudentRecord();
                case 3 -> ss.showStudentRecords();
                case 4 -> ss.totalAvgGrade();
                case 5 -> r = false;
                default -> System.out.println("Invalid option");
            }
        }
    }
}
