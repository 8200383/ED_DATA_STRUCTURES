package Algorithms;

public class SearchingAndSortingAlgorithms {

    /**
     * Searches the specified array of objects using a
     * linear search algorithm.
     *
     * @param data   the array to be sorted
     * @param min    the integer representation of the start position
     * @param max    the integer representation of the ending value
     * @param target the element being searched for
     * @return true if the desired element is found
     */
    public static <T extends Comparable<? super T>> boolean linearSearch(T[] data, int min, int max, T target) {
        int index = min;
        while (index < max) {
            if (data[index].compareTo(target) == 0) {
                return true;
            }

            index++;
        }

        return false;
    }

    /**
     * Searches the specified array of objects using a
     * binary search algorithm. (Recursive)
     *
     * @param data   the array to be sorted
     * @param min    the integer representation of the minimum value
     * @param max    the integer representation of the maximum value
     * @param target the element being searched for
     * @return true if the desired element is found
     */
    public static <T extends Comparable<? super T>> boolean recursiveBinarySearch(T[] data, int min, int max, T target) {
        int middle = (min + max) / 2;

        if (data[middle].compareTo(target) == 0) {
            return true;
        }

        if (data[middle].compareTo(target) > 0) {
            min = middle + 1;
            return recursiveBinarySearch(data, min, max, target);
        }

        if (data[middle].compareTo(target) < 0) {
            max = middle - 1;
            return recursiveBinarySearch(data, min, max, target);
        }

        return false;
    }

    /**
     * Searches the specified array of objects using a
     * binary search algorithm. (Non-Recursive)
     *
     * @param data   the array to be sorted
     * @param min    the integer representation of the minimum value
     * @param max    the integer representation of the maximum value
     * @param target the element being searched for
     * @return true if the desired element is found
     */
    public static <T extends Comparable<? super T>> boolean binarySearch(T[] data, int min, int max, T target) {
        while (min <= max) {
            int middle = (min + max) / 2;

            if (data[middle].compareTo(target) == 0) {
                return true;
            } else if (data[middle].compareTo(target) > 0) {
                min = middle + 1;
            } else {
                max = middle - 1;
            }
        }

        return false;
    }

    /**
     * Sorts the specified array of integers using the selection sort algorithm.
     *
     * @param data the array to be sorted
     */
    public static <T extends Comparable<? super T>> void selectionSort(T[] data) {

        for (int i = 0; i < data.length - 1; i++) {
            int min = i;

            for (int j = i + 1; j < data.length; j++) {
                if (data[j].compareTo(data[min]) < 0) {
                    min = j;
                }
            }

            T tmp = data[i];
            data[i] = data[min];
            data[min] = tmp;
        }
    }

    /**
     * Sorts the specified array of objects using an insertion sort algorithm.
     *
     * @param data the array to be sorted
     */
    public static <T extends Comparable<? super T>> void insertionSort(T[] data) {

        for (int i = 0; i < data.length; i++) {
            T key = data[i];
            int j = i - 1;

            while (j >= 0 && key.compareTo(data[j]) < 0) {
                data[j + 1] = data[j];
                j--;
            }

            data[j + 1] = key;
        }
    }

    /**
     * Sorts the specified array of objects using a bubble sort algorithm.
     *
     * @param data the array to be sorted
     */
    public static <T extends Comparable<? super T>> void bubbleSort(T[] data) {

        //for (int i = data.length - 1; i >= 0; i--) {
        //    for (int j = 0; j <= i - 1; j++) {
        //        if (data[j].compareTo(data[j + 1]) > 0) {
        //            T tmp = data[j];
        //            data[j] = data[j + 1];
        //            data[j + 1] = tmp;
        //        }
        //    }
        //}

        boolean swapped;
        int indexOfLastUnsortedElement = data.length;
        do {
            swapped = false;
            for (int i = 0; i < indexOfLastUnsortedElement - 1; i++) {
                if (data[i].compareTo(data[i + 1]) > 0) {
                    T tmp = data[i];
                    data[i] = data[i + 1];
                    data[i + 1] = tmp;
                    swapped = true;
                }
            }
            indexOfLastUnsortedElement--;
        } while (swapped);
    }

    /**
     * Sorts the specified array of objects using the quick sort
     * algorithm.
     *
     * @param data the array to be sorted
     * @param min  the integer representation of the minimum value
     * @param max  the integer representation of the maximum value
     */
    public static <T extends Comparable<? super T>> void quickSort(T[] data, int min, int max) {
        int idxPartition;

        while (max - min > 0) {
            idxPartition = findPartition(data, min, max);

            /* Left side until pivot-1 */
            quickSort(data, min, idxPartition - 1);

            /* Right side from pivot+1 until max */
            quickSort(data, idxPartition + 1, max);
        }
    }

    /**
     * Used by the quick sort algorithm to find the partition.
     *
     * @param data the array to be sorted
     * @param min  the integer representation of the minimum value
     * @param max  the integer representation of the maximum value
     */
    private static <T extends Comparable<? super T>> int findPartition(T[] data, int min, int max) {

        int pivot = (min + max) / 2;
        int left = min;
        int right = max;

        while (left < right) {
            /* Move the smaller things to the left */
            while (data[left].compareTo(data[pivot]) < 0) {
                left++;
            }

            /* Move the bigger things to the right */
            while (data[right].compareTo(data[pivot]) > 0) {
                right--;
            }

            if (left < right) {
                T tmp = data[left];
                data[left] = data[right];
                data[right] = tmp;
            }
        }

        T tmp = data[min];
        data[min] = data[right];
        data[right] = tmp;

        return right;
    }
}
