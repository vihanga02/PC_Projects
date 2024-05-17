package ProtoType;

import java.util.ArrayList;

// Base prototype.
abstract class Shape{
    int x;
    int y;
    private String color;

    Shape(){}

    // The prototype constructor. A fresh object is initialized
    // with values from the existing object.
    Shape(Shape shape){
        this.color = shape.color;
        this.x = shape.x;
        this.y = shape.y;
    }

    // The clone operation returns one of the Shape subclasses.
    public abstract Shape clone();
}

// Concrete prototype
class Rectangle extends Shape{
    private int width,height;
    Rectangle(){}

    // A parent constructor call is needed to copy private
    // fields defined in the parent class.
    Rectangle(Rectangle rectangle){
        super(rectangle);
        this.height = rectangle.height;
        this.width = rectangle.width;
    }

    // The cloning method creates a new object
    // in one go by calling the constructor of the current class and
    // passing the current object as the constructor's argument.
    @Override
    public Shape clone() {
        return new Rectangle(this);
    }
}

class Circle extends Shape{
    int r;
    Circle(){}
    Circle(Circle circle){
        super(circle);
        this.r = circle.r;
    }

    @Override
    public Shape clone() {
        return new Circle(this);
    }
}

// Somewhere in the client code.
class Application {
    ArrayList<Shape> shape = new ArrayList<>();
    Application(){
        Circle circle = new Circle();
        circle.x = 10;
        circle.y = 10;
        circle.r = 20;
        shape.add(circle);

        Circle anotherCircle = (Circle) circle.clone();
        shape.add(anotherCircle);
        // The `anotherCircle` variable contains an exact copy
        // of the `circle` object.

        Rectangle rectangle = new Rectangle();
        rectangle.x = 10;
        rectangle.y = 20;
        shape.add(rectangle);
    }

    public void businessLogic() {
        ArrayList<Shape> shapesCopy = new ArrayList<>();

        // For instance, we don't know the exact elements in the
        // shapes array. All we know is that they are all
        // shapes. But thanks to polymorphism, when we call the
        // `clone` method on a shape the program checks its real
        // class and runs the appropriate clone method defined
        // in that class.
        for (Shape shape1: shape){
            shapesCopy.add(shape1.clone());
        }
    }

    public static void main (String [] args){
        Application application = new Application();
        application.businessLogic();
    }
}
