package me.ghost.API;

public class TagAPI {

	public static String getShortStr(String name) {
		if (name.length() == 16) {
			String shorts = name.substring(0, name.length() - 2);
			return shorts;
		}
		if (name.length() == 15) {
			String shorts = name.substring(0, name.length() - 1);
			return shorts;
		}
		return name;
	}

	public static String getMod(String name) {
		if (name.length() == 16) {
			String shorts = name.substring(0, name.length() - 4);
			return shorts;
		}
		if (name.length() == 15) {
			String shorts = name.substring(0, name.length() - 3);
			return shorts;
		}
		if (name.length() == 14) {
			String shorts = name.substring(0, name.length() - 2);
			return shorts;
		}
		if (name.length() == 13) {
			String shorts = name.substring(0, name.length() - 1);
			return shorts;
		}
		return name;
	}
}
