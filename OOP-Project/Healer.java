public class Healer extends Character {
    public Healer(String name){
        super(name);
        this.createArcher(name);
    }
    private void createArcher(String name){
        switch (name){
            case "Soother":
                this.setPrice(95);
                this.setAttack(10);
                this.setDefence(8);
                this.setHealth(9);
                this.setSpeed(6);
                this.setCharacterType("Sunchild");
                break;
            case "Medic":
                this.setPrice(125);
                this.setAttack(12);
                this.setDefence(9);
                this.setHealth(10);
                this.setSpeed(7);
                this.setCharacterType("Highlander");
                break;
            case "Alchemist":
                this.setPrice(150);
                this.setAttack(13);
                this.setDefence(13);
                this.setHealth(13);
                this.setSpeed(13);
                this.setCharacterType("Marshlander");
                break;
            case "Saint":
                this.setPrice(200);
                this.setAttack(16);
                this.setDefence(14);
                this.setHealth(17);
                this.setSpeed(9);
                this.setCharacterType("Mystic");
                break;
            case "Lightbringer":
                this.setPrice(260);
                this.setAttack(17);
                this.setDefence(15);
                this.setHealth(19);
                this.setSpeed(12);
                this.setCharacterType("Sunchild");
                break;
        }
    }
}
