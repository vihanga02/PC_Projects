import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
class POS {
    Scanner scanner = new Scanner(System.in);
    Bill bill;
    String cashierName = "";
    String branch = "";
    String customerName = "";
    Database database = new Database();
    private String itemCode;
    public  static ArrayList<Bill> pendingBill;

    POS(){
        this.database.addItem("1", 2.99, 1.5, "2024-04-01", "2024-03-15", "Manufacturer 1", 0.1, "Item 1");
        this.database.addItem("2", 3.49, 2.0, "2024-04-15", "2024-03-10", "Manufacturer 2", 0.05, "Item 2");
        this.database.addItem("3", 1.99, 1.0, "2024-03-31", "2024-03-01", "Manufacturer 3", 0.0, "Item 3");
        this.database.addItem("4", 4.99, 2.5, "2024-04-20", "2024-03-05", "Manufacturer 4", 0.15, "Item 4");
        this.database.addItem("5", 2.79, 1.2, "2024-04-10", "2024-03-20", "Manufacturer 5", 0.08, "Item 5");
        this.database.addItem("6", 3.99, 1.8, "2024-04-25", "2024-03-25", "Manufacturer 6", 0.12, "Item 6");
    }

    public void displayFunctions(){
        collectCashierDetails();
        collectCustomerDetails();
        shopping();
    }
    public GroceryItem getItemDetails() throws IOException, ItemCodeNotFondException {
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);
        System.out.println("Enter item code: ");
        itemCode = br.readLine();

        GroceryItem item = null;
        item = findItemInDatabase(itemCode);

        try {
            br.close();
            r.close();
        } catch (IOException e) {
            System.out.println("An error occurred while closing input stream.");
        }
        return item;
    }
    public GroceryItem findItemInDatabase(String itemCode) throws ItemCodeNotFondException {

        for (GroceryItem item : database.getItemDatabase()) {
                if (item.getItemCode().equals(itemCode)) {
                    return item;
                }
            }
            throw new ItemCodeNotFondException();
    }
    public void addPendingBill(Bill b1){
        pendingBill.add(b1);
    }
    private void collectCashierDetails() {
        System.out.println("Welcome");
        while (cashierName.trim().isEmpty()) {
            System.out.print("Enter Cashier Name: ");
            cashierName = scanner.nextLine();
            if (cashierName.trim().isEmpty()) {
                System.out.println("Cashier Name cannot be empty. Please enter again.\n");
            } else {
                break;
            }
        }
        while (branch.trim().isEmpty()) {
            System.out.print("Enter Branch: ");
            branch = scanner.nextLine();
            if (branch.trim().isEmpty()) {
                System.out.println("Branch cannot be empty. Please enter again.\n");
            } else {
                break;
            }
        }
    }

    private void collectCustomerDetails() {
        while (customerName.trim().isEmpty()) {
            System.out.print("Enter Customer Name: ");
            customerName = scanner.nextLine();
            if (customerName.trim().isEmpty()) {
                System.out.println("Customer Name cannot be empty. Please enter again.\n");
            } else {
                break;
            }
        }
        if (database.getRegisteredCustumerList().contains(customerName)) {
            bill = new Bill(cashierName, branch, customerName);
        } else {
            bill = new Bill(cashierName, branch);
        }
    }

    private void shopping() throws IOException, ItemCodeNotFondException {
        boolean continueShopping = true;

        while (continueShopping) {
            // int itemcode = 0;
            GroceryItem item = getItemDetails();
            try {
                i = this.findItemInDatabase(itemcode, database);

            } catch (ItemCodeNotFondException exception) {
                System.out.println("Enter a Valid Code");
                break;
            }
            System.out.println("Enter Quantity: ");
            int quantity = scanner.nextInt();
            bill.addBill(i, quantity);
            System.out.println("Do you want to Continue Shopping? (Y/N): ");
            String answer = scanner.nextLine();
            if (answer == "N") {
                continueShopping = false;
            }
        }
        bill.printBill();
    }
}

