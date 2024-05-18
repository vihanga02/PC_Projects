package Strategy;

// strategy interface operations which are common for all strategy's
interface Strategy{
    int execute(int a, int b);
}

// concrete strategy implement the algorithms
class ConcreteStrategyAdd implements Strategy {
    public int execute(int a, int b) {
        return a + b;
    }
}

class ConcreteStrategySubtract implements Strategy {
    public int execute(int a, int b) {
        return a - b;
    }
}

class ConcreteStrategyMultiply implements Strategy {
    public int execute(int a, int b) {
        return a * b;
    }
}

// context is the only one to deal with the client
class Context{
    // context maintains the reference to the one of the strategy object
    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int a, int b){
        return strategy.execute(a, b);
    }
}

public class Main {
    public static void main(String[] args){
        Context context = new Context();

        int a = 10;
        int b = 20;

        context.setStrategy(new ConcreteStrategyAdd());
        System.out.println(context.executeStrategy(a, b));

        context.setStrategy(new ConcreteStrategySubtract());
        System.out.println(context.executeStrategy(a, b));

        context.setStrategy(new ConcreteStrategyMultiply());
        System.out.println(context.executeStrategy(a, b));
    }
}
