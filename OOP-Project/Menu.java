import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    public Menu(){
        displayMenu();
    }
    public void displayMenu(){
        System.out.println("1. New Profile.\n" +
                "2. Load Profile.");
        int choice = scanner.nextInt();

        switch (choice){
            case 1:
                this.createUser();
                break;
        }
    }

    private void createUser(){
        System.out.println("Name: ");
        String name = scanner.next();

        System.out.println("User Name: ");
        String userName = scanner.next();

        System.out.println("Home Ground\n " +
                "1. Hillcrest\n" +
                "2. Marshland\n" +
                "3.Desert\n" +
                "4. Arcane");

        int homeGroundCoice = scanner.nextInt();
        while (4 < homeGroundCoice || 1> homeGroundCoice){
            System.out.println("Wrong Choice. Prompt again!!!!");
            System.out.println("Home Ground\n " +
                    "1. Hillcrest\n" +
                    "2. Marshland\n" +
                    "3.Desert\n" +
                    "4. Arcane");

            homeGroundCoice = scanner.nextInt();
        }

        if (homeGroundCoice == 1){
            User newUser = new User(name, userName, "Hillcrest");
        }
        else if (homeGroundCoice == 2){
            User newUser = new User(name, userName, "Marshland");
        }
        else if (homeGroundCoice == 3){
            User newUser = new User(name, userName, "Desert");
        }
        else{
            User newUser = new User(name, userName, "Arcane");
        }
    }
    private void
}
