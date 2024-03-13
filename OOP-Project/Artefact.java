public class Artefact extends Equipment{
    public Artefact(String name) {
        super(name);
    }
    private void createArmour(String name){
        switch (name){
            case "excalibur":
                this.setPrice(150);
                this.setAttack(2);
                this.setDefence(0);
                this.setHealth(0);
                this.setSpeed(0);
                break;
            case "amulet":
                this.setPrice(200);
                this.setAttack(1);
                this.setDefence(-1);
                this.setHealth(1);
                this.setSpeed(1);
                break;
            case "fleece":
                this.setPrice(210);
                this.setAttack(2);
                this.setDefence(1);
                this.setHealth(-1);
                this.setSpeed(-1);
                break;
        }
    }
}
