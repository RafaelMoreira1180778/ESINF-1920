
package PL;

import java.util.*;

/**
 * @author DEI-ESINF
 */
public class Utils {
    public static <E extends Comparable<E>> Iterable<E> sortByBST(List<E> listUnsorted) {
        Set<E> s = new HashSet<>(listUnsorted);
        List<E> sorted = new ArrayList<>(s);
        Collections.sort(sorted, Comparable::compareTo);
        return sorted;
    }
}
