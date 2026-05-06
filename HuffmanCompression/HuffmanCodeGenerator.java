import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCodeGenerator {

    private HashMap<Character, Integer> map;
    private FrequencyNode root = null;
    private HashMap<Character, String> binaryDictionary;

    public HuffmanCodeGenerator(String frequencyFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(frequencyFile))) {
            map = new HashMap<Character, Integer>();
            binaryDictionary = new HashMap<Character, String>();
            int charAsInt;
            // Read until the end of the stream (-1 is returned)
            while ((charAsInt = reader.read()) != -1) {
                // Cast the integer value to a character
                char character = (char) charAsInt;
                if (map.containsKey(character)) {
                    int value = map.get(character);
                    map.put(character, value + 1);
                } else {
                    map.put(character, 1);
                }
            }
            map.put(((char) 26), 1);
            createTree();
            assignBinary(root);
            makeCodeFile(frequencyFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getFrequency(char c) {
        if (map.containsKey(c)) {
            return map.get(c);
        }
        return 0;
    }

    public FrequencyNode getRoot() {
        return root;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<FrequencyNode> frequencySort() {
        ArrayList<FrequencyNode> frequencyArray = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            frequencyArray.add(new FrequencyNode(entry.getKey(), entry.getValue()));
        }
        Collections.sort(frequencyArray);
        return frequencyArray;
    }

    public void createTree() {
        PriorityQueue<FrequencyNode> pq = new PriorityQueue<FrequencyNode>();

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            pq.add(new FrequencyNode(entry.getKey(), entry.getValue()));
        }

        while (pq.size() > 1) {
            FrequencyNode left = pq.poll();
            FrequencyNode right = pq.poll();
            FrequencyNode parent = new FrequencyNode((char) 0, left.getFrequency() + right.getFrequency());
            parent.setLeft(left);
            parent.setRight(right);
            left.setParent(parent);
            right.setParent(parent);
            pq.add(parent);
        }
        
        root = pq.poll();

        // ArrayList<FrequencyNode> sortedArray = frequencySort();
        // while (sortedArray.size() > 1) {
        //     int last = sortedArray.size() - 1;
        //     int almostLast = sortedArray.size() - 2;
        //     int parentFrequency = sortedArray.get(last).getFrequency() + sortedArray.get(almostLast).getFrequency();

        //     FrequencyNode parent = new FrequencyNode((char) 0, parentFrequency);
        //     parent.setLeft(sortedArray.get(last));
        //     parent.getLeft().setBinary("0");
        //     parent.setRight(sortedArray.get(almostLast));
        //     parent.getRight().setBinary("1");
        //     sortedArray.get(last).setParent(parent);
        //     sortedArray.get(almostLast).setParent(parent);
        //     sortedArray.remove(last);
        //     sortedArray.remove(almostLast);
        //     sortedArray.add(parent);
        //     Collections.sort(sortedArray);
        //     root = parent;
        // }
    }
    
    public void assignBinary(FrequencyNode node) {
        assignBinary(node, new StringBuilder());
    }

    public void assignBinary(FrequencyNode node, StringBuilder sb) {
        if (node.getLeft() == null && node.getRight() == null) {
            binaryDictionary.put(node.getValue(), sb.toString());
        }
        if (node.getLeft() != null) {
            sb.append('0');
            assignBinary(node.getLeft(), sb);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (node.getRight() != null) {
            sb.append('1');
            assignBinary(node.getRight(), sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public String getCode(char c) {
        if (binaryDictionary.get(c) == null) {
            return "";
        }
        return binaryDictionary.get(c);
    }

    // public FrequencyNode getNode(FrequencyNode node, char c) {
    //     if (c == (char) 0) {
    //         return null;
    //     }
    //     if (node.getValue() == c) {
    //         return node;
    //     } else {
    //         if (node.getLeft() != null) {
    //             FrequencyNode leftNode = getNode(node.getLeft(), c);
    //             if (leftNode != null) {
    //                 return leftNode;
    //             }
    //         }
    //         if (node.getRight() != null) {
    //             FrequencyNode rightNode = getNode(node.getRight(), c);
    //             if (rightNode != null) {
    //                 return rightNode;
    //             }
    //         }
    //     }
    //     return null;
    // }

    public void makeCodeFile(String codeFile) throws IOException {
        try {
            PrintWriter pw = new PrintWriter("studentCodeFile.txt");

            for (int i = 0; i < 128; i++) {
                pw.println(getCode((char) i));
            }
            pw.close();
        } catch (Exception e) {
            System.out.println("Uh oh.");
        }
        // try {
        // BufferedReader br = new BufferedReader(new FileReader(codeFile));
        // PrintWriter pw = new PrintWriter(codeFile + ".huf");

        // StringBuilder toReturn = new StringBuilder();

        // char previousChar = (char) br.read();

        // while (br.ready()) {
        // previousChar = (char) br.read();
        // String toAdd = getCode(previousChar);
        // toReturn.append(toAdd);
        // }

        // br.close();
        // pw.write(toReturn.toString());
        // pw.close();
        // } catch (Exception e) {
        // System.out.println("Uh oh.");
        // }
    }
}
