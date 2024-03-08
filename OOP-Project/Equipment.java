public abstract class Equipment {
    private String name;
    private double price;
    private double attack;
    private double defence;
    private double health;
    private double speed;

    public Equipment(String name, double price, double attack, double defence, double health, double speed){
        this.name = name;
        this.price = price;
        this.attack = attack;
        this.defence = defence;
        this.health = health;
        this.speed = speed;
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
        return price;
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
