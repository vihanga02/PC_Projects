import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// The POS class represents the Point of Sale system
class POS {
    // Scanner to read user input
    Scanner scanner = new Scanner(System.in);
    // BufferedReader to read user input
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    // Bill object to hold the current bill
    Bill bill;
    // Strings to hold the cashier name, branch, and customer name
    String cashierName = "";
    String branch = "";
    String customerName = "";
    // Database object to hold the database of items and customers
    Database database = new Database();
    // String to hold the item code
    private String itemCode;
    // Map to hold the pending bills
    public static Map<String, Bill> pendingBill = new HashMap<>();

    // Constructor for the POS class
    POS() {
        // Load the pending bills and registered customers

        loadPendingBills();
        loadRegCustomersList();
        // Add some items to the database for testing purposes
        this.database.addItem("1", 2.99, 1.5, "2024-04-01", "2024-03-15", "Manufacturer 1", 0.1, "Item 1");
        this.database.addItem("2", 3.49, 2.0, "2024-04-15", "2024-03-10", "Manufacturer 2", 0.05, "Item 2");
        this.database.addItem("3", 1.99, 1.0, "2024-03-31", "2024-03-01", "Manufacturer 3", 0.0, "Item 3");
        this.database.addItem("4", 4.99, 2.5, "2024-04-20", "2024-03-05", "Manufacturer 4", 0.15, "Item 4");
        this.database.addItem("5", 2.79, 1.2, "2024-04-10", "2024-03-20", "Manufacturer 5", 0.08, "Item 5");
        this.database.addItem("6", 3.99, 1.8, "2024-04-25", "2024-03-25", "Manufacturer 6", 0.12, "Item 6");
    }

    // Method to display the functions of the POS system
    public void displayFunctions() throws IOException, ItemCodeNotFondException {
        // Collect the cashier and customer details
        collectCashierDetails();
        collectCustomerDetails();
        // Close the BufferedReader
        if (reader != null) {
            reader.close();
        }
    }

    // Method to collect the cashier details
    private void collectCashierDetails() {
        System.out.println("Welcome");
        // Loop until a valid cashier name is entered
        while (cashierName.trim().isEmpty()) {
            System.out.print("Enter Cashier Name: ");
            cashierName = scanner.nextLine();
            if (cashierName.trim().isEmpty()) {
                System.out.println("Cashier Name cannot be empty. Please enter again.\n");
            } else {
                break;
            }
        }
        // Loop until a valid branch is entered
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

    // Method to collect the customer details
    private void collectCustomerDetails() throws IOException, ItemCodeNotFondException {
        // Loop until a valid customer name is entered
        while (customerName.trim().isEmpty()) {
            System.out.print("Enter Customer Name: ");
            customerName = scanner.nextLine();
            if (customerName.trim().isEmpty()) {
                System.out.println("Customer Name cannot be empty. Please enter again.\n");
            } else if (pendingBill != null) {
                // If the customer has a pending bill, load it
                if (pendingBill.containsKey(customerName)) {
                    this.bill = pendingBill.get(customerName);
                    shopping();
                } else {
                    // If the customer is registered, create a new bill with their name
                    if (database.getRegisteredCustomerList().contains(customerName)) {
                        bill = new Bill(cashierName, branch, customerName);
                    } else {
                        // If the customer is not registered, create a new bill without their name
                        bill = new Bill(cashierName, branch);
                    }
                    shopping();
                }
            } else {
                // If there are no pending bills, create a new bill
                if (database.getRegisteredCustomerList().contains(customerName)) {
                    bill = new Bill(cashierName, branch, customerName);
                } else {
                    bill = new Bill(cashierName, branch);
                }
                shopping();
            }
        }
    }

    // Method to load the registered customers list from a file
    public void loadRegCustomersList() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Customers.ser"))){
            ArrayList<String> loadedRegisteredCustomers = (ArrayList<String>)  in.readObject();
            database.setRegisteredCustumerList(loadedRegisteredCustomers);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("File not found");
        }
    }

    // Method to load the pending bills from a file
    public void loadPendingBills() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("PendingCustomers.ser"))){
            Map<String, Bill> loadedPendingBills = (Map<String, Bill>) in.readObject();
            pendingBill = loadedPendingBills;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("File not found");
        }
    }

    // Method to save the registered customers list to a file
    public void saveRegCustomers(){
        try{
            FileOutputStream data = new FileOutputStream("Customers.ser");
            ObjectOutputStream obj = new ObjectOutputStream(data);
            obj.writeObject(database.getRegisteredCustomerList());
            obj.close();
        }
        catch (IOException e) {
            System.out.println("Could not save the customer...");
        }
    }

    // Method to save the pending bills to a file
    public void savePendingBills(){
        try{
            FileOutputStream data = new FileOutputStream("PendingCustomers.ser");
            ObjectOutputStream obj = new ObjectOutputStream(data);
            obj.writeObject(pendingBill);
            obj.close();
        }
        catch (IOException e) {
            System.out.println("Could not save the bill...");
        }
    }

    // Method to get the details of an item
    public GroceryItem getItemDetails() throws IOException, ItemCodeNotFondException {
        System.out.println("Enter item code: ");
        itemCode = reader.readLine();

        try {
            // Loop through the items in the database to find the item with the entered code
            for (GroceryItem item : database.getItemDatabase()) {
                if (item.getItemCode().equals(itemCode)) {
                    return item;
                }
            }
            // If the item is not found, throw an exception
            throw new ItemCodeNotFondException();
        } catch (ItemCodeNotFondException e) {
            System.out.println("Item not found.");
            return getItemDetails();
        }
    }

    // Method to handle the shopping process
    private void shopping() throws IOException, ItemCodeNotFondException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean continueShopping = true;
        // Loop until the customer decides to stop shopping
        while (continueShopping) {
            // Get the details of the item to be added to the bill
            GroceryItem item = getItemDetails();

            System.out.println("Enter Quantity: ");
            int quantity;
            // Loop until a valid quantity is entered
            while (true) {
                try {
                    quantity = Integer.parseInt(reader.readLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Enter an integer value.");
                }
            }
            // Add the item and its quantity to the bill
            bill.addBill(item, quantity);
            System.out.println("Do you want to Continue Shopping? Yes [Y]/ No [N]/ Hold bill [H]: ");
            // Loop until a valid answer is entered
            while (true) {
                String answer = reader.readLine().trim();

                if (answer.equalsIgnoreCase("N")) {
                    // If the customer decides to stop shopping, print the bill and save the customers and bills
                    continueShopping = false;
                    if (pendingBill != null) {
                        if (pendingBill.containsKey(customerName)) {
                            pendingBill.remove(customerName);
                        }
                    }
                    bill.printBill();
                    saveRegCustomers();
                    savePendingBills();
                    break;
                } else if (answer.equalsIgnoreCase("H")) {
                    // If the customer decides to hold the bill, add it to the pending bills
                    continueShopping = false;
                    pendingBill.put(customerName, bill);
                    break;
                } else if (answer.equalsIgnoreCase("Y")) {
                    // If the customer decides to continue shopping, continue the loop
                    break;
                } else {
                    System.out.println("Invalid input. Please enter Y/N/H");
                }
            }
        }
    }
}