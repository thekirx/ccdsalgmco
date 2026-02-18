/* This file contains implementations of sorting algorithms.
 * You are NOT allowed to modify any of the given method headers.
 */

public class SortingAlgorithms {

    /*
     * You may define additional helper functions here if you need to.
     * Make sure the helper functions have the private access modifier, as
     * they will only be used in this class.
     */

    // Counter for tracking operations (comparisons and swaps)
    private long operationCount;

    public void resetCount() {
        operationCount = 0;
    }

    public long getCount() {
        return operationCount;
    }

    public void insertionSort(Record[] arr, int n) {
        for (int i = 1; i < n; i++) {
            Record key = arr[i];
            int j = i - 1;
            operationCount++; // for assignment of key

            // Move elements greater than key one position ahead
            while (j >= 0) {
                operationCount++; // comparison
                if (arr[j].getIdNumber() > key.getIdNumber()) {
                    arr[j + 1] = arr[j];
                    operationCount++; // shift operation
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
            operationCount++; // assignment
        }
    }

    public void selectionSort(Record[] arr, int n) {
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            operationCount++; // assignment

            // Find the minimum element in unsorted portion
            for (int j = i + 1; j < n; j++) {
                operationCount++; // comparison
                if (arr[j].getIdNumber() < arr[minIndex].getIdNumber()) {
                    minIndex = j;
                    operationCount++; // assignment
                }
            }

            // Swap the found minimum with first element of unsorted portion
            if (minIndex != i) {
                Record temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                operationCount += 3; // swap = 3 assignments
            }
        }
    }

    public void mergeSort(Record[] arr, int p, int r) {
        if (p < r) {
            operationCount++; // comparison
            int q = p + (r - p) / 2;
            operationCount++; // calculation and assignment

            mergeSort(arr, p, q);
            mergeSort(arr, q + 1, r);
            merge(arr, p, q, r);
        }
    }

    private void merge(Record[] arr, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;
        operationCount += 2; // calculations

        Record[] left = new Record[n1];
        Record[] right = new Record[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; i++) {
            left[i] = arr[p + i];
            operationCount++; // copy
        }
        for (int j = 0; j < n2; j++) {
            right[j] = arr[q + 1 + j];
            operationCount++; // copy
        }

        int i = 0, j = 0;
        int k = p;
        operationCount += 3; // assignments

        // Merge temp arrays back
        while (i < n1 && j < n2) {
            operationCount++; // comparison
            if (left[i].getIdNumber() <= right[j].getIdNumber()) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
            operationCount += 2; // assignment and increment
        }

        // Copy remaining elements of left[]
        while (i < n1) {
            arr[k] = left[i];
            i++;
            k++;
            operationCount += 3; // copy and increments
        }

        // Copy remaining elements of right[]
        while (j < n2) {
            arr[k] = right[j];
            j++;
            k++;
            operationCount += 3; // copy and increments
        }
    }

    /*
     * Define AT LEAST ONE more sorting algorithm here, apart from the
     * ones given above. Make sure that the method accepts an array of
     * records
     */

    /**
     * Quick Sort implementation provided as the additional sorting algorithm.  
     * It sorts the array of records in ascending order based on their ID numbers
     * and tracks the number of key operations performed.
     *
     * @param arr the array to be sorted
     * @param n   the number of elements in the array
     */
    public void quickSort(Record[] arr, int n) {
        quickSortHelper(arr, 0, n - 1);
    }

    /**
     * Recursive helper method for Quick Sort. It partitions the array and
     * recursively sorts the sub-arrays.
     *
     * @param arr the array to sort
     * @param low starting index of the sub-array
     * @param high ending index of the sub-array
     */
    private void quickSortHelper(Record[] arr, int low, int high) {
        while (low < high) {
            operationCount++; // comparison for loop condition
            int pi = partition(arr, low, high);
            // Recursively sort the smaller partition first
            if (pi - low < high - pi) {
                // Left partition is smaller
                quickSortHelper(arr, low, pi - 1);
                // Iteratively sort the right partition
                low = pi + 1;
            } else {
                // Right partition is smaller
                quickSortHelper(arr, pi + 1, high);
                // Iteratively sort the left partition
                high = pi - 1;
            }
        }
    }

    /**
     * Partition method used by Quick Sort. It places the pivot element at its
     * correct position and rearranges elements smaller than the pivot to the
     * left and larger ones to the right. Tracks operations for analysis.
     *
     * @param arr the array to partition
     * @param low starting index for the partition
     * @param high pivot index
     * @return the index of the pivot element after partitioning
     */
    private int partition(Record[] arr, int low, int high) {
        Record pivot = arr[high];
        operationCount++; // pivot assignment
        int i = low - 1;
        operationCount++; // assignment
        for (int j = low; j <= high - 1; j++) {
            operationCount++; // comparison
            if (arr[j].getIdNumber() <= pivot.getIdNumber()) {
                i++;
                operationCount++; // increment
                Record temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                operationCount += 3; // swap operations
            }
        }
        Record temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        operationCount += 3; // final swap
        return i + 1;
    }
}

