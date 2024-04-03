import java.util.LinkedList;
import java.util.Queue;

public class SharedQueue {
    private final Queue<PrintJob> queue;
    private static final int capacity = 5;
    public SharedQueue(){
        this.queue = new LinkedList<>();
    }
    public void enqueue(PrintJob job) {
        if (queue.size() < capacity){
            queue.add(job);
            System.out.println("Job added to the queue.");
        }
        else{
            System.out.println("Queue is full");
        }
    }

    public void dequeue(PrintJob job) {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty.");
        }
        else if (!queue.contains(job)) {
            System.out.println("This job is not in the queue.");
        }
        else{
            queue.remove(job);
        }
    }
}
