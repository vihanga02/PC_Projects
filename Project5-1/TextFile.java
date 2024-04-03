import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextFile {
    private String content;
    // Creating a new string builder object
    StringBuilder contentBuilder = new StringBuilder();
    public TextFile(String filePath) {
        // Calling the readFromFile function
        this.readFromFile(filePath);
    }
    public void readFromFile (String filePath){
        // Read the files and get its data
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
        finally {
            // Assign content of the job via string builder
            this.content = contentBuilder.toString();
        }
    }
}
