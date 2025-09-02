package design_patterns.AbstractFactory.products.windows;

import design_patterns.AbstractFactory.interfaces.CheckBox;

public class WindowsCheckBox implements CheckBox {
    @Override
    public void onCheck() {
        System.out.println("Windows check box checked");
    }
}
