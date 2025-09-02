package design_patterns.Builder.builders;

import design_patterns.Builder.interfaces.Builder;
import design_patterns.Builder.interfaces.Engine;
import design_patterns.Builder.products.Car;

public class CarBuilder implements Builder {
    private Car car;
    
    public CarBuilder() {
        reset();
    }

    @Override
    public void reset() {
        car = new Car();
    }

    @Override
    public void setSeats(int number) {
        car.setSeats(number);
    }

    @Override
    public void setEngine(Engine engine) {
        car.setEngine(engine);
    }

    @Override
    public void setTripComputer() {
        car.setTripComputer(true);
    }

    @Override
    public void setGPS() {
        car.setGPS(true);
    }

    public Car getResult() {
        Car result = car;
        reset();
        return result;
    }
}
