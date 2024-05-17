package Signleton;

// The Database class defines the `getInstance` method that lets
// clients access the same instance of a database connection
// throughout the program.
class Database{
    // the static field for store the instance of the class
     private static Database instance;

    // The singleton's constructor should always be private to
    // prevent direct construction calls with the `new`
    // operator.
    private Database(){}

    // The static method that controls access to the singleton
    // instance.
    public static Database getInstance(){
         if (instance == null){
             instance = new Database();
         }
         return instance;
    }

    public void query(String sql) {
        // For instance, all database queries of an app go
        // through this method. Therefore, you can place
        // throttling or caching logic here.
        // ...
        System.out.println("Executing query: " + sql);
    }
}

public class Application {
    public static void main(String[] args) {
        Database foo = Database.getInstance();
        foo.query("SELECT * FROM users");

        Database bar = Database.getInstance();
        bar.query("SELECT * FROM products");

        // The variable `bar` will contain the same object as
        // the variable `foo`.
        System.out.println("foo == bar: " + (foo == bar));
    }
}

