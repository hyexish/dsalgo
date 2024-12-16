package com.example.sorting;

import java.util.Arrays;

public class HeapSort {

    public void sort(int[] arr) {
        int len = arr.length;
        for (int i = len / 2; i >= 0; i--) {
            heapify(arr, i, len);
        }
        for (int i = len - 1; i > 0; i--) {
            SortUtils.swap(arr, 0, i);
            heapify(arr, 0, --len);
        }
    }

    public void heapify(int[] arr, int i, int len) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        if (left < len && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < len && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            SortUtils.swap(arr, i, largest);
            heapify(arr, largest, len);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[2_000_000];
        SortUtils.generate(arr);
        SortUtils.shuffle(arr);
        SortUtils.sort(arr, new HeapSort());
        SortUtils.traversal(arr);

    }
}
