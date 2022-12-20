import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {

    public static Double[][] readInput (String filename) {
        File file = new File(filename);
        Double[][] matrix = new Double[3000][7];

        try (Scanner scanner = new Scanner(file)) {
            int line = 0;
            scanner.useDelimiter("\n");
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String tmp = scanner.nextLine();
                String[] elementArr  = tmp.split(",");
                for (int i = 0; i < 7; i++) {
                    matrix[line][i] = Double.parseDouble(elementArr[i]);
                }
                line++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return matrix;
    }

    public static Integer[][] readOutput (String filename) {
        File file = new File(filename);
        Integer[][] matrix = new Integer[3000][4];

        try (Scanner scanner = new Scanner(file)) {
            int line = 0;
            scanner.useDelimiter("\n");
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String tmp = scanner.nextLine();
                String[] elementArr  = tmp.split(",");
                for (int i = 0; i < 4; i++) {
                    matrix[line][i] = Integer.parseInt(elementArr[i]);
                }
                line++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return matrix;
    }

}
