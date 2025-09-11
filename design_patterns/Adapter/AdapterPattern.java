package design_patterns.Adapter;

public class AdapterPattern {
    // Target Interface (what client expects)
    interface Printer {
        void printDocument(String text);
    }

    // Adaptee (legacy/incompatible class we cannot modify)
    static class OldPrinter {
        public void printText(String text) {
            System.out.println("OldPrinter: " + text);
        }
    }

    // Adapter (makes OldPrinter compatible with Printer)
    static class PrinterAdapter implements Printer {
        private OldPrinter oldPrinter;

        public PrinterAdapter(OldPrinter oldPrinter) {
            this.oldPrinter = oldPrinter;
        }

        @Override
        public void printDocument(String text) {
            // Translate the call
            oldPrinter.printText(text);
        }
    }

    // Client Code
    public static void main(String[] args) {
        // We have an old printer
        OldPrinter oldPrinter = new OldPrinter();

        // Use adapter to make it compatible
        Printer adapter = new PrinterAdapter(oldPrinter);

        // Client works with Printer interface
        adapter.printDocument("Hello Adapter Pattern!");
    }

}
