import java.util.ArrayList;
import java.util.Objects;

public class PugSaver { // *******MAKE IT TO O(N) BY GETTING RID OF ADD WITHIN FOR LOOPS*******

	//Moves every dog whose breed is "Pug" in the list to the back of the list
	public static void rescuePugs(MyArrayList<Dog> list) {
		// int goldenCounter = 0;
		// for (int i = 0; i < list.size(); i++) {
		// 	if (list.get(i) == null) {
		// 		throw new IllegalArgumentException();
		// 	}
		// 	String breed = list.get(i).getBreed();
		// 	if (breed.toLowerCase().contains("golden")) {
		// 		Dog temp = list.get(i);

		// 		for (int j = list.size() - goldenCounter - 1; j > 0; j--) {
		// 			if (i >= j) {
		// 				break;
		// 			}
		// 			if (!list.get(j).getBreed().toLowerCase().contains("golden")) {
		// 				list.set(i, list.get(j));
		// 				list.set(j, temp);
		// 				goldenCounter++;
		// 			}
		// 		}
		// 		if (goldenCounter + i > list.size()) {
		// 			break;
		// 		}
		// 	}
		// }
		MyArrayList<Dog> goodBoys = new MyArrayList<Dog>();
		MyArrayList<Dog> badBoys = new MyArrayList<Dog>();
		int end = list.size() - 1;
		for (int i = 0; i < list.size(); i++) {
			if (end == i) {
					break;
				}
			if (list.get(i).getBreed().toLowerCase().contains("golden")) {
				goodBoys.add(list.get(i));
				list.set(i, list.get(end));
				list.set(end, goodBoys.get(goodBoys.size() - 1));
				end--;
				i--;
			} else {
				badBoys.add(list.get(i));
			}
		}
		list = badBoys;
		for (int i = 0; i < goodBoys.size(); i++) {
			list.add(goodBoys.get(i));
		}
	}
}
