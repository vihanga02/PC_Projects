import java.util.ArrayList;
public class Database {
    private ArrayList<GroceryItem> itemDataBase = new ArrayList<>();
    private ArrayList<String> registeredCustumerList = new ArrayList<>();

    public Database(){

    }
    public void addItem(String itemCode,double price,double weight,String dateOfExp,String dateofMan, String manufactueName,double discount,String Name){
        this.itemDataBase.add(new GroceryItem( itemCode, price,weight,dateOfExp, dateofMan,manufactueName,discount,Name));
    }
    public void addCustumer(String name){
        registeredCustumerList.add(name);
    }
    public ArrayList<GroceryItem> getItemDatabase() {
        return itemDataBase;
    }
    public ArrayList<String> getRegisteredCustumerList(){
        return registeredCustumerList;
    }
}