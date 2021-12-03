package Demos;

import Algorithms.SearchingAndSortingAlgorithms;

import java.util.Arrays;

public class SearchingAndSortingDemo {

    public static void main(String[] args) {

        Integer[] list = new Integer[10];

        int k = 0;
        for (int i = 10; i != 0; i--) {
            list[k++] = i * 7;
        }

        System.out.println(Arrays.toString(list));

        boolean found = SearchingAndSortingAlgorithms.binarySearch(list, 0, list.length, 42);
        System.out.println(found);

        boolean recursiveFound = SearchingAndSortingAlgorithms.recursiveBinarySearch(list, 0, list.length, 28);
        System.out.println(recursiveFound);

        boolean linearFound = SearchingAndSortingAlgorithms.linearSearch(list, 0, list.length, 14);
        System.out.println(linearFound);

        //SearchingAndSortingAlgorithms.insertionSort(list);
        //System.out.println(Arrays.toString(list));

        //SearchingAndSortingAlgorithms.selectionSort(list);
        //System.out.println(Arrays.toString(list));

        //SearchingAndSortingAlgorithms.bubbleSort(list);
        //System.out.println(Arrays.toString(list));

        SearchingAndSortingAlgorithms.quickSort(list, 0, list.length);
        System.out.println(Arrays.toString(list));

    }
}
