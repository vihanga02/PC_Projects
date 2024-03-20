public class GroceryItem {
    private  String itemCode;
    private double price;
    private double weight;
    private  String dateOfExp;
    private String dateOfMan;
    private String manufactueName;
    private  double discount;
    private String name;

    GroceryItem(String  itemCode, double price, double weight, String date_Of_Exp, String date_of_Man, String manufactue_Name, double discount, String name){
        this.itemCode = itemCode;
        this.price = price;
        this.weight = weight;
        this.dateOfExp = date_Of_Exp;
        this.dateOfMan = date_of_Man;
        this.manufactueName = manufactue_Name;
        this.discount = discount;
        this.name = name;
    }

    public String getItemCode() {
        return itemCode;
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    public String getDateOfExp() {
        return dateOfExp;
    }

    public String getDateOfMan() {
        return dateOfMan;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setDateOfExp(String dateOfExp) {
        this.dateOfExp = dateOfExp;
    }

    public void setDateOfMan(String dateOfMan) {
        this.dateOfMan = dateOfMan;
    }

    public void setManufactueName(String manufactueName) {
        this.manufactueName = manufactueName;
    }

    public String getManufactueName() {
        return manufactueName;
    }
    public void setDiscount(double discount){
        this.discount = discount;
    }
    public  double getDiscount(){
        return discount;
    }
    public String getName(){
        return name;
    }
}
