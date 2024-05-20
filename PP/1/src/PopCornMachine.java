import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PopCornMachine {
    private static final int MAX_CAPACITY = 3;
    protected static final BlockingQueue<String> outputBin = new ArrayBlockingQueue<>(MAX_CAPACITY);

    static class Bell{
        public static void ring(){
            System.out.println("Bell rings.");
        }
    }

     static class Light{
        public static void blink(){
            System.out.println("Light blinks.");
        }
    }
}
