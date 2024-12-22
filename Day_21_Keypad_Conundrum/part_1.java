import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class part_1 {
    static String keypad[][] = { { "7", "8", "9" }, { "4", "5", "6" }, { "1", "2", "3" }, { "null", "0", "A" } };    

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
        String init = "A"; // Start at A
        for (int k = 0; k < line.length(); k++) {
            String fin = String.valueOf(line.charAt(k));
            int[] initArr = getIndex(init);
            int[] finArr = getIndex(fin);
            if (initArr == null || finArr == null) {
                throw new IllegalArgumentException("Invalid input character: " + fin);
            }
            // Calculate moves
            int[] move = { finArr[0] - initArr[0], finArr[1] - initArr[1] };
            for (int i = 0; i < Math.abs(move[0]); i++) {
                path.add(move[0] > 0 ? ">" : "<");
            }
            for (int j = 0; j < Math.abs(move[1]); j++) {
                path.add(move[1] > 0 ? "V" : "^");
            }
            path.add("A"); // Add only after completing moves
            init = fin; // Update initial position
        }
        return path;
    }
    

    public static int[] getIndex(String a) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (keypad[i][j] != null && keypad[i][j].equals(a)) {
                    return new int[] { i, j };
                }
            }
        }
        return null; // Return null if element is not found
    }

    public static ArrayList<String> directionpadPath(ArrayList<String> givenPath) {
        int[] initIndex = { 2, 0 }; // Starting position ("A")
        ArrayList<String> directionPath = new ArrayList<>();
        for (String direction : givenPath) {
            int[] finIndex = getDirectionIndex(direction);
            if (finIndex == null) {
                throw new IllegalArgumentException("Invalid direction: " + direction);
            }
            // Calculate moves
            int[] move = { finIndex[0] - initIndex[0], finIndex[1] - initIndex[1] };
            for (int i = 0; i < Math.abs(move[0]); i++) {
                directionPath.add(move[0] > 0 ? ">" : "<");
            }
            for (int j = 0; j < Math.abs(move[1]); j++) {
                directionPath.add(move[1] > 0 ? "V" : "^");
            }
            directionPath.add("A"); // Add only after completing moves
            initIndex = finIndex; // Update position
        }
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



