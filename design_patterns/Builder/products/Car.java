package design_patterns.Builder.products;

import design_patterns.Builder.interfaces.Engine;

public class Car {
    private Engine engine;
    private int seats;
    private boolean tripComputer;
    private boolean gps;

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setTripComputer(boolean tripComputer) {
        this.tripComputer = tripComputer;
    }

    public void setGPS(boolean gps) {
        this.gps = gps;
    }

    public void showInfo() {
        System.out.println("Car Specifications:");
        System.out.println("Seats: " + seats);
        System.out.println("Trip Computer: " + (tripComputer ? "Yes" : "No"));
        System.out.println("GPS: " + (gps ? "Yes" : "No"));
        if (engine != null) {
            engine.start();
        }
    }
}
