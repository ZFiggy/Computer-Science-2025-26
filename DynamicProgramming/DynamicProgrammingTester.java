public class DynamicProgrammingTester {
    public static void main(String[] args) {
        int[] lowPayouts = {2, 3, 2, 3};
        int[] highPayouts = {4, 6, 8, 5};

        System.out.println(DynamicProgramming.hiLoStress(lowPayouts, highPayouts));

        int[] times = {3, 5, 6, 7, 10};
        int[] money = {10, 4, 8, 9, 15};
        System.out.println(DynamicProgramming.scavHunt(times, money));
    }
}
