package design_patterns.Builder.products;

import design_patterns.Builder.BuilderPattern.CarEngine;
import design_patterns.Builder.interfaces.Engine;

public class CarManual {
    private String engineInstructions;
    private String seatsInstructions;
    private String tripComputerInstructions;
    private String gpsInstructions;

    public void setEngineInstructions(Engine engine) {
        this.engineInstructions = "Engine Instructions: " + 
            (engine instanceof CarEngine ? "Sports Car Engine" : "Standard Engine");
    }

    public void setSeatsInstructions(int seats) {
        this.seatsInstructions = "Seats Instructions: Adjust " + seats + " seats for comfort";
    }

    public void setTripComputerInstructions(boolean hasTripComputer) {
        this.tripComputerInstructions = hasTripComputer ? 
            "Trip Computer: Press START to begin navigation" : 
            "No Trip Computer installed";
    }

    public void setGPSInstructions(boolean hasGPS) {
        this.gpsInstructions = hasGPS ? 
            "GPS: Enter destination on touch screen" : 
            "No GPS installed";
    }

    public void showManual() {
        System.out.println("\nCAR MANUAL:");
        System.out.println(engineInstructions);
        System.out.println(seatsInstructions);
        System.out.println(tripComputerInstructions);
        System.out.println(gpsInstructions);
    }
}
