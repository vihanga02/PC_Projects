import java.util.ArrayList;

public class Printer extends Thread{
    private String printerName;
    private boolean printStatus;

    public Printer(String printerName){
        this.printerName = printerName;
    }
    @Override
    public void run() {
        while (true) {
            try {
                // Dequeue a print job from the shared queue
                PrintJob printJob = SharedQueue.dequeue();

                // Process and print the job
                print(printJob);
            } catch (InterruptedException | TypeNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean isPrinting() {
        return printStatus;
    }

    public void setPrintStatus(boolean printStatus) {
        this.printStatus = printStatus;
    }

    public synchronized void print(PrintJob printJob) throws TypeNotSupportedException, InterruptedException {
        if (printJob != null) {
            if (Computer.getFiletypeList().contains(printJob.getFileType())) {
                setPrintStatus(true);
                sleep(10);
                System.out.println("Printing Done!");
                SharedQueue.dequeue();
            } else {
                throw new TypeNotSupportedException("File type not supported for printing");
            }
        }
    }
}
