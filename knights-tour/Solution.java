import java.util.*;

public class Solution {

    private static int[] iDeltas = new int[]{2, 2, -2, -2, 1, 1, -1, -1};
    private static int[] jDeltas = new int[]{-1, 1, -1, 1, -2, 2, -2, 2};
    private static int nMoves = 8;
    private static int side = 5; // For an nxn board

    public static void main(String[] args) {
        side = Integer.parseInt(args[0]);
        int[][] board = new int[side][side];
        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                board[i][j] = -1;
            }
        }

        boolean solved = solve(board, 0, 0);
        if (solved) {
            printSolution(board);
        } else {
            System.out.println("No solution");
        }
    }
    
    private static void printSolution(int[][] board) {
        for (int[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static boolean solve(int[][] board, int startI, int startJ) {
        return helper(board, startI, startJ, 0);
    }

    private static boolean helper(int[][] board, int i, int j, int moveNum) {
        board[i][j] = moveNum;
        if (isSolved(board)) {
            return true;
        }
        for (int a = 0; a < nMoves; a++) {
            int newI = i + iDeltas[a];
            int newJ = j + jDeltas[a];
            if (isSafe(board, newI, newJ)) {
                //System.out.println();
                //printSolution(board);
                boolean worked = helper(board, newI, newJ, moveNum + 1);
                if (worked) {
                    return true;
                }
            }
        }
        board[i][j] = -1;
        return false;
    }

    private static boolean isSolved(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == -1) return false;
            }
        }
        return true;
    }

    private static boolean isSafe(int[][] board, int i, int j) {
        boolean onBoard = i < board.length &&
            i >= 0 &&
            j < board[i].length &&
            j >= 0;
        return onBoard && board[i][j] == -1;
    }
}
