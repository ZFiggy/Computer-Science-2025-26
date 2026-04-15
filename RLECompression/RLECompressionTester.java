import java.io.File;

public class RLECompressionTester {
    public static void main(String[] args) {
        try {
            File f = new File("words.txt.bw");
            File b = new File("words.txt.bw.rle");
            f.delete();
            b.delete();
            File g = new File("decode");
            File c = new File("dec");
            g.delete();
            c.delete();
            RLECompression.compress("words.txt");
            RLECompression.decompress("decode.txt");
        } catch (Exception e) {
            
        }
    }
}
