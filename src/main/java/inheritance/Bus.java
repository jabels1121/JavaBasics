package inheritance;

public class Bus extends FuelAuto {

    private int passengerNumber;

    public Bus(String brand, String model, Engine engine, int tankVolume, int availablePetrol, int passengerNumber) {
        super(brand, model, engine, tankVolume, availablePetrol);
        this.passengerNumber = passengerNumber;
        System.out.println("Bus was initialized");
    }

    public void fuelUp() {
        int volume = getTankVolume() - getAvailablePetrol();
        fuelUp(volume);
    }

    @Override
    public void fuelUp(int fuelVolume) {
        int volume = getAvailablePetrol() + fuelVolume;
        if (volume > getTankVolume()) {
            setAvailablePetrol(getTankVolume());
        } else {
            setAvailablePetrol(volume);
        }
        System.out.println("Adding " + this.getEngine().getEngineType());
    }

    public int getPassengerNumber() {
        return passengerNumber;
    }

    public void setPassengerNumber(int passengerNumber) {
        this.passengerNumber = passengerNumber;
    }

    public void pickUpPassenger(int passengerNumber) {
        this.passengerNumber += passengerNumber;
        System.out.println("Picking up " + passengerNumber + " passengers");
    }

    public void releasePassengers() {
        if (isRunning()) {
            stop();
        }
        passengerNumber = 0;
        System.out.println("Passengers released");
    }

    @Override
    public void start() {
        setRunning(true);
        setCurrentSpeed(10);
        System.out.println("Bus is starting");
    }

    @Override
    public void stop() {
        setRunning(false);
        setCurrentSpeed(0);
        System.out.println("Bus has stopped");
    }

    @Override
    public void energize() {
        fuelUp(getTankVolume() - getAvailablePetrol());
    }
}
