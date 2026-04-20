public class HuffmanCodeGeneratorConstructorTester {
    public static void main(String[] args) {
        HuffmanCodeGenerator newGenerator = new HuffmanCodeGenerator("ABCDEFG.txt");

        System.out.println(newGenerator.getFrequency('A'));
        System.out.println(newGenerator.getFrequency('a'));
    }
}
