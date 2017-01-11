import java.util.*;

public class Client {
    public static void main(String[] args) {
        List<List<Integer>> l = new ArrayList<>();
        l.add(new ArrayList<>(Arrays.asList(1,2,3)));
        l.add(new ArrayList<>());
        l.add(new ArrayList<>(Arrays.asList(9, 10, 11)));

        ListItr i = new ListItr(l);

        i.remove();
        for (int j = 0; j < 3; j++) {
            System.out.println(i.next());
        }
        i.remove();
        System.out.println(i.next());
        System.out.println(l);

        System.out.println("Done");
    }
}
