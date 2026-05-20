public class DynamicProgrammingTester {
    public static void main(String[] args) {
        int[] lowPayouts = {1, 2, 3};
        int[] highPayouts = {4, 5, 6};

        System.out.println(DynamicProgramming.hiLoStress(lowPayouts, highPayouts));

        int[] times = {3, 5, 6, 7, 10};
        int[] money = {10, 4, 8, 9, 15};
        System.out.println(DynamicProgramming.scavHunt(times, money));

        int[][] matrix = {
            {4, 5, 8, 0},
            {3, 2, 1, 0},
            {4, 3, 4, 9}
        };

        System.out.println(DynamicProgramming.dynamicCookies(matrix));
    }
}
