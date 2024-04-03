public class PrintJob {
    private String id;
    private String fileName;
    private String fileType;
    private Byte[] content;

    public PrintJob(String id, String fileName, String fileType, Byte[] content){
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.content = content;
    }
}
