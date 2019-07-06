package lambdas;

import lambdas.model.RichPerson;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class HigherOrderFunctionExample {

    public static void main(String[] args) {

        List<RichPerson> persons = new ArrayList<>();
        persons.add(new RichPerson("Alex", "Smith", 50000, 25));
        persons.add(new RichPerson("John", "White", 75000, 35));
        persons.add(new RichPerson("Sam", "Brown", 80000, 40));
        persons.add(new RichPerson("Tony", "Grey", 90000, 50));
        persons.add(new RichPerson("Anna", "Blue", 75150, 39));
        testPredicate(persons);
        testFunction(persons);
        testConsumer(persons);
    }

    private static void testPredicate(List<RichPerson> persons) {
        System.out.println("Testing predicate...");
        Predicate<RichPerson> isRich = x -> x.getSalary() >= 75000;
        Predicate<RichPerson> isYoung = x -> x.getAge() <= 40;
        System.out.println("Is rich and young");
        findAll(persons, isRich.and(isYoung)).forEach(System.out::println);
        System.out.println("Is rich and young");
        findAll(persons, isRich.or(isYoung)).forEach(System.out::println);

        testFunction(persons);
    }

    private static <T> List<T> findAll(List<T> elements, Predicate<T> predicate) {
        List<T> filteredList = new ArrayList<>();
        for (T e : elements) {
            if (predicate.test(e)) {
                filteredList.add(e);
            }
        }
        return filteredList;
    }

    private static void testFunction(List<RichPerson> persons) {
        System.out.println("Testing function...");
        // f1.compose(f2) ==> f1(f2(x));
        // f1.andThen(f2) ==> f2(f1(x));
        // Function.identity();
        Function<RichPerson, String> fullName = x -> x.getFirstName().concat(" ").concat(x.getLastName());
        Function<String, String> sayHello = y -> "Hello " + y;
        Function<RichPerson, String> composedFunction = sayHello.compose(fullName);
        List<String> transformedList = transform(persons, composedFunction);

        Function<String, String> exclaim = z -> z + "!!!";
        Function<String, String> toUpper = String::toUpperCase;

        transform(transformedList, compose(toUpper, exclaim)).forEach(System.out::println);
    }

    private static <T> Function<T, T> compose(Function<T, T>... functions) {
        Function<T, T> result = Function.identity();
        for (Function<T, T> func : functions) {
            result = result.andThen(func);
        }
        return result;
    }

    private static <T, R> List<R> transform(List<T> elements, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T p : elements) {
            result.add(function.apply(p));
        }
        return result;
    }

    private static void testConsumer(List<RichPerson> persons) {
        System.out.println("Testing consumer...");
        // f1.andThen(f2(x)) ==> f1(x) andThen f2(x);
        Consumer<RichPerson> rised = x -> x.setSalary(x.getSalary() * 11 / 10);
        processList(persons, rised.andThen(System.out::println));
    }

    private static <T> void processList(List<T> elements, Consumer<T> consumer) {
        for (T e : elements) {
            consumer.accept(e);
        }
    }

}
