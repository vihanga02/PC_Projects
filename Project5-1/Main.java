import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String [] args){

        Printer printer1 = new Printer("Printer 1");
        Printer printer2 = new Printer("Printer 2");

        // Create computers
        Computer computer1 = new Computer("Computer 1");
        Computer computer2 = new Computer("Computer 2");
        Computer computer3 = new Computer("Computer 3");

        SharedQueue queue = new SharedQueue();

        try {
            queue.enqueue(computer1.createPrintJob("1", "File1.txt", "txt", new TextFile("File1.txt")));
            queue.enqueue(computer2.createPrintJob("2", "File2.pdf", "pdf", new TextFile("File2.pdf")));
            queue.enqueue(computer3.createPrintJob("3", "File3.docx", "docx", new TextFile("File3.docx")));
        } catch (TypeNotSupportedException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
