import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class part_1 {
    static String keypad[][] = { { "7", "8", "9" }, { "4", "5", "6" }, { "1", "2", "3" }, { "null", "0", "A" } };
    static String directionpad[][] = { { null, "^", "A" }, { "<", "V", ">" } };
    

    public static void main(String[] args) {
        int sum = 0;
        ArrayList<String> path = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                path = keypadPath(line);
                System.out.println(line);
                // System.out.println(path);
                path = directionpadPath(path);
                path = directionpadPath(path);
                System.out.println(path.size() +" * " +extractNumber(line));
                System.out.println(path);
                sum += path.size() * extractNumber(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ANSWER " + sum);
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

    public static ArrayList<String> directionpadPath(ArrayList<String> givenPath) {
        int initIndex[] = {2, 0};
        int finIndex[] = {2, 0};
        ArrayList<String> directionPath = new ArrayList<>();
        for (String direction : givenPath) {
            initIndex = finIndex;
            finIndex = getDirectionIndex(direction);
            if (initIndex == null || finIndex == null) {
                // System.out.println("Invalid input: " + init + " or " + fin + " not found.");
                return null;
            }
            int move[] = {(finIndex[0] - initIndex[0]), (finIndex[1] - initIndex[1])};
            for (int i = 0; i < Math.abs(move[0]); i++) {
                if (move[0] > 0) {
                    directionPath.add(">");
                    initIndex[0] += 1;
                } 
                else {
                    directionPath.add("<");
                    initIndex[0] -= 1;
                }
                // if (initIndex[0] == 0 && initIndex[1] == 0) {
                //     initIndex[0] += 1;
                //     initIndex[1] += 1;
                //     directionPath.add("V");
                // }
            }
            for (int j = 0; j < Math.abs(move[1]); j++) {
                if (move[1] > 0) {
                    directionPath.add("V");
                    initIndex[1] += 1;
                } 
                else {
                    directionPath.add("^");
                    initIndex[1] -= 1;
                }
                if (initIndex[0] == 0 && initIndex[1] == 0) {
                    // System.out.println("X" + initIndex[0] + "Y" + initIndex[1]);
                    directionPath.add("V");
                }
            }
            directionPath.add("A");
        }
        // System.out.println(directionPath);
        return directionPath;
    }
    public static int[] getDirectionIndex(String input) {
        if (input == "<") return new int[]{0, 1};
        if (input == ">") return new int[]{2, 1};
        if (input == "^") return new int[]{1, 0};
        if (input == "V") return new int[]{1, 1};
        if (input == "A") return new int[]{2, 0};
        throw new IllegalArgumentException("Invalid input character: " + input);
    }
    public static int extractNumber(String input) {
        // Remove all non-digit characters
        String numericPart = input.replaceAll("\\D", "");
        // Parse the numeric part as an integer to ignore leading zeros
        return numericPart.isEmpty() ? 0 : Integer.parseInt(numericPart);
    }
}



