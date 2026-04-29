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

    private HashMap<Character, String> dictionary;

    public HuffmanEncoder(String codeFile) {
        dictionary = new HashMap<Character, String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(codeFile));

            char previousChar;
            int count = 0;
            String binary = "";

            while (br.ready()) {
                previousChar = (char) br.read();

                if (previousChar == (char) '\n') {
                    if (!binary.equals("")) {
                        dictionary.put((char) count, binary);
                        binary = "";
                    }
                    count++;
                } else {
                    binary += previousChar;
                }
            }

            br.close();
        } catch (Exception e) {
            System.out.println("Uh oh.");
        }
    }

    public String encodeChar(char input) {
        if (dictionary.get(input) == null) {
            return "";
        }
        return dictionary.get(input);
    }

    public void encodeFileToHuffmanCodes(String fileToCompress, String encodedFile) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileToCompress));
            PrintWriter pw = new PrintWriter(encodedFile);

            int count = 0;
            char previousChar;

            while (br.ready()) {
                previousChar = (char) br.read();
                pw.write(encodeChar(previousChar));
                count += encodeChar(previousChar).length();
            }
            pw.write(encodeChar((char) 26));
            count += encodeChar((char) 26).length();
            int add = ((8 - (count % 8)) % 8);
            for (int i = 0; i < add; i++) {
                pw.write('0');
            }

            br.close();
            pw.close();
        } catch (Exception e) {
            System.out.println("Uh oh.");
        }
    }
}
