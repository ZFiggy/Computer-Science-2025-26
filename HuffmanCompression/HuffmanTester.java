public class HuffmanTester {
    public static void main(String[] args) {
        HuffmanCodeGenerator newGenerator = new HuffmanCodeGenerator("ABCDEFG.txt");

        System.out.println(newGenerator.getFrequency('a'));
    }
}
