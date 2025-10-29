package org.example.model;

import com.opencsv.bean.CsvBindByPosition;

public class StudentRecord {
    @CsvBindByPosition(position = 0)
    private int id;

    @CsvBindByPosition(position = 1)
    private String name;

    @CsvBindByPosition(position = 2)
    private String subject;

    @CsvBindByPosition(position = 3)
    private int grade;

    private double avg;
    public StudentRecord(){}
    public StudentRecord(int id, String name, String subject, int grade) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.grade = grade;
    }
    public StudentRecord(int id, String name, double avg) {
        this.id = id;
        this.name = name;
        this.avg = avg;
    }

    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public double getAvg() {
        return avg;
    }
}
