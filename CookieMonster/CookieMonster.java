import java.io.*;
import java.util.*;

// You are allowed (and expected!) to use either Java's ArrayDeque or LinkedList class to make stacks and queues

public class CookieMonster {

	private int[][] cookieGrid;
	private int numRows;
	private int numCols;

	// Constructs a CookieMonster from a file with format:
	// numRows numCols
	// <<rest of the grid, with spaces in between the numbers>>
	public CookieMonster(String fileName) {
		int row = 0;
		int col = 0;
		try {
			Scanner input = new Scanner(new File(fileName));

			numRows = input.nextInt();
			numCols = input.nextInt();
			cookieGrid = new int[numRows][numCols];

			for (row = 0; row < numRows; row++)
				for (col = 0; col < numCols; col++)
					cookieGrid[row][col] = input.nextInt();

			input.close();
		} catch (Exception e) {
			System.out.print("Error creating maze: " + e.toString());
			System.out.println("Error occurred at row: " + row + ", col: " + col);
		}

	}

	public CookieMonster(int[][] cookieGrid) {
		this.cookieGrid = cookieGrid;
		this.numRows = cookieGrid.length;
		this.numCols = cookieGrid[0].length;
	}

	// You may find it VERY helpful to write this helper method. Or not!
	private boolean validPoint(int row, int col) {
		if (row >= numRows || row < 0 || col >= numCols || col < 0 || cookieGrid[row][col] == -1) {
			return false;
		}
		return true;
	}

	/*
	 * RECURSIVELY calculates the route which grants the most cookies.
	 * Returns the maximum number of cookies attainable.
	 */
	public int recursiveCookies() {
		if (cookieGrid[0][0] == -1) {
			return 0;
		}
		return recursiveCookies(0, 0);
	}

	// Returns the maximum number of cookies edible starting from (and including)
	// cookieGrid[row][col]
	public int recursiveCookies(int row, int col) {
		if (row == numRows - 1 && col == numCols - 1) {
			return cookieGrid[row][col];
		}
		int maxCookies = cookieGrid[row][col];
		int right = 0;
		int down = 0;
		if (validPoint(row + 1, col)) {
			right = recursiveCookies(row + 1, col);
		}
		if (validPoint(row, col + 1)) {
			down = recursiveCookies(row, col + 1);
		}
		return maxCookies + Math.max(right, down);
		// int rightValue = -1;
		// int downValue = -1;
		// if (row == numRows - 1 && col == numCols - 1) {
		// 	return cookieGrid[row][col];
		// }
		// if (row + 1 < numRows && validPoint(row + 1, col)) {
		// 	rightValue = recursiveCookies(row + 1, col);
		// }
		// if (col + 1 < numCols && validPoint(row, col + 1)) {
		// 	downValue = recursiveCookies(row, col + 1);
		// }
		// if (rightValue != -1 && downValue != -1) {
		// 	if (rightValue > downValue) {
		// 		return rightValue + cookieGrid[row][col];
		// 	} else {
		// 		return downValue + cookieGrid[row][col];
		// 	}
		// } else if (rightValue != -1) {
		// 	return rightValue + cookieGrid[row][col];
		// } else if (downValue != -1) {
		// 	return downValue + cookieGrid[row][col];
		// } else {
		// 	return -1;
		// }
	}

	/*
	 * Calculate which route grants the most cookies using a QUEUE.
	 * Returns the maximum number of cookies attainable.
	 */
	/*
	 * From any given position, always add the path right before adding the path
	 * down
	 */
	public int queueCookies() {
		if (cookieGrid[0][0] == -1) {
			return 0;
		}
		ArrayDeque<OrphanScout> queue = new ArrayDeque<>();
		int row = 0;
		int col = 0;
		OrphanScout first = new OrphanScout(row, col, cookieGrid[0][0]);
		queue.add(first);
		int max = cookieGrid[0][0];
		while (!queue.isEmpty()) {
			OrphanScout current = queue.poll();
			row = current.getEndingRow();
			col = current.getEndingCol();
			int newCookieCount = current.getCookiesDiscovered();
			if (validPoint(row + 1, col)) {
				OrphanScout rightKid = new OrphanScout(row + 1, col, newCookieCount + cookieGrid[row + 1][col]);
				if (!(rightKid.getCookiesDiscovered() < newCookieCount)) {
					if (rightKid.getCookiesDiscovered() > max) {
						max = rightKid.getCookiesDiscovered();
					}
				}
				queue.add(rightKid);
			}
			if (validPoint(row, col + 1)) {
				OrphanScout downKid = new OrphanScout(row, col + 1, newCookieCount + cookieGrid[row][col + 1]);
				if (!(downKid.getCookiesDiscovered() < newCookieCount)) {
					if (downKid.getCookiesDiscovered() > max) {
						max = downKid.getCookiesDiscovered();
					}
				}
				queue.add(downKid);
			}
		}
		return max;
		// if (!validPoint(0, 0) || !validPoint(numRows - 1, numCols - 1)) {
		// 	return 0;
		// }
		// int row = 0;
		// int col = 0;
		// ArrayDeque<OrphanScout> queue = new ArrayDeque<>();
		// OrphanScout first = new OrphanScout(row, col, cookieGrid[0][0]);
		// int newCookieCount = 0;
		// queue.add(first);
		// int maxCookies = 0;
		// int cookies;
		// while (!queue.isEmpty()) {
		// 	row = queue.peek().getEndingRow();
		// 	col = queue.peek().getEndingCol();
		// 	if (validPoint(row + 1, col)) {
		// 		newCookieCount = queue.peek().getCookiesDiscovered() + cookieGrid[row + 1][col];
		// 		queue.add(new OrphanScout(row + 1, col, newCookieCount));
		// 	}
		// 	if (validPoint(row, col + 1)) {
		// 		newCookieCount = queue.peek().getCookiesDiscovered() + cookieGrid[row][col + 1];
		// 		queue.add(new OrphanScout(row, col + 1, newCookieCount));
		// 	}
		// 	if (row == numRows - 1 && col == numCols - 1) {
		// 		cookies = queue.remove().getCookiesDiscovered();
		// 		if (cookies > maxCookies) {
		// 			maxCookies = cookies;
		// 		}
		// 	} else {
		// 		queue.remove();
		// 	}
		// }
		// return maxCookies;
	}

