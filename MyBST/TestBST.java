public class TestBST {
    public static void main(String[] args) {
        MyBST<Integer> newBST = new MyBST<Integer>();
        newBST.add(1);
        newBST.add(2);
        newBST.remove(1);
        System.out.println(newBST.toString());
    }
}
