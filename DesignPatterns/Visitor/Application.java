package Visitor;

import java.util.ArrayList;
import java.util.List;

interface Shape {
    void move(int x, int y);
    void draw();
    void accept(Visitor v);
}

// Each concrete element class must implement the `accept`
// method in such a way that it calls the visitor's method that
// corresponds to the element's class.
class Dot implements Shape {
    private int id;
    private int x, y;

    // Constructors, getters, and setters

    @Override
    public void move(int x, int y) {
        // Move dot to a new position
    }

    @Override
    public void draw() {
        // Draw the dot
    }

    @Override
    public void accept(Visitor v) {
        v.visitDot(this);
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
class Circle implements Shape {
    private int id;
    private int x, y;
    private int radius;

    // Constructors, getters, and setters

    @Override
    public void move(int x, int y) {
        // Move circle to a new position
    }

    @Override
    public void draw() {
        // Draw the circle
    }

    @Override
    public void accept(Visitor v) {
        v.visitCircle(this);
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }
}

class Rectangle implements Shape {
    private int id;
    private int x, y;
    private int width, height;

    // Constructors, getters, and setters

    @Override
    public void move(int x, int y) {
        // Move rectangle to a new position
    }

    @Override
    public void draw() {
        // Draw the rectangle
    }

    @Override
    public void accept(Visitor v) {
        v.visitRectangle(this);
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}


class CompoundShape implements Shape {
    private int id;
    private List<Shape> children = new ArrayList<>();

    // Constructors, getters, and setters

    @Override
    public void move(int x, int y) {
        // Move compound shape to a new position
    }

    @Override
    public void draw() {
        // Draw the compound shape
    }

    @Override
    public void accept(Visitor v) {
        v.visitCompoundShape(this);
    }

    public int getId() {
        return id;
    }

    public List<Shape> getChildren() {
        return children;
    }
}

// The Visitor interface declares a set of visiting methods that
// correspond to element classes.
interface Visitor {
    void visitDot(Dot d);
    void visitCircle(Circle c);
    void visitRectangle(Rectangle r);
    void visitCompoundShape(CompoundShape cs);
}

// Concrete visitors implement several versions of the same
// algorithm, which can work with all concrete element classes.
class XMLExportVisitor implements Visitor {
    @Override
    public void visitDot(Dot d) {
        // Export the dot's ID and center coordinates.
        System.out.println("<dot id='" + d.getId() + "' x='" + d.getX() + "' y='" + d.getY() + "'/>");
    }

    @Override
    public void visitCircle(Circle c) {
        // Export the circle's ID, center coordinates and radius.
        System.out.println("<circle id='" + c.getId() + "' x='" + c.getX() + "' y='" + c.getY() + "' radius='" + c.getRadius() + "'/>");
    }

    @Override
    public void visitRectangle(Rectangle r) {
        // Export the rectangle's ID, left-top coordinates, width and height.
        System.out.println("<rectangle id='" + r.getId() + "' x='" + r.getX() + "' y='" + r.getY() + "' width='" + r.getWidth() + "' height='" + r.getHeight() + "'/>");
    }

    @Override
    public void visitCompoundShape(CompoundShape cs) {
        // Export the shape's ID as well as the list of its children's IDs.
        System.out.println("<compoundShape id='" + cs.getId() + "'>");
        for (Shape shape : cs.getChildren()) {
            shape.accept(this);
        }
        System.out.println("</compoundShape>");
    }
}
public class Application {
    private List<Shape> allShapes = new ArrayList<>();

    public void export() {
        XMLExportVisitor exportVisitor = new XMLExportVisitor();
        for (Shape shape : allShapes) {
            shape.accept(exportVisitor);
        }
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.allShapes.add(new Dot(/* initialize fields */));
        app.allShapes.add(new Circle(/* initialize fields */));
        app.allShapes.add(new Rectangle(/* initialize fields */));
        app.allShapes.add(new CompoundShape(/* initialize fields */));

        app.export();
    }
}
