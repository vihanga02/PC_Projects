import java.util.LinkedList;
import java.util.Queue;

public class SharedQueue {
    private static Queue<PrintJob> queue = null;
    private static final int capacity = 5;
    public SharedQueue(){
        queue = new LinkedList<>();
    }
    public synchronized static void enqueue(PrintJob job) {
        if (queue.size() < capacity) {
            queue.add(job);
            System.out.println("Job added to the queue.");
        } else {
            System.out.println("Queue is full");
        }
    }
    public synchronized static PrintJob dequeue() {
        PrintJob printJob = null;
        if (queue.isEmpty()) {
            System.out.println("Queue is empty.");
        }
        else{
            printJob = queue.remove();
        }
        return printJob;
    }
}
