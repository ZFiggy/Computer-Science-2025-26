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

public class HuffmanEncoder {

    private HashMap<Character, Integer> dictionary;

    public HuffmanEncoder(String codeFile) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(codeFile));
            PrintWriter pw = new PrintWriter(codeFile + ".huf");

            char previousChar = (char) br.read();
            int count = 1;
            String binaryFrequency = "";

            while (br.ready()) {
                previousChar = (char) br.read();

                if (previousChar == (char) '\n') {
                    if (!binaryFrequency.equals("")) {
                        dictionary.put(previousChar, count);
                        binaryFrequency = "";
                    }
                    count++;
                } else {
                    binaryFrequency += previousChar;
                }
            }

            br.close();
            pw.write(toReturn.toString());
            pw.close();
        } catch (Exception e) {
            System.out.println("Uh oh.");
        }
    }
}
