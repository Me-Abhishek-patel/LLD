package design_patterns.Decorator;

public class DecoratorPattern {
    public interface DataSource {
        public String getData();
        
    }

    public static class ConcreteDataSource implements DataSource {
        String data;


        public ConcreteDataSource(String data) {
            this.data = data;
        }


        @Override
        public String getData() {
            return data;
        }
    }

    public static class EncryptDataSource implements DataSource {
        DataSource dataSource;

        public EncryptDataSource(DataSource dataSource) {
            this.dataSource = dataSource;
        }

        @Override
        public String getData() {
            return encrypt(dataSource.getData());
        }

        public String encrypt(String data){
            return "Encrypted data : " + data;
        }
        
    }

    public static class CompressDataSource implements DataSource {
        DataSource dataSource;
        

        public CompressDataSource(DataSource dataSource) {
            this.dataSource = dataSource;
        }

        @Override
        public String getData() {
            return compress(dataSource.getData());
        }

        public String compress(String data){
            return "Compressed data : " + data;
        }
        
    }

    public static void main(String[] args) {
        DataSource dataSource = new ConcreteDataSource("Abhishek");
        DataSource compressedData = new CompressDataSource( new EncryptDataSource(dataSource));
        System.out.println(compressedData.getData());
    }
}
