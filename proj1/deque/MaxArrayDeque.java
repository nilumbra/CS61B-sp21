package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> maxCompartor;

    MaxArrayDeque(Comparator<T> c) {
        maxCompartor = c;
    }

    public T max() {
        return this.max(this.maxCompartor);
    }

    public T max(Comparator<T> c) {
        if (this.isEmpty()) {
            return null;
        }
        T currMax = this.get(0);
        for (T ele: this) {
            if (c.compare(currMax, ele) < 0) {
                currMax = ele;
            }
        }

        return currMax;
    }
}
