public abstract class Characters {
    private String name;
    private double price;
    private double attac;
    private double defence;
    private double health;
    private double speed;

    public Characters(String name, double price, double attac, double defence, double health, double speed){
        this.name = name;
        this.price = price;
        this.attac = attac;
        this.defence = defence;
        this.health = health;
        this.speed = speed;
    }
    public Characters(String name){
        this.name = name;
    }

    protected Characters() {
    }

    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public double getAttac() {
        return attac;
    }
    public double getDefence() {
        return defence;
    }

    public double getHealth() {
        return health;
    }

    public double getSpeed() {
        return speed;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDefence(double defence) {
        this.defence = defence;
    }

    public void setAttac(double attac) {
        this.attac = attac;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
