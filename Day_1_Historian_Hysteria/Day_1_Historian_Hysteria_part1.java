import java.io.*;
import java.util.ArrayList;

public class Day_1_Historian_Hysteria_part1{
    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty() && line.length() >= 5) { // Ensure line is not empty and has at least 5 characters
                    // System.out.println(line.substring(8, 13));
                    list1.add(Integer.parseInt(line.substring(0, 5)));
                    list2.add(Integer.parseInt(line.substring(8, 13)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        list1.sort(null);
        list2.sort(null);
        System.out.println(list1.get(0) - list2.get(0));
        int sum = 0;
        for (int i = 0; i < list1.size(); i++) {
            sum += Math.abs(list1.get(i) - list2.get(i));
        }
        System.out.println(sum);

    }
}
