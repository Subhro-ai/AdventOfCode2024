import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day_1_Historian_Hysteria_part2 {
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
        
        HashMap <Integer, Integer> map = new HashMap<>();
        for (Integer integer : list2) {
            map.put(integer, (map.getOrDefault(integer, 0)) + 1);
        }
        int ans = 0;
        System.out.println(map);
        for (Integer integer : list1) {
            if (map.containsKey(integer)) {
                ans += integer * map.get(integer);
            }
        }
        System.out.println(ans);
    }
}
