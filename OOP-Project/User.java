import java.util.*;

public class User {
    private static int userCount = 1;
    private String name;
    private final String userName;
    private double coins;
    private final String userID;
    private double xp;
    private final String homeGround;
    private ArrayList<Character> myArmy = new ArrayList<>();
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
                // decrease the total coins
                coins -= troop.getPrice();
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
        else {
            // sell the current troop
            myArmy.remove(oldTroop);
            coins += oldTroop.getPrice();
            System.out.println(oldTroop.getName() + " is sold");
        }
        // add the new troop to the army
        addTroopToArmy(newTroop);
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

    public void addEquipment(Character troop, Equipment equipment) {
        // checks whether user have enough money
        if (coins - equipment.getPrice() > 0){
            if (equipment instanceof Artefact) {
                // checks whether user already have an artefact
                if (troop.getArtefact() == null) {
                    troop.setArtefact((Artefact) equipment);
                }
            } else if (equipment instanceof Armour) {
                // checks whether user already have an artefact
                if (troop.getArmour() == null) {
                    troop.setArmour((Armour) equipment);
                }
            }
            // set the new price of the troop after adding an equipment
            troop.setPrice(troop.getPrice() * 1.2);
            System.out.println("Equipment of Type " + equipment.getClass().toString() +
                    " is added to " + troop.getName());
        }
        else {
            System.out.println("Not Enough Coins");
        }
    }

    public ArrayList<Character> getArmy(){
        return myArmy;
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
        this.coins = coins;
    }
    public void setXp(double xp) {
        this.xp = xp;
    }

//

}

