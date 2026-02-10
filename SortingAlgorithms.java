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

    // Bubble Sort with swapped optimization
    public void bubbleSort(Record[] arr, int n) {
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            operationCount++; // assignment

            // Last i elements are already in place
            for (int j = 0; j < n - i - 1; j++) {
                operationCount++; // comparison
                if (arr[j].getIdNumber() > arr[j + 1].getIdNumber()) {
                    // Swap arr[j] and arr[j+1]
                    Record temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                    operationCount += 4; // 3 for swap + 1 for boolean assignment
                }
            }

            // If no swapping occurred, array is sorted
            operationCount++; // comparison for if
            if (!swapped) {
                break;
            }
        }
    }
}