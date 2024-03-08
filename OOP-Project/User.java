public class User {
    private String name;
    private final String userName;
    private double coins;
    private int id;
    private double xp;
    public User(String name, String userName,String homeGround){
        this.name = name;
        this.userName = userName;
        this.coins = 500;
        this.id = id;
        this.xp = xp;
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

    public int getId() {
        return id;
    }

    public double getXp() {
        return xp;
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
