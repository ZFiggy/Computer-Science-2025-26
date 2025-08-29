import java.util.ArrayList;

public class PugSaverTester {
    public static void main(String[] args) {
        ArrayList<Dog> testList = new ArrayList<Dog>();
        testList.add(new Dog("Ziggy", "Golden Doodle"));
        testList.add(new Dog("Charlie"));
        PugSaver.rescuePugs(testList);
    }
}
