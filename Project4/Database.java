import java.util.ArrayList;

// The Database class represents a database of grocery items and registered customers
public class Database {
    // List of grocery items in the database
    private ArrayList<GroceryItem> itemDataBase = new ArrayList<>();
    // List of registered customers
    private ArrayList<String> registeredCustumerList = new ArrayList<>();

    // Default constructor for the Database class
    public Database(){

    }

    // Method to add a grocery item to the database
    public void addItem(String itemCode,double price,double weight,String dateOfExp,String dateofMan, String manufactueName,double discount,String Name){
        this.itemDataBase.add(new GroceryItem( itemCode, price,weight,dateOfExp, dateofMan,manufactueName,discount,Name));
    }

    // Method to add a customer to the registered customers list
    public void addCustumer(String name){
        registeredCustumerList.add(name);
    }

    // Method to get the list of grocery items in the database
    public ArrayList<GroceryItem> getItemDatabase() {
        return itemDataBase;
    }

    // Method to get the list of registered customers
    public ArrayList<String> getRegisteredCustomerList(){
        return registeredCustumerList;
    }

    // Method to set the list of registered customers
    public void setRegisteredCustumerList(ArrayList<String> registeredCustumerList) {
        this.registeredCustumerList = registeredCustumerList;
    }
}