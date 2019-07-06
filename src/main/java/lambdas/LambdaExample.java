package lambdas;

import java.util.*;

@FunctionalInterface
interface ElementProcessor<T extends Number> {
    double process(T element);
}

@FunctionalInterface
interface Operation {

    void process();

    static void measure(Operation func) {
        long start = new Date().getTime();
        func.process();
        long end = new Date().getTime();
        System.out.println("Time spent: " + (end - start) + " milliseconds.");
    }

    default Operation combineOperation(Operation that) {
        return () -> {
            process();
            that.process();
        };
    }

}

public class LambdaExample {

    public static void main(String[] args) {

        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        intList.add(3);
        intList.add(4);
        intList.add(5);
        intList.add(6);

        List<Double> doubleList = new ArrayList<>();
        doubleList.add(3.2);
        doubleList.add(6.1);
        doubleList.add(7.02);
        doubleList.add(8.90022);
        doubleList.add(1.12);
        doubleList.add(22.11);

        processElements(intList, x -> Math.pow(x.doubleValue(), 2.2));
        processElements(doubleList, x -> Math.pow(x.doubleValue(), 2.2));

        Operation operation1 = () -> Arrays.sort(getRandomArray());
        Operation operation2 = () -> Arrays.sort(getRandomArray());
        Operation.measure(operation1.combineOperation(operation2));


        Operation.measure(() -> {
            double pow = Math.pow(322323.0001231, 91923.9192);
            System.out.println(pow);
        });
    }

    private static <T extends Number> void processElements(List<T> list, ElementProcessor func) {
        List<Double> doubleList = new ArrayList<>();
        for (T i : list) {
            // Аргументом передаётся объект функционального интерфейса, у которого вызывается абстрактный метод,
            // аналогичный подход с Callback в JavaScript, когда передаём какую-то функцию, которая выполнится потом, но
            // отличается тем, что при написании lambda эту функцию нужно имплементировать.
            doubleList.add(func.process(i));
        }
        System.out.println(doubleList);
    }

    private static int[] getRandomArray() {
        int[] i = new int[10000000];
        Random r = new Random();
        for (int k = 0; k < i.length; k++) {
            i[k] = r.nextInt(i.length + 1);
        }
        return i;
    }

}
