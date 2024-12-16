#include <stdio.h>

void merge(int arr[], int left, int mid, int right, int tmp[]) {
    int i = left;
    int j = mid + 1;
    int k = 0;
    while (i <= mid && j <= right)
    {
        if (arr[i] <= arr[j]) {
            tmp[k++] = arr[i++];
        } else {
            tmp[k++] = arr[j++];
        }
    }
    while (i <= mid)
    {
        tmp[k++] = arr[i++];
    }
    while (j <= right)
    {
        tmp[k++] = arr[j++];
    }
    k = 0;
    while (left <= right)
    {
        arr[left++] = tmp[k++];
    }
}

void sort(int arr[], int left, int right, int tmp[]) {
    if (left < right) {
        int mid = left + (right - left) / 2;
        sort(arr, left, mid, tmp);
        sort(arr, mid + 1, right, tmp);
        merge(arr, left, mid, right, tmp);
    }
}

int main(int argc, char const *argv[])
{
    int arr[9] = {9, 8, 7, 6, 5, 4, 3, 2, 1};
    int tmp[9] = {};
    sort(arr, 0, 9 - 1, tmp);
    for (int i = 0; i < 9; i++)
    {
        printf("%d, ", arr[i]);
    }
    
    return 0;
}
