package AbstractFactory;

import java.awt.*;
import java.util.Scanner;

// The abstract factory interface declares a set of methods that
// return different abstract products.
interface GUIFactory {
        Buttons createButton();
        CheckBox createCheckbox();
}

// concrete factory class's methods are returning corresponding product families of buttons and checkboxes
class WinFactory implements GUIFactory{
    public Buttons createButton(){
        return new WinButton();
    }

    @Override
    public CheckBox createCheckbox() {
        return new WinBox();
    }
}

class MacFactory implements GUIFactory{

    @Override
    public Buttons createButton() {
        return new MacButton();
    }

    @Override
    public CheckBox createCheckbox() {
        return new MacBox();
    }
}

// Each distinct product of a product family should have a base
// interface. All variants of the product must implement this
// interface.
interface Buttons{
    void paint();
}

class WinButton implements Buttons{

    @Override
    public void paint() {
        System.out.println("This is a Windows button");
    }
}

class MacButton implements Buttons{

    @Override
    public void paint() {
        System.out.println("This is a Mac Button.");
    }
}

// Here's the base interface of another product. All products
// can interact with each other, but proper interaction is
// possible only between products of the same concrete variant.
interface CheckBox{
    void paint();
}

class WinBox implements CheckBox{
    @Override
    public void paint() {
        System.out.println("This is a Windows Check Box.");
    }
}

class MacBox implements CheckBox{
    @Override
    public void paint() {
        System.out.println("This is a Mac Check Box.");
    }
}

// The client code works with factories and products only
// through abstract types: GUIFactory, Button and Checkbox. This
// lets you pass any factory or product subclass to the client
// code without breaking it.
class Application{
    private Buttons buttons;
    private GUIFactory guiFactory;

    Application(GUIFactory guiFactory){
        this.guiFactory = guiFactory;
    }

    void createUI(){
        this.buttons = guiFactory.createButton();
    }

    void paint(){
        buttons.paint();
    }
}

// The application picks the factory type depending on the
// current configuration or environment settings and creates it
// at runtime (usually at the initialization stage).
public class ApplicationConfigurator {
    public static void main(String[] args){
        GUIFactory factory;

        Scanner scanner = new Scanner(System.in);
        String os = scanner.next();

        if (os.equals("windows")){
            factory = new WinFactory();
        }
        else{
            factory = new MacFactory();
        }
        scanner.close();

        Application application = new Application(factory);
        application.createUI();
        application.paint();
    }
}
