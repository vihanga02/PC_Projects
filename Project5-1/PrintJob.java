public class PrintJob {
    private String id;
    private String fileName;
    private String fileType;
    private TextFile content;
    private boolean printStatus;

    public PrintJob(String id, String fileName, String fileType, TextFile content){
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.content = content;
        this.printStatus = false;
    }
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
