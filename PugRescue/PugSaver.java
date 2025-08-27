import java.util.ArrayList;
import java.util.Objects;

public class PugSaver {

	//Moves every dog whose breed is "Pug" in the list to the back of the list
	public static void rescuePugs(ArrayList<Dog> list) {
		ArrayList<Dog> goodBoys = new ArrayList<Dog>();
		ArrayList<Dog> badBoys = new ArrayList<Dog>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getBreed().toLowerCase().contains("golden")) {
				goodBoys.add(list.get(i));
				list.remove(i);
				i--;
			}
		}
		list.addAll(goodBoys);
	}
}
