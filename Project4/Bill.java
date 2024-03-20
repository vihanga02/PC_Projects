import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Bill {
    private String cashierName;
    private String branch;
    private String customerName;
    boolean rejistered;
    private Map<GlocerItem,Integer> billitem;


    public Bill(String cashierName, String branch) {
        this.cashierName = cashierName;
        this.branch = branch;
        this.billitem=new HashMap<>();
        this.rejistered=false;

    }

    public Bill(String cashierName, String branch, String customerName) {
        this.cashierName = cashierName;
        this.branch = branch;
        this.customerName = customerName;
        this.billitem=new HashMap<>();
        this.rejistered=true;
    }
    public void addBill(GlocerItem G1,Integer quantity){
        billitem.put(G1,quantity);

    }
    public void printBill(){
        LocalDateTime currentDateTime=LocalDateTime.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime=currentDateTime.format(formatter);

        double total=0;
        double discount=0;
        double finalAmmount=total-discount;
        System.out.println("Current date and time=" + formattedDateTime);
        System.out.println("Cashier Name = "+cashierName);
        System.out.println("Branch = "+branch);
        if(rejistered){
            System.out.println("Customer name = "+customerName);
        }
        for(Map.Entry<GlocerItem,Integer> entry:billitem.entrySet()){
            GlocerItem g1=entry.getKey();
            Integer q1= entry.getValue();
            System.out.println("Item Name = "+g1.getName()+"Unit Prize = "+g1.getPrice()+"Quantity = "+q1+"Discount = "+g1.getPrice()*g1.getDiscount()+"Netprize = "+(g1.getPrice()-g1.getPrice()*g1.getDiscount()));
            total+=g1.getPrice();
            discount+=g1.getPrice()*g1.getDiscount();
        }
        System.out.println("Total Prize=" + total);
        System.out.println("Total Discoutn = "+discount);
        System.out.println("Final Price = " + (total-discount));
        System.out.println("ThankYou!");

    }

}