	/*
	 * Calculate which route grants the most cookies using a stack.
	 * Returns the maximum number of cookies attainable.
	 */
	/*
	 * From any given position, always add the path right before adding the path
	 * down
	 */
	public int stackCookies() {
		if (cookieGrid[0][0] == -1) {
			return 0;
		}
		Stack<OrphanScout> stack = new Stack<>();
		int row = 0;
		int col = 0;
		OrphanScout first = new OrphanScout(row, col, cookieGrid[0][0]);
		stack.add(first);
		int max = cookieGrid[0][0];
		while (!stack.isEmpty()) {
			OrphanScout current = stack.pop();
			row = current.getEndingRow();
			col = current.getEndingCol();
			int newCookieCount = current.getCookiesDiscovered();
			if (validPoint(row + 1, col)) {
				OrphanScout rightKid = new OrphanScout(row + 1, col, newCookieCount + cookieGrid[row + 1][col]);
				if (!(rightKid.getCookiesDiscovered() < newCookieCount)) {
					if (rightKid.getCookiesDiscovered() > max) {
						max = rightKid.getCookiesDiscovered();
					}
				}
				stack.push(rightKid);
			}
			if (validPoint(row, col + 1)) {
				OrphanScout downKid = new OrphanScout(row, col + 1, newCookieCount + cookieGrid[row][col + 1]);
				if (!(downKid.getCookiesDiscovered() < newCookieCount)) {
					if (downKid.getCookiesDiscovered() > max) {
						max = downKid.getCookiesDiscovered();
					}
				}
				stack.push(downKid);
			}
		}
		return max;
		// if (!validPoint(0, 0) || !validPoint(numRows - 1, numCols - 1)) {
		// 	return 0;
		// }
		// int row = 0;
		// int col = 0;
		// Stack<OrphanScout> stack = new Stack<>();
		// OrphanScout first = new OrphanScout(row, col, cookieGrid[0][0]);
		// int newCookieCount = 0;
		// stack.push(first);
		// OrphanScout rightKid = null;
		// OrphanScout downKid = null;
		// int maxCookies = 0;
		// int cookies;
		// while (!stack.empty()) {
		// 	row = stack.peek().getEndingRow();
		// 	col = stack.peek().getEndingCol();
		// 	if (validPoint(row + 1, col)) {
		// 		newCookieCount = stack.peek().getCookiesDiscovered() + cookieGrid[row + 1][col];
		// 		rightKid = new OrphanScout(row + 1, col, newCookieCount);
		// 	}
		// 	if (validPoint(row, col + 1)) {
		// 		newCookieCount = stack.peek().getCookiesDiscovered() + cookieGrid[row][col + 1];
		// 		downKid = new OrphanScout(row, col + 1, newCookieCount);
		// 	}
		// 	if (row == numRows - 1 && col == numCols - 1) {
		// 		cookies = stack.pop().getCookiesDiscovered();
		// 		if (cookies > maxCookies) {
		// 			maxCookies = cookies;
		// 		}
		// 	} else {
		// 		stack.pop();
		// 	}
		// 	if (downKid != null) {
		// 		stack.push(downKid);
		// 		downKid = null;
		// 	}
		// 	if (rightKid != null) {
		// 		stack.push(rightKid);
		// 		rightKid = null;
		// 	}
		// }
		// return maxCookies;
	}

}
