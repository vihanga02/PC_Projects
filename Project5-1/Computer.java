import java.util.ArrayList;
import java.util.Arrays;

public class Computer extends Thread{
    private String computerName;
    //supported file types for the printer
    private static final ArrayList<String> filetypes = new ArrayList<>(Arrays.asList("pdf","docs","txt"));
    //constructor for a computer
    public Computer(String computerName) {
        this.computerName = computerName;
    }

    @Override
    //this is the method that will be called when the thread is started
    public void run() {

        while (true) {
            try {
                //create print job
                PrintJob printJob = createPrintJob("1", "File1.txt", "txt", new TextFile("File1.txt"));

                //enqueue the print job to the shared queue
                SharedQueue.enqueue(printJob);
            } catch (TypeNotSupportedException e) {
                //throw a runtime exception if the file type is not supported
                throw new RuntimeException(e);
            }
        }
    }

    //create a print job
    public synchronized PrintJob createPrintJob(String fileId, String fileName, String fileType, TextFile content ) throws TypeNotSupportedException{
        PrintJob printJob;
        //check if the file type is supported for creating a print job
        if(filetypes.contains(fileType)){
            printJob = new PrintJob(fileId, fileName, fileType, content);
        }else{
            //throw an exception if the file type is not supported
            throw new TypeNotSupportedException("File type not supported: " + fileType);
        }
        //return the print job
        return printJob;
    }
    //getters and setters
    public String getComputerName() {
        return computerName;
    }
    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }
    public static ArrayList<String> getFiletypeList (){
        return filetypes;
    }
}
