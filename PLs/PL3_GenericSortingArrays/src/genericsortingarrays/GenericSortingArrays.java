package genericsortingarrays;

import java.util.Arrays;

/**
 * @author DEI-ISEP
 */


public class GenericSortingArrays {

    /**
     * Swaps two vector positions                  O(1)
     */
    public static <E> void swap(E[] v, int i, int j) {
        E temp = v[i];
        v[i] = v[j];
        v[j] = temp;
    }

    //  printArray
    public static <E> void printArray(E[] v) {
        for (E element : v)
            System.out.println(", " + element);
    }

    /**
     * Selection Sort Algorithm
     */
    public static <E extends Comparable<E>> void selectionSort(E[] v) {
        int min;
        for (int i = 0; i < v.length - 1; i++) {
            min = i;
            for (int j = i + 1; j < v.length; j++) {
                if (v[j].compareTo(v[min]) < 0) {
                    min = j;
                }
            }
            swap(v, i, min);
        }
    }

    /**
     * Bubble Sort Algorithm
     *
     * @param v
     */
    public static <E extends Comparable<E>> void bubbleSort(E[] v) {
        boolean swap = true;
        for (int i = 0; i < v.length - 1 && swap; i++) {
            swap = false;
            for (int j = v.length - 1; j > i; j--) {
                if (v[j - 1].compareTo(v[j]) > 0) {
                    swap(v, i, j - 1);
                    swap = true;
                }
            }
        }
    }

    /**
     * insertionSort Algorithm
     */
    public static <E extends Comparable<E>> void insertionSort(E[] v) {
        int n = v.length;

        int i, j;
        E x;
        for (i = 1; i < n; i++) {
            j = i;
            x = v[i];
            while ((j > 0) && (x.compareTo(v[j - 1]) < 0)) {
                v[j] = v[j - 1];
                j = j - 1;
            }
            v[j] = x;
        }
    }

    /**
     * Mergesort Algorithm
     */
    private static <E extends Comparable<E>> void merge(E[] S1, E[] S2, E[] S) {
        int i = 0, j = 0, k = 0;
        int left = (S.length) / 2;
        int right = (S.length) - left;
        while (i < left && j < right) {
            if (S1[i].compareTo(S2[j]) <= 0) {
                S[k++] = S1[i++];
            } else {
                S[k++] = S2[j++];
            }
        }
        while (i < left) {
            S[k++] = S1[i++];
        }
        while (j < right) {
            S[k++] = S2[j++];
        }
    }

    public static <E extends Comparable<E>> void mergeSort(E[] S) {
        if (S.length >= 2) {
            int mid = (S.length) / 2;
            E[] S1 = Arrays.copyOfRange(S, 0, mid);
            E[] S2 = Arrays.copyOfRange(S, mid, S.length);
            mergeSort(S1);
            mergeSort(S2);
            merge(S1, S2, S);
        }
    }

    /**
     * Quicksort Algorithm
     */
    public static <E extends Comparable<E>> void quickSort(E v[]) {
        E pivot = v[(v.length - 1) / 2];
        int i = 0;
        int j = v.length - 1;
        while (i <= j) {
            while (v[i].compareTo(pivot) < 0) {
                i++;
            }
            while (v[j].compareTo(pivot) > 0) {
                j--;
            }
            if (i <= j) {
                swap(v, i, j);
                i++;
                j--;
            }
            if (0 < j) quickSort(v, 0, j);
            if (v.length - 1 > i) quickSort(v, i, v.length - 1);
        }
    }

    private static <E extends Comparable<E>> void quickSort(E v[], int left, int right) {
        E pivot = v[(left + right) / 2];
        int i = left;
        int j = right;
        while (i <= j) {
            while (v[i].compareTo(pivot) < 0) {
                i++;
            }
            while (v[j].compareTo(pivot) > 0) {
                j--;
            }
            if (i <= j) {
                swap(v, i, j);
                i++;
                j--;
            }
        }
        if (left < j) quickSort(v, left, j);
        if (right > i) quickSort(v, i, right);
    }
}
