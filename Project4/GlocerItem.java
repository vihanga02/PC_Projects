public class GlocerItem {
    private  int itemCode;
    private double price;
    private double weight;
    private  String date_Of_Exp;
    private String date_of_Man;
    private String manufactue_Name;
    private  double discount;
    private String name;

    GlocerItem(int itemCode,double price,double weight,String date_Of_Exp,String date_of_Man, String manufactue_Name,double discount,String name){
        this.itemCode=itemCode;
        this.price=price;
        this.weight=weight;
        this.date_Of_Exp=date_Of_Exp;
        this.date_of_Man=date_of_Man;
        this.manufactue_Name=manufactue_Name;
        this.discount=discount;
        this.name=name;
    }

    public int getItemCode() {
        return itemCode;
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    public String getDate_Of_Exp() {
        return date_Of_Exp;
    }

    public String getDate_of_Man() {
        return date_of_Man;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setDate_Of_Exp(String date_Of_Exp) {
        this.date_Of_Exp = date_Of_Exp;
    }

    public void setDate_of_Man(String date_of_Man) {
        this.date_of_Man = date_of_Man;
    }

    public void setManufactue_Name(String manufactue_Name) {
        this.manufactue_Name = manufactue_Name;
    }

    public String getManufactue_Name() {
        return manufactue_Name;
    }
    public void setDiscount(double discount){
        this.discount=discount;

    }
    public  double getDiscount(){
        return discount;
    }
    public String getName(){
        return name;

    }
}
