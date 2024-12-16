package com.example.sorting;

import java.util.Random;

public class SortUtils {

    public static void testSort(int len, Object obj) {
        int[] arr = new int[len];
        generate(arr);
        shuffle(arr);
        long start = System.currentTimeMillis();
        sort(arr, obj);
        long end = System.currentTimeMillis();
        System.out.println("time out: " + (end - start) + "ms");
    }

    public static void sort(int[] arr, Object obj) {
        if (obj instanceof QuickSort) {
            ((QuickSort) obj).sort(arr);
        }
        if (obj instanceof MergeSort) {
            ((MergeSort) obj).sort(arr);
        }
        if (obj instanceof HeapSort) {
            ((HeapSort) obj).sort(arr);
        }
        if (obj instanceof BubbleSort) {
            ((BubbleSort) obj).sort(arr);
        }
    }

    public static void traversal(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
    }

    public static void generate(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
    }

    public static void shuffle(int[] arr) {
        Random rd = new Random();
        for (int i = 0; i < arr.length; i++) {
            swap(arr, rd.nextInt(arr.length), rd.nextInt(arr.length));
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
