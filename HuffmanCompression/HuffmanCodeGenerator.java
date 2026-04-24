import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanCodeGenerator {

    private HashMap<Character, Integer> map;
    private FrequencyNode root = null;

    public HuffmanCodeGenerator(String frequencyFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(frequencyFile))) {
            map = new HashMap<Character, Integer>();
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

    @SuppressWarnings("unchecked")
    public void createTree() {
        ArrayList<FrequencyNode> sortedArray = frequencySort();
        while (sortedArray.size() > 1) {
            int last = sortedArray.size() - 1;
            int almostLast = sortedArray.size() - 2;
            int parentFrequency = sortedArray.get(last).getFrequency() + sortedArray.get(almostLast).getFrequency();

            FrequencyNode parent = new FrequencyNode(null, parentFrequency);
            parent.setLeft(sortedArray.get(last));
            parent.setRight(sortedArray.get(almostLast));
            sortedArray.get(last).setParent(parent);
            sortedArray.get(almostLast).setParent(parent);
            sortedArray.remove(last);
            sortedArray.remove(almostLast);
            sortedArray.add(parent);
            Collections.sort(sortedArray);
            root = parent;
        }
    }

    public void setBinary() {
        while(root.getLeft() != null || root.getRight() != null) {
            if (root.getLeft() != null) {
                root.getLeft().setBinary(0);
                // Need to figure out how to loop through everything and make sure that the node resets to a next node. Maybe use the arraylist from before? Probably a bad idea though. Not a part of my Huffman Day 3 Submission just trying to work on it
            }
        }
    }
}
