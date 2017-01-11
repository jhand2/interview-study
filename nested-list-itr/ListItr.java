import java.lang.*;
import java.util.*;

public class ListItr {
    private List<List<Integer>> lst;
    private int i;
    private int j;

    public ListItr(List<List<Integer>> lst) {
        this.lst = lst;
        this.i = 0;
        this.j = 0;
    }

    public boolean hasNext() {
        return i < lst.size();
    }

    public int next() {
        if (this.hasNext()) {
            int val = this.lst.get(i).get(j);
            this.j++;
            this.resetIndices();
            return val;
        } else {
            throw new NoSuchElementException();
        }
    }

    private void resetIndices() {
        if (this.hasNext() && (this.j >= this.lst.get(i).size())) {
            this.i++;
            while (this.hasNext() && this.lst.get(i).size() == 0) {
                this.i++;
            }
            this.j = 0;
        }
    }

    public int remove() {
        if (this.hasNext()) {
            int val = this.lst.get(i).remove(j);
            this.resetIndices();
            return val;
        } else {
            throw new NoSuchElementException();
        }
    }
}
