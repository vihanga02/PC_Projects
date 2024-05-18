package Template;

// abstract class defining the template methode
abstract class BeverageMaker{
    public void makeBeverage(){
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    // Abstract methods to be implemented by subclasses
    abstract void brew();
    abstract void addCondiments();

    // Common methods
    void boilWater() {
        System.out.println("Boiling water");
    }

    void pourInCup() {
        System.out.println("Pouring into cup");
    }
}

class TeaMaker extends BeverageMaker{
    // Implementing abstract methods
    @Override
    void brew() {
        System.out.println("Steeping the tea");
    }

    @Override
    void addCondiments() {
        System.out.println("Adding lemon");
    }
}

class CoffeeMaker extends BeverageMaker{
    // Implementing abstract methods
    @Override
    void brew() {
        System.out.println("Dripping coffee through filter");
    }

    @Override
    void addCondiments() {
        System.out.println("Adding sugar and milk");
    }
}

public class Application {
    public static void main(String[] args){
        System.out.println("Making tea....");
        BeverageMaker tea = new TeaMaker();
        tea.makeBeverage();

        System.out.println("\nMaking coffee:");
        BeverageMaker coffeeMaker = new CoffeeMaker();
        coffeeMaker.makeBeverage();
    }
}
