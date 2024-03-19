import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class Menu implements MenuInterface{
    static List<User> userList = new ArrayList<>();
    static List<String> userNames = new ArrayList<>();
    private final List<String> archers = List.of("shooter", "ranger", "sunfire", "zing", "sagittarius");
    private final List<String> knights = List.of("squire", "cavalier", "templar", "zoro", "swiftblade");
    private final List<String> mages = List.of("warlock", "illusionist", "enchanter", "conjurer", "eldritch");
    private final List<String> healers = List.of("soother", "medic", "alchemist", "saint", "lightbringer");
    private final List<String> mythicalCreatures = List.of("dragon", "basilisk", "hydra", "phoenix", "pegasus");
    private final List<String> armours = List.of("chainmail", "regalia", "fleece");
    private final List<String> artefacts = List.of("excalibur", "amulet", "crystal");

    private Scanner scanner = new Scanner(System.in);
    private User currentUser;
    private User challenger;

    public Menu() {
        loadFile();
    }
    public void displayMenu() {
        while (true) {
            System.out.println(""
                    + "╔═══════════════════╗\n"
                    + "   Ｍａｉｎ Ｍｅｎｕ  \n"
                    + "╚═══════════════════╝\n"
                    + "1. New Adventure\n"
                    + "2. Add a Custom Profile\n"
                    + "3. Load Profile\n"
                    + "4. View Profile\n"
                    + "5. View Army\n"
                    + "6. Reinforce Army\n"
                    + "7. Initiate a Battle\n"
                    + "8. Exit Arena\n"
                    + "═════════════════════");
            try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        clearScreen();
                        this.createNewProfile();
                        break;
                    case 2:
                        clearScreen();
                        this.createCustomProfile();
                        break;
                    case 3:
                        clearScreen();
                        this.loadProfile();
                        break;
                    case 4:
                        clearScreen();
                        this.printUserData(currentUser);
                        this.waitForInput();
                        break;
                    case 5:
                        clearScreen();
                        this.printUserDetailsinWar(currentUser);
                        this.waitForInput();
                        break;
                    case 6:
                        clearScreen();
                        this.reinforceArmy();
                        this.waitForInput();
                        break;
                    case 7:
                        clearScreen();
                        this.initiateBattle();
                        break;
                    case 8:
                        saveUserList();
                        System.out.println("Exiting program...");
                        System.exit(0);
                    default:
                        System.out.println("\nInvalid choice, Please enter a digit from 1 to 8\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input, Please enter a digit from 1 to 8\n");
                scanner.next();
            }
        }
    }
    public void saveUserList(){
        try{
            FileOutputStream data = new FileOutputStream("Data.ser");
            ObjectOutputStream obj = new ObjectOutputStream(data);
            obj.writeObject(userList);
            obj.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void loadFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Data.ser"))){
            userList = (List<User>) in.readObject();
            userNames.clear(); // Clear the list before populating
            for (User user : userList) {
                userNames.add(user.getUserName()); // Add each user's username to the list
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }
    private void createNewProfile() {
        System.out.print("Enter Your Name: ");
        String name = scanner.next();

        String userName;
        System.out.println("Now choose a USERNAME. This must be UNIQUE and you can't change this later.");
        while (true) {
            System.out.print("Enter Username: ");
            userName = scanner.next();
            if (userNames.contains(userName)) {
                System.out.println("Username Already Exists!");
            } else {
                userNames.add(userName);
                break;
            }
        }
        System.out.println("Now choose a home ground for your army\n" +
                "1. Hillcrest\n" +
                "2. Marshland\n" +
                "3. Desert\n" +
                "4. Arcane");

        int homeGroundChoice = 0;
        while (true) {
            try {
                homeGroundChoice = scanner.nextInt();
                if (homeGroundChoice >= 1 && homeGroundChoice <= 4) {
                    break; // Exit the loop if a valid choice is made
                } else {
                    System.out.println("\nInvalid Choice, Please enter a digit from 1 to 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Input, Please enter a digit from 1 to 4");
                scanner.next(); // Clear the scanner buffer
            }
        }
        User newUser;
        if (homeGroundChoice == 1) {
            newUser = new User(name, userName, "Hillcrest");
        } else if (homeGroundChoice == 2) {
            newUser = new User(name, userName, "Marshland");
        } else if (homeGroundChoice == 3) {
            newUser = new User(name, userName, "Desert");
        } else {
            newUser = new User(name, userName, "Arcane");
        }

        System.out.println("\nPerfect! You're on the verge of your first battle. Let's now enforce your army");
        System.out.println("Here's a detailed table about troops. Be mindful to choose only one character from each type\n");
        Table.displayTroopsTable();
        System.out.println("\nYou have only " + Math.round(newUser.getCoins()) + " gc in your treasure");
        System.out.println("Embark on your journey by selecting characters from above categories\n");

        while (newUser.getArmy().size() < 5) {
            System.out.print("Provide a name to bring it to life: ");
            String characterName = scanner.next().toLowerCase();
            if (archers.contains(characterName)) {
                newUser.addTroopToArmy(new Archer(characterName));
            } else if (knights.contains(characterName)) {
                newUser.addTroopToArmy(new Knight(characterName));
            } else if (mages.contains(characterName)) {
                newUser.addTroopToArmy(new Mage(characterName));
            } else if (healers.contains(characterName)) {
                newUser.addTroopToArmy(new Healer(characterName));
            } else if (mythicalCreatures.contains(characterName)) {
                newUser.addTroopToArmy(new MythicalCreature(characterName));
            } else {
                System.out.println("Enter a valid name!!");
            }
            System.out.println("You have only " + Math.round(newUser.getCoins()) + " coins remaining\n");
        }
        System.out.println("PERFECT!! Your legion is now complete");
        userList.add(newUser);
        saveUserList();
        System.out.println("\nWELCOME to the world of Mystic Mayhem" + "\nYou are all set to go\n");
        printUserData(newUser);
        printArmy(newUser);
        setCurrentUser(newUser);

        System.out.println("\n" +
            "════════════════════\n" +
            "1. Initiate a Battle\n" +
            "2. Back to Main Menu\n" +
            "════════════════════\n");
        while (true) {
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        clearScreen();
                        initiateBattle();
                        saveUserList();
                        break;
                    case 2:
                        System.out.println("Returning to the main menu...\n");
                        displayMenu();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                scanner.next();
            }
        }
    }
    //This function is to create a custom profile like "GeraltofRivia" from the start
    private void createCustomProfile(){
        System.out.print("Enter Your Name: ");
        String name = scanner.next();

        String userName;
        System.out.println("Now choose a USERNAME. This must be UNIQUE and you can't change this later.");
        while (true) {
            System.out.print("Enter Username: ");
            userName = scanner.next();
            if (userNames.contains(userName)) {
                System.out.println("Username Already Exists!");
            } else {
                userNames.add(userName);
                break;
            }
        }
        System.out.println("Now choose a home ground for your army\n" +
                "1. Hillcrest\n" +
                "2. Marshland\n" +
                "3. Desert\n" +
                "4. Arcane");

        int homeGroundChoice = 0;
        while (true) {
            try {
                homeGroundChoice = scanner.nextInt();
                if (homeGroundChoice >= 1 && homeGroundChoice <= 4) {
                    break; // Exit the loop if a valid choice is made
                } else {
                    System.out.println("\nInvalid Choice, Please enter a digit from 1 to 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Input, Please enter a digit from 1 to 4");
                scanner.next(); // Clear the scanner buffer
            }
        }
        User newUser;
        if (homeGroundChoice == 1) {
            newUser = new User(name, userName, "Hillcrest");
        } else if (homeGroundChoice == 2) {
            newUser = new User(name, userName, "Marshland");
        } else if (homeGroundChoice == 3) {
            newUser = new User(name, userName, "Desert");
        } else {
            newUser = new User(name, userName, "Arcane");
        }
        setCurrentUser(newUser);
        int xp = 0;
        while (true) {
            System.out.print("\nEnter the XP value: ");
            try {
                xp = scanner.nextInt();
                if (xp > 0) {
                    newUser.setXp(xp);
                    break; // Exit the loop if a valid input is provided
                } else {
                    System.out.println("Please enter a positive integer");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear the invalid input from the scanner buffer
            }
        }
        newUser.setCoins(1000000); // Set the gold coins to a large value until troops added.then get the input and change the gold coins to the required amount
        System.out.println("\nPerfect! You're on the verge of your first battle. Let's now enforce your army");
        System.out.println("Here's a detailed table about troops. Be mindful to choose only one character from each type\n");
        Table.displayTroopsTable();
        System.out.println("Embark on your journey by selecting characters from above categories\n");

        while (newUser.getArmy().size() < 5) {
            System.out.print("Provide a name to bring it to life: ");
            String characterName = scanner.next().toLowerCase();
            if (archers.contains(characterName)) {
                newUser.addTroopToArmy(new Archer(characterName));
            } else if (knights.contains(characterName)) {
                newUser.addTroopToArmy(new Knight(characterName));
            } else if (mages.contains(characterName)) {
                newUser.addTroopToArmy(new Mage(characterName));
            } else if (healers.contains(characterName)) {
                newUser.addTroopToArmy(new Healer(characterName));
            } else if (mythicalCreatures.contains(characterName)) {
                newUser.addTroopToArmy(new MythicalCreature(characterName));
            } else {
                System.out.println("Enter a valid name!!");
            }
        }
        int goldCoins = 0;
        while (true) {
            System.out.print("\nEnter the coin amount: ");
            try {
                goldCoins = scanner.nextInt();
                if (goldCoins > 0) {
                    newUser.setCoins(-1000000 + goldCoins);
                    break; // Exit the loop if a valid input is provided
                } else {
                    System.out.println("Please enter a positive integer");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear the invalid input from the scanner buffer
            }
        }
        System.out.println("PERFECT!! Your legion is now complete");
        userList.add(newUser);
        setCurrentUser(newUser);
        saveUserList();
        System.out.println("\nWELCOME to the world of Mystic Mayhem" + "\nYou are all set to go\n");
        printUserData(newUser);
        System.out.println();
        printArmy(newUser);

        System.out.println("\n" +
                "════════════════════\n" +
                "1. Initiate a Battle\n" +
                "2. Back to Main Menu\n" +
                "════════════════════\n");
        while (true) {
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        clearScreen();
                        initiateBattle();
                        saveUserList();
                        break;
                    case 2:
                        System.out.println("Returning to the main menu...\n");
                        displayMenu();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                scanner.next();
            }
        }
    }
    public void loadProfile() {
        boolean validUsernameEntered = false;
        if (!userList.isEmpty()){
            while (!validUsernameEntered) {
                System.out.println("Select a profile to load from below");
                for (User user : userList) {
                    System.out.print(user.getUserName() + "  " + Math.round(user.getXp()) + "Xp \n");
                }
                System.out.print("\nEnter the username of the profile you want to load: ");
                String userNameToLoad = scanner.next();

                for (User user : userList) {
                    if (user.getUserName().equals(userNameToLoad)) {
                        System.out.println("\nProfile loaded successfully!\n");
                        setCurrentUser(user);
                        printUserData(user);
                        validUsernameEntered = true; // Valid username entered, exit the loop
                        break;
                    }
                }
                if (!validUsernameEntered) {
                    System.out.println("\nProfile with username '" + userNameToLoad + "' not found. Please try again.\n");
                }
            }
        }
        else{
            System.out.println("No users!");
        }
        waitForInput();
        displayMenu();
    }
    public void changeCharacters(User curretUser) {
        if (curretUser != null) {
            printArmy(curretUser);
            Table.displayTroopsTable();
            System.out.println("\nChoose replacing character type.\n" +
                    "1. Archer\n" +
                    "2. Knight\n" +
                    "3. Mage\n" +
                    "4. Healer\n" +
                    "5. Mythical Creature");
            try {
                int charactertype = scanner.nextInt();
                while (1 > charactertype || charactertype > 5) {
                    System.out.println("Select a number from 1 to 5\n");
                    changeCharacters(curretUser);
                }
                while (true) {
                    System.out.print("Enter character name to replace with the current character: ");
                    String newCharacterName = scanner.next().toLowerCase().strip();
                    if (charactertype == 1) {
                        if (archers.contains(newCharacterName)) {
                            curretUser.replaceTroop(curretUser.getMyArmyMap().get("Archer"), new Archer(newCharacterName));
                            break;
                        }
                    } else if (charactertype == 2) {
                        if (knights.contains(newCharacterName)) {
                            curretUser.replaceTroop(curretUser.getMyArmyMap().get("Knight"), new Knight(newCharacterName));
                            break;
                        }
                    } else if (charactertype == 3) {
                        if (mages.contains(newCharacterName)) {
                            curretUser.replaceTroop(curretUser.getMyArmyMap().get("Mage"), new Mage(newCharacterName));
                            break;
                        }
                    } else if (charactertype == 4) {
                        if (healers.contains(newCharacterName)) {
                            curretUser.replaceTroop(curretUser.getMyArmyMap().get("Healer"), new Healer(newCharacterName));
                            break;
                        }
                    } else {
                        if (mythicalCreatures.contains(newCharacterName)) {
                            curretUser.replaceTroop(curretUser.getMyArmyMap().get("MythicalCreature"), new MythicalCreature(newCharacterName));
                            break;
                        }
                    }
                }
                saveUserList();
                waitForInput();
                displayMenu();
            }catch (InputMismatchException e){
                System.out.println("Select a number from 1 to 5\n");
                scanner.next();
                changeCharacters(curretUser);
            }
        }else{
            System.out.println("You don't have an active profile yet\n"+
                    "Please create a new profile or load a profile to change troops\n");
            waitForInput();
            System.out.println("Directing back to Main Menu...\n");
            displayMenu();
        }
    }
    public void addEquipments() {
        System.out.println("You currently have: ");
        for (Character character : currentUser.getArmy()) {
            System.out.println("                 " + character.getName().toUpperCase() + " [" + character.getClass().getSimpleName() + "]" + " with " + character.armourCount + " armour and "
                    + character.artefactCount + " artefact");
        }
        System.out.println("Select the character to enforce with equipment\n");
        try {
            System.out.println("ARCHER [enter 1], KNIGHT [enter 2], MAGE [enter 3], HEALER [enter 4], MYTHICAL CREATURE [enter 5]: ");
            int choice = scanner.nextInt();
            System.out.println("\nDelve into the extensive selection of available equipment detailed below\n");
            Table.displayEquipmentTable();
            System.out.print("\nEnter the name of the equipment: ");
            String equipment = scanner.next().toLowerCase();
            Armour armour = null;
            Artefact artefact = null;

            if (armours.contains(equipment)) {
                armour = new Armour(equipment);
            } else if (artefacts.contains(equipment)) {
                artefact = new Artefact(equipment);
            } else {
                System.out.println("Equipment not found. Prompt Again ");
                addEquipments();
                return;
            }

            switch (choice) {
                case 1:
                    if (currentUser.addEquipment(currentUser.getMyArmyMap().get("Archer"), armour != null ? armour : artefact)) {
                        System.out.println("Equipment added successfully.");
                    }
                    break;
                case 2:
                    if (currentUser.addEquipment(currentUser.getMyArmyMap().get("Knight"), armour != null ? armour : artefact)) {
                        System.out.println("Equipment added successfully.");
                    }
                    break;
                case 3:
                    if (currentUser.addEquipment(currentUser.getMyArmyMap().get("Mage"), armour != null ? armour : artefact)) {
                        System.out.println("Equipment added successfully.");
                    }
                    break;
                case 4:
                    if (currentUser.addEquipment(currentUser.getMyArmyMap().get("Healer"), armour != null ? armour : artefact)) {
                        System.out.println("Equipment added successfully.");
                    }
                    break;
                case 5:
                    if (currentUser.addEquipment(currentUser.getMyArmyMap().get("Mythical Creature"), armour != null ? armour : artefact)) {
                        System.out.println("Equipment added successfully.");
                    }
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
            waitForInput();
            System.out.println("\nDirecting back to main menu...\n");
            saveUserList();
        } catch(InputMismatchException e){
            System.out.println("Invalid input!");
            scanner.next();
            addEquipments();// Clear the scanner buffer
        }
    }
    public User getCurrentUser() {
        return currentUser;
    }
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
    public void printUserData(User user) {
        try {
            System.out.println("▂ ▅ ▇ █ █ ▇ ▅ ▂");
            System.out.println("Username: " + user.getUserName());
            System.out.println("UserID: " + user.getUserID());
            System.out.println("Coins: " + Math.round(user.getCoins()) + " gc");
            System.out.println("Battle Xp: " + Math.round(user.getXp()));
            System.out.println("▂ ▂ ▂ PROFILE ▂ ▂ ▂ \n");
        } catch (NullPointerException e){
            System.out.println("Sorry you haven't created any profiles yet");
            displayMenu();
        }
    }
    public void printUserDetailsinWar(User user) {
        clearScreen();
        try {
            System.out.println("Username: " + user.getUserName());
            System.out.println("XP: " + Math.round(user.getXp()));
            System.out.println("Characters:");
            int i = 1;
            for (Character character : user.getArmy()) {
                System.out.println("   " + i + ". " + character.getName().toUpperCase());
                i++;
            }
            System.out.println("╚════════════════════════════╝\n");
        } catch (NullPointerException e){
            System.out.println("Please create or load a profile to view army\n");
            displayMenu();
        }
    }
    public void printArmy(User user) {
        System.out.println("️══ Your Legion ══");
        for (Character character : user.getArmy()) {
            String characterType = "";
            if (character instanceof Archer) {
                characterType = "Archer";
            } else if (character instanceof Knight) {
                characterType = "Knight";
            } else if (character instanceof Mage) {
                characterType = "Mage";
            } else if (character instanceof Healer) {
                characterType = "Healer";
            } else if (character instanceof MythicalCreature) {
                characterType = "Mythical Creature";
            }

            System.out.println(characterType.toUpperCase() + ": " + character.getName().toUpperCase() +
                    " --> Price " + character.getPrice() + " | Attack " + character.getAttack() +
                    " | Defence " + character.getDefence() + " | Health " + character.getHealth() +
                    " | Speed " + character.getSpeed());
        }
        System.out.println();
    }
    private User getOpponent() {
        if (userList.size() < 2) {
            System.out.println("There are not enough users to challenge. Please add more users.");
            return null;
        }

        List<User> availableOpponents = new ArrayList<>(userList);
        availableOpponents.remove(currentUser);

        if (availableOpponents.isEmpty()) {
            System.out.println("No available opponents to challenge.");
            return null;
        }

        Random random = new Random();
        User opponent = availableOpponents.get(random.nextInt(availableOpponents.size()));

        printUserDetailsinWar(opponent);

        while (true) {
            System.out.print("Challenge , Skip or Exit to Main Menu (C/S/E): ");
            String decision = scanner.next();
            if (decision.equalsIgnoreCase("c")) {
                challenger = opponent;
                return challenger;
            } else if (decision.equalsIgnoreCase("s")) {
                opponent = availableOpponents.get(random.nextInt(availableOpponents.size()));
                printUserDetailsinWar(opponent);
            } else if (decision.equalsIgnoreCase("e")) {
                displayMenu();
                return null;
            } else {
                System.out.println("Enter 'c' to challenge , 's' to skip or 'e' to exit to main menu");
            }
        }
    }
    public void reinforceArmy(){
        System.out.println("\n════════════════════\n" +
            "1. Replace Troops\n" +
            "2. GearUp Troops\n" +
            "3. Back to Main Menu\n" +
            "════════════════════\n");
        if (currentUser != null) {
            while (true) {
                try {
                    int choice = scanner.nextInt();
                    if (choice == 1 || choice == 2){
                        if (choice == 1) {
                            changeCharacters(currentUser);
                        } else {
                            addEquipments();
                        }
                        break;
                    } else if (choice == 3){
                        displayMenu();
                        break;
                    } else {
                        System.out.println("Invalid choice, choose a number from 1 to 3");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input, choose a number from 1 to 3");
                    scanner.next();
                }
            }
        }
        else {
            System.out.println("You don't have an active profile yet\n"+
                    "Please create a new profile or load a profile to change troops\n");
            System.out.println("Directing back to Main Menu...\n");
            displayMenu();
        }
        saveUserList();
    }
    public void initiateBattle(){
        if (currentUser != null) {
            System.out.println("\n═════════════════════\n" +
                "1. Find a Battle\n" +
                "2. Back to Main Menu\n" +
                "═════════════════════\n");
            while (true) {
                try {
                    int choice = scanner.nextInt();
                    if (choice == 1) {
                        User opponent = getOpponent();
                        War war = new War(currentUser,opponent);
                        waitForInput();
                        displayMenu();
                        break;
                    } else if (choice == 2) {
                        displayMenu();
                        break;
                    } else {
                        System.out.println("\nInvalid choice, choose a number from 1 to 3");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\nInvalid input, choose a number from 1 to 3");
                    scanner.next();
                }
            }
        }
        else {
            System.out.println("You don't have an active profile yet\n"+
                    "Please create a new profile or load a profile to change troops\n");
            System.out.println("Directing back to Main Menu...\n");
            displayMenu();
        }
        saveUserList();
    }
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    private void waitForInput() {
        System.out.println("Press Enter to continue...");
        try {
            System.in.read(); // Wait for user to press Enter
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
}