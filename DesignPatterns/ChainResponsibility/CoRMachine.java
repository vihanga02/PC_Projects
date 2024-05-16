package ChainResponsibility;

//Class representing an abstract Temerature Controller
abstract class TemperatureController {
    private TemperatureController next;
    public TemperatureController
            (TemperatureController next) {
        this.next = next;
    }
    public TemperatureController getNext() {
        return next;
    }
    abstract public boolean control(double t);
}
//Class representing a concrete Temerature Controller
class FreezingController extends TemperatureController {
    public FreezingController(TemperatureController next) {
        super(next);
    }
    public boolean control(double t) {
        if(t <= 0) {
            System.out.println("Handling freezing conditions");
            return true;
        }
        else if(getNext() == null)
            return false;
        else
            return getNext().control(t);
    }
}
//Class representing a concrete Temerature Controller
class LiquidController extends TemperatureController {
    public LiquidController(TemperatureController next) {
        super(next);
    }
    public boolean control(double t) {
        if((t > 0) & (t < 100)) {
            System.out.println("Handling liquid conditions");
            return true;
        }
        else if(getNext() == null)
            return false;
        else
            return getNext().control(t);
    }
}
//Class representing a concrete Temerature Controller
class BoilingController extends TemperatureController {
    public BoilingController(TemperatureController next) {
        super(next);
    }
    public boolean control(double t) {
        if(t >= 100) {
            System.out.println("Handling boiling conditions");
            return true;
        }
        else if(getNext() == null)
            return false;
        else
            return getNext().control(t);
    }
}
// Class representing a Group or Chain of Temperature
// Controllers. By using appropriate constructors, we can
// reconfigure the chain of controllers even at run time
class ControllerChain {
    private BoilingController bc;
    private LiquidController lc;
    private FreezingController fc;
    public ControllerChain() {
        bc = new BoilingController(null);
        lc = new LiquidController(bc);
        fc = new FreezingController(lc);
    }
    public boolean control(double t) {
        return fc.control(t);
    }
}
// Client class "CoR Machine" is calling the generic
// temperature "Controller Chain" without a specific
// reference to a particular type of Temperature
// Controller
public class CoRMachine {
    public static void main(String [] args) {
        ControllerChain cc = new ControllerChain();
        cc.control(-50);
        cc.control(50);
        cc.control(150);
    }
}