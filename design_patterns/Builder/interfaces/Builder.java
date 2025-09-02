package design_patterns.Builder.interfaces;

public interface Builder {
    void reset();
    void setSeats(int number);
    void setEngine(Engine engine);
    void setTripComputer();
    void setGPS();
}
