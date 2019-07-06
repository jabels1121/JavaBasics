package lambdas;

import lambdas.model.Employee;
import lambdas.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class LambdaFunctionExample {

    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alex", "Black", 50000));
        employees.add(new Employee("John", "White", 75000));
        employees.add(new Employee("Sam", "Brown", 80000));
        employees.add(new Employee("Tony", "Grey", 90000));

        List<Person> people = new ArrayList<>();
        people.add(new Person("Alex", "Smith", 25));
        people.add(new Person("John", "White", 30));
        people.add(new Person("Sam", "Brown", 32));
        people.add(new Person("Tony", "Grey", 34));


        // Predicate
        System.out.println(findMatch(employees, e -> e.getSalary() >= 80000));
        System.out.println(findMatch(people, p -> p.getAge() >= 32));

        // Function
        System.out.println("Total sum: " + calcSum(employees, Employee::getSalary));
        System.out.println("Total age: " + calcSum(people, Person::getAge));

        BinaryOperator<Integer> combiner = (n1, n2) -> Math.max(n1, n2);
        Integer zeroElement = 0;
        System.out.println("Combine salary: " + combine(employees, zeroElement, Employee::getSalary, combiner));

        employees.forEach(employee -> employee.setSalary(employee.getSalary() * 11 / 10));
        employees.forEach(System.out::println);


        Stream.of("Andy", "Mike").map(Student::new).forEach(x -> System.out.println(x.getName()));

    }

    private static <T> T findMatch(List<T> elements, Predicate<T> predicateFunction) {
        for (T e : elements) {
            if (predicateFunction.test(e)) {
                return e;
            }
        }
        return null;
    }

    private static <T> Integer calcSum(List<T> elements, Function<T, Integer> func) {
        int sum = 0;
        for (T e : elements) {
            sum = sum + func.apply(e);
        }
        return sum;
    }

    private static <T, R> R combine(List<T> elements, R zeroElement, Function<T, R> function, BinaryOperator<R> combiner) {
        for (T e : elements) {
            zeroElement = combiner.apply(zeroElement, function.apply(e));
        }
        return zeroElement;
    }

}

class Student {
    String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

























