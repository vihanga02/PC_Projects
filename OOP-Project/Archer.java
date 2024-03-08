public class Archer extends Characters{
    private String name;
    public Archer(String name){
        super(name, 0, 0, 0, 0, 0);
        this.createArcher(name);
    }
    private void createArcher(String name){
        switch (name){
            case "Shooter":
                this.setPrice(80);
                this.setAttack(11);
                this.setDefence(4);
                this.setHealth(6);
                this.setSpeed(9);
            case "Ranger":
                this.setPrice(115);
                this.setAttack(14);
                this.setDefence(5);
                this.setHealth(8);
                this.setSpeed(10);
            case "Sunfire":
                this.setPrice(160);
                this.setAttack(15);
                this.setDefence(5);
                this.setHealth(8);
                this.setSpeed(14);
            case "Saggitarius":
                this.setPrice(230);
                this.setAttack(18);
                this.setDefence(7);
                this.setHealth(12);
                this.setSpeed(17);
            case "Zing":
                this.setPrice(200);
                this.setAttack(16);
                this.setDefence(9);
                this.setHealth(11);
                this.setSpeed(14);
        }
    }
}
