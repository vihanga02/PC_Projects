import java.util.ArrayList;

public class Printer {
    private String printerName;
    private ArrayList<String> compatibleTypes;
    private boolean printStatus;

    public Printer(String printerName, ArrayList<String> compatibleTypes){
        this.printerName = printerName;
        this.compatibleTypes = compatibleTypes;
    }

    public boolean isPrinting() {
        return printStatus;
    }

    public void setPrintStatus(boolean printStatus) {
        this.printStatus = printStatus;
    }

    public void print(PrintJob printJob) throws TypeNotSupportedException {
        if (compatibleTypes.contains(printJob.getFileType())) {
            System.out.println("Printing ");
            setPrintStatus(true);
        } else {
            throw new TypeNotSupportedException("File type not supported for printing");
        }
    }

}
