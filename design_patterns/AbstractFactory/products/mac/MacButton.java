package design_patterns.AbstractFactory.products.mac;

import design_patterns.AbstractFactory.interfaces.Button;

public class MacButton implements Button {
    @Override
    public void onClick() {
        System.out.println("Mac button clicked");
    }
}
