import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String [] args){

        // Creating 2 printers
        Printer printer1 = new Printer("Printer 1");
        Printer printer2 = new Printer("Printer 2");

        // Create 3 computers
        Computer computer1 = new Computer("Computer 1");
        Computer computer2 = new Computer("Computer 2");
        Computer computer3 = new Computer("Computer 3");

        // Initializing the sharedQueue object
        SharedQueue queue = new SharedQueue();

        // Start printer threads
        printer1.start();
        printer2.start();

        // Start computer threads
        computer1.start();
        computer2.start();
        computer3.start();

        try {
            //these are the jobs coming from web interface
            SharedQueue.enqueue(computer1.createPrintJob("1", "File1.txt", "txt", new TextFile("File1.txt")));
            SharedQueue.enqueue(computer2.createPrintJob("2", "File2.txt", "txt", new TextFile("File2.txt")));
            SharedQueue.enqueue(computer3.createPrintJob("3", "File3.txt", "txt", new TextFile("File3.txt")));
        } catch (TypeNotSupportedException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
