package utils;

public class StringOfNumberUtils {

	public static boolean isNumeric(String value) {
		try {
			Long.parseLong(value);
		} catch (Exception pe) {
			return false;
		}
		return true;
	}

	public static boolean containsNumeric(String value) {
		char[] characters = value.toCharArray();
		for (Character c : characters) {
			if (isNumeric(c.toString()))
				return true;
		}
		return false;
	}

	public static int containsNumericPosition(String value) {
		int cont = 0;
		char[] characters = value.toCharArray();
		for (Character c : characters) {
			if (isNumeric(c.toString()))
				return cont;
			cont++;
		}
		return cont;
	}

	public static int countNumbers(String[] values) {
		int countNumbers = 0;
		for (String string : values) {
			if (StringOfNumberUtils.isNumeric(string)) {
				countNumbers++;
			} else if (StringOfNumberUtils.containsNumeric(string)) {
				countNumbers++;
			}
		}
		return countNumbers;
	}
}
