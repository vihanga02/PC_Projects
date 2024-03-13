public class Armour extends Equipment{
    public Armour(String name){
        super(name);
        createArmour(name);
    }
    private void createArmour(String name){
        switch (name){
            case "chainmail":
                this.setPrice(70);
                this.setAttack(0);
                this.setDefence(1);
                this.setHealth(0);
                this.setSpeed(-1);
                break;
            case "regalia":
                this.setPrice(105);
                this.setAttack(0);
                this.setDefence(1);
                this.setHealth(0);
                this.setSpeed(0);
                break;
            case "rleece":
                this.setPrice(150);
                this.setAttack(0);
                this.setDefence(2);
                this.setHealth(1);
                this.setSpeed(-1);
                break;
        }
    }
}
