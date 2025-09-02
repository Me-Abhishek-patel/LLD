package design_patterns.Factory.products;

import design_patterns.Factory.interfaces.Button;

public class MacButton implements Button {
    @Override
    public void onClick() {
        System.out.println("Mac btn clicked");
    }
}
