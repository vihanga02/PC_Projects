import java.util.ArrayList;

public class Printer extends Thread{
    private String printerName;
    private boolean printStatus;
    public Printer(String printerName){
        this.printerName = printerName;
    }
    @Override
    //this is the method that will be called when the thread is started
    public void run() {
        while (true) {
            try {
                //dequeue a print job from the shared queue ( return the print job and remove it from the queue)
                PrintJob printJob = SharedQueue.dequeue();

                //process and print the job
                print(printJob);
            } catch (InterruptedException | TypeNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }
    //printing method
    public synchronized void print(PrintJob printJob) throws TypeNotSupportedException, InterruptedException {
        //check if the print job is not null
        if (printJob != null) {
            //check if the file type is supported for printing
            if (Computer.getFiletypeList().contains(printJob.getFileType())) {
                //set the current print status to true
                setPrintStatus(true);
                //sleep for 10 seconds to simulate printing
                sleep(10);
                System.out.println("Printing Done!");
                //dequeue the print job from the shared queue ( remove it from the queue)
                SharedQueue.dequeue();
            } else {
                //throw an exception if the file type is not supported
                throw new TypeNotSupportedException("File type not supported for printing");
            }
        }
    }

    //getters and setters
    public boolean isPrinting() {
        return printStatus;
    }

    public void setPrintStatus(boolean printStatus) {
        this.printStatus = printStatus;
    }
}
