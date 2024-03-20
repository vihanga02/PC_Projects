import java.util.Scanner;
import java.util.InputMismatchException;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    Bill bill;
    String cashierName;
    String branch;
    String customerName;
    Database database = new Database();
    POS pos = new POS();
    GroceryItem i = null;

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

    private void shopping() {
        boolean continueShopping = true;

        while (continueShopping) {
            int itemcode = 0;
            System.out.println("Enter Item Code: ");
            try {
                itemcode = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine(); // Consume the invalid input
            }
            try {
                i = pos.findItemInDatabase(itemcode, database);

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
