package design_patterns.Prototype;

import java.util.HashMap;
import java.util.Map;

public class PrototypePattern {
    public interface Prototype {
        Prototype clone();
        String getColor();
        void setPosition(int x, int y);
        String toString();
    }

    public static class Button implements Prototype {
        private int x, y;
        private String color;
        private String text;

        // Constructor for creating a new button
        public Button(String color, String text) {
            this.color = color;
            this.text = text;
        }

        // Copy constructor used in cloning
        public Button(Button prototype) {
            this.x = prototype.x;
            this.y = prototype.y;
            this.color = prototype.color;
            this.text = prototype.text;
        }

        @Override
        public String getColor() {
            return color;
        }

        @Override
        public void setPosition(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public Prototype clone() {
            return new Button(this);
        }

        @Override
        public String toString() {
            return String.format("Button{color='%s', text='%s', position=(%d,%d)}", 
                               color, text, x, y);
        }
    }

    public static class RoundButton extends Button {
        private int radius;

        public RoundButton(String color, String text, int radius) {
            super(color, text);
            this.radius = radius;
        }

        // Copy constructor
        public RoundButton(RoundButton prototype) {
            super(prototype);
            this.radius = prototype.radius;
        }

        @Override
        public Prototype clone() {
            return new RoundButton(this);
        }

        @Override
        public String toString() {
            return super.toString() + " Radius=" + radius;
        }
    }

    public static class PrototypeRegistry {
        private Map<String, Prototype> items = new HashMap<>();
        
        public void addItem(String key, Prototype prototype) {
            items.put(key, prototype);
        }
        
        public Prototype getByKey(String key) {
            Prototype prototype = items.get(key);
            return prototype != null ? prototype.clone() : null;
        }

        public void showAll() {
            System.out.println("\nPrototype Registry Contents:");
            items.forEach((key, value) -> 
                System.out.println(key + ": " + value.toString()));
        }
    }

    public static void main(String[] args) {
        // Create prototype registry
        PrototypeRegistry registry = new PrototypeRegistry();

        // Add prototype buttons
        Button blueButton = new Button("blue", "OK");
        registry.addItem("blue_button", blueButton);

        RoundButton redRoundButton = new RoundButton("red", "Cancel", 10);
        registry.addItem("red_round_button", redRoundButton);

        // Clone and customize buttons
        System.out.println("Cloning and customizing buttons:");

        // Clone blue button and modify position
        Button blueClone1 = (Button) registry.getByKey("blue_button");
        blueClone1.setPosition(10, 10);
        System.out.println("Blue Clone 1: " + blueClone1);

        // Clone another blue button with different position
        Button blueClone2 = (Button) registry.getByKey("blue_button");
        blueClone2.setPosition(20, 20);
        System.out.println("Blue Clone 2: " + blueClone2);

        // Clone round button
        RoundButton redClone = (RoundButton) registry.getByKey("red_round_button");
        redClone.setPosition(15, 15);
        System.out.println("Red Round Clone: " + redClone);

        // Show all prototypes in registry
        registry.showAll();
    }
}