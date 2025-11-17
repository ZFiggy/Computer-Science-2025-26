public class RecursionTester {
    public static void main(String[] args) {
        System.out.println(Recursion.countWaysToJumpUpStairs(7));
        String str = "hmu";
        Recursion.printSubsets(str);
        Recursion.solveHanoi(3);
        int[] times = {3, 7, 9, 13};
        int[] points = {10, 15, 10, 6};
        System.out.println(Recursion.scavHunt(times, points));
        int[] numbers = {5, 1, 8, 6, 2, 9, -6, -7, 3};
        Recursion.quickSort(numbers);
    }
}
