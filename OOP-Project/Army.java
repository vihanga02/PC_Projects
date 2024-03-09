import java.util.Vector;

public class Army {
    private Vector<Character> troops;

    public Army() {
        troops = new Vector<>();
    }

    public void addTroop(Character troop) {
        for (Character existingTroop : troops) {
            if (existingTroop.getClass().equals(troop.getClass())) {
                System.out.println(existingTroop + "Already Exists in Army");
                return;
            }
        }
        troops.add(troop);
    }

    public Vector<Character> getArmy(){
        return troops;
    }

    public void removeTroop(Character troop){
        troops.remove(troop);
    }
}
