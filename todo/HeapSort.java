public class HeapSort {

    public void sort(int[] arr) {
        int len = arr.length;
        for (int i = len / 2; i >= 0; i--) {
            heapify(arr, i, len);
        }
        for (int i = len - 1; i > 0; i--) {
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            heapify(arr, 0, --len);
        }
    } 

    public void heapify(int[] arr, int i, int len) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int j = i;
        if (left < len && arr[left] > arr[j]) {
            j = left;
        }
        if (right < len && arr[right] > arr[j]) {
            j = right;
        }
        if (j != i) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            heapify(arr, j, len);
        }
    }
    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1}; 
        new HeapSort().sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
    }
}
