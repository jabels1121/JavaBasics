package streams;

import streams.model.Employee;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamOverviewMain {

    private static List<Employee> employees = new ArrayList<>();
    private static List<Employee> secondList = new ArrayList<>();
    private static Map<Integer, Employee> employeeMap = null;

    public static void main(String[] args) throws IOException {

        employees.add(new Employee(1, "John", "Black", 65000, "IT"));
        employees.add(new Employee(2, "Bill", "Woffy", 58000, "Finance"));
        employees.add(new Employee(3, "Susan", "Orange", 90000, "IT"));
        employees.add(new Employee(4, "Ann", "Magenta", 115000, "Finance"));
        employees.add(new Employee(4, "Ann", "Magenta", 115000, "IT"));
        employees.add(new Employee(5, "William", "Yellow", 88000, "Finance"));
        employees.add(new Employee(6, "Tommy", "Green", 80000, "IT"));
        employees.add(new Employee(7, "Robert", "Blue", 60000, "IT"));
        employees.add(new Employee(8, "Silly", "White", 61500, "Finance"));
        employees.add(new Employee(9, "Victoria", "Grey", 99000, "IT"));
        employees.add(new Employee(9, "Victoria", "Grey", 99000, "Finance"));
        employees.add(new Employee(10, "Mike", "Brown", 89000, "Finance"));
        employees.add(new Employee(11, "Tony", "Pink", 54500, "IT"));
        employees.add(new Employee(19, "Tony", "Pink", 54500, "Finance"));


        secondList.add(new Employee(4, "Ann", "Magenta", 115000, "Finance"));
        secondList.add(new Employee(4, "Ann", "Magenta", 115000, "IT"));
        secondList.add(new Employee(5, "William", "Yellow", 88000, "IT"));
        secondList.add(new Employee(6, "Tommy", "Green", 80000, "Finance"));
        secondList.add(new Employee(7, "Robert", "Blue", 60000, "IT"));
        secondList.add(new Employee(8, "Silly", "White", 61500, "Finance"));
        secondList.add(new Employee(9, "Victoria", "Grey", 99000, "Finance"));
        secondList.add(new Employee(9, "Victoria", "Grey", 99000, "Finance"));
        secondList.add(new Employee(10, "Mike", "Brown", 89000, "IT"));
        secondList.add(new Employee(11, "Tony", "Pink", 54500, "Finance"));
        secondList.add(new Employee(19, "Tony", "Pink", 54500, "IT"));

        //testSortAndRuduce();
        //testStreamFromList();

        //testStreamFromFile();
        //partitionByIncome();
        //groupByCriterion(Employee::getSalary);
        //testStreamGenerator(10);
        //testStreamIterator(10);

        //testParallelStream();

        Supplier<Integer> supplier = new Supplier<Integer>() {
            private int previous = 0;
            private int current = 1;

            @Override
            public Integer get() {
                int next = previous + current;
                previous = current;
                current = next;
                return current;
            }
        };

        testStreamGenerator(10,supplier);
    }

    private static void testParallelStream() throws IOException {

        employees.parallelStream()
                .map(Employee::getId)
                .sorted()
                .collect(Collectors.toList())
                .forEach(System.out::println);

        Files.lines(Paths.get("words.txt"))
                .parallel()
                .sorted();
    }

    private static void testStreamIterator(Integer size) {
        Stream.iterate(2, e -> e * 2).limit(size).forEach(System.out::println);
    }

    private static <T> void  testStreamGenerator(Integer size, Supplier<T> supplier) {
        Stream.generate(supplier)
                .parallel()
                .limit(size).forEach(System.out::println);
    }

    private static <R> void groupByCriterion(Function<Employee, R> function) {

        Map<R, List<Employee>> collectedEmployees = employees.stream()
                .collect(Collectors.groupingBy(function));
        collectedEmployees.keySet().stream().forEach(e -> System.out.println(e + "\n" + collectedEmployees.get(e)));
    }

    private static void partitionByIncome() {

        Map<Boolean, List<Employee>> collectedEmployee = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 70000));

        System.out.println("Poor employees");
        System.out.println(collectedEmployee.get(false));
        System.out.println("Rich employees");
        System.out.println(collectedEmployee.get(true));
    }

    private static void testSortAndRuduce() {
        Employee employeeWithMaxId = employees.stream()
                .max(Comparator.comparingInt(Employee::getId)).get();

        employees.stream()
                .sorted(Comparator.comparing(Employee::getFirstName))
                .collect(Collectors.toList()).forEach(System.out::println);

        System.out.println(employeeWithMaxId);

        //System.out.println(employeeWithMinId);

        Employee identity = new Employee(0, "", "", 0, "IT");

        Employee reduced = employees.stream()
                .reduce(identity, (e1, e2) -> {
                    e1.setId(e1.getId() + e2.getId());
                    e1.setSalary(e1.getSalary() + e2.getSalary());
                    return e1;
                });

        System.out.println(reduced);
    }

    private static void testStreamFromList() {
        employees.stream()
                .filter(x -> x.getSalary() > 60000)
                .filter(e -> e.getId() < 10)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        Integer[] ids = {1, 2, 3, 4, 5, 6, 7, 8, 9, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        Optional<Employee> first = Stream.of(ids)
                .map(StreamOverviewMain::findById)
                .filter(Objects::nonNull)
                .findFirst();

        Stream.of(ids)
                .map(StreamOverviewMain::findById)
                .filter(Objects::nonNull)
                .mapToInt(Employee::getSalary)
                .forEach(System.out::println);

        Random random = new Random();
        Integer integer = Stream.of(ids)
                .filter(e -> e % 2 == 0)
                .filter(e -> e % 3 == 0)
                .skip(2)
                .findFirst().orElseGet(() -> random.nextInt());

        System.out.println(integer);


        List<List<Employee>> departments = new ArrayList<>();
        departments.add(employees);
        departments.add(secondList);

    }

    private static void testStreamFromFile() throws IOException {
        Files.lines(Paths.get("words.txt"))
            .filter(e -> e.length() > 4)
            .map(String::toUpperCase)
            .sorted();

    }

    private static Employee findById(int id) {
        if (employeeMap == null) {
            employeeMap = employees.stream()
                    .distinct().collect(Collectors.toMap(Employee::getId, e -> e));
        }
        return employeeMap.get(id);
    }

}
