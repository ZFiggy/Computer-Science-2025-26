import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanDecoder {

    private HashMap<String, Character> dictionary;

    public HuffmanDecoder(String codeFile) {
        dictionary = new HashMap<String, Character>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(codeFile));

            char previousChar;
            int count = 0;
            String binary = "";

            while (br.ready()) {
                previousChar = (char) br.read();

                if (previousChar == (char) '\n') {
                    if (!binary.equals("")) {
                        dictionary.put(binary.trim(), (char) count);
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

    public boolean isCode(String binary) {
        return dictionary.containsKey(binary);
        // for (Map.Entry<String, Character> entry : dictionary.entrySet()) {
        // if (binary.equals(entry.getValue())) {
        // return true;
        // }
        // }
        // return false;
    }

    public char decodeChar(String binary) {
        return dictionary.get(binary);
    }

    public void decodeFileFromHuffmanCodes(String encodedFile, String decodedFile) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(encodedFile));
            PrintWriter pw = new PrintWriter(decodedFile);

            int previousBinary;
            String binary = "";

            while (br.ready()) {
                previousBinary = br.read();
                binary += (char) previousBinary;
                if (isCode(binary)) {
                    if (decodeChar(binary) == (char) 26) {
                        break;
                    }
                    pw.write(decodeChar(binary));
                    binary = "";
                }
            }

            br.close();
            pw.close();
        } catch (Exception e) {
            System.out.println("Uh oh.");
        }
    }

    public void decodeFile(String encodedFile) {
        String newFile = encodedFile.substring(0, encodedFile.length() - 4);
        decodeFileHelper(encodedFile);
        decodeFileFromHuffmanCodes("newFile.txt", newFile);
        File f = new File("newFile.txt");
        f.delete();
    }

    public void decodeFileHelper(String encodedFile) {
        if (!encodedFile.substring(encodedFile.length() - 4).equals(".huf")) {
            throw new IllegalArgumentException("Must be a .huf");
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(encodedFile));
            PrintWriter pw = new PrintWriter("newFile.txt");

            char previousChar;

            while (br.ready()) {
                previousChar = (char) br.read();
                int charAsInt = (int) previousChar;
                String binaryConvert = Integer.toBinaryString(charAsInt);
                for (int i = binaryConvert.length(); i < 8; i++) {
                    binaryConvert = '0' + binaryConvert;
                }
                pw.write(binaryConvert);
            }

            br.close();
            pw.close();
        } catch (Exception e) {
            System.out.println("Uh oh.");
        }
    }
}
