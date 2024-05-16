package Flyweight;

// This is used to build a character in a game
class PolygonMap {
// consider this class as having a complex data structure
// that store points representing a polygon map of a
// game character
}
// This is a "person" from the video game "The Sims"
interface Sim {
    // This is the extrinsic operation. That is, the operation
// requiring data which is not shared across the similar
//set of objects
    public void render(String pose);
    public void dressUp(String dressType, String dressColor);
}
// This is the Sim with "heavy data object"
class GenericSim implements Sim {
    // These are the Intrinsic States
    private PolygonMap body;
    private String type;
    GenericSim(String filename, String type) {
// type is a Man, Woman, Child, etc
        this.type = type;
// load the polygon map data from a file
        System.out.println("Loading a "+type+" Sim");
    }
    public void render(String pose) {
// pose is Standing, Sitting, Running, etc)
        System.out.println("Rendering a "+type+" Sim "+pose);
    }
    public void dressUp(String dressType, String dressColor) {
        System.out.println("Dressing up in"+dressColor+" "+dressType);
    }
}

// This is the Sim without "Heavey Data Object"
class ShadowSim implements Sim {
    // Refer to the Class Diagram to understand this example
    public void render(String pose) {
// Render the shadow; as an example.
// It is assumed that these operations do not require the
// heavy duty data object
        System.out.println("Rendering a "+pose);
    }
    public void dressUp(String dressType, String dressColor) {
        System.out.println("Dressing up in"+dressColor+" "+dressType);
    }
}
class SimFactory {
    // The Factory that create Sims can use a HashMap/HashTable.
// The code in this class implements such a data structure
// using two arrays.
    private String [] livetypes;
    private Sim [] livesims;
    private int size;
    private int count;
    SimFactory(int size) {
        this.size = size;
        livetypes = new String[size];
        livesims = new Sim[size];
        count = 0;
    }
    public Sim makeSim(String type) {
        int idx = 0; boolean found = false; String filename = type;
        for(idx=0; idx<count; idx++) {
            if (livetypes[idx].equals(type))
                found = true;
                break;
        }
        if((!found) && (count<size)) {
            livetypes[count] = type; filename.concat(".data");
            livesims[count] = new GenericSim(filename,type);
            idx= count; count++; found = true;
        }
        if(found)
            return livesims[idx];
        else
            return null;
    }
}

public class TheSimsGame {
    public static void main(String [] args) {
        SimFactory simfactory = new SimFactory(5);
        System.out.println("Creating Alice");
        Sim alice = simfactory.makeSim("Woman");
        System.out.println("Creating Bob");
        Sim bob = simfactory.makeSim("Man");
        System.out.println("Creating Charlie");
        Sim charlie = simfactory.makeSim("Woman");
        System.out.println("Creating Dave");
        Sim dave = simfactory.makeSim("Man");
        System.out.println("Rendering Alice");
        alice.render("Standing");
        System.out.println("Rendering Dave");
        dave.render("Sitting");
        System.out.println("Dress up Alice");
        alice.dressUp("Gown","Flower Pattern");
    }
}