package Flyweight;

import javax.lang.model.element.Name;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// The Flyweight class contains a portion of the state of a tree. These fields store values
// that are unique for each particular tree.
class TreeType{
    private String name;
    private String color;
    private String texture;

    public TreeType(String name, String color, String texture){
        this.color = color;
        this.name = name;
        this.texture = texture;
    }

    public void draw(Object canvas, int x, int y){
        System.out.println("Drawing tree of type " + name + " with color " + color + " and texture " + texture + " at (" + x + ", " + y + ")");
    }
}

// Flyweight factory decides whether to re-use existing
// flyweight or to create a new object.
class TreeFactory{
    private static Map<String, TreeType> treeTypeMap = new HashMap<>();

    public static TreeType getTreeType(String name, String color, String texture){
        String key = name + "-" + color + "-" + texture;
        TreeType treeType = treeTypeMap.get(key);
        if (treeType == null){
            treeType = new TreeType(name, color, texture);
            treeTypeMap.put(key, treeType);
        }
        return treeType;
    }
}

// The contextual object contains the extrinsic part of the tree state.
class Tree{
    private int x,y;
    private TreeType type;

    public Tree(int x, int y, TreeType type){
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void draw(Object canvas) {
        type.draw(canvas, this.x, this.y);
    }
}

// The Tree and the Forest classes are the flyweight's clients.
class Forest{
    private List<Tree> treeList = new ArrayList<>();

    public void plantTree(int x, int y, String name, String color, String texture) {
        TreeType type = TreeFactory.getTreeType(name, color, texture);
        Tree tree = new Tree(x, y, type);
        treeList.add(tree);
    }

    public void draw(Object canvas) {
        for (Tree tree : treeList) {
            tree.draw(canvas);
        }
    }
}

// Main class to test the Flyweight pattern implementation.
public class Application {
    public static void main(String[] args){
        Forest forest = new Forest();

        forest.plantTree(1, 2, "Oak", "Green", "Smooth");
        forest.plantTree(3, 4, "Pine", "Dark Green", "Rough");
        forest.plantTree(5, 6, "Oak", "Green", "Smooth");
        forest.plantTree(7, 8, "Pine", "Dark Green", "Rough");

        Object canvas = new Object();
        forest.draw(canvas);
    }
}
