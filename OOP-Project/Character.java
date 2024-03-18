import java.io.Serializable;

public abstract class Character implements Serializable, Cloneable {
    private String name;
    private double price;
    private double attack;
    private double defence;
    private double health;
    private double speed;
    private String characterType;
    private Artefact artefact;
    public int artefactCount = 0;
    private Armour armour;
    public int armourCount = 0;

    private static final long serialVersionUID = 8787261772055597799L;

    public Character(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public double getAttack() {
        return attack;
    }
    public double getDefence() {
        return defence;
    }

    public double getHealth() {
        return health;
    }

    public double getSpeed() {
        return speed;
    }
    public Artefact getArtefact() {
        return artefact;
    }

    public Armour getArmour() {
        return armour;
    }

    public void setHealth(double health) {
        if (health <= 0){
            this.health = 0;
        }
        else {
            this.health = (double) Math.round(health * 100) /100;
        }
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDefence(double defence) {
        this.defence = defence;
    }

    public void setAttack(double attack) {
        this.attack = attack;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
    public void setArtefact(Artefact artefact) {
        this.artefact = artefact;
        artefactCount++;
    }
    public void setArmour(Armour armour) {
        this.armour = armour;
        armourCount++;
    }


        @Override
        public Character clone() {
            try {
                Character cloned = (Character) super.clone();
                // copy fields if needed
                return cloned;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(); // Can't happen
            }
        }


    public String getCharacterType() {
        return characterType;
    }
    public void setCharacterType(String characterType) {
        if (this.characterType == null) {
            this.characterType = characterType;
        }
    }
}
