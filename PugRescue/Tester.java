public class Tester {
    public static void main(String[] args) {
        SortedArrayList<String> idk = new SortedArrayList<>();
        idk.add("nah");
        idk.add("sure");
        idk.add("jake");
        idk.add("jackson");
        idk.add("abcd");
        System.out.println(idk.toString());
        idk.remove("jackson");
        System.out.println(idk.toString());
        System.out.println(idk.min());
        System.out.println(idk.max());
    }
}
