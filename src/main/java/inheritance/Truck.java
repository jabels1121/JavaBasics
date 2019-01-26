package inheritance;

public class Truck extends FuelAuto{

    private int cargoWeight;

    public Truck(String brand, String model, Engine engine, int tankVolume, int availablePetrol, int cargoWeight) {
        super(brand, model, engine, tankVolume, availablePetrol);
        this.cargoWeight = cargoWeight;
        System.out.println("Constructing truck");
    }

    public int getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(int cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    public void load(){
        System.out.println("Cargo loaded");
    }

    public void unLoad(){
        System.out.println("Cargo unloaded");
    }

    @Override
    public void start() {
        setRunning(true);
        setCurrentSpeed(10);
        System.out.println("Truck is starting");
    }

    @Override
    public void stop() {
        setRunning(false);
        setCurrentSpeed(0);
        System.out.println("Truck has stopped");
    }

    @Override
    public void energize() {
        fuelUp(getTankVolume() - getAvailablePetrol());
    }
}
