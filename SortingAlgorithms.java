/* with implementations ng sorting algorithms.
 */

public class SortingAlgorithms {

    

    // Counter para i-track yung operations (comparisons at swaps)
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
            operationCount++; // for key assign

            // Ilipat yung mga elements na mas malaki sa key forward
            while (j >= 0) {
                operationCount++; // comparison lang
                if (arr[j].getIdNumber() > key.getIdNumber()) {
                    arr[j + 1] = arr[j];
                    operationCount++; // shift operation lang
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
            operationCount++; // assignment again

            // to find minimum element sa unsorted portion
            for (int j = i + 1; j < n; j++) {
                operationCount++; // comparison lang
                if (arr[j].getIdNumber() < arr[minIndex].getIdNumber()) {
                    minIndex = j;
                    operationCount++; // assignment lang
                }
            }

            // I-swap yung nahanap na minimum sa first element ng unsorted portion
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
            operationCount++; // comparison lang
            int q = p + (r - p) / 2;
            operationCount++; // calculation at assignment

            mergeSort(arr, p, q);
            mergeSort(arr, q + 1, r);
            merge(arr, p, q, r);
        }
    }

    private void merge(Record[] arr, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;
        operationCount += 2; // calculations lang

        Record[] left = new Record[n1];
        Record[] right = new Record[n2];

        // I-copy sa temp arrays
        for (int i = 0; i < n1; i++) {
            left[i] = arr[p + i];
            operationCount++; 
        }
        for (int j = 0; j < n2; j++) {
            right[j] = arr[q + 1 + j];
            operationCount++; 
        }

        int i = 0, j = 0;
        int k = p;
        operationCount += 3; 

        // I-merge yung temp arrays 
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
            operationCount += 2; // assignment at increment
        }

        // copy yung remaining elements 
        while (i < n1) {
            arr[k] = left[i];
            i++;
            k++;
            operationCount += 3; // copy at increments
        }

        // copy yung remaining elements ulit sa kbila naman
            arr[k] = right[j];
            j++;
            k++;
            operationCount += 3; // copy at increments
        }
    }

   
    public void quickSort(Record[] arr, int n) {
        quickSortHelper(arr, 0, n - 1);
    }

    /**
     * Recursive helper method para sa Quick Sort. Nagpa-partition siya ng array at
     * recursively nagsosort ng sub-arrays.
     *
     * @param arr yung array na isosort
     * @param low starting index ng sub-array
     * @param high ending index ng sub-array
     */
    private void quickSortHelper(Record[] arr, int low, int high) {
        while (low < high) {
            operationCount++; // comparison para sa loop condition
            int pi = partition(arr, low, high);
            // Recursively sort yung smaller partition muna
            if (pi - low < high - pi) {
                // Yung left partition ang mas maliit
                quickSortHelper(arr, low, pi - 1);
                // Iteratively sort yung right partition
                low = pi + 1;
            } else {
                // Yung right partition ang mas maliit
                quickSortHelper(arr, pi + 1, high);
                // Iteratively sort yung left partition
                high = pi - 1;
            }
        }
    }

    /**
     * Partition method na ginagamit ng Quick Sort. 
     * @param arr yung array na ipapa-partition
     * @param low starting index para sa partition
     * @param high pivot index
     * @return yung index ng pivot element after partitioning
     */
    private int partition(Record[] arr, int low, int high) {
        Record pivot = arr[high];
        operationCount++; // pivot assignment lang
        int i = low - 1;
        operationCount++; // assignment lang
        for (int j = low; j <= high - 1; j++) {
            operationCount++; // comparison lang
            if (arr[j].getIdNumber() <= pivot.getIdNumber()) {
                i++;
                operationCount++; // increment lang
                Record temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                operationCount += 3; // swap operations lang
            }
        }
        Record temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        operationCount += 3; // final swap lang
        return i + 1;
    }
}

