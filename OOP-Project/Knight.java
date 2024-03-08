public class Knight extends Character {
    public Knight(String name){
        super(name);
        this.createArcher(name);
    }
    private void createArcher(String name){
        switch (name){
            case "Squire":
                this.setPrice(85);
                this.setAttack(8);
                this.setDefence(9);
                this.setHealth(7);
                this.setSpeed(8);
                this.setCharacterType("Marshlander");
            case "Cavalier":
                this.setPrice(110);
                this.setAttack(10);
                this.setDefence(12);
                this.setHealth(7);
                this.setSpeed(10);
                this.setCharacterType("Highlander");
            case "Templar":
                this.setPrice(155);
                this.setAttack(14);
                this.setDefence(16);
                this.setHealth(12);
                this.setSpeed(12);
                this.setCharacterType("Sunchild");
            case "Zoro":
                this.setPrice(180);
                this.setAttack(17);
                this.setDefence(16);
                this.setHealth(13);
                this.setSpeed(14);
                this.setCharacterType("Highlander");
            case "Swiftblade":
                this.setPrice(250);
                this.setAttack(18);
                this.setDefence(20);
                this.setHealth(17);
                this.setSpeed(13);
                this.setCharacterType("Marshlander");
        }
    }

}
