public class QuickSort {

    public void sort(int[] arr, int left, int right) {
        if (left < right) {
            int pivot = partition(arr, left, right); 
            sort(arr, left, pivot - 1);
            sort(arr, pivot + 1, right);
        }
    }
    public int partition(int[] arr, int left, int right) {
        int pivot = arr[left];
        while (left < right) {
            while (left < right && arr[right] > pivot) {
                --right; 
            } 
            arr[left] = arr[right];
            while (left < right && arr[left] <= pivot) {
                ++left; 
            }
            arr[right] = arr[left];
        }
        arr[left] = pivot;
        return left;
    }
    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1}; 
        new QuickSort().sort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
    }
}
