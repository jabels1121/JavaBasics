package inheritance;

import java.util.ArrayList;
import java.util.List;

public class Engine {

    private double volume;
    private EngineType engineType;

    public List<Piston> getPistons() {
        return pistons;
    }

    private int power;
    private List<Piston> pistons = new ArrayList<>();

    public Engine() {
    }

    public Engine(double volume, EngineType engineType, int power) {
        this.volume = volume;
        this.engineType = engineType;
        this.power = power;
        for (int i = 0; i < 5; i++) {
            this.pistons.add(new Piston(0.3, i));
        }
    }

    public double getVolume() {
        return volume;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public int getPower() {
        return power;
    }
}
