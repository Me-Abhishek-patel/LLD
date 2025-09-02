package design_patterns.Builder.engines;

import design_patterns.Builder.interfaces.Engine;

public class CarEngine implements Engine {
    @Override
    public void start() {
        System.out.println("Sports Car Engine: Vroom Vroom!");
    }
}
