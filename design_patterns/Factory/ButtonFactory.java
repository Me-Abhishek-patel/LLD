package design_patterns.Factory;

import design_patterns.Factory.enums.ButtonType;
import design_patterns.Factory.interfaces.Button;
import design_patterns.Factory.products.MacButton;
import design_patterns.Factory.products.WindowsButton;

/**
 * Factory class responsible for creating different types of buttons.
 * This is an example of the Factory Pattern where a single factory class
 * is responsible for creating different variations of a product.
 */
public class ButtonFactory {
    
    /**
     * Creates a button of the specified type.
     * 
     * @param buttonType the type of button to create
     * @return a Button instance of the specified type
     * @throws IllegalArgumentException if buttonType is null or unsupported
     */
    public Button createButton(ButtonType buttonType) {
        if (buttonType == null) {
            throw new IllegalArgumentException("Button type cannot be null");
        }

        switch (buttonType) {
            case MAC:
                return new MacButton();
            case WINDOWS:
                return new WindowsButton();
            default:
                throw new IllegalArgumentException("Unsupported button type: " + buttonType);
        }
    }

    public static void main(String[] args) {
        ButtonFactory btnFactory = new ButtonFactory();
        
        // Create and test MAC button
        System.out.println("Testing MAC button:");
        Button macButton = btnFactory.createButton(ButtonType.MAC);
        macButton.onClick();
        
        // Create and test Windows button
        System.out.println("\nTesting Windows button:");
        Button windowsButton = btnFactory.createButton(ButtonType.WINDOWS);
        windowsButton.onClick();
    }
}
