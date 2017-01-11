import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
            {1, 1, 0, 0, 0, 0},
            {0, 1, 0, 1, 0, 1},
            {0, 1, 0, 1, 0, 1},
            {0, 1, 0, 1, 0, 1},
            {0, 1, 0, 1, 0, 1},
            {0, 1, 0, 1, 1, 1}
        };

        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }

        List<List<String>> islands = getIslands(matrix);
        System.out.println();
        System.out.println(islands);
    }

    public static List<List<String>> getIslands(int[][] m) {
        Set<String> visited = new HashSet<>();
        int count = 0;
        List<List<String>> islands = new ArrayList<>();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] == 1 && !visited.contains(i + "" + j)) {
                    List<String> land = new ArrayList<>();
                    dfs(m, visited, i, j, land);
                    islands.add(land);
                    count++;
                }
            }
        }
        return islands;
    }

    public static void dfs(int[][] m, Set<String> v, int i, int j, List<String> island) {
        //System.out.println(i + "" + j);
        if (i >= 0 && i < m.length && j >= 0 && j < m[i].length) {
            if (!v.contains(i + "" + j) && m[i][j] == 1) {
                v.add(i + "" + j);
                island.add(i + "" + j);
                for (int x = 0; x < 9; x++) {
                    dfs(m, v, i + (x / 3) - 1, j + (x % 3) - 1, island);
                }
            }
        }
    }
}
