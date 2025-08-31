package design_patterns.Singleton;

public class Singleton {
    public static class Logger {
        private static Logger instance;

        private Logger() {
        }

        public static Logger getInstance(){
            if (instance== null){
                instance = new Logger();
            }
            return instance;
        }

        public void log(String message){
            System.out.println(message);
        }

    }

    public static void main(String[] args) {
        Logger logger1 = new Logger();
        Logger logger2 = new Logger();
        System.out.println(logger1==logger2);

        logger1 = Logger.getInstance();
        logger2 = Logger.getInstance();
        System.out.println(logger1==logger2);


    }
}
