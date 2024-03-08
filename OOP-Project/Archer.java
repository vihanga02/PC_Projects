public class Archer extends Characters{
    private String name;
    public Archer(String name){
        this.createArcher(name);
    }
    public Archer(String name, double price, double attac, double defence, double health, double speed){
        super(name, price, attac, defence, health, speed);
    }
    private void createArcher(String name){
        switch (name){
            case "Shooter":
                new Archer(name, 80, 11, 4, 6, 9);
        }
    }
}
