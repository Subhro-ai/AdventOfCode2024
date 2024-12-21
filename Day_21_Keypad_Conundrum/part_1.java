import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class part_1 {
    static String keypad[][] = { { "7", "8", "9" }, { "4", "5", "6" }, { "1", "2", "3" }, { "null", "0", "A" } };
    

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            ArrayList<String> path = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                path = keypadPath(line);
                System.out.println(line);
                System.out.println(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> keypadPath(String line) {
        ArrayList<String> path = new ArrayList<>();
        String init = "A";
        String fin = "A";
        // System.out.println(line);
        for (int k = 0; k < line.length(); k++) {
            init = fin;
            fin = String.valueOf(line.charAt(k));
            // System.out.println("From " + init + " To " + fin);

            int[] initArr = getIndex(init);
            int[] finArr = getIndex(fin);
            // System.out.println("initArr" + initArr[0] + " " +initArr[1]);
            // System.out.println("finArr" + finArr[0] + " " + finArr[1]);

            // If either init or fin is not found, do not proceed further
            if (initArr == null || finArr == null) {
                // System.out.println("Invalid input: " + init + " or " + fin + " not found.");
                return null;
            }

            int[] move = { (finArr[0] - initArr[0]), (finArr[1] - initArr[1]) };
            // System.out.println(move[0] + " " + move[1]);
            
            for (int i = 0; i < Math.abs(move[0]); i++) {
                if (move[0] > 0) {
                    path.add(">");
                } else {
                    path.add("<");
                }
            }
            for (int j = 0; j < Math.abs(move[1]); j++) {
                if (move[1] > 0) {
                    path.add("V");
                } else {
                    path.add("^");
                }
            }
            path.add("A");
        }
        return path;
    }

    public static int[] getIndex(String a) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (keypad[i][j] != null && keypad[i][j].equals(a)) {
                    return new int[] { j, i };
                }
            }
        }
        return null; // Return null if element is not found
    }
}
