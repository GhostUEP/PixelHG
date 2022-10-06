package me.ghost.Util;

import net.md_5.bungee.api.ChatColor;

public class CharAPI {
	public static String getCube() {
		return "█";

	}

	public static String getCubeYellow() {
		return ChatColor.YELLOW + "█";

	}

	public static String getCubeWhite() {
		return ChatColor.WHITE + "█";

	}

	public static String getCubeGreen() {
		return ChatColor.GREEN + "█";

	}

	public static String getCubeGold() {
		return ChatColor.GOLD + "█";

	}

	public static String getCubeAqua() {
		return ChatColor.AQUA + "█";

	}

	public static String getCubeCinza() {
		return ChatColor.GRAY + "█";

	}

	public static String getCubeBlank() {
		return ChatColor.DARK_PURPLE + "    ";

	}

	public static String preFix() {
		return ChatColor.WHITE + "" + ChatColor.BOLD + "P" + ChatColor.GOLD + "" + ChatColor.BOLD + "I" + ChatColor.DARK_RED + "" + ChatColor.BOLD + "X" + ChatColor.YELLOW + "" + ChatColor.BOLD + "E" + ChatColor.BLUE + "" + ChatColor.BOLD + "L" + ChatColor.WHITE + ":";

	}

	public static String preFix2() {
		return ChatColor.WHITE + "" + ChatColor.BOLD + "P" + ChatColor.GOLD + "" + ChatColor.BOLD + "I" + ChatColor.DARK_RED + "" + ChatColor.BOLD + "X" + ChatColor.YELLOW + "" + ChatColor.BOLD + "E" + ChatColor.BLUE + "" + ChatColor.BOLD + "L" + ChatColor.GRAY + "" + ChatColor.BOLD + "H" + ChatColor.GRAY + "" + ChatColor.BOLD + "G";

	}
}
