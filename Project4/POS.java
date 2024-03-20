import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


class POS {
    Scanner scanner=new Scanner(System.in);
    public  static ArrayList<Bill> pendingBill;

    public GlocerItem getItemDetails(Database database) {
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);
        GlocerItem item = null;

        while (true) {
            try {
                System.out.print("Enter item code: ");
                int itemCode = Integer.parseInt(br.readLine());
                scanner.nextLine();

                item = findItemInDatabase(itemCode, database);
                break; // Exit the loop if the item is found
            } catch (NumberFormatException e) {
                System.out.println("Invalid item code. Please enter a valid integer.");
            } catch (ItemCodeNotFondException e) {
                System.out.println("Item code not found. Please re-enter.");
            } catch (IOException e) {
                System.out.println("An error occurred while reading input. Please try again.");
            }
        }

        try {
            br.close();
            r.close();
        } catch (IOException e) {
            System.out.println("An error occurred while closing input stream.");
        }

        return item;
    }

    public GlocerItem findItemInDatabase(int itemCode, Database database) throws ItemCodeNotFondException {
        for (GlocerItem item : database.getDatabase()) {
            if (item.getItemCode() == itemCode) {
                return item;
            }
        }
        throw new ItemCodeNotFondException();

    }
    public void addPendingBill(Bill b1){
        pendingBill.add(b1);
    }
}

