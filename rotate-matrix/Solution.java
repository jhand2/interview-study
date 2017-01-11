import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int n = 6;
        int[][] matrix = new int[6][6];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = (i * n) + j + 1;
            }
        }

        rotate(matrix);

        for (int[] row : matrix) {
            printRow(row);
        }
    }

    public static void rotate(int[][] m) {
        for (int i = 0; i < m.length / 2; i++) {
            for (int j = i; j < m[i].length - i - 1; j++) {
                int top = m[i][j];
                int right = m[j][m[i].length - 1 - i];
                int bottom = m[m.length - 1 - i][m.length - 1 - j];
                int left = m[m.length - 1 - j][i];

                m[i][j] = left;
                m[j][m[i].length - 1 - i] = top;
                m[m.length - 1 - i][m.length - 1 - j] = right;
                m[m.length - 1 - j][i] = bottom;
            }
        }
    }

    public static void printRow(int[] row) {
        for (int i : row) {
            System.out.print(i);
            System.out.print("\t");
        }
        System.out.println();
        System.out.println();
    }
}
