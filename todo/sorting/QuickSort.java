package com.example.sorting;

public class QuickSort {

    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }
    public void sort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);
            sort(arr, low, pivot - 1);
            sort(arr, pivot + 1, high);
        }
    }

    public int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        while (low < high) {
            while (low < high && arr[high] > pivot) {
                --high;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] <= pivot) {
                ++low;
            }
            arr[high] = arr[low];
        }
        arr[low] = pivot;
        return low;
    }

    public static void main(String[] args) {
        int[] arr = new int[2_000_000];
        SortUtils.generate(arr);
        SortUtils.shuffle(arr);
        SortUtils.sort(arr, new QuickSort());
        SortUtils.traversal(arr);
    }
}
