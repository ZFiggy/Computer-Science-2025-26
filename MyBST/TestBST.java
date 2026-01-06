public class TestBST {
    public static void main(String[] args) {
        MyBST<Integer> newBST = new MyBST<>();
        newBST.add(10);
        newBST.add(5);
        newBST.add(6);
        newBST.add(21);
        newBST.add(12);
        System.out.println(newBST.toString());
    }
}
