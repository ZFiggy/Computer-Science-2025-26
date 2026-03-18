public class MiniGPTTester {
    public static void main(String[] args) {
        MiniGPT myGPT = new MiniGPT("thegreatgatsby.txt", 10);
        myGPT.generateText("thegreatgatsby.txt", 1000);
    }
}
