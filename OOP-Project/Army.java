import java.util.Vector;

public class Army {
    private Vector<Character> troops; // Private variable to store the troops in the Army.

    // Constructor for creating an Army object.
    public Army() {
        troops = new Vector<>(); // Initialize the troops Vector.
    }

    // Method to add a troop to the Army.
    public void addTroop(Character troop) {
        // Loop through existing troops in the Army.
        for (Character existingTroop : troops) {
            // Check if the troop being added has the same class as any existing troop.
            if (existingTroop.getClass().equals(troop.getClass())) {
                System.out.println(existingTroop + "Already Exists in Army"); // Print a message if a troop of the same class already exists.
                return; // Exit the method.
            }
        }
        troops.add(troop); // Add the troop to the Army if no troop of the same class exists.
    }

    // Method to get the troops in the Army.
    public Vector<Character> getArmy(){
        return troops; // Return the troops in the Army.
    }

    // Method to remove a troop from the Army.
    public void removeTroop(Character troop){
        troops.remove(troop); // Remove the specified troop from the Army.
    }
}
