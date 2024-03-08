public class Knight extends Characters{
    public Knight(String name){
        super(name, 0, 0, 0, 0, 0);
        this.createArcher(name);
    }
    private void createArcher(String name){
        switch (name){
            case "Squire":
                this.setPrice(85);
                this.setAttac(8);
                this.setDefence(9);
                this.setHealth(7);
                this.setSpeed(8);
            case "Cavalier":
                this.setPrice(110);
                this.setAttac(10);
                this.setDefence(12);
                this.setHealth(7);
                this.setSpeed(10);
            case "Templar":
                this.setPrice(155);
                this.setAttac(14);
                this.setDefence(16);
                this.setHealth(12);
                this.setSpeed(12);
            case "Zoro":
                this.setPrice(180);
                this.setAttac(17);
                this.setDefence(16);
                this.setHealth(13);
                this.setSpeed(14);
            case "Swiftblade":
                this.setPrice(250);
                this.setAttac(18);
                this.setDefence(20);
                this.setHealth(17);
                this.setSpeed(13);
        }
    }

}
