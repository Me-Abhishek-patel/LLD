package design_patterns.Builder.engines;

import design_patterns.Builder.interfaces.Engine;

public class StandardEngine implements Engine {
    @Override
    public void start() {
        System.out.println("Standard Engine: Starting quietly...");
    }
}
