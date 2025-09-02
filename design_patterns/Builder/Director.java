package design_patterns.Builder;

import design_patterns.Builder.builders.CarBuilder;
import design_patterns.Builder.builders.CarManualBuilder;
import design_patterns.Builder.engines.CarEngine;
import design_patterns.Builder.engines.StandardEngine;
import design_patterns.Builder.interfaces.Builder;
import design_patterns.Builder.products.Car;
import design_patterns.Builder.products.CarManual;

public class Director {
    public void constructSportsCar(Builder builder) {
        builder.reset();
        builder.setSeats(2);
        builder.setEngine(new CarEngine());
        builder.setTripComputer();
        builder.setGPS();
    }

    public void constructStandardCar(Builder builder) {
        builder.reset();
        builder.setSeats(4);
        builder.setEngine(new StandardEngine());
        builder.setGPS();
    }
}
