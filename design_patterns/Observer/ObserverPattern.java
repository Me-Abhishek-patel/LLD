package design_patterns.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Observer Pattern Implementation
 * Demonstrates a publisher-subscriber relationship
 */
public class ObserverPattern {
    
    /**
     * Observer interface defining the update method
     */
    public interface Observer {
        void update(String message);
    }

    /**
     * Publisher/Subject class that notifies observers
     */
    public static class Publisher {
        private final List<Observer> observers;
        private String state;
        
        public Publisher() {
            observers = new ArrayList<>();
        }

        public void addObserver(Observer obs) {
            if (obs != null && !observers.contains(obs)) {
                observers.add(obs);
                System.out.println("Added observer: " + obs.getClass().getSimpleName());
            }
        }

        public void removeObserver(Observer obs) {
            if (observers.remove(obs)) {
                System.out.println("Removed observer: " + obs.getClass().getSimpleName());
            }
        }

        public void notifyObservers() {
            notifyObservers(state);
        }

        public void notifyObservers(String message) {
            System.out.println("\nNotifying all observers with message: " + message);
            for (Observer observer : observers) {
                observer.update(message);
            }
        }

        public void setState(String state) {
            this.state = state;
            notifyObservers();
        }
    }

    /**
     * Concrete observer implementations
     */
    public static class EmailObserver implements Observer {
        private final String email;

        public EmailObserver(String email) {
            this.email = email;
        }

        @Override
        public void update(String message) {
            System.out.println("Email sent to " + email + ": " + message);
        }
    }

    public static class LoggerObserver implements Observer {
        @Override
        public void update(String message) {
            System.out.println("Logging: " + message);
        }
    }

    public static class AlertObserver implements Observer {
        private final String alertType;

        public AlertObserver(String alertType) {
            this.alertType = alertType;
        }

        @Override
        public void update(String message) {
            System.out.println(alertType + " Alert: " + message);
        }
    }

    public static void main(String[] args) {
        // Create publisher
        Publisher publisher = new Publisher();

        // Create observers
        Observer emailObs = new EmailObserver("user@example.com");
        Observer loggerObs = new LoggerObserver();
        Observer alertObs = new AlertObserver("High Priority");

        // Add observers to publisher
        publisher.addObserver(emailObs);
        publisher.addObserver(loggerObs);
        publisher.addObserver(alertObs);

        // Trigger notifications
        publisher.notifyObservers("System startup complete");

        // Remove an observer
        publisher.removeObserver(loggerObs);

        // Update state and notify
        publisher.setState("Configuration changed");

        // Add another observer
        publisher.addObserver(new EmailObserver("admin@example.com"));

        // Final notification
        publisher.notifyObservers("System shutdown initiated");
    }
}

