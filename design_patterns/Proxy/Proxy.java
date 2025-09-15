package design_patterns.Proxy;

public class Proxy {
    public interface Database {
        void query(String sql);
        
    }

    public static class RealDatabase implements Database  {

        @Override
        public void query(String sql) {
            System.out.println("Executing :"+sql);
        }

        public RealDatabase(){
            System.out.println("Connecting to heavy real database");
        }
        
    }

    public  static class DatabaseProxy implements Database {
        Database rDatabase;

        @Override
        public void query(String sql) {
            if(rDatabase==null){
                rDatabase =  new RealDatabase();
            }
            rDatabase.query(sql);
        }

        public DatabaseProxy(){
            System.out.println("No  heavy conection made to heavy DB");
        }
        
        
    }

    public static void main(String[] args) {
        Database db = new DatabaseProxy();

        System.out.println("Executing query");
        db.query("SELECT * FROM STUDENTS");

    }
}
