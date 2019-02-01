package javaio;

import collections.map.treemap.AverageStudentGrade;
import collections.map.treemap.SubjectGrade;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Writer {

    public void writeWithFormatter() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Formatter formatter = new Formatter("BankAccounts.txt");
        System.out.println("Enter clientId, clientName, client surname, account balance");
        int i = 0;
        while (i < 3) {
            try {
                formatter.format("%d, %s, %s, %.2f%n", scanner.nextInt(), scanner.next(), scanner.next(), scanner.nextFloat());
                i++;
            } catch (InputMismatchException e) {
                System.out.println("Wrong value. Please try again.");
                scanner.nextLine();
            }
        }
        formatter.close();
    }

    public void writeFile(SortedMap<AverageStudentGrade, Set<SubjectGrade>> grades, String fileName) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (AverageStudentGrade gradeKey : grades.keySet()) {
                writer.write("++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                writer.write("Student: " + gradeKey.getName() + " Average grade is: " + gradeKey.getAverageGrade() + "\n");
                for (SubjectGrade grade : grades.get(gradeKey)) {
                    writer.write("Subject: " + grade.getSubject() + " grade is: " + grade.getGrade() + "\n");
                }
            }
        }
    }

    public void writeObjects(List<Student> students, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))) {
            for (Student student : students) {
                oos.writeObject(student);
            }
            oos.writeObject(new Student("", -1, null));
        } catch (IOException e) {
            System.out.println("File cannot be opened. Program terminates.");
            e.printStackTrace();
        }
    }
}
