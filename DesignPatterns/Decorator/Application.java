package Decorator;

import javax.naming.ldap.SortResponseControl;
import javax.xml.crypto.Data;
import java.util.SplittableRandom;

// The component interface defines operations that can be altered by decorators.
interface DataSource{
    void writeData(String data);
    String readData();
}

// Concrete components provide default implementations for the operations.
class FileDataSource implements DataSource{
    private String fileName;

    public FileDataSource(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void writeData(String data) {
        System.out.println("Writing data to file: " + fileName);
    }

    @Override
    public String readData() {
        System.out.println("Reading data from file: " + fileName);
        return "Data from " + fileName;
    }
}

// The base decorator class follows the same interface as the other components.
class DataSourceDecorator implements DataSource{
    protected DataSource wrappee;

    public DataSourceDecorator(DataSource dataSource){
        this.wrappee = dataSource;
    }

    // The base decorator simply delegates all work to the
    // wrapped component. Extra behaviors can be added in
    // concrete decorators.
    @Override
    public void writeData(String data) {
        wrappee.writeData(data);
    }

    // Concrete decorators may call the parent implementation of
    // the operation instead of calling the wrapped object
    // directly. This approach simplifies extension of decorator
    // classes.
    @Override
    public String readData() {
        return wrappee.readData();
    }
}

// Decorators can execute the added behavior either before or after the
// call to a wrapped object.
class EncryptionDecorator extends DataSourceDecorator{

    public EncryptionDecorator(DataSource dataSource) {
        super(dataSource);
    }

    public void writeData(String data){
        String encryptedData = encrypt(data);
    }

    public String readData(){
        String data = wrappee.readData();
        return decrypt(data);
    }

    private String encrypt(String data){
        return "Encrypted(" + data + ")";
    }

    private String decrypt(String data){
        return data.replace("Encrypted(", "").replace(")", "");
    }
}

class CompressionDecorator extends DataSourceDecorator{

    public CompressionDecorator(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public String readData() {
        String data = wrappee.readData();
        return decompress(data);
    }

    @Override
    public void writeData(String data) {
        String compressedData = compress(data);
        wrappee.writeData(compressedData);
    }

    private String compress(String data){
        return "Compressed(" + data + ")";
    }

    private String decompress(String data){
        return data.replace("Compressed(", "").replace(")", "");
    }
}

// decorator assembly.
class Application1 {
    public void dumbUsageExample(){
        DataSource dataSource = new FileDataSource("someFile.dat");
        // The target file has been written with plain data.
        dataSource.writeData("Salary Records");

        // The target file has been written with compressed data.
        dataSource = new CompressionDecorator(dataSource);
        dataSource.writeData("Salary records");

        // The file has been written with compressed and encrypted data.
        dataSource = new EncryptionDecorator(dataSource);
        dataSource.writeData("Salary records");
    }
}

// work with a pre-configured data source received from the app configurator.
class SalaryManager {
    private DataSource dataSource;

    public SalaryManager(DataSource dataSource){
        this.dataSource = dataSource;
    }
    public String load(){
        return dataSource.readData();
    }

    public void write(String data){
        dataSource.writeData(data);
    }
}

class ApplicationConfigure{
    private boolean enabledEncryption = true; // Just for demonstration
    private boolean enabledCompression = true; // Just for demonstration

    DataSource dataSource = new FileDataSource("salary.dat");

    public void configurationExample() {
        DataSource source = new FileDataSource("salary.dat");
        if (enabledEncryption) {
            source = new EncryptionDecorator(source);
        }
        if (enabledCompression) {
            source = new CompressionDecorator(source);
        }

        SalaryManager logger = new SalaryManager(source);
        String salary = logger.load();
        System.out.println("Loaded salary data: " + salary);
    }
}

public class Application {
    public static void main(String[] args){
        Application1 application1 = new Application1();
        application1.dumbUsageExample();

        ApplicationConfigure applicationConfigure = new ApplicationConfigure();
        applicationConfigure.configurationExample();
    }
}
