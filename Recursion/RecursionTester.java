public class RecursionTester {
    public static void main(String[] args) {
        System.out.println(Recursion.countWaysToJumpUpStairs(7));
        String str = "abc";
        Recursion.printPermutations(str);
        Recursion.solveHanoi(3);
        int[] times = {3, 7, 9, 13};
        int[] points = {10, 15, 10, 6};
        System.out.println(Recursion.scavHunt(times, points));
        int[] numbers = {5, 4, 18, 3, 6, 100, 2, 1, 0};
        Recursion.quickSort(numbers);
    }
}
