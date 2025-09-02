package design_patterns.AbstractFactory.products.windows;

import design_patterns.AbstractFactory.interfaces.Button;

public class WindowsButton implements Button {
    @Override
    public void onClick() {
        System.out.println("Windows button clicked");
    }
}
