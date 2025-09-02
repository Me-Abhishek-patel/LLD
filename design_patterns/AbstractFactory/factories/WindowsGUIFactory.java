package design_patterns.AbstractFactory.factories;

import design_patterns.AbstractFactory.interfaces.Button;
import design_patterns.AbstractFactory.interfaces.CheckBox;
import design_patterns.AbstractFactory.interfaces.GUIFactory;
import design_patterns.AbstractFactory.products.windows.WindowsButton;
import design_patterns.AbstractFactory.products.windows.WindowsCheckBox;

public class WindowsGUIFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public CheckBox createCheckBox() {
        return new WindowsCheckBox();
    }
}
