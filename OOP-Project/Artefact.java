public class Artefact extends Equipment{
    public Artefact(String name) {
        super(name, 0, 0, 0, 0, 0);
    }
    private void createArmour(String name){
        switch (name){
            case "Excalibur":
                this.setPrice(150);
                this.setAttack(2);
                this.setDefence(0);
                this.setHealth(0);
                this.setSpeed(0);
            case "Amulet":
                this.setPrice(200);
                this.setAttack(1);
                this.setDefence(-1);
                this.setHealth(1);
                this.setSpeed(1);
            case "Fleece":
                this.setPrice(210);
                this.setAttack(2);
                this.setDefence(1);
                this.setHealth(-1);
                this.setSpeed(-1);
        }
    }
}
