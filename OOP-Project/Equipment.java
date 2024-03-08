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

}
