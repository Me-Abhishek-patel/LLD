package design_patterns.Builder;

public class BuilderPattern {
    // Product
    public static class Car {
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

    // Another Product
    public static class CarManual {
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

    public interface Engine {
        void start();
    }

    public static class CarEngine implements Engine {
        @Override
        public void start() {
            System.out.println("Sports Car Engine: Vroom Vroom!");
        }
    }

    public static class StandardEngine implements Engine {
        @Override
        public void start() {
            System.out.println("Standard Engine: Starting quietly...");
        }
    }

    // Builder Interface
    public interface Builder {
        void reset();
        void setSeats(int number);
        void setEngine(Engine engine);
        void setTripComputer();
        void setGPS();
    }
    
    // Concrete Builder for Car
    public static class CarBuilder implements Builder {
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

    // Concrete Builder for Manual
    public static class CarManualBuilder implements Builder {
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

    // Director
    public static class Director {
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

    public static void main(String[] args) {
        Director director = new Director();

        // Building a sports car
        CarBuilder carBuilder = new CarBuilder();
        director.constructSportsCar(carBuilder);
        Car sportsCar = carBuilder.getResult();
        System.out.println("Sports Car built:");
        sportsCar.showInfo();

        // Building a sports car manual
        CarManualBuilder manualBuilder = new CarManualBuilder();
        director.constructSportsCar(manualBuilder);
        CarManual sportsCarManual = manualBuilder.getResult();
        sportsCarManual.showManual();

        // Building a standard car
        System.out.println("\nStandard Car built:");
        director.constructStandardCar(carBuilder);
        Car standardCar = carBuilder.getResult();
        standardCar.showInfo();

        // Building a standard car manual
        director.constructStandardCar(manualBuilder);
        CarManual standardCarManual = manualBuilder.getResult();
        standardCarManual.showManual();
    }
}
