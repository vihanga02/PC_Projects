import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class Menu {
    static List<User> userList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    public Menu(){
    }
    public void displayMenu(){
        System.out.println("-----Menu-----\n" +
                "1. New Profile.\n" +
                "2. Load Profile.");
        int choice = scanner.nextInt();

        switch (choice){
            case 1:
                this.createUser();
                break;
            case 2:
                this.loadPlayer();
                break;
            default:
                System.out.println("Enter a number!!");
                displayMenu();
        }
    }

    private void createUser(){
        System.out.print("Name: ");
        String name = scanner.next();

        System.out.print("User Name: ");
        String userName = scanner.next();

        System.out.println("Home Grounds\n" +
                "1. Hillcrest\n" +
                "2. Marshland\n" +
                "3.Desert\n" +
                "4. Arcane");

        int homeGroundCoice = scanner.nextInt();
        while (4 < homeGroundCoice || 1> homeGroundCoice){
            System.out.println("Wrong Choice. Prompt again!!!!");
            System.out.println("Home Grounds\n" +
                    "1. Hillcrest\n" +
                    "2. Marshland\n" +
                    "3.Desert\n" +
                    "4. Arcane");

            homeGroundCoice = scanner.nextInt();
        }
        User newUser;
        if (homeGroundCoice == 1){
            newUser = new User(name, userName, "Hillcrest");
        }
        else if (homeGroundCoice == 2){
            newUser = new User(name, userName, "Marshland");
        }
        else if (homeGroundCoice == 3){
            newUser = new User(name, userName, "Desert");
        }
        else{
            newUser = new User(name, userName, "Arcane");
        }
        userList.add(newUser);
        System.out.println("New profile created!!");
    }
    private void loadPlayer(){
        userList.add(new User("Muthumala","muthu","HillCrest"));
        userList.add(new User("Rusiru","russ","Marshland"));
        userList.add(new User("Rivindu","rivi","Desert"));
        userList.add(new User("Thrinith","wick","Arcane"));

        for (User user: userList){
            System.out.println("Username: " + user.getUserName());
            System.out.println("Xp: " + user.getXp());
            if (user.getArmy() != null){
                for (Character character: user.getArmy()) {
                    if (character instanceof Archer)
                        System.out.println("Archer: " + character.getName());
                    else if (character instanceof Knight) {
                        System.out.println("Knight: " + character.getName());
                    } else if (character instanceof Mage) {
                        System.out.println("Mage: " + character.getName());
                    } else if (character instanceof Healer) {
                        System.out.println("Healer: " + character.getName());
                    } else {
                        System.out.println("Mythical Creature: " + character.getName());
                    }
                }
            }
            System.out.println("\n");
        }
    }
}
