import java.util.ArrayList;

public class PugSaverTester {
    public static void main(String[] args) {
        MyArrayList<Dog> testList = new MyArrayList<Dog>();
        testList.add(new Dog("Ziggy", "Golden Doodle"));
        testList.add(new Dog("Charlie"));
        testList.add(new Dog("Sammy", "Golden Retriever"));
        testList.add(new Dog("Forrest"));
        testList.add(new Dog("Zack", "Grey Doodle"));
        testList.add(new Dog("Mateo"));
        testList.add(null);
        System.out.println(testList);
        PugSaver.rescuePugs(testList);
        System.out.println(testList);
    }
}
