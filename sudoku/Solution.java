import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int[][] board = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = s.nextInt();
            }
        }

        solve(board);
    }

    public static void solve(int[][] board) {
        for (int[] row : board) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();

        int[][] solution = new int[board.length][board[0].length];
        
        for (int i = 0; i < solution.length; i++) {
            for (int j = 0; j < solution[i].length; j++) {
                solution[i][j] = board[i][j];
            }
        }


        boolean solved = solveHelper(board, solution, 0, 0, 9);
        if (solved) {
            for (int[] row : solution) {
                System.out.println(Arrays.toString(row));
            }
        } else {
            System.out.println("Cannot be solved");
        }
    }

    public static boolean solveHelper(int[][] board, int[][] solution, int i, int j, int side) {
        if (board[i][j] != 0) {
            solution[i][j] = board[i][j];
            boolean worked = true;
            if (j < side - 1) {
                worked = solveHelper(board, solution, i, j + 1, side);
            } else if (i < side - 1) {
                worked = solveHelper(board, solution, i + 1, 0, side);
            }
            
            return worked;
        } else {
            for (int a = 1; a <= side; a++) {
                solution[i][j] = a;
                if (isValid(solution, i, j, a)) {
                    boolean worked = true;
                    if (j < side - 1) {
                        worked = solveHelper(board, solution, i, j + 1, side);
                    } else if (i < side - 1) {
                        worked = solveHelper(board, solution, i + 1, 0, side);
                    } else {
                        worked = true;
                    }

                    if (worked) {
                        return true;
                    }
                }
            }
        }
        solution[i][j] = 0;
        return false;
    }

    private static boolean isValid(int[][] board, int i, int j, int n) {
        boolean onBoard = i >= 0 &&
            i <= board.length &&
            j >= 0 &&
            j <= board[0].length;

        // Valid col
        for (int ii = 0; ii < board.length; ii++) {
            if (ii != i && board[ii][j] == n) return false;
        }

        // Valid row
        for (int jj = 0; jj < board[i].length; jj++) {
            if (j != jj && board[i][jj] == n) return false;
        }

        int boxSide = (int) Math.sqrt(board.length);
        int iQuad = i / boxSide;
        int jQuad = j / boxSide;
        for (int k = 0; k < board.length; k++) {
            int ii = (k / boxSide) + (iQuad * boxSide);
            int jj = (k % boxSide) + (jQuad * boxSide);

            if (!(i == ii && j == jj) && board[ii][jj] == n) return false;
        }
        return true;
    }
}
