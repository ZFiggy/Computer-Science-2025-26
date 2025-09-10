import java.util.ArrayList;

public class PugSaverTester {
    public static void main(String[] args) {
        ArrayList<Dog> testList = new ArrayList<Dog>();
        for (int i = 0; i < 1000000; i++) {
            testList.add(new Dog("Ziggy", "Golden Doodle"));
            testList.add(new Dog("Charlie"));
            testList.add(new Dog("Sammy", "Golden Retriever"));
            testList.add(new Dog("Forrest"));
            testList.add(new Dog("Zack", "Grey Doodle"));
            testList.add(new Dog("Mateo"));
        }
        //System.out.println(testList);
        PugSaver.rescuePugs(testList);
        System.out.println(testList);
        if (!testList.get(2999999).getName().contains("golden")) {
            System.out.println("Correct!");
        }
    }
}
