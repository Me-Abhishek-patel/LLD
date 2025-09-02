package design_patterns.AbstractFactory.products.mac;

import design_patterns.AbstractFactory.interfaces.CheckBox;

public class MacCheckBox implements CheckBox {
    @Override
    public void onCheck() {
        System.out.println("Mac check box checked");
    }
}
