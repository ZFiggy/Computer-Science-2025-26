public class Utils {
    public static <E extends Comparable<E>> int binarySearchRecursiveHelper(E[] arr, E key, int low, int high) {
        if (low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (arr[mid].equals(key)) {
            return mid;
        } else if (arr[mid].compareTo(key) < 0) {
            return binarySearchRecursiveHelper(arr, key, mid + 1, high);
        } else {
            return binarySearchRecursiveHelper(arr, key, low, mid - 1);
        }
    }

    public static <E extends Comparable<E>> int findInsertion(E[] arr, E key, int low, int high) {
        if (low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (binarySearchRecursiveHelper(arr, key, low, mid) < 0 && binarySearchRecursiveHelper(arr, key, mid + 1, high) > 0) {
            return mid;
        } else if (arr[mid].compareTo(key) < 0) {
            return binarySearchRecursiveHelper(arr, key, mid + 1, high);
        } else {
            return binarySearchRecursiveHelper(arr, key, low, mid - 1);
        }
    }
}
