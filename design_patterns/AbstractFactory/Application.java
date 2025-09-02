package design_patterns.AbstractFactory;

import design_patterns.AbstractFactory.interfaces.Button;
import design_patterns.AbstractFactory.interfaces.CheckBox;
import design_patterns.AbstractFactory.interfaces.GUIFactory;

public class Application {
    private final Button button;
    private final CheckBox checkBox;
    
    public Application(GUIFactory factory) {
        this.button = factory.createButton();
        this.checkBox = factory.createCheckBox();
    }
    
    public void createUI() {
        button.onClick();
        checkBox.onCheck();
    }
}
