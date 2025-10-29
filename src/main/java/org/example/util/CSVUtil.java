package org.example.util;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.example.model.StudentRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {

    public static void writeToCsv(List<StudentRecord> studentsRecords, String path) {

        try(FileWriter fw = new FileWriter(path)) {
            StatefulBeanToCsv<StudentRecord> beanToCsv = new StatefulBeanToCsvBuilder<StudentRecord>(fw).build();
            beanToCsv.write(studentsRecords);
            System.out.println("Written to CSV");
        } catch (Exception ex) {
            System.out.println("Error writing to CSV: " + ex.getMessage());
        }
    }

    public static List<StudentRecord> readFromCsv(String path) {
        List<StudentRecord> studentRecords = new ArrayList<>();
        try {
            studentRecords = new CsvToBeanBuilder<StudentRecord>(new FileReader(path))
                    .withType(StudentRecord.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();
            System.out.println("Fetched from CSV");
        } catch (Exception ex) {
            System.out.println("Error writing to CSV: " + ex.getMessage());
        }
        return studentRecords;
    }
}
