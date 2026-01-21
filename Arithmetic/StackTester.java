public class StackTester {
    public static void main(String[] args) {
        MyStack<String> s = new MyStack<>();

        s.push("matteo");
        String str = s.peek();
        System.out.println(str);
        s.push("zack");
        System.out.println(s.pop());
        System.out.println(s.pop());
    }
}
