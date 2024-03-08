public class Armour extends Equipment{
    public Armour(String name){
        super(name, 0, 0, 0, 0, 0);
        createArmour(name);
    }
    private void createArmour(String name){
        switch (name){
            case "Chainmail":
                this.setPrice(70);
                this.setAttack(0);
                this.setDefence(1);
                this.setHealth(0);
                this.setSpeed(-1);
            case "Regalia":
                this.setPrice(105);
                this.setAttack(0);
                this.setDefence(1);
                this.setHealth(0);
                this.setSpeed(0);
            case "Fleece":
                this.setPrice(150);
                this.setAttack(0);
                this.setDefence(2);
                this.setHealth(1);
                this.setSpeed(-1);
        }
    }
}
