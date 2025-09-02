package design_patterns.Builder.builders;

import design_patterns.Builder.interfaces.Builder;
import design_patterns.Builder.interfaces.Engine;
import design_patterns.Builder.products.CarManual;

public class CarManualBuilder implements Builder {
    private CarManual manual;
    
    public CarManualBuilder() {
        reset();
    }

    @Override
    public void reset() {
        manual = new CarManual();
    }

    @Override
    public void setSeats(int number) {
        manual.setSeatsInstructions(number);
    }

    @Override
    public void setEngine(Engine engine) {
        manual.setEngineInstructions(engine);
    }

    @Override
    public void setTripComputer() {
        manual.setTripComputerInstructions(true);
    }

    @Override
    public void setGPS() {
        manual.setGPSInstructions(true);
    }

    public CarManual getResult() {
        CarManual result = manual;
        reset();
        return result;
    }
}
