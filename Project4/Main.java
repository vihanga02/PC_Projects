import java.util.Scanner;
import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        POS pos = new POS();
        GlocerItem i=null;
        Bill bill;
        Database database = new Database();
        ArrayList<Bill> pendingBills = new ArrayList<>();

        // Add items to the database
        //database.addItem(1, 2.99, 1.5, "2024-04-01", "2024-03-15", "Manufacturer 1", 0.1, "Item 1");
        // Add more items similarly...
        database.addItem(1, 2.99, 1.5, "2024-04-01", "2024-03-15", "Manufacturer 1", 0.1, "Item 1");
        database.addItem(2, 3.49, 2.0, "2024-04-15", "2024-03-10", "Manufacturer 2", 0.05, "Item 2");
        database.addItem(3, 1.99, 1.0, "2024-03-31", "2024-03-01", "Manufacturer 3", 0.0, "Item 3");
        database.addItem(4, 4.99, 2.5, "2024-04-20", "2024-03-05", "Manufacturer 4", 0.15, "Item 4");
        database.addItem(5, 2.79, 1.2, "2024-04-10", "2024-03-20", "Manufacturer 5", 0.08, "Item 5");
        database.addItem(6, 3.99, 1.8, "2024-04-25", "2024-03-25", "Manufacturer 6", 0.12, "Item 6");



        System.out.println("Welcome");
        System.out.print("Enter Cashier Name: ");
        String cashierName = scanner.nextLine();
        System.out.print("Enter Branch: ");
        String branch = scanner.nextLine();
        System.out.print("Are you a registered Customer? (Y/N): ");
        String choice = scanner.nextLine();


        if (choice.equalsIgnoreCase("Y")) {
            System.out.print("Enter Customer Name: ");
            String customerName = scanner.nextLine();
            bill = new Bill(cashierName, branch, customerName);
        } else {
            bill = new Bill(cashierName, branch);
        }

        boolean continueShopping = true;
        System.out.print("Are you a new customer or a Pending Customer? (Y/N): ");
        String customType = scanner.nextLine();
        if (customType.equalsIgnoreCase("Y")) {
            System.out.print("Enter Bill Name: ");
            String billName = scanner.nextLine();
            bill = new Bill(cashierName, branch, billName);
        }
        while (continueShopping) {


            while(true){
                System.out.println("Enter Item Code");

                int itemcode=scanner.nextInt();
                scanner.nextLine();
                if(itemcode==-1){
                    System.out.println("Do you want to print a bill?Y/N");
                    String a1=scanner.nextLine();
                    if(a1.equals("Y")){
                        bill.printBill();
                        continueShopping=false;
                        break;

                    }else{
                        System.out.print("Do you want to Continue Shopping? (Y/N): ");
                        String answer = scanner.nextLine();
                        continueShopping = answer.equalsIgnoreCase("Y");

                        if (!continueShopping) {
                            pendingBills.add(bill); // Add current bill to pending bills
                        }else{
                            break;
                        }

                    }
                }

                try{
                    i=pos.findItemInDatabase(itemcode,database);

                }catch (ItemCodeNotFondException exception){
                    System.out.println("Enter a Valid Code");
                    break;

                }
                System.out.println("Enter Quantitiy=");
                int q=scanner.nextInt();
                bill.addBill(i,q);




            }




        }
        System.out.println("THANK YOU COME AGAIN!!!");




        scanner.close();
    }
}
