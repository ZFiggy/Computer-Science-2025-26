import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class MarkovPrediction {

    // Please define your own variables and data structures
    //
    public ArrayList<String[]> readData(String filePath) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            ArrayList<String[]> newStrings = new ArrayList<String[]>();
            while ((line = br.readLine()) != null) {
                newStrings.add(line.split(","));
            }

            return newStrings;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to predict the next state given a current state
    public String predictNextState(String currentState) {
        HashMap map = new HashMap<>();

    }

}