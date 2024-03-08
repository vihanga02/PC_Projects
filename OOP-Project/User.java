import java.util.Vector;

public class User {
    private static int userCount = 1;
    private String name;
    private final String userName;
    private double coins;
    private final String userID;
    private double xp;
    private final String homeGround;
    private Vector<Character> myArmy;
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
        if (!isAbsent(troop)){
            if ((coins - troop.getPrice()) > 0){
                myArmy.add(troop);
                coins -= troop.getPrice();
                System.out.println("You Purchased a New" + troop.getName());
            }
            else {
                System.out.println("Not Enough Coins");
            }
        }
        else {
            System.out.println("Troop of type " + troop.getClass().getName() + " Already Exists in Your Army" );
        }

    }

    public void replaceTroop(Character oldTroop, Character newTroop){
        if (isAbsent(oldTroop)){
            System.out.println("Troop You Want to Sell is not in Your Army");
        }
        else {
            sellTroop(oldTroop);
            System.out.println(oldTroop.getName() + " is Sold");
        }
        addTroopToArmy(newTroop);
    }

    private void sellTroop(Character troop) {
        if (!isAbsent(troop)){
            myArmy.remove(troop);
            coins += troop.getPrice();
        }
    }

    private boolean isAbsent(Character troop) {
        for (Character existingTroop : myArmy) {
            if (existingTroop.getClass().equals(troop.getClass())) {
                return false;
            }
        }
        return true;
    }

    public void addEquipment(){
        return;
    }
    public Vector<Character> getArmy(){
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



}

