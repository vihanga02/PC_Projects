import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class Menu {
    static List<User> userList = new ArrayList<>();
    static List<String> userNames = new ArrayList<>();
    private final List<String> archers = List.of("shooter", "ranger", "sunfire", "zing", "sagittarius");
    private final List<String> knights = List.of("squire", "cavalier", "templar", "zoro", "swiftblade");
    private final List<String> mages = List.of("warlock", "illusionist", "enchanter", "conjurer", "eldritch");
    private final List<String> healers = List.of("soother", "medic", "alchemist", "saint", "lightbringer");
    private final List<String> mythicalCreatures = List.of("dragon", "basilisk", "hydra", "phoenix", "pegasus");
    private final List<String> armours = List.of("chainmail", "regalia", "fleece");
    private final List<String> artefacts = List.of("excalibur", "amulet", "crystal");

    private Random random = new Random();
    private Scanner scanner = new Scanner(System.in);
    private User currentUser;
    private User challenger;

    public Menu() {
    }

    public void displayMenu() {
        System.out.println("""
        ╔═══════════════════╗
           Ｍａｉｎ Ｍｅｎｕ  
        ╚═══════════════════╝
        1. New Adventure
        2. Load Your Journey
        3. View Your Journey
        4. Reinforce Army
        5. Initiate a Battle
        6. Exit Arena
        ═════════════════════""");

        while (true) {
            try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        this.createProfile();
                        return;
                    case 2:
                        this.loadProfile();
                        return;
                    case 3:
                        this.printUserData(currentUser);
                        return;
                    case 4:
                        this.reinforceArmy();
                        return;
                    case 5:
                        this.initiateBattle();
                    case 6:
                        System.out.println("Exiting program...");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice, Please enter a digit from 1 to 6\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, Please enter a digit from 1 to 6\n");
                scanner.next();
            }
        }
    }


    private void createProfile() {
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
        System.out.println("\nYou have only " + newUser.getCoins() + " gc in your treasure");
        System.out.println("Embark on yout journey by selecting characters from above categories");

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
            System.out.println("You have only " + newUser.getCoins() + " coins remaining\n");
        }

        System.out.println("PERFECT!! Your legion is now complete");
        userList.add(newUser);
        System.out.println("\nWELCOME to the world of Mystic Mayhem" + "\nYou are all set to go\n");
        printUserData(newUser);
        printArmy(newUser);
        setCurrentUser(newUser);
        System.out.println();

        System.out.println("""
        ════════════════════
        1. Initiate a Battle
        2. Back to Main Menu       
        ════════════════════    
        """);
        while (true) {
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Battle initiated!");
                        initiateBattle();
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
                scanner.next(); // Clear the scanner buffer
            }
        }


    }
    public void changeCharacters(User curretUser) {
        if (curretUser != null) {
            printArmy(curretUser);
            Table.displayTroopsTable();
            System.out.println("Choose replacing character type.\n" +
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
                    System.out.print("Enter character name to replace with the c: ");
                    String newCharacterName = scanner.next();
                    if (charactertype == 1) {
                        if (archers.contains(newCharacterName.toLowerCase().strip())) {
                            curretUser.replaceTroop(curretUser.getMyArmyMap().get("Archer"), new Archer(newCharacterName));
                            break;
                        }
                    } else if (charactertype == 2) {
                        if (knights.contains(newCharacterName.toLowerCase().strip())) {
                            curretUser.replaceTroop(curretUser.getMyArmyMap().get("Knight"), new Knight(newCharacterName));
                            break;
                        }
                    } else if (charactertype == 3) {
                        if (mages.contains(newCharacterName.toLowerCase().strip())) {
                            curretUser.replaceTroop(curretUser.getMyArmyMap().get("Mage"), new Mage(newCharacterName));
                            break;
                        }
                    } else if (charactertype == 4) {
                        if (healers.contains(newCharacterName.toLowerCase().strip())) {
                            curretUser.replaceTroop(curretUser.getMyArmyMap().get("Healer"), new Healer(newCharacterName));
                            break;
                        }
                    } else {
                        if (mythicalCreatures.contains(newCharacterName.toLowerCase().strip())) {
                            curretUser.replaceTroop(curretUser.getMyArmyMap().get("MythicalCreature"), new MythicalCreature(newCharacterName));
                            break;
                        }
                    }
                }
            }catch (InputMismatchException e){
                    System.out.println("Select a number from 1 to 5\n");
                    changeCharacters(curretUser);
            }
        }else{
            System.out.println("You don't have an active profile yet\n"+
                    "Please create a new profile or load a profile to change troops\n");
            System.out.println("Directing back to Main Menu...\n");
            displayMenu();
        }
    }

    private void loadProfile() {
        boolean validUsernameEntered = false;
        while (!validUsernameEntered) {
            System.out.println("Select a profile to load from below");
            for (User user : userList) {
                System.out.print(user.getUserName() + "  " + user.getXp() + "Xp | ");
            }
            System.out.println("\nEnter the username of the profile you want to load: ");
            String userNameToLoad = scanner.next();

            for (User user : userList) {
                if (user.getUserName().equals(userNameToLoad)) {
                    System.out.println("\nProfile loaded successfully!\n");
                    printUserData(user);
                    setCurrentUser(user);
                    validUsernameEntered = true; // Valid username entered, exit the loop
                    break;
                }
            }

            if (!validUsernameEntered) {
                System.out.println("\nProfile with username '" + userNameToLoad + "' not found. Please try again.\n");
            }
        }
        displayMenu();
    }

    private void addEquipments() {
        System.out.println("You currently have: ");
        for (Character character : currentUser.getArmy()) {
            System.out.println("                 " + character.getName() + " with " + character.armourCount + " armour and "
                    + character.artefactCount + " artefact");
        }
        System.out.println("Select the character to enforce with equipment\n");
        try {
            System.out.println("ARCHER [enter 1], KNIGHT [enter 2], MAGE [enter 3], HEALER [enter 4], MYTHICAL_CREATURE [enter 5]: ");
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
            }

            switch (choice) {
                case 1:
                    if (currentUser.addEquipment(currentUser.getMyArmyMap().get("Archer"), armour != null ? armour : artefact)) {
                        System.out.println("Equipment added successfully.");
                    } else {
                        System.out.println("Equipment not added. Insufficient funds or troop already equipped.");
                    }
                    break;
                case 2:
                    if (currentUser.addEquipment(currentUser.getMyArmyMap().get("Knight"), armour != null ? armour : artefact)) {
                        System.out.println("Equipment added successfully.");
                    } else {
                        System.out.println("Equipment not added. Insufficient funds or troop already equipped.");
                    }
                    break;
                // Repeat for other cases...
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        } catch(InputMismatchException e){
            System.out.println("Invalid input!");
            scanner.next(); // Clear the scanner buffer
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    private void printUserData(User user) {
        try {
            System.out.println("▂ ▅ ▇ █ 🌟 █ ▇ ▅ ▂");
            System.out.println("Username: " + user.getUserName());
            System.out.println("UserID: " + user.getUserID());
            System.out.println("Coins: " + user.getCoins() + " gc");
            System.out.println("Battle Xp: " + user.getXp());
            System.out.println("▂ ▂ ▂ PROFILE ▂ ▂ ▂ \n");
        } catch (NullPointerException e){
            System.out.println("Sorry you haven't created any profiles yet");
            displayMenu();
        }
    }

    private void printUserDetailsinWar(User user) {
        System.out.println("╔═══════════════════════════╗");
        System.out.println(" 👑 Username: " + user.getUserName());
        System.out.println(" 🌟 XP: " + user.getXp());
        System.out.println(" 🎖️ Characters:");
        int i = 1;
        for (Character character : user.getArmy()) {
            System.out.println("   " + i + ". " + character.getName());
            i++;
        }
        System.out.println("╚════════════════════════════╝\n");
    }


    private void printArmy(User user) {
        System.out.println("⚔️══ Your Legion ══⚔️");
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

            System.out.println(characterType + ": " + character.getName() +
                    " --> Price " + character.getPrice() + " | Attack " + character.getAttack() +
                    " | Defence " + character.getDefence() + " | Health " + character.getHealth() +
                    " | Speed " + character.getSpeed());
        }
    }

    private User getOpponent() {
        if (userList.size() < 2) {
            System.out.println("There are not enough users to challenge. Please add more users.");
            return null;
        }

        int randomIndex;
        User opponent;
        do {
            randomIndex = random.nextInt(userList.size());
            opponent = userList.get(randomIndex);
        } while (opponent.equals(currentUser));

        printUserDetailsinWar(opponent);

        while (true) {
            System.out.print("Challenge , Skip or Exit to Main Menu (C/S/E): ");
            String decision = scanner.next();
            if (decision.equalsIgnoreCase("c")) {
                challenger = opponent;
                return challenger;
            } else if (decision.equalsIgnoreCase("s")) {
                // Print the details of the next random user
                do {
                    randomIndex = random.nextInt(userList.size());
                    opponent = userList.get(randomIndex);
                } while (opponent.equals(currentUser));
                printUserDetailsinWar(opponent);
            } else {
                System.out.println("Enter 'c' to challenge , 's' to skip or 'e' to exit to main menu");
            }
        }
    }

    private void reinforceArmy(){
        System.out.println("""
        ════════════════════
        1. Replace Troops
        2. GearUp Troops   
        3. Back to Main Menu   
        ════════════════════    
        """);
        if (currentUser != null) {
            while (true) {
                try {
                    int choice = scanner.nextInt();
                    if (choice == 1) {
                        changeCharacters(currentUser);
                        break;
                    } else if (choice == 2) {
                        addEquipments();
                        break;
                    } else if (choice == 3){
                        displayMenu();
                        break;
                    } else System.out.println("Invalid choice, choose a number from 1 to 3");
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
    }

    private void initiateBattle(){
        if (currentUser != null) {
            System.out.println("""
        ════════════════════
        1. Find a Battle
        2. Reinforce Army
        3. Back to Main Menu      
        ════════════════════    
        """);
            while (true) {
                try {
                    int choice = scanner.nextInt();
                    if (choice == 1) {
                        User opponent = getOpponent();
                        War war = new War(currentUser,opponent);
                        war.startWar();
                        displayMenu();
                        break;
                    } else if (choice == 2) {
                        reinforceArmy();
                        break;
                    } else if (choice == 3) {
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
    }

}
