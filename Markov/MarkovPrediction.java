import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class MarkovPrediction {

    // Please define your own variables and data structures
    //
    public static HashMap<String, ArrayList<String>> readData(String filePath) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            ArrayList<String[]> newStrings = new ArrayList<String[]>();
            while ((line = br.readLine()) != null) {
                newStrings.add(line.split(","));
            }
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        for (int i = 0; i < newStrings.size(); i++) {
            if (map.containsKey(newStrings.get(i)[0])) {
                map.get(newStrings.get(i)[0]).add(newStrings.get(i)[1]);
            } else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(newStrings.get(i)[1]);
                map.put(newStrings.get(i)[0], list);
            }
        }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to predict the next state given a current state
    public static String predictNextState(String currentState, String filePath) {
        HashMap<String, ArrayList<String>> map = readData(filePath);
        ArrayList<String> list = map.get(currentState);
        int number = (int) (Math.random() * list.size());
        return list.get(number);
    }

}