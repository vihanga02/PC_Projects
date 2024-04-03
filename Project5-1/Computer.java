import java.util.ArrayList;
import java.util.Arrays;

public class Computer {
    private String computerName;
    private static final ArrayList<String> filetypes = new ArrayList<>(Arrays.asList("pdf","docs","txt"));
    public Computer(String computerName) {
        this.computerName = computerName;
    }
    public String getComputerName() {
        return computerName;
    }
    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }
    public void createPrintJob(String fileId,String fileName,String fileType,TextFile content ) throws TypeNotSupportedException{
        if(filetypes.contains(fileType)){
            new PrintJob(fileId, fileName, fileType, content);
        }else{
            throw new TypeNotSupportedException("File type not supported: " + fileType);
        }
    }
    public static ArrayList<String> getFiletypeList (){
        return filetypes;
    }
}