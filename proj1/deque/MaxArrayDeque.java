package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    Comparator<T> max_compartor;

    public MaxArrayDeque(Comparator<T> c) {
        max_compartor = c;
    }

    public T max() {
        return this.max(this.max_compartor);
    }

    public T max(Comparator<T> c) {
        T curr_max = this.get(0);
        for (T ele: this) {
            if (c.compare(curr_max, ele) < 0) {
                curr_max = ele;
            }
        }

        return curr_max;
    }
}
