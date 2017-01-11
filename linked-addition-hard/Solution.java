// Bastardized java solution
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Node l1 = new Node(1, null);
        l1.next = new Node(9, null);
        l1.next.next = new Node(3, null);
        l1.next.next.next = new Node(2, null);

        Node l2 = new Node(2, null);
        l2.next = new Node(3, null);
        l2.next.next = new Node(4, null);

        Node res = add(l1, l2);
        Node curr = res;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static Node add(Node l1, Node l2) {
        if (l1 == null || l2 == null) {
            throw new IllegalArgumentException();
        }
        // Calculate sizes of lists
        Node curr1 = l1;
        int c1 = 0;
        Node curr2 = l2;
        int c2 = 0;
        while (curr1 != null || curr2 != null) {
            if (curr1 != null) {
                c1++;
                curr1 = curr1.next;
            }

            if (curr2 != null) {
                c2++;
                curr2 = curr2.next;
            }
        }

        // Make the beginning of the longer list
        int diff = c1 - c2;
        Node longer = l2;
        Node shorter = l1;
        if (diff > 0) {
            longer = l1;
            shorter = l2;
        }
        diff = Math.abs(diff);
        Node head = new Node(0, null);
        Node curr = head;
        if (diff > 0) {
            for (int i = 0; i < diff; i++) {
                curr.next = new Node(longer.data, null);
                curr = curr.next;
                longer = longer.next;
            }
        }

        // Get result of equal lists
        Node result = addEqual(longer, shorter);
        result = addDiff(head, result, new Node(0, null));
        if (result.data == 0)
            result = result.next;

        return result;
    }

    public static Node addDiff(Node head, Node remaining, Node carry) {
        int val = head.data + carry.data;
        if (head.next == null) {
            val += remaining.data;
            head.next = remaining.next;
        } else {
            head.next = addDiff(head.next, remaining, carry);
        }
        head.data = val % 10;
        carry.data = val / 10;
        return head;
    }

    public static Node addEqual(Node l1, Node l2) {
        if (l1 == null || l2 == null) {
            return null;
        }
        if (l1.next == null || l2.next == null) {
            int val = l1.data + l2.data;
            Node end = new Node(val % 10, null);
            Node head = new Node(val / 10, end);

            return head;
        } else {
            Node tail = addEqual(l1.next, l2.next);
            int headVal = tail.data + l1.data + l2.data;
            Node end = new Node(headVal % 10, tail.next);
            Node head = new Node(headVal / 10, end);

            return head;
        }
    }

    public static class Node {
        int data;
        Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
