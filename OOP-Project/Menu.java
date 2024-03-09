import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class Menu {
    static List<User> userList = new ArrayList<>();
    static List<String> userNames = new ArrayList<>();
    private final List<String> archers = List.of("shooter", "ranger","sunfire","zing","sagittarius");
    private final List<String> knights = List.of("squire","cavalier","templar", "zoro","swiftblade");
    private final List<String> mages = List.of("warlock","illusionist","enchanter","conjurer","eldritch");
    private final List<String> healers = List.of("soother","medic","alchemist","saint","lightbringer");
    private final List<String> mythicalCreatures = List.of("dragon", "basilisk","hydra","phoenix","pegasus");
    private final List<String> armours = List.of("chainmail", "regalia", "fleece");
    private final List<String> artefacts = List.of("excalibur", "amulet", "crystal");
    Scanner scanner = new Scanner(System.in);
    private User currentUser;
    public Menu(){
    }
    public void displayMenu(){
        System.out.println("-----Menu-----\n" +
                "1. New Profile.\n" +
                "2. Load Profile.");
        int choice = scanner.nextInt();

        switch (choice){
            case 1:
                this.createProfile();
                break;
            case 2:
                this.loadProfile();
                break;
            default:
                System.out.println("Enter a number!!");
        }
    }

    private void createProfile(){
        System.out.print("Enter Your Name: ");
        String name = scanner.next();

        String userName;
        System.out.println("Now choose a USERNAME. This must be UNIQUE and you can't change this later.");
        while (true){
            System.out.println("Enter Username: ");
            userName = scanner.next();
            if (userNames.contains(userName)){
                System.out.println("Username Already Exists!");
            }
            else{
                break;
            }
        }

        System.out.println("Now choose a home ground for your army\n" +
                "1. Hillcrest\n" +
                "2. Marshland\n" +
                "3. Desert\n" +
                "4. Arcane");

        int homeGroundChoice = scanner.nextInt();
        while (4 < homeGroundChoice || 1> homeGroundChoice){
            System.out.println("Wrong Choice. Prompt again!!!!");
            homeGroundChoice = scanner.nextInt();
        }

        User newUser;
        if (homeGroundChoice == 1){
            newUser = new User(name, userName, "Hillcrest");
        }
        else if (homeGroundChoice == 2){
            newUser = new User(name, userName, "Marshland");
        }
        else if (homeGroundChoice == 3){
            newUser = new User(name, userName, "Desert");
        }
        else{
            newUser = new User(name, userName, "Arcane");
        }

        System.out.println("Perfect! You're on the verge of your first battle. Let's now enforce your army");
        System.out.println("Here's a detailed table about troops. Be mindful to choose only one character from each type");
        System.out.println();
        Table.displayTroopsTable();
        System.out.println();
        System.out.println("You have only " + newUser.getCoins() + " remaining");

        while(newUser.getArmy().size() < 5){
            System.out.print("Enter the name of the character from the categories above: ");
            String characterName = scanner.next().toLowerCase();
            if (archers.contains(characterName)){
                newUser.addTroopToArmy(new Archer(characterName));
            }
            else if (knights.contains(characterName)){
                newUser.addTroopToArmy(new Knight(characterName));
            }
            else if (mages.contains(characterName)){
                newUser.addTroopToArmy(new Mage(characterName));
            }
            else if (healers.contains(characterName)){
                newUser.addTroopToArmy(new Healer(characterName));
            }
            else if (mythicalCreatures.contains(characterName)){
                newUser.addTroopToArmy(new MythicalCreature(characterName));
            }
            else {
                System.out.println("Enter a valid character name!!");
            }
            System.out.println("You have only " + newUser.getCoins() + " coins remaining");
            System.out.println();
        }

        System.out.println("PERFECT!! Now you have your own army");
        System.out.println();
        userList.add(newUser);
        userNames.add(userName);
        System.out.println("New profile creation DONE!!");
        printUserData(newUser);
        setCurrentUser(newUser);
    }

    private void loadProfile() {
        boolean validUsernameEntered = false;

        while (!validUsernameEntered) {
            System.out.println("Select a profile to load from below");
            for (User user : userList) {
                System.out.println(user.getUserName() + " " + user.getXp() + "Xp | ");
            }
            System.out.print("Enter the username of the profile you want to load: ");
            String userNameToLoad = scanner.next();

            for (User user : userList) {
                if (user.getUserName().equals(userNameToLoad)) {
                    System.out.println("Profile loaded successfully:");
                    printUserData(user);
                    setCurrentUser(user);
                    validUsernameEntered = true; // Valid username entered, exit the loop
                    break;
                }
            }

            if (!validUsernameEntered) {
                System.out.println("Profile with username '" + userNameToLoad + "' not found. Please try again.");
            }
        }

    }
    private void addEquipments(){
        System.out.println("Here's a detailed table about Equipments available.");
        System.out.println();
        Table.displayEquipmentTable();
        System.out.println("You currently have: ");
        for (Character character: currentUser.getArmy()){
            System.out.println("                 " + character.getName() + " with " + character.armourCount + " armour and "
                                + character.artefactCount + " artefact");
        }
        System.out.println("Select the character to enforce with equipment");
        System.out.println();
        System.out.print("ARCHER [enter 1], KNIGHT [enter 2], MAGE [enter 3], HEALER [enter 4], MYTHICAL CREATURE [ enter 5]");
        int choice = scanner.nextInt();
        System.out.print("Enter the name of the equipment: ");
        String equipment = scanner.next();
        switch (choice){
            case 1:

                currentUser.addEquipment();
        }

    }

    public User getCurrentUser(){
        return currentUser;
    }
    public void setCurrentUser(User user){
        this.currentUser = user;
    }

    private void printUserData(User user){
        System.out.println("Username: " + user.getUserName());
        System.out.println("UserID: " + user.getUserID());
        System.out.println("Coins Remaining: " + user.getCoins());
        System.out.println("Xp: " + user.getXp());
        if (user.getArmy() != null){
            for (Character character: user.getArmy()) {
                if (character instanceof Archer) {
                    System.out.println("Archer: " + character.getName());
                }else if (character instanceof Knight) {
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

    //private void
