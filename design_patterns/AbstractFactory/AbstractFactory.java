package design_patterns.AbstractFactory;

import design_patterns.AbstractFactory.factories.MacGUIFactory;
import design_patterns.AbstractFactory.factories.WindowsGUIFactory;

public class AbstractFactory {
    public static void main(String[] args) {
        // Create Windows UI
        System.out.println("Creating Windows UI:");
        Application windowsApp = new Application(new WindowsGUIFactory());
        windowsApp.createUI();
        
        System.out.println("\nCreating Mac UI:");
        // Create Mac UI
        Application macApp = new Application(new MacGUIFactory());
        macApp.createUI();
    }
}
