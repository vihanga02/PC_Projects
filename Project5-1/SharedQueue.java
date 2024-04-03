import java.util.LinkedList;
import java.util.Queue;

public class SharedQueue {
    // Initialize the queue
    private static Queue<PrintJob> queue = null;
    // Initialize the maximum capacity of the queue as 5
    private static final int capacity = 5;
    public SharedQueue(){
        queue = new LinkedList<>();
    }
    public synchronized static void enqueue(PrintJob job) {
        // Adding new jobs to the job queue while queue is full
        if (queue.size() <= capacity) {
            queue.add(job);
            System.out.println("Job added to the queue.");
        } else {
            System.out.println("Queue is full");
        }
    }
    public synchronized static PrintJob dequeue() {
        // Initialize a new printJob object
        PrintJob printJob = null;
        // Dequeue the queue and returning the removed printJOb object
        if (queue.isEmpty()) {
            System.out.println("Queue is empty.");
        }
        else{
            printJob = queue.remove();
        }
        return printJob;
    }
}
