public class Mage extends Character {
    public Mage(String name){
        super(name, 0, 0, 0, 0, 0);
        this.createArcher(name);
    }
    private void createArcher(String name){
        switch (name){
            case "Warlock":
                this.setPrice(100);
                this.setAttack(12);
                this.setDefence(7);
                this.setHealth(10);
                this.setSpeed(12);
            case "Illusionist":
                this.setPrice(120);
                this.setAttack(13);
                this.setDefence(8);
                this.setHealth(12);
                this.setSpeed(14);
            case "Enchanter":
                this.setPrice(160);
                this.setAttack(16);
                this.setDefence(10);
                this.setHealth(13);
                this.setSpeed(16);
            case "Conjurer":
                this.setPrice(195);
                this.setAttack(18);
                this.setDefence(15);
                this.setHealth(14);
                this.setSpeed(12);
            case "Eldritch":
                this.setPrice(270);
                this.setAttack(19);
                this.setDefence(17);
                this.setHealth(18);
                this.setSpeed(14);
        }
    }
}
