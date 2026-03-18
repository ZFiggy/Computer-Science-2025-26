import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.StringBuilder;
import java.util.Map;
import java.util.Set;

public class MiniGPT {

	private HashMap<String, ArrayList<Character>> map;

	public MiniGPT(String fileName, int chainOrder) {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			map = new HashMap<String, ArrayList<Character>>();
			int charAsInt;
			ArrayList<Character> chars = new ArrayList<>();
			// Read until the end of the stream (-1 is returned)
			while ((charAsInt = reader.read()) != -1) {
				// Cast the integer value to a character
				char character = (char) charAsInt;
				chars.add((Character) character);
			}
			for (int i = 0; i < chars.size() - chainOrder - 1; i++) {
				StringBuilder newString = new StringBuilder();
				for (int j = 0; j < chainOrder; j++) {
					newString.append(chars.get(j + i));
				}
				if (map.containsKey(newString.toString())) {
					map.get(newString.toString()).add(chars.get(i + chainOrder));
				} else {
					ArrayList<Character> list = new ArrayList<Character>();
					list.add(chars.get(i + chainOrder));
					map.put(newString.toString(), list);
				}
			}
		} catch (IOException e) {
			System.err.println("An I/O error occurred: " + e.getMessage());
		}
	}

	// public static HashMap<String, ArrayList<String>> readData(String filePath) {

	// try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	// String line;
	// ArrayList<String[]> newStrings = new ArrayList<String[]>();
	// while ((line = br.readLine()) != null) {
	// newStrings.add(line.substring(0, 0))
	// }
	// HashMap<String, ArrayList<String>> map = new HashMap<String,
	// ArrayList<String>>();
	// for (int i = 0; i < newStrings.size(); i++) {
	// if (map.containsKey(newStrings.get(i)[0])) {
	// map.get(newStrings.get(i)[0]).add(newStrings.get(i)[1]);
	// } else {
	// ArrayList<String> list = new ArrayList<String>();
	// list.add(newStrings.get(i)[1]);
	// map.put(newStrings.get(i)[0], list);
	// }
	// }
	// return map;
	// } catch (Exception e) {
	// e.printStackTrace();
	// return null;
	// }
	// }

	public void generateText(String outputFileName, int numChars) {
		MiniGPT newGPT = new MiniGPT(outputFileName, 6);
		String[] keys = newGPT.map.keySet().toArray(new String[0]);
		StringBuilder curr = new StringBuilder();
		curr.append(keys[0]);
		System.out.print(curr.toString());
		
		for (int i = 0; i < numChars; i++) {
			String currString = curr.toString();
			int index = (int) (Math.random() * newGPT.map.get(currString).size());
			char nextChar = newGPT.map.get(currString).get(index);
			System.out.print(nextChar);
			curr.deleteCharAt(0);
			curr.append(nextChar);
		}
	}
}
