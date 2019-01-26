package inheritance;

import java.util.List;

public class InheritanceMain {

    public static void main(String[] args) {
        Engine truckEngine = new Engine(6.0, EngineType.DIESEL, 900);
        Engine busEngine = new Engine(3.5, EngineType.PETROL, 595);

        /*Truck truck = new Truck("Volvo", "VNL 300", truckEngine, 500, 300, 10000);
        truck.start();
        truck.accelerate(40);
        truck.stop();
        truck.fuelUp(50);
        truck.load();
        truck.unLoad();

        System.out.println("--------------------\n");*/

        /*ElectricCar car = new ElectricCar("Tesla", "Model S", 10000, 4);
        car.start();
        car.stop();
        car.charge();

        System.out.println("--------------------\n");

        Bus bus = new Bus("ICARUS", "s550", busEngine, 600, 150, 110);
        bus.fuelUp();
        bus.pickUpPassenger(5);
        bus.start();
        bus.accelerate(60);
        bus.releasePassengers();
        Engine engine = bus.getEngine();
        System.out.println(engine.getEngineType());
        List<Piston> pistons = engine.getPistons();
        System.out.println(pistons);*/

        Auto bus = new Bus("ICARUS", "s550", busEngine, 600, 150, 110);
        Auto car = new ElectricCar("Tesla", "Model X", 385, 4);
        Auto truck = new Truck("Volvo", "VNL 300", truckEngine, 500, 300, 10000);
        //Auto auto = new Auto();

        runCar(bus);
        runCar(car);
        runCar(truck);
    }

    private static void runCar(Auto auto) {
        auto.start();
        auto.stop();
        auto.energize();
    }

}
