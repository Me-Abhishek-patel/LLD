package design_patterns.Factory;

public class ButtonFactory {
    public enum ButtonType {
        WINDOWS,
        MAC
    }

    public interface Button {
        void onClick();
    }

    private static class WindowsButton implements Button {
        @Override
        public void onClick() {
            System.out.println("Windows btn clicked");
        }
    }

    private static class MacButton implements Button {
        @Override
        public void onClick() {
            System.out.println("Mac btn clicked");
        }
    }

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
        Button btn = btnFactory.createButton(ButtonType.MAC);
        btn.onClick();
        btn = btnFactory.createButton(ButtonType.WINDOWS);
        btn.onClick();
    }
}
