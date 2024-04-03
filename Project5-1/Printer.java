import java.util.ArrayList;

public class Printer {
    private String printerName;
    private boolean printStatus;

    public Printer(String printerName){
        this.printerName = printerName;
    }

    public boolean isPrinting() {
        return printStatus;
    }

    public void setPrintStatus(boolean printStatus) {
        this.printStatus = printStatus;
    }

    public void print(PrintJob printJob) throws TypeNotSupportedException {
        if (Computer.getFiletypeList().contains(printJob.getFileType())) {
            System.out.println("Printing ");
            setPrintStatus(true);
        } else {
            throw new TypeNotSupportedException("File type not supported for printing");
        }
    }
}