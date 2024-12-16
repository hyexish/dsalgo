package com.example.sorting;

public class MergeSort {

    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1, new int[arr.length]);
    }

    public void sort(int[] arr, int left, int right, int[] tmp) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            sort(arr, left, mid, tmp);
            sort(arr, mid + 1, right, tmp);
            merge(arr, left, mid, right, tmp);
        }
    }
    public void merge(int[] arr, int left, int mid, int right, int[] tmp) {
        int i = left;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                tmp[t++] = arr[i++];
            } else {
                tmp[t++] = arr[j++];
            }
        }
        while (i <= mid) {
            tmp[t++] = arr[i++];
        }
        while (j <= right) {
            tmp[t++] = arr[j++];
        }
        t = 0;
        while (left <= right) {
            arr[left++] = tmp[t++];
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[20];
        SortUtils.generate(arr);
        SortUtils.shuffle(arr);
//        SortUtils.sort(arr, new MergeSort());
        new MergeSort().sort(arr);
        SortUtils.traversal(arr);
    }
}
