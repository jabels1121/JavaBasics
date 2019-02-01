package collections.map.treemap;

import java.util.*;

public class TreeMapLauncher {

    public static void main(String[] args) {

        NavigableMap<AverageStudentGrade, Set<SubjectGrade>> grades = createGrades();
        printGrades(grades, false);
        AverageStudentGrade border = grades.ceilingKey(new AverageStudentGrade("", 80));
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        System.out.println("Scholarship students:");
        SortedMap<AverageStudentGrade, Set<SubjectGrade>> scholarshipStudents = grades.tailMap(border);
        printGrades(scholarshipStudents, false);
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        System.out.println("Contender student:");
        AverageStudentGrade contender = grades.lowerKey(border);
        System.out.println(contender);
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        System.out.println("Higher grade student:");
        Map.Entry<AverageStudentGrade, Set<SubjectGrade>> higherGradeStudent = grades.lastEntry();
        System.out.println(higherGradeStudent);
    }

    private static void printGrades(Map<AverageStudentGrade, Set<SubjectGrade>> grades, boolean printValue) {
        Set<AverageStudentGrade> averageStudentGrades = grades.keySet();
        for (AverageStudentGrade grade:
             averageStudentGrades) {
            System.out.println(grade);
            if (printValue) {
                System.out.println(grades.get(grade));
            }
        }
    }

    public static NavigableMap<AverageStudentGrade, Set<SubjectGrade>> createGrades() {
        Set<SubjectGrade> alexGrades = new HashSet<>();
        alexGrades.add(new SubjectGrade("History", 83));
        alexGrades.add(new SubjectGrade("Physics", 65));
        alexGrades.add(new SubjectGrade("English-Language", 76));
        alexGrades.add(new SubjectGrade("Mathematics", 94));
        alexGrades.add(new SubjectGrade("Chemistry", 62));

        Set<SubjectGrade> janeGrades = new HashSet<>();
        janeGrades.add(new SubjectGrade("History", 69));
        janeGrades.add(new SubjectGrade("Physics", 82));
        janeGrades.add(new SubjectGrade("English-Language", 70));
        janeGrades.add(new SubjectGrade("Mathematics", 78));
        janeGrades.add(new SubjectGrade("Chemistry", 92));

        Set<SubjectGrade> mikeGrades = new HashSet<>();
        mikeGrades.add(new SubjectGrade("History", 78));
        mikeGrades.add(new SubjectGrade("Physics", 78));
        mikeGrades.add(new SubjectGrade("English-Language", 84));
        mikeGrades.add(new SubjectGrade("Mathematics", 89));
        mikeGrades.add(new SubjectGrade("Chemistry", 79));

        Set<SubjectGrade> saraGrades = new HashSet<>();
        saraGrades.add(new SubjectGrade("History", 91));
        saraGrades.add(new SubjectGrade("Physics", 89));
        saraGrades.add(new SubjectGrade("English-Language", 96));
        saraGrades.add(new SubjectGrade("Mathematics", 92));
        saraGrades.add(new SubjectGrade("Chemistry", 87));

        Set<SubjectGrade> victoriaGrades = new HashSet<>();
        victoriaGrades.add(new SubjectGrade("History", 98));
        victoriaGrades.add(new SubjectGrade("Physics", 92));
        victoriaGrades.add(new SubjectGrade("English-Language", 84));
        victoriaGrades.add(new SubjectGrade("Mathematics", 90));
        victoriaGrades.add(new SubjectGrade("Chemistry", 79));

        NavigableMap<AverageStudentGrade, Set<SubjectGrade>> map = new TreeMap<>();
        map.put(new AverageStudentGrade("Alex", calcAverage(alexGrades)), alexGrades);
        map.put(new AverageStudentGrade("Mike", calcAverage(mikeGrades)), mikeGrades);
        map.put(new AverageStudentGrade("Sara", calcAverage(saraGrades)), saraGrades);
        map.put(new AverageStudentGrade("Victoria", calcAverage(victoriaGrades)), victoriaGrades);
        map.put(new AverageStudentGrade("Jane", calcAverage(janeGrades)), janeGrades);

        return map;
    }

    private static float calcAverage(Set<SubjectGrade> grades) {
        float sum = 0;
        for (SubjectGrade sg : grades) {
            sum += sg.getGrade();
        }
        return sum / grades.size();
    }

}
