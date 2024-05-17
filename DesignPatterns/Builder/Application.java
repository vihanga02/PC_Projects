package Builder;

class Car{
    private int seats;
    private Engine engine;
    private boolean gps;
    private boolean tripComputer;

    public void setSeats(int seats){
        this.seats = seats;
    }

    public void setGps(boolean gps) {
        this.gps = gps;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setTripComputer(boolean tripComputer) {
        this.tripComputer = tripComputer;
    }
}

class Manual{
    private int seats;
    private Engine engine;
    private boolean gps;
    private boolean tripComputer;

    public void setSeats(int seats){
        this.seats = seats;
    }

    public void setGps(boolean gps) {
        this.gps = gps;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setTripComputer(boolean tripComputer) {
        this.tripComputer = tripComputer;
    }
}

class Engine{

}

// The builder interface specifies methods for creating the
// different parts of the product objects.
interface Builder{
    void reset();
    void setSeats(int n);
    void setEngine(Engine engine);
    void setTripComputer(boolean tripComputer);
    void setGPS(boolean gps);
}

// The concrete builder classes follow the builder interface and
// provide specific implementations of the building steps. Your
// program may have several variations of builders, each
// implemented differently.
class CarBuilder implements Builder{

    private Car car;

    @Override
    public void reset() {
        this.car = new Car();
    }

    @Override
    public void setSeats(int n) {
        car.setSeats(n);
    }

    @Override
    public void setTripComputer(boolean tripComputer) {
        car.setTripComputer(tripComputer);
    }

    @Override
    public void setGPS(boolean gps) {
        car.setGps(gps);
    }

    @Override
    public void setEngine(Engine engine) {
        car.setEngine(engine);
    }

    // returning the product and reset current product
    public Car getProduct(){
        Car car1 = this.car;
        this.reset();
        return car1;
    }
}


class ManualBuilder implements Builder {
    Manual manual;

    @Override
    public void reset() {
        this.manual = new Manual();
    }

    @Override
    public void setSeats(int n) {
        manual.setSeats(n);
    }

    @Override
    public void setTripComputer(boolean tripComputer) {
        manual.setTripComputer(tripComputer);
    }

    @Override
    public void setGPS(boolean gps) {
        manual.setGps(gps);
    }

    @Override
    public void setEngine(Engine engine) {
        manual.setEngine(engine);
    }

    public Manual getProduct(){
        Manual manual1 = this.manual;
        this.reset();
        return manual1;
    }
}

// The director works with any builder instance that the
// client code passes to it. This way, the client code may
// alter the final type of the newly assembled product.
// The director can construct several product variations
// using the same building steps.
class Director{
    public void carBuilder(CarBuilder carBuilder){
        carBuilder.reset();
        carBuilder.setEngine(new Engine());
        carBuilder.setGPS(true);
        carBuilder.setTripComputer(true);
    }

    public void manualBuilder(ManualBuilder manualBuilder){
        manualBuilder.reset();
        manualBuilder.setEngine(new Engine());
        manualBuilder.setGPS(true);
        manualBuilder.setTripComputer(true);
    }
}

// The client code creates a builder object, passes it to the
// director and then initiates the construction process. The end
// result is retrieved from the builder object.
public class Application {
    public static void main (String[] args) {
        Director director = new Director();

        CarBuilder carBuilder = new CarBuilder();
        ManualBuilder manualBuilder = new ManualBuilder();

        director.carBuilder(carBuilder);
        director.manualBuilder(manualBuilder);
    }
}
