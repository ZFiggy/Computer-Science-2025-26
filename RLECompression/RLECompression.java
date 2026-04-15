import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

public class RLECompression {

    // Creates a compressed version with fileName + ".rle.bw"
    public static void compress(String fileName) throws IOException {
        bwTransform(fileName);
        encode(fileName + ".bw");
    }

    // Decompresses a .rle.bw file
    public static void decompress(String fileName) throws IOException {
        decode(fileName);
        invertBWTransform(fileName.substring(0, fileName.length() - 4));
    }

    // Encodes the contents of a file using the RLE compression scheme:
    // single characters are left alone, and runs of 2+ characters are encoded as
    // that letter twice, followed by the length of the run, cast as a char
    public static void encode(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        PrintWriter pw = new PrintWriter(fileName + ".rle");

        char previousChar = (char) br.read();
        int count = 1;

        if (!(((int) previousChar) == -1)) {
            while (br.ready()) {
                char c = (char) br.read();

                if (previousChar == c) {
                    count++;
                } else {
                    if (count == 1) {
                        pw.write(previousChar);
                        previousChar = c;
                    } else {
                        pw.write(previousChar);
                        pw.write(previousChar);
                        pw.write((char) count + '0');
                        previousChar = c;
                        count = 1;
                    }
                }
                // TO-DO
                // Now here: do things with the char you just read, dependent on the char you
                // just read
            }
            if (count == 1) {
                pw.write(previousChar);
            } else {
                pw.write(previousChar);
                pw.write(previousChar);
                pw.write((char) count + '0');
            }
        }
        br.close();
        pw.close();
    }

    // Decodes the above scheme
    // AA4BB2
    public static void decode(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        PrintWriter pw = new PrintWriter(fileName.substring(0, fileName.length() - 4));

        char previousChar = (char) br.read();
        int repeat = 1;

        while (br.ready()) {
            char c = (char) br.read();

            if ((isNumber(c) && repeat == 2)) {
                for (int i = 0; i < ((int) c) - 48; i++) {
                    pw.write(previousChar);
                }
                repeat = 0;
            } else if (c == previousChar && repeat == 1) {
                repeat++;
            } else {
                if (repeat == 1) {
                    pw.write(previousChar);
                }
                previousChar = c;
                repeat = 1;
            }
            // TO-DO
            // Now here: do things with the char you just read, dependent on the char you
            // just read
        }

        if (repeat == 1) {
            pw.write(previousChar);
        }

        br.close();
        pw.close();
    }

    public static boolean isNumber(char c) {
        if (c < 48 || c > 57) {
            return false;
        } else {
            return true;
        }
    }

    public static void bwTransform(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        // Add a null character at the beginning, as a
        // "beginning of file" character
        StringBuilder originalText = new StringBuilder("" + '\0');

        while (br.ready()) {
            char c = (char) br.read();
            originalText.append(c);
        }
        br.close();

        String[] rotations = new String[originalText.length()];
        rotations[0] = originalText.toString();
        // TO-DO
        // Now do the Burrows-Wheeler Transform
        String useThis = rotations[0];
        for (int i = 0; i < originalText.length(); i++) {
            StringBuilder toAdd = new StringBuilder(
                    useThis.charAt(useThis.length() - 1) + useThis.substring(0, useThis.length() - 1));
            useThis = toAdd.toString();
            rotations[i] = toAdd.toString();
        }

        rotations = alphabetize(rotations);

        StringBuilder finalAnswer = new StringBuilder();

        for (int i = 0; i < rotations.length; i++) {
            finalAnswer.append(rotations[i].charAt(rotations.length - 1));
        }

        // And then write the transformation into a file
        PrintWriter pw = new PrintWriter(fileName + ".bw");
        pw.write(finalAnswer.toString());
        pw.close();
    }

    public static String[] alphabetize(String[] rotations) {
        ArrayList<String> rotationsArray = new ArrayList<String>(rotations.length);

        for (int i = 0; i < rotations.length; i++) {
            rotationsArray.add(rotations[i]);
        }

        Collections.sort(rotationsArray);

        String[] newRotations = new String[rotations.length];

        for (int i = 0; i < rotations.length; i++) {
            newRotations[i] = rotationsArray.get(i);
        }

        return newRotations;
    }

    public static void invertBWTransform(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        StringBuilder originalText = new StringBuilder();

        while (br.ready()) {
            char c = (char) br.read();
            originalText.append(c);
        }
        br.close();

        StringBuilder[] reconstructions = new StringBuilder[originalText.length()];
        for (int i = 0; i < reconstructions.length; i++) {
            reconstructions[i] = new StringBuilder("" + originalText.charAt(i));
        }
        // TO-DO
        // Now undo the Burrows-Wheeler transform

        for (int j = 0; j < reconstructions.length - 1; j++) {

            ArrayList<String> newRoationsArray = new ArrayList<String>(originalText.length());
            for (int i = 0; i < originalText.length(); i++) {
                newRoationsArray.add(reconstructions[i].toString());
            }

            Collections.sort(newRoationsArray);

            for (int i = 0; i < originalText.length(); i++) {
                reconstructions[i] = new StringBuilder(originalText.charAt(i) + newRoationsArray.get(i));
            }
        }

        for (int i = 0; i < reconstructions.length; i++) {
            if (reconstructions[i].charAt(0) == '\0') {
                reconstructions[0] = reconstructions[i];
                break;
            }
        }

        // TO-DO
        // And write the appropriate reconstruction into the file, without the null char
        PrintWriter pw = new PrintWriter(fileName.substring(0, fileName.length() - 3));
        pw.write(reconstructions[0].substring(1));
        pw.close();
    }
}
