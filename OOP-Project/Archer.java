public class Archer extends Character {
    private String name;
    public Archer(String name){
        super(name);
        this.createArcher(name);
    }
    private void createArcher(String name){
        switch (name){
            case "shooter":
                this.setPrice(80);
                this.setAttack(11);
                this.setDefence(4);
                this.setHealth(6);
                this.setSpeed(9);
                this.setCharacterType("Highlander");
                break;
            case "ranger":
                this.setPrice(115);
                this.setAttack(14);
                this.setDefence(5);
                this.setHealth(8);
                this.setSpeed(10);
                this.setCharacterType("Highlander");
                break;
            case "sunfire":
                this.setPrice(160);
                this.setAttack(15);
                this.setDefence(5);
                this.setHealth(8);
                this.setSpeed(14);
                this.setCharacterType("SunChild");
                break;
            case "saggitarius":
                this.setPrice(230);
                this.setAttack(18);
                this.setDefence(7);
                this.setHealth(12);
                this.setSpeed(17);
                this.setCharacterType("Mystic");
                break;
            case "zing":
                this.setPrice(200);
                this.setAttack(16);
                this.setDefence(9);
                this.setHealth(11);
                this.setSpeed(14);
                this.setCharacterType("SunChild");
                break;
        }
    }

}
