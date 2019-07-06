package inheritance;

public abstract class FuelAuto extends Auto {

    private int tankVolume;
    private int availablePetrol;

    public FuelAuto(String brand, String model, Engine engine, int tankVolume, int availablePetrol) {
        super(brand, model, engine);
        this.tankVolume = tankVolume;
        this.availablePetrol = availablePetrol;
    }

    public int getTankVolume() {
        return tankVolume;
    }

    public void setTankVolume(int tankVolume) {
        this.tankVolume = tankVolume;
    }

    public int getAvailablePetrol() {
        return availablePetrol;
    }

    public void setAvailablePetrol(int availablePetrol) {
        this.availablePetrol = availablePetrol;
    }

    public void fuelUp(int fuelVolume) {
        availablePetrol += fuelVolume;
        System.out.println("Adding " + getEngine().getEngineType());
    }
}
