package inheritance;

public class ElectricCar extends Auto {

    private int batteryVolume;
    private int passengersNumber;

    public ElectricCar(String brand, String model, int batteryVolume, int passengersNumber) {
        super(brand, model, new Engine());
        this.batteryVolume = batteryVolume;
        this.passengersNumber = passengersNumber;
    }

    private void charge() {
        System.out.println("Battery is charging");
    }

    public int getBatteryVolume() {
        return batteryVolume;
    }

    public void setBatteryVolume(int batteryVolume) {
        this.batteryVolume = batteryVolume;
    }

    public int getPassengersNumber() {
        return passengersNumber;
    }

    public void setPassengersNumber(int passengersNumber) {
        this.passengersNumber = passengersNumber;
    }

    @Override
    public void energize() {
        charge();
    }
}
