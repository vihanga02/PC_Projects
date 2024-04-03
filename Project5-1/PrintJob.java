public class PrintJob {
    // Creating attributes of the printJob
    private String id;
    private String fileName;
    private String fileType;
    private TextFile content;

    // Adding values to the constructor via the constructor
    public PrintJob(String id, String fileName, String fileType, TextFile content){
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.content = content;
    }
    // Getter and setter methods for the PrintJob
    public String getId() {
        return id;
    }
    public String getFileType() {
        return this.fileType;
    }
    public String getFileName() {
        return fileName;
    }
    public TextFile getContent() {
        return content;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public void setContent(TextFile content) {
        this.content = content;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
