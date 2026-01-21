import java.lang.Math;

public class Arithmetic {

	// Evaluates a String exp that has an arithmetic expression, written in classic
	// notation
	public static int evaluate(String exp) {

		return 0;
	}

	// Returns the result of doing operand1 operation operand2,
	// e.g. operate(5, 2, "-") should return 3
	public static int operate(int operand1, int operand2, String operation) {
		if (operation.equals("+")) {
			return operand1 + operand2;
		} else if (operation.equals("-")) {
			return operand1 - operand2;
		} else if (operation.equals("*")) {
			return operand1 * operand2;
		} else if (operation.equals("/")) {
			return operand1 / operand2;
		} else if (operation.equals("%")) {
			return operand1 % operand2;
		} else if (operation.equals("^")) {
			return (int) Math.pow(operand1, operand2);
		}
		System.out.println("Invalid operation.");
		throw new IllegalArgumentException();
	}

	// Evaluates a String exp that has an arithmetic expression written in STOUT
	// notation
	public static int evaluateStout(String exp) {
		String[] newExp = exp.split(" ");
		MyStack<Integer> newStack = new MyStack<Integer>();
		for (int i = 0; i < newExp.length; i++) {
			if (!isOperator(newExp[i])) {
				newStack.push(Integer.parseInt(newExp[i]));
			} else {
				int operand2 = newStack.pop();
				int operand1 = newStack.pop();
				int newValue = operate(operand1, operand2, newExp[i]);
				newStack.push(newValue);
			}
		}
		return newStack.pop();
	}

	public static boolean isOperator(String operation) {
		if (operation.equals("+") || operation.equals("-") || operation.equals("*") || operation.equals("/")
				|| operation.equals("(") || operation.equals(")")
				|| operation.equals("^") || operation.equals("%")) {
			return true;
		}
		return false;
	}

	public static int priority(String operation) {
		if (operation.equals("+")) {
			return 4;
		} else if (operation.equals("-")) {
			return 4;
		} else if (operation.equals("*")) {
			return 5;
		} else if (operation.equals("/")) {
			return 5;
		} else if (operation.equals("%")) {
			return 5;
		} else if (operation.equals("^")) {
			return 6;
		} else if (operation.equals("(")) {
			return 8;
		} else if (operation.equals(")")) {
			return 8;
		}
		return 0;
	}

	public static String convertClassicToStout(String exp) {
		return "";
	}

}
