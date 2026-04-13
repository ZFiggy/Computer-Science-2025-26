import java.io.File;

public class RLECompressionTester {
    public static void main(String[] args) {
        try {
            File f = (new File("words.txt.bw"));
            File b = new File("words.txt.bw.rle");
            f.delete();
            b.delete();
            RLECompression.compress("words.txt");
            RLECompression.decompress("words.txt.bw.rle");
        } catch (Exception e) {
            
        }
    }
}
