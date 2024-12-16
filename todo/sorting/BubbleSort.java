package com.example.sorting;

public class BubbleSort {

    public void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    SortUtils.swap(arr, j, j + 1);
                }
            }
        }
    }

}
