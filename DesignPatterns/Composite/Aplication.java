package Composite;

import java.util.ArrayList;
import java.util.List;

// The component interface declares common operations for both
// simple and complex objects of a composition.
interface Graphic {
    void draw();
    void move(int x,int y);
}

// Usually, it's leaf
// objects that do the actual work, while composite objects only
//// delegate to their sub-components.
class Dot implements Graphic{
    protected int x, y;

    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a dot at (" + x + ", " + y + ")");
    }

    @Override
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }
}

class Circle extends Dot{
    private int radius;

    public Circle(int x, int y, int radius){
        super(x, y);
        this.radius = radius;
    }
    @Override
    public void draw() {
        System.out.println("Drawing a circle at (" + x + ", " + y + ") with radius " + radius);
    }
}

// Composite objects usually delegate the actual
// work to their children and then "sum up" the result.
class CompoundGraphic implements Graphic{
    private List<Graphic> children = new ArrayList<>();

    // A composite object can add or remove other components
    // (both simple or complex) to or from its child list.
    public void add(Graphic child) {
        children.add(child);
    }

    public void remove(Graphic child) {
        children.remove(child);
    }

    // composite's children pass these calls to their own
    // children and so forth, the whole object tree is traversed
    // as a result.
    @Override
    public void draw() {
        for (Graphic child : children) {
            child.draw();
        }
        System.out.println("Drawing a compound graphic with " + children.size() + " children");
    }

    @Override
    public void move(int x, int y) {
        for (Graphic child : children) {
            child.move(x, y);
        }
    }
}


// The client code works with all the components via their base interface
class ImageCreator{
    private CompoundGraphic all;

    public void load() {
        all = new CompoundGraphic();
        all.add(new Dot(1, 2));
        all.add(new Circle(5, 3, 10));
    }

    // Combine selected components into one complex composite component.
    public void groupSelected(List<Graphic> components) {
        CompoundGraphic group = new CompoundGraphic();
        for (Graphic component : components) {
            group.add(component);
            all.remove(component);
        }
        all.add(group);
        // All components will be drawn.
        all.draw();
    }

    public void draw() {
        if (all != null) {
            all.draw();
        }
    }
}

public class Aplication {
    public static void main(String[] args){
        ImageCreator editor = new ImageCreator();
        editor.load();

        List<Graphic> selectedComponents = new ArrayList<>();
        selectedComponents.add(new Dot(7, 8));
        selectedComponents.add(new Circle(9, 10, 15));

        // Group selected components
        editor.groupSelected(selectedComponents);

        // Draw all components
        editor.draw();
    }
}
