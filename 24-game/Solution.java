// Postfix notation is cool

import java.util.*;

public class Solution {
    private static int[] cards = new int[]{7,6,7,7};
    private static char[] ops = new char[]{'-', '+', '/', '*'};

    public static void main(String[] args) {
        int cLeft = 4;
        int opsLeft = 3;
        Set<Integer> cUsed = new HashSet<>();
        boolean worked = findSolutions("", opsLeft, cLeft, cUsed);
        System.out.println(worked);
    }

    public static boolean findSolutions(String exp, int opsLeft, int cLeft, Set<Integer> cUsed) {
        boolean worked = false;
        if (cUsed.size() >= 2 && opsLeft > 0) {
            for (int i = 0; i < ops.length; i++) {
                worked = findSolutions(exp + ", " + ops[i], opsLeft - 1, cLeft, cUsed);
                if (worked) return true;
            }
        }

        if (cLeft > 0) {
            for (int j = 0; j < cards.length; j++) {
                if (!cUsed.contains(j)) {
                    cUsed.add(j);
                    if (exp != "")
                        worked = findSolutions(exp + ", " + cards[j], opsLeft, cLeft - 1, cUsed);
                    else
                        worked = findSolutions(exp + cards[j], opsLeft, cLeft - 1, cUsed);
                    if (worked) {
                        return true;
                    }
                    cUsed.remove(j);
                }
            }
        }

        if (opsLeft == 0 && cLeft == 0) {
            int n = evalSolution(exp);
            if (n == 24) {
                return true;
            }
        }
        return false;
    }

    public static int evalSolution(String solution) {
        String[] chars = solution.split(", ");
        Stack<Integer> s = new Stack<>();
        int n;
        int v1;
        int v2;
        for (int i = 0; i < chars.length; i++) {
            try {
                switch (chars[i]) {
                    case "+":
                        v2 = s.pop();
                        v1 = s.pop();
                        n = v1 + v2;
                        s.push(n);
                        break;
                    case "-":
                        v2 = s.pop();
                        v1 = s.pop();
                        n = v1 - v2;
                        s.push(n);
                        break;
                    case "*":
                        v2 = s.pop();
                        v1 = s.pop();
                        n = v1 * v2;
                        s.push(n);
                        break;
                    case "/":
                        v2 = s.pop();
                        v1 = s.pop();
                        n = v1 / v2;
                        s.push(n);
                        break;
                    default:
                        n = Integer.parseInt(chars[i]);
                        s.push(n);
                        break;
                }
            } catch(EmptyStackException | ArithmeticException ae) {
                return -1;
            }
        }
        if (s.size() != 1) {
            return -1;
        } else {
            return s.pop();
        }
    }
}
