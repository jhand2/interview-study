import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Map<Integer, List<Integer>> g = new HashMap<>();
        g.put(1, new ArrayList<Integer>(Arrays.asList(2)));
        g.put(2, new ArrayList<Integer>(Arrays.asList(1, 5)));
        g.put(3, new ArrayList<Integer>(Arrays.asList(1, 2)));
        g.put(4, new ArrayList<Integer>(Arrays.asList(5)));
        g.put(5, new ArrayList<Integer>(Arrays.asList(2, 4)));

        boolean valid = canColor(g, 1);
        System.out.println(valid);
    }

    public static boolean canColor(Map<Integer, List<Integer>> g, int startV) {
        Queue<Integer> open = new LinkedList<>();
        Map<Integer, Integer> c = new HashMap<>();
        open.add(startV);
        int color = 0;
        c.put(startV, color);
        color = color - 1;
        while (!open.isEmpty()) {
            int curr = open.remove();
            color = (c.get(curr) + 1) % 2;
            c.put(curr, color);
            for (int v : g.get(curr)) {
                if (c.containsKey(v)) {
                    if (c.get(v) == color) {
                        return false;
                    }
                } else {
                    c.put(v, color);
                    open.add(v);
                }
            }
        }
        return true;
    }
}
