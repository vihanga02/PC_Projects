

import java.util.ArrayList;
public class Database {
    public static ArrayList<GlocerItem> database=new ArrayList<>();
    public void addItem(int itemCode,double price,double weight,String date_Of_Exp,String date_of_Man, String manufactue_Name,double discount,String Name){
        GlocerItem g1=new GlocerItem( itemCode, price,weight,date_Of_Exp, date_of_Man,manufactue_Name,discount,Name) ;
        database.add(g1);

    }


    public ArrayList<GlocerItem> getDatabase() {
        return database;
    }
}
