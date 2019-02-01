package javaio;

import collections.map.treemap.SubjectGrade;

import java.io.Serializable;
import java.util.Set;

public class Student implements Serializable {

    private String studentName;
    private float averageGrage;
    private Set<SubjectGrade> grades;

    public Student(String studentName, float averageGrage, Set<SubjectGrade> grades) {
        this.studentName = studentName;
        this.averageGrage = averageGrage;
        this.grades = grades;
    }

    public String getStudentName() {
        return studentName;
    }

    public float getAverageGrage() {
        return averageGrage;
    }

    public Set<SubjectGrade> getGrades() {
        return grades;
    }
}
