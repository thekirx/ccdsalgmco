import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    /*
     will return array ng Record objects na from data na naread natin.
     */
    public Record[] readFile(String path) {
        try {
            File f = new File(path);
            Scanner scanner = new Scanner(f);
            int n = scanner.nextInt(); // number of records
            Record[] result = new Record[n]; // Initialize array ng records
            for (int i = 0; i < n; i++) { // Loop 
                int idNumber = scanner.nextInt(); // kunin yung ID number
                String name = scanner.nextLine(); // kunin yung name
                Record record = new Record(name, idNumber); // for new Record object
                result[i] = record; // I-store yung record sa array
            }
            scanner.close();
            return result;
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
            e.printStackTrace();
            return null;
        }
    }
}
