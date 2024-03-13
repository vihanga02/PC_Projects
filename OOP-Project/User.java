import java.io.Serializable;
import java.util.*;

public class User implements Serializable {
    private static int userCount = 1;
    private String name;
    private final String userName;
    private double coins;
    private final String userID;
    private double xp;
    private final String homeGround;
    private ArrayList<Character> myArmy = new ArrayList<>();
    private Map<String, Character> myArmyMap = new HashMap<>();
    public User(String name, String userName, String homeGround){
        this.name = name;
        this.userName = userName;
        this.homeGround = homeGround;
        this.coins = 500;
        this.xp = 0;
        this.userID = userName.substring(0, Math.min(userName.length(), 2)).toUpperCase() +
                String.format("%03d", userCount);
        userCount++;
    }
    public void addTroopToArmy(Character troop) {
        // checks whether troop is already in the army
        if (isAbsent(troop)){
            // checks whether user have enough money
            if ((coins - troop.getPrice()) > 0){
                myArmy.add(troop);
                myArmyMap.put(troop.getClass().getName(), troop);
                // decrease the total coins
                this.setCoins(-troop.getPrice());
                System.out.println("You Purchased a new " + troop.getName().toUpperCase() + " of type " + troop.getClass().getName());
            }
            else {
                System.out.println("Not Enough Coins");
            }
        }
        else {
            System.out.println("Troop of type " + troop.getClass().getName() + " already exists in your army" );
        }
    }
    public void replaceTroop(Character oldTroop, Character newTroop){
        // checks whether troop is already in the army
        if (isAbsent(oldTroop)){
            System.out.println("Troop you want to sell is not in your army");
        }
        else if ((coins + oldTroop.getPrice() - newTroop.getPrice()) > 0) {
            // sell the current troop
            myArmy.remove(oldTroop);
            this.setCoins(oldTroop.getPrice());
            System.out.println(oldTroop.getName() + " is sold");
            addTroopToArmy(newTroop);
        }
    }
    private boolean isAbsent(Character troop) {
        // checks whether troop is already in the army
        for (Character existingTroop : myArmy) {
            if (existingTroop.getClass().equals(troop.getClass())) {
                return false;
            }
        }
        return true;
    }
    public boolean addEquipment(Character troop, Equipment equipment) {
        // Check if the user has enough coins to purchase the equipment
        if (coins - equipment.getPrice() >= 0) {
            // Check if the equipment is an Artefact
            if (equipment instanceof Artefact) {
                // Check if the troop already has an artefact
                if (troop.getArtefact() == null) {
                    // Add the artefact to the troop
                    troop.setArtefact((Artefact) equipment);
                    // Deduct the equipment price from the user's coins
                    //coins -= equipment.getPrice();
                    this.setCoins(-equipment.getPrice());
                    // Update the troop's price
                    troop.setPrice(troop.getPrice() * 1.2);
                    return true; // Equipment successfully added
                } else {
                    System.out.println("Equipment not added. Troop already has an artefact.");
                }
            } else if (equipment instanceof Armour) {
                // Check if the troop already has an armour
                if (troop.getArmour() == null) {
                    // Add the armour to the troop
                    troop.setArmour((Armour) equipment);
                    // Deduct the equipment price from the user's coins
                    //coins -= equipment.getPrice();
                    this.setCoins(-equipment.getPrice());
                    // Update the troop's price
                    troop.setPrice(troop.getPrice() * 1.2);
                    return true; // Equipment successfully added
                } else {
                    System.out.println("Equipment not added. Troop already has an armour.");
                }
            }
        } else {
            System.out.println("Not enough coins to purchase the equipment.");
        }
        // If the equipment was not added, return false
        return false;
    }
    public ArrayList<Character> getArmy(){
        return myArmy;
    }
    public Map<String, Character> getMyArmyMap() {
        return myArmyMap;
    }
    public String getName() {
        return name;
    }
    public String getUserName() {
        return userName;
    }
    public double getCoins() {
        return coins;
    }
    public String getUserID() {
        return userID;
    }
    public double getXp() {
        return xp;
    }
    public static int getUserCount() {
        return userCount;
    }
    public String getHomeGround() {
        return homeGround;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCoins(double coins) {
        this.coins += coins;
    }
    public void setXp(double xp) {
        this.xp = xp;
    }
}