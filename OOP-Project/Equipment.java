import java.io.Serializable;

public abstract class Equipment implements Serializable {
    private final String name;
    private double price;
    private double attack;
    private double defence;
    private double health;
    private double speed;
    private static final long serialVersionUID = 7896356060120723561L;


    public Equipment(String name){
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAttack(double attack) {
        this.attack = attack;
    }

    public void setDefence(double defence) {
        this.defence = defence;
    }
    public void setHealth(double health){
        this.health = health;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return this.price;
    }
    public double getDefence() {
        return defence;
    }
    public double getAttack() {
        return attack;
    }
    public double getHealth() {
        return health;
    }
    public double getSpeed() {
        return speed;
    }
}