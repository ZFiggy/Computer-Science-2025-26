import java.util.ArrayList;
import java.util.Objects;

public class PugSaver {

	//Moves every dog whose breed is "Pug" in the list to the back of the list
	public static void rescuePugs(ArrayList<Dog> list) {
		ArrayList<Dog> goodBoys = new ArrayList<Dog>();
		ArrayList<Dog> badBoys = new ArrayList<Dog>();
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
		list.addAll(goodBoys);
	}
}
