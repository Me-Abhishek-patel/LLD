package design_patterns.Factory.products;

import design_patterns.Factory.interfaces.Button;

public class WindowsButton implements Button {
    @Override
    public void onClick() {
        System.out.println("Windows btn clicked");
    }
}
