package design_patterns.AbstractFactory.factories;

import design_patterns.AbstractFactory.interfaces.Button;
import design_patterns.AbstractFactory.interfaces.CheckBox;
import design_patterns.AbstractFactory.interfaces.GUIFactory;
import design_patterns.AbstractFactory.products.mac.MacButton;
import design_patterns.AbstractFactory.products.mac.MacCheckBox;

public class MacGUIFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public CheckBox createCheckBox() {
        return new MacCheckBox();
    }
}
