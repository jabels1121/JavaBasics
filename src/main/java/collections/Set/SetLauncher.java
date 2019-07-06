package collections.Set;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetLauncher {
    public static void main(String[] args) {
        Set<Car> sixCars = new HashSet<>();
        sixCars.add(new Car("VW", "Golf", 25));
        sixCars.add(new Car("Audi", "a1", 19));
        sixCars.add(new Car("Skoda", "Octavia", 23));
        sixCars.add(new Car("Nissan", "Almera", 14));
        sixCars.add(new Car("VW", "Polo", 17));


        Set<Car> europaCars = new HashSet<>();
        europaCars.add(new Car("Toyota", "Auris", 18));
        europaCars.add(new Car("VW", "Polo", 15 + 2));
        europaCars.add(new Car("Toyota", "Corolla", 17));
        europaCars.add(new Car("VW", "Golf", 25));
        europaCars.add(new Car("Renault", "Megane", 21));

        Set<Car> uniqueCars = new TreeSet<>(sixCars);
        uniqueCars.addAll(europaCars);

        printOut(uniqueCars);
    }

    private static void printOut(Set<Car> set) {
        System.out.printf("%-20s %-20s %-20s\n", "Car Brand", "Model", "Price per Day");
        for (Car c : set) {
            System.out.printf("%-20s %-20s %-20s\n", c.getCarBrand(), c.getModel(), c.getPricePerDay());
        }
    }
}
