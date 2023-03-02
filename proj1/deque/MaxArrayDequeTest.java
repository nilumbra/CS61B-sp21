package deque;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Comparator;

public class MaxArrayDequeTest {
    private static class IC implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1 == null) {
                if (o2 == null) return 0;
                return -1;
            }
            return o1 - o2;
        }
    }

    // C
    private static class SC implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            if (s1 == null) {
                if (s2 == null) return 0;
                return -1;
            }
            s1 = s1.toLowerCase();
            s2 = s2.toLowerCase();

            int minL = Math.min(s1.length(), s2.length());
            // if same character
            for (int i = 0; i < minL; i++) {
                char c1 = s1.charAt(i); char c2= s2.charAt(i);
                if (c1 != c2) return c1 - c2;
            }
            if (s1.length() == s2.length()) {
                return 0;
            }

//            System.out.printf("%s(%d) %s(%d)", s1, s1.length(), s2, s2.length());
            return s1.length() - s2.length(); // whichever that's the prefix is smaller
        }
    }

    @Test
    public void testIntMax() {
        MaxArrayDeque<Integer> mdq =  new MaxArrayDeque<>(new IC());

        Integer max = 10000;
        mdq.addFirst(1);
        mdq.addFirst(2);
        mdq.addFirst(1000);
        mdq.addFirst(max);
        assertTrue(max == mdq.max());

//        System.out.println('1' < 'a');
    }

    @Test
    public void testStringMax() {
        MaxArrayDeque<String> mdq = new MaxArrayDeque<>(new SC());
        String maxStr = "zzzzz";
        mdq.addFirst("Meg");
        mdq.addFirst("Megrez");

        assertTrue(mdq.max() == "Megrez");

        mdq.addFirst("Terry");
        mdq.addFirst("John");
        mdq.addFirst("Z");
        mdq.addLast(maxStr);

        System.out.println(mdq.max());
        assertTrue(mdq.max().equals(maxStr));
    }
}
