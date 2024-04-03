import java.io.BufferedReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextFile {
    String content;
    String fileType;

    StringBuilder contentBuilder = new StringBuilder();
    public TextFile(String filePath) {
        this.readFromFile(filePath);
    }
    public void readFromFile (String filePath){
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
        finally {
            this.content = contentBuilder.toString();
        }
    }

    public void getFileType(String fileType){

    }

}