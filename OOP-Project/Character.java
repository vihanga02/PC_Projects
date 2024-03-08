public abstract class Character {
    private String name;
    private double price;
    private double attack;
    private double defence;
    private double health;
    private double speed;
    private String characterType;

    public Character(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public double getAttack() {
        return attack;
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

    public void setAttack(double attack) {
        this.attack = attack;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String getCharacterType() {
        return characterType;
    }

    public void setCharacterType(String characterType) {
        if (this.characterType == null) {
            this.characterType = characterType;
        }
    }

}
