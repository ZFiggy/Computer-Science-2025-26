import java.util.ArrayList;

public class HuffmanCodeGeneratorTester {
    public static void main(String[] args) {
        HuffmanCodeGenerator newGenerator = new HuffmanCodeGenerator("ABCDEFG.txt");

        System.out.println(newGenerator.getFrequency('A'));
        System.out.println(newGenerator.getFrequency('a'));

        ArrayList<FrequencyNode> sortedList = newGenerator.frequencySort();
        for (FrequencyNode node : sortedList) {
            System.out.println(node.getFrequency());
        }

        newGenerator.createTree();
        FrequencyNode root = newGenerator.getRoot();
        System.out.println(root.getFrequency());
        System.out.println(root.getLeft().getFrequency());
        System.out.println(root.getRight().getFrequency());
    }
}
