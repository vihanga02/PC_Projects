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

    public String getId() {
        return id;
    }

    public String getFileType() {
        return fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public Byte[] getContent() {
        return content;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setContent(Byte[] content) {
        this.content = content;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
