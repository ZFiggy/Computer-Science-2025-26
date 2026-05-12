public class DynamicProgrammingTester {
    public static void main(String[] args) {
        int[] lowPayouts = {2, 3, 2, 3};
        int[] highPayouts = {4, 6, 8, 5};

        System.out.println(DynamicProgramming.hiLoStress(lowPayouts, highPayouts));
    }
}
