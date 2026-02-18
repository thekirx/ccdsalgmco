import java.util.Arrays;

public class Main {

    // for averaging
    private static final int NUM_RUNS = 5;

    // File paths ng mga dataset
    private static final String[] DATASETS = {
        "data/random100.txt",
        "data/random25000.txt",
        "data/random50000.txt",
        "data/random75000.txt",
        "data/random100000.txt",
        "data/almostsorted.txt",
        "data/totallyreversed.txt"
    };

    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        SortingAlgorithms sorter = new SortingAlgorithms();

        System.out.println("================================================================================");
        System.out.println("                    SORTING ALGORITHM BENCHMARK RESULTS                         ");
        System.out.println("================================================================================\n");

        for (String datasetPath : DATASETS) {
            System.out.println("Dataset: " + datasetPath);
            System.out.println("--------------------------------------------------------------------------------");

            // read original data ng isang beses lang
            Record[] originalData = fileReader.readFile(datasetPath);
            if (originalData == null) {
                System.err.println("Failed to load dataset: " + datasetPath);
                continue;
            }
            int n = originalData.length;
            System.out.println("Number of records: " + n);
            System.out.println();

            // Test Insertion Sort
            runBenchmark("Insertion Sort", originalData, n, sorter, (arr, size, sort) -> {
                sort.insertionSort(arr, size);
            });

            // Test Selection Sort
            runBenchmark("Selection Sort", originalData, n, sorter, (arr, size, sort) -> {
                sort.selectionSort(arr, size);
            });

            // Test Merge Sort
            runBenchmark("Merge Sort", originalData, n, sorter, (arr, size, sort) -> {
                sort.mergeSort(arr, 0, size - 1);
            });

            // Test Quick Sort
            runBenchmark("Quick Sort", originalData, n, sorter, (arr, size, sort) -> {
                sort.quickSort(arr, size);
            });

            System.out.println("================================================================================\n");
        }
    }

    // Functional interface para sa pag-execute ng sorting algorithm
    @FunctionalInterface
    interface SortExecutor {
        void execute(Record[] arr, int n, SortingAlgorithms sorter);
    }

    private static void runBenchmark(String algorithmName, Record[] originalData, int n,
                                     SortingAlgorithms sorter, SortExecutor executor) {
        long totalTime = 0;
        long totalOperations = 0;
        boolean allSorted = true;

        System.out.println("  " + algorithmName + ":");

        for (int run = 0; run < NUM_RUNS; run++) {
            // Gumawa ng fresh copy ng original unsorted data
            Record[] dataCopy = Arrays.copyOf(originalData, n);

            // I-reset yung operation counter
            sorter.resetCount();

            // I-measure yung execution time (hindi kasama file reading/printing)
            long startTime = System.currentTimeMillis();
            executor.execute(dataCopy, n, sorter);
            long endTime = System.currentTimeMillis();

            long executionTime = endTime - startTime;
            long operationCount = sorter.getCount();

            totalTime += executionTime;
            totalOperations += operationCount;

            // I-verify kung sorted na yung array
            if (!isSorted(dataCopy)) {
                allSorted = false;
                System.err.println("    WARNING: Run " + (run + 1) + " did not produce sorted output!");
            }
        }

        double avgTime = (double) totalTime / NUM_RUNS;
        double avgOperations = (double) totalOperations / NUM_RUNS;

        System.out.println("    Average Execution Time: " + String.format("%.3f", avgTime) + " ms");
        System.out.println("    Average Operation Count: " + String.format("%.0f", avgOperations));
        System.out.println("    Verification: " + (allSorted ? "PASSED" : "FAILED"));
        System.out.println();
    }

    // Helper method para i-check kung sorted na yung array in ascending order by ID
    private static boolean isSorted(Record[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].getIdNumber() > arr[i + 1].getIdNumber()) {
                return false;
            }
        }
        return true;
    }
}
