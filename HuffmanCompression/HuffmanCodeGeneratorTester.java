import java.io.IOException;
import java.util.ArrayList;

public class HuffmanCodeGeneratorTester {
    public static void main(String[] args) {
        HuffmanCodeGenerator newGenerator;
        try {
            newGenerator = new HuffmanCodeGenerator("ABCDEFG.txt");
            HuffmanCodeGeneratorTester.treePrinter(newGenerator.getRoot(), 0);
            System.out.println(newGenerator.getCode('D'));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void treePrinter(FrequencyNode node, int givenDepth) {
        char root = node.getValue();
        int rootFrequency = node.getFrequency();
        for (int i = 0; i < givenDepth; i++) {
            System.out.print("    ");
        }
        System.out.println("{" + root + "}" + " | Frequency: " + rootFrequency + " | Binary: " + node.getBinary());

        if (node.getLeft() != null) {
            treePrinter(node.getLeft(), givenDepth + 1);
        }
        if (node.getRight() != null) {
            treePrinter(node.getRight(), givenDepth + 1);
        }
    }
}
