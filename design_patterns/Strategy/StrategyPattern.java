package design_patterns.Strategy;

class StrategyPattern {
    public interface PaymentStrategy {
        public void execute();
    } 

    public static class UPIPaymentStrategy implements PaymentStrategy {

        @Override
        public void execute() {
            System.out.println("Paying through UPI");
        }
    
        
    }

    public static class CardPaymentStrategy implements PaymentStrategy {

        @Override
        public void execute() {
            System.out.println("Paying through Card");
        }
    }

    public static class PaymentHandler {
        PaymentStrategy paymentStrategy;
        public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
            this.paymentStrategy = paymentStrategy;
        }

        public void pay(){
            paymentStrategy.execute();
        }
        
    }

    public static void main(String[] args) {
        PaymentHandler paymentHandler = new PaymentHandler();
        paymentHandler.setPaymentStrategy(new UPIPaymentStrategy());
        paymentHandler.pay();
        paymentHandler.setPaymentStrategy(new CardPaymentStrategy());
        paymentHandler.pay();
    }
    
}