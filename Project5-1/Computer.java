import java.util.ArrayList;
import java.util.Arrays;

public class Computer extends Thread{
    private String computerName;
    private static final ArrayList<String> filetypes = new ArrayList<>(Arrays.asList("pdf","docs","txt"));
    public Computer(String computerName) {
        this.computerName = computerName;
    }
    @Override
    public void run() {

        while (true) {
            System.out.println("here at computer......");
            try {
                // Create print job
                PrintJob printJob = createPrintJob("1", "File1.txt", "txt", new TextFile("File1.txt"));

                // Enqueue the print job to the shared queue
                SharedQueue.enqueue(printJob);

                // Sleep for some time before creating the next print job
                Thread.sleep(1000); // Example sleep time
            } catch (InterruptedException | TypeNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }
    public String getComputerName() {
        return computerName;
    }
    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }
    public synchronized PrintJob createPrintJob(String fileId, String fileName, String fileType, TextFile content ) throws TypeNotSupportedException{
        PrintJob printJob;
        if(filetypes.contains(fileType)){
            printJob = new PrintJob(fileId, fileName, fileType, content);
        }else{
            throw new TypeNotSupportedException("File type not supported: " + fileType);
        }
        return printJob;
    }
    public static ArrayList<String> getFiletypeList (){
        return filetypes;
    }
}
