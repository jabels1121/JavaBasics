package javaio;

import collections.map.treemap.AverageStudentGrade;
import collections.map.treemap.SubjectGrade;
import collections.map.treemap.TreeMapLauncher;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;

public class IOMain {

    public static final String FILE_NAME = "GradeBook.txt";
    public static final String BINARY_FILE = "Students.bin";

    public static void main(String[] args) throws IOException {
        SortedMap<AverageStudentGrade, Set<SubjectGrade>> grades = TreeMapLauncher.createGrades();
        Reader reader = new Reader();
        Writer writer = new Writer();
        FileUtils utils = new FileUtils();
        writer.writeFile(grades, FILE_NAME);
        //utils.printNioFileDetails(FILE_NAME);
        /*utils.deleteFileByName(FILE_NAME);
        try {
            reader.readFileInFull(FILE_NAME);
        } catch (NoSuchFileException e) {
            writer.writeFile(grades, FILE_NAME);
            reader.readFileInFull(FILE_NAME);
        }*/

        reader.nioReadFileWithBuffer(FILE_NAME);

        /*reader.readFile(FILE_NAME);
        processGrades(grades, writer, BINARY_FILE);
        outPutObjects(reader, BINARY_FILE);*/
    }

    private static void processGrades(SortedMap<AverageStudentGrade, Set<SubjectGrade>> grades, Writer writer, String fileName) {
        List<Student> students = new ArrayList<>();
        for (AverageStudentGrade gradeKey: grades.keySet()) {
            students.add(new Student(gradeKey.getName(), gradeKey.getAverageGrade(), grades.get(gradeKey)));
        }
        writer.writeObjects(students, fileName);
    }

    private static void outPutObjects(Reader reader, String fileName) {
        List<Student> students = reader.readObjects(fileName);
        for (Student student : students) {
            System.out.printf("%-15s %-15.2f %n", student.getStudentName(), student.getAverageGrage());
            System.out.println(student.getGrades());
        }
    }

}
