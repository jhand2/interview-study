import java.util.Stack;

public class Solution {
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<Integer>();
        s.push(2);
        s.push(1);
        s.push(12);
        s.push(3);
        s.push(13);
        s.push(1);
        s.push(12);
        s.push(3);
        s.push(26);
        s.push(0);
        s.push(-10);
        s.push(2);
        
        sort(s);
        
        System.out.println(s);
    }
    
    public static void sort(Stack<Integer> s) {
        int lastSorted = Integer.MAX_VALUE;
        boolean sorted = false;
        Stack<Integer> aux = new Stack<>();
        while (!sorted) {
            int max = Integer.MIN_VALUE;
            int nOfMax = 0;
            int n;
            while (!s.isEmpty()) {
                if (s.peek() == lastSorted) {
                    if (aux.isEmpty()) {
                        sorted = true;
                    }
                    break;
                }
                n = s.pop();
                if (n > max) {
                    if (max != Integer.MIN_VALUE) {
                        for (int i = 0; i < nOfMax; i++) {
                            aux.push(max);
                        }
                    }
                    max = n;
                    nOfMax = 1;
                } else if (n == max) {
                    nOfMax++;
                } else {
                    aux.push(n);
                }
            }
            if (max != Integer.MIN_VALUE) {
                for (int i = 0; i < nOfMax; i++) {
                    s.push(max);
                }
                nOfMax = 0;
                lastSorted = max;
            }
            while (!aux.isEmpty()) {
                s.push(aux.pop());
            }
        }
    }
}
