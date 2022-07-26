package utils;

public class StringUtils {
	public static String convertToPascalCase(String input) {
//		input = input.replaceAll("_", " ");
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			if (!(i == 0 || input.charAt(i - 1) == '_'))
				res.append(Character.toLowerCase(input.charAt(i)));
			else
				res.append(input.charAt(i));
		}
		return res.toString();
	}

	public static String makeNumberSigned(double input) {
		if (input > 0)
			return "+" + input;
		else if (input == 0)
			return String.valueOf(input);
		else
			return "-" + input;
	}

	public static String makeFirstCapital(String input) {
		StringBuilder sb = new StringBuilder(input.toLowerCase());
		sb.setCharAt(0, String.valueOf(sb.charAt(0)).toUpperCase().charAt(0));
		return sb.toString();
	}

}
