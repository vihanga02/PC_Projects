public class MythicalCreature extends Character {
    public MythicalCreature(String name){
        super(name);
        this.createArcher(name);
    }
    private void createArcher(String name){
        switch (name){
            case "Dragon":
                this.setPrice(120);
                this.setAttack(12);
                this.setDefence(14);
                this.setHealth(15);
                this.setSpeed(8);
                this.setCharacterType("Sunchild");
                break;
            case "Basilisk":
                this.setPrice(165);
                this.setAttack(15);
                this.setDefence(11);
                this.setHealth(10);
                this.setSpeed(12);
                this.setCharacterType("Marshlander");
                break;
            case "Hydra":
                this.setPrice(205);
                this.setAttack(12);
                this.setDefence(16);
                this.setHealth(15);
                this.setSpeed(11);
                this.setCharacterType("Marshlander");
                break;
            case "Phoenix":
                this.setPrice(275);
                this.setAttack(17);
                this.setDefence(13);
                this.setHealth(17);
                this.setSpeed(19);
                this.setCharacterType("Sunchild");
                break;
            case "Pegasus":
                this.setPrice(340);
                this.setAttack(14);
                this.setDefence(18);
                this.setHealth(20);
                this.setSpeed(20);
                this.setCharacterType("Mystic");
                break;
        }
    }
}
