import java.util.ArrayList;

public class Recursion {

	// Prints the value of every node in the singly linked list with the given head,
	// but in reverse
	public static void printListInReverse(ListNode head) {
		if (head.getNext() == null) {
			System.out.println(head.getValue());
		} else {
			printListInReverse(head.getNext());
			System.out.println(head.getValue());
		}
	}

	// For the given 2D array of Strings, replaces the String at index[r][c]
	// with "infected" unless the String is "vaccinated";
	// also, any Strings they are orthogonally adjacent to
	// that are not "vaccinated" will also be infected, and any adjacent to
	// them as well etc.
	// Infecting someone who is already infected has no effect
	// Trying to infect outside the confines of the grid also has no effect
	// Precondition: grid has no null entries
	public static void infect(String[][] grid, int r, int c) {
		if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) {
			return;
		}
		if (grid[r][c].equals("vaccinated") || grid[r][c].equals("infected")) {
			return;
		} else {
			grid[r][c] = "infected";
			infect(grid, r + 1, c);
			infect(grid, r - 1, c);
			infect(grid, r, c + 1);
			infect(grid, r, c - 1);
		}
	}

	// How many subsets are there of the numbers 1...n
	// that don't contain any consecutive integers?
	// e.g. for n = 4, the subsets are {}, {1}, {2}, {3}, {4},
	// {1, 3}, {1, 4}, {2, 4}
	// The other subsets of 1,2,3,4 that DO contain consecutive integers are
	// {1,2}, {2,3}, {3,4}, {1,2,3}, {1,2,4}, {1,3,4}, {1,2,3,4}
	// Precondition: n > 0
	public static long countNonConsecutiveSubsets(int n) {
		if (n == 1) {
			return 2;
		}
		if (n == 2) {
			return 3;
		} else {
			return countNonConsecutiveSubsets(n - 1) + countNonConsecutiveSubsets(n - 2);
		}
	}

	// A kid at the bottom of the stairs can jump up 1, 2, or 3 stairs at a time.
	// How many different ways can they jump up n stairs?
	// Jumping 1-1-2 is considered different than jumping 1-2-1
	// Precondition: n > 0
	public static long countWaysToJumpUpStairs(int n) {
		if (n == 1) {
			return 1;
		} else if (n == 2) {
			return 2;
		} else if (n == 3) {
			return 4;
		}
		return countWaysToJumpUpStairs(n - 1) + countWaysToJumpUpStairs(n - 2) + countWaysToJumpUpStairs(n - 3);
	}

	// Everything above this line does NOT require a recursive helper method
	// ----------------------------------
	// Everything below this line requires a recursive helper method
	// Any recursive helper method you write MUST have a comment describing:
	// 1) what the helper method does/returns
	// 2) your description must include role of each parameter in the helper method

	// Prints all the subsets of str on separate lines
	// You may assume that str has no repeated characters
	// For example, subsets("abc") would print out "", "a", "b", "c", "ab", "ac",
	// "bc", "abc"
	// Order is your choice
	public static void printSubsets(String str) {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < str.length(); i++) {
			list.add("" + str.charAt(i));
		}
		ArrayList<String> subs = new ArrayList<String>();
		subs.add("");
		ArrayList<String> subsets = createSubsets(list, subs);
		for (String string : subsets) {
			System.out.println(string);
		}
	}

	// This method creates the subsets to be printed out later. It takes the list,
	// which is just the list of the chars as Strings from the str that we had
	// earlier. Then, we took the subsets that exist and added to those subsets to
	// get the overall subsets and then return them all.
	public static ArrayList<String> createSubsets(ArrayList<String> list, ArrayList<String> subsets) {
		if (subsets.size() == 1) {
			subsets.add(list.get(0));
			list.remove(list.get(0));
			return createSubsets(list, subsets);
		} else {
			int length = subsets.size();
			String removed = list.remove(0);
			for (int i = 0; i < length; i++) {
				subsets.add(subsets.get(i) + removed);
			}
			if (list.size() > 0) {
				createSubsets(list, subsets);
			}
		}
		return subsets;
	}

	// List contains a single String to start.
	// Prints all the permutations of str on separate lines
	// You may assume that str has no repeated characters
	// For example, permute("abc") could print out "abc", "acb", "bac", "bca",
	// "cab", "cba"
	// Order is your choice
	public static void printPermutations(String str) {
		ArrayList<String> subsets = createPermutations(str);
		for (int i = 0; i < subsets.size() - 1; i++) {
			System.out.print("\"" + subsets.get(i) + "\", ");
		}
		System.out.print("\"" + subsets.get(subsets.size() - 1) + "\"");
	}

	// This method creates the permutations to be printed out later. It takes a
	// String str from the print method and then uses that in the helper method to
	// use in making substrings to combine to form the permutations. Finally, the
	// permutations are put into the ArrayList toReturn and then returned to be
	// printed.
	public static ArrayList<String> createPermutations(String str) {
		ArrayList<String> toReturn = new ArrayList<String>();
		if (str.length() == 0) {
			toReturn.add(str);
			return toReturn;
		} else if (str.length() == 2) {
			toReturn.add(str);
			String backward = str.substring(1) + str.substring(0, 1);
			toReturn.add(backward);
			return toReturn;
		}
		String letter = str.substring(0, 1);
		ArrayList<String> permutations = createPermutations(str.substring(1));
		for (int i = 0; i < permutations.size(); i++) {
			for (int j = 0; j < str.length(); j++) {
				if (j == 0) {
					toReturn.add(letter + permutations.get(i));
				} else if (j == str.length() - 1) {
					toReturn.add(permutations.get(i) + letter);
				} else {
					toReturn.add(permutations.get(i).substring(0, j) + letter + permutations.get(i).substring(j));
				}
			}
		}
		return toReturn;
	}

	// Performs a mergeSort on the given array of ints
	// Precondition: you may assume there are NO duplicates!!!
	public static void mergeSort(int[] ints) {
		if (ints.length == 0 || ints.length == 1) {
			return;
		}
		int[] right;
		if (ints.length % 2 == 1) {
			right = new int[ints.length / 2 + 1];
		} else {
			right = new int[ints.length / 2];
		}
		int[] left = new int[ints.length / 2];
		for (int i = 0; i < ints.length; i++) {
			if (i < ints.length / 2) {
				left[i] = ints[i];
			} else {
				right[i - (ints.length / 2)] = ints[i];
			}
		}
		mergeSort(left);
		mergeSort(right);
		ArrayList<Integer> leftSide = new ArrayList<Integer>();
		ArrayList<Integer> rightSide = new ArrayList<Integer>();
		for (int i = 0; i < left.length; i++) {
			leftSide.add(left[i]);
		}
		for (int i = 0; i < right.length; i++) {
			rightSide.add(right[i]);
		}
		ArrayList<Integer> finalList = recombineArrays(leftSide, rightSide);
		for (int i = 0; i < ints.length; i++) {
			ints[i] = finalList.get(i);
		}
	}

	// This array recombines the arrays, putting them back together and sorting them
	// based on the fact that the two given arrays are already sorted because it is
	// recursively calling until the length is 1 and 1
	public static ArrayList<Integer> recombineArrays(ArrayList<Integer> firstArray, ArrayList<Integer> secondArray) {
		int pointerOne = 0;
		int pointerTwo = 0;
		ArrayList<Integer> newList = new ArrayList<Integer>();
		while (pointerOne != firstArray.size() && pointerTwo != secondArray.size()) {
			if (firstArray.get(pointerOne) < secondArray.get(pointerTwo)) {
				newList.add(firstArray.get(pointerOne));
				pointerOne++;
			} else {
				newList.add(secondArray.get(pointerTwo));
				pointerTwo++;
			}
		}
		if (pointerOne == firstArray.size()) {
			for (int i = pointerTwo; i < secondArray.size(); i++) {
				newList.add(secondArray.get(i));
			}
		} else {
			for (int i = pointerOne; i < firstArray.size(); i++) {
				newList.add(firstArray.get(i));
			}
		}
		return newList;
	}

	// Performs a quickSort on the given array of ints
	// Use the middle element (index n/2) as the pivot
	// Precondition: you may assume there are NO duplicates!!!
	public static void quickSort(int[] ints) {

	}

	// Prints a sequence of moves (one on each line)
	// to complete a Towers of Hanoi problem:
	// disks start on tower 0 and must end on tower 2.
	// The towers are number 0, 1, 2, and each move should be of
	// the form "1 -> 2", meaning "take the top disk of tower 1 and
	// put it on tower 2" etc.
	public static void solveHanoi(int startingDisks) {
		if (startingDisks % 2 == 1) {
			solveHanoiWithLessInMiddle(startingDisks, 0, 2, 1);
		} else {
			solveHanoiWithLessInMiddle(startingDisks, 0, 1, 2);
		}
	}

	// It takes the information from solveHanoi and solves it with one less until it
	// gets to the base case of 2. From there, it prints it out, switching around
	// until it prints out the solution for the entirety of the problem
	public static void solveHanoiWithLessInMiddle(int startingDisks, int start, int end, int middle) {
		if (startingDisks == 1) {
			System.out.println(start + " -> " + end);
		} else if (startingDisks == 2) {
			System.out.println(start + " -> " + end);
			System.out.println(start + " -> " + middle);
			System.out.println(end + " -> " + middle);
		} else {
			solveHanoiWithLessInMiddle(startingDisks - 1, start, end, middle);
			if (startingDisks % 2 == 1) {
				System.out.println(start + " -> " + end);
				solveHanoiWithLessInMiddle(startingDisks - 1, middle, start, end);
			} else {
				System.out.println(start + " -> " + middle);
				solveHanoiWithLessInMiddle(startingDisks - 1, end, middle, start);
			}
		}
	}

	// You are partaking in a scavenger hunt!
	// You've gotten a secret map to find many of the more difficult
	// items, but they are only available at VERY specific times at
	// specific places. You have an array, times[], that lists at which
	// MINUTE an item is available. Times is sorted in ascending order.
	// Items in the ScavHunt are worth varying numbers of points.
	// You also have an array, points[], same length as times[],
	// that lists how many points each of the corresponding items is worth.
	// Problem is: to get from one location to the other takes 5 minutes,
	// so if there is an item, for example, available at time 23 and another
	// at time 27, it's just not possible for you to make it to both: you'll
	// have to choose!
	// (but you COULD make it from a place at time 23 to another at time 28)
	// Write a method that returns the maximum POINTS you can get.
	// For example, if times = [3, 7, 9]
	// and points = [10, 15, 10]
	// Then the best possible result is getting the item at time 3 and the one at
	// time 9
	// for a total of 20 points, so it would return 20.
	public static int scavHunt(int[] times, int[] points) {

	}

}
