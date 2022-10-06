package me.ghost.Manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import me.ghost.Main;
import me.ghost.Util.CharAPI;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PixelsManager {
	public static void darpixels(Player p, Integer valor) {
		try {
			int pixelsantes = 0;
			if (!Main.plugin.fake.containsKey(p.getUniqueId())) {
				if (Main.playerDataContainsPlayer(p.getName())) {
					PreparedStatement sql = Main.c.prepareStatement("SELECT pixels FROM `player_pixels` WHERE player=?;");

					sql.setString(1, p.getName());

					ResultSet result = sql.executeQuery();
					result.next();

					pixelsantes = result.getInt("pixels");

					PreparedStatement loginsUpdate = Main.c.prepareStatement("UPDATE `player_pixels` SET pixels=? WHERE player=?;");
					loginsUpdate.setInt(1, pixelsantes + valor);

					loginsUpdate.setString(2, p.getName());

					loginsUpdate.executeUpdate();
					p.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "+" + valor.toString() + ChatColor.AQUA + CharAPI.getCube());

					loginsUpdate.close();
					sql.close();
					result.close();
				} else {
					PreparedStatement newPlayer = Main.c.prepareStatement("INSERT INTO `player_pixels` values(?," + valor + ");");
					p.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "+" + valor.toString() + ChatColor.AQUA + CharAPI.getCube());
					newPlayer.setString(1, p.getName());
					newPlayer.execute();
					newPlayer.close();
				}
			} else {
				if (Main.playerDataContainsPlayer(Main.plugin.fakeoriginal.get(p.getUniqueId()).toString())) {
					PreparedStatement sql = Main.c.prepareStatement("SELECT pixels FROM `player_pixels` WHERE player=?;");

					sql.setString(1, Main.plugin.fakeoriginal.get(p.getUniqueId()).toString());

					ResultSet result = sql.executeQuery();
					result.next();

					pixelsantes = result.getInt("pixels");

					PreparedStatement loginsUpdate = Main.c.prepareStatement("UPDATE `player_pixels` SET pixels=? WHERE player=?;");
					loginsUpdate.setInt(1, pixelsantes + valor);

					loginsUpdate.setString(2, Main.plugin.fakeoriginal.get(p.getUniqueId()).toString());

					loginsUpdate.executeUpdate();
					p.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "+" + valor.toString() + ChatColor.AQUA + CharAPI.getCube());

					loginsUpdate.close();
					sql.close();
					result.close();
				} else {
					PreparedStatement newPlayer = Main.c.prepareStatement("INSERT INTO `player_pixels` values(?," + valor + ");");
					p.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "+" + valor.toString() + ChatColor.AQUA + CharAPI.getCube());
					newPlayer.setString(1, Main.plugin.fakeoriginal.get(p.getUniqueId()).toString());
					newPlayer.execute();
					newPlayer.close();
				}
			}
		} catch (Exception e2) {

		}
	}

	public static void darpixels(String p, Integer valor) {
		try {
			int pixelsantes = 0;
			if (Main.playerDataContainsPlayer(p)) {
				PreparedStatement sql = Main.c.prepareStatement("SELECT pixels FROM `player_pixels` WHERE player=?;");

				sql.setString(1, p);

				ResultSet result = sql.executeQuery();
				result.next();

				pixelsantes = result.getInt("pixels");

				PreparedStatement loginsUpdate = Main.c.prepareStatement("UPDATE `player_pixels` SET pixels=? WHERE player=?;");
				loginsUpdate.setInt(1, pixelsantes + valor);

				loginsUpdate.setString(2, p);

				loginsUpdate.executeUpdate();

				loginsUpdate.close();
				sql.close();
				result.close();
			} else {
				PreparedStatement newPlayer = Main.c.prepareStatement("INSERT INTO `player_pixels` values(?," + valor + ");");
				newPlayer.setString(1, p);
				newPlayer.execute();
				newPlayer.close();
			}
		} catch (Exception e2) {

		}
	}

	public static void darWin(Player p, Integer valor) {
		try {
			int winsantes = 0;
			if (!Main.plugin.fake.containsKey(p.getUniqueId())) {
				if (Main.playerDataContainsPlayerStatus(p.getName())) {
					PreparedStatement sql = Main.c.prepareStatement("SELECT wins FROM `player_status` WHERE player=?;");

					sql.setString(1, p.getName());

					ResultSet result = sql.executeQuery();
					result.next();

					winsantes = result.getInt("wins");

					PreparedStatement loginsUpdate = Main.c.prepareStatement("UPDATE `player_status` SET wins=? WHERE player=?;");
					loginsUpdate.setInt(1, winsantes + valor);

					loginsUpdate.setString(2, p.getName());

					loginsUpdate.executeUpdate();

					loginsUpdate.close();
					sql.close();
					result.close();
				} else {
					PreparedStatement newPlayer = Main.c.prepareStatement("INSERT INTO `player_status` values(?," + valor + ",0,0);");

					newPlayer.setString(1, p.getName());
					newPlayer.execute();
					newPlayer.close();
				}
			} else {
				if (Main.playerDataContainsPlayerStatus(Main.plugin.fakeoriginal.get(p.getUniqueId()).toString())) {
					PreparedStatement sql = Main.c.prepareStatement("SELECT wins FROM `player_status` WHERE player=?;");

					sql.setString(1, Main.plugin.fakeoriginal.get(p.getUniqueId()).toString());

					ResultSet result = sql.executeQuery();
					result.next();

					winsantes = result.getInt("wins");

					PreparedStatement loginsUpdate = Main.c.prepareStatement("UPDATE `player_status` SET wins=? WHERE player=?;");
					loginsUpdate.setInt(1, winsantes + valor);

					loginsUpdate.setString(2, Main.plugin.fakeoriginal.get(p.getUniqueId()).toString());

					loginsUpdate.executeUpdate();

					loginsUpdate.close();
					sql.close();
					result.close();
				} else {
					PreparedStatement newPlayer = Main.c.prepareStatement("INSERT INTO `player_status` values(?," + valor + ",0,0);");

					newPlayer.setString(1, Main.plugin.fakeoriginal.get(p.getUniqueId()).toString());
					newPlayer.execute();
					newPlayer.close();
				}
			}
		} catch (Exception e2) {

		}
	}

	public static void darKils(Player p, Integer valor) {
		try {
			int killsantes = 0;
			if (!Main.plugin.fake.containsKey(p.getUniqueId())) {
				if (Main.playerDataContainsPlayerStatus(p.getName())) {
					PreparedStatement sql = Main.c.prepareStatement("SELECT kills FROM `player_status` WHERE player=?;");

					sql.setString(1, p.getName());

					ResultSet result = sql.executeQuery();
					result.next();

					killsantes = result.getInt("kills");

					PreparedStatement loginsUpdate = Main.c.prepareStatement("UPDATE `player_status` SET kills=? WHERE player=?;");
					loginsUpdate.setInt(1, killsantes + valor);

					loginsUpdate.setString(2, p.getName());

					loginsUpdate.executeUpdate();

					loginsUpdate.close();
					sql.close();
					result.close();
				} else {
					PreparedStatement newPlayer = Main.c.prepareStatement("INSERT INTO `player_status` values(?,0," + valor + ",0);");

					newPlayer.setString(1, p.getName());
					newPlayer.execute();
					newPlayer.close();
				}
			} else {
				if (Main.playerDataContainsPlayerStatus(Main.plugin.fakeoriginal.get(p.getUniqueId()).toString())) {
					PreparedStatement sql = Main.c.prepareStatement("SELECT kills FROM `player_status` WHERE player=?;");

					sql.setString(1, Main.plugin.fakeoriginal.get(p.getUniqueId()).toString());

					ResultSet result = sql.executeQuery();
					result.next();

					killsantes = result.getInt("kills");

					PreparedStatement loginsUpdate = Main.c.prepareStatement("UPDATE `player_status` SET kills=? WHERE player=?;");
					loginsUpdate.setInt(1, killsantes + valor);

					loginsUpdate.setString(2, Main.plugin.fakeoriginal.get(p.getUniqueId()).toString());

					loginsUpdate.executeUpdate();

					loginsUpdate.close();
					sql.close();
					result.close();
				} else {
					PreparedStatement newPlayer = Main.c.prepareStatement("INSERT INTO `player_status` values(?,0," + valor + ",0);");

					newPlayer.setString(1, Main.plugin.fakeoriginal.get(p.getUniqueId()).toString());
					newPlayer.execute();
					newPlayer.close();
				}
			}
		} catch (Exception e2) {

		}
	}

	public static void darMorte(Player p, Integer valor) {
		try {
			int mortesantes = 0;
			if (!Main.plugin.fake.containsKey(p.getUniqueId())) {
				if (Main.playerDataContainsPlayerStatus(p.getName())) {
					PreparedStatement sql = Main.c.prepareStatement("SELECT mortes FROM `player_status` WHERE player=?;");

					sql.setString(1, p.getName());

					ResultSet result = sql.executeQuery();
					result.next();

					mortesantes = result.getInt("mortes");

					PreparedStatement loginsUpdate = Main.c.prepareStatement("UPDATE `player_status` SET mortes=? WHERE player=?;");
					loginsUpdate.setInt(1, mortesantes + valor);

					loginsUpdate.setString(2, p.getName());

					loginsUpdate.executeUpdate();

					loginsUpdate.close();
					sql.close();
					result.close();
				} else {
					PreparedStatement newPlayer = Main.c.prepareStatement("INSERT INTO `player_status` values(?,0,0," + valor + ");");

					newPlayer.setString(1, p.getName());
					newPlayer.execute();
					newPlayer.close();
				}
			} else {
				if (Main.playerDataContainsPlayerStatus(Main.plugin.fakeoriginal.get(p.getUniqueId()).toString())) {
					PreparedStatement sql = Main.c.prepareStatement("SELECT mortes FROM `player_status` WHERE player=?;");

					sql.setString(1, Main.plugin.fakeoriginal.get(p.getUniqueId()).toString());

					ResultSet result = sql.executeQuery();
					result.next();

					mortesantes = result.getInt("mortes");

					PreparedStatement loginsUpdate = Main.c.prepareStatement("UPDATE `player_status` SET mortes=? WHERE player=?;");
					loginsUpdate.setInt(1, mortesantes + valor);

					loginsUpdate.setString(2, Main.plugin.fakeoriginal.get(p.getUniqueId()).toString());

					loginsUpdate.executeUpdate();

					loginsUpdate.close();
					sql.close();
					result.close();
				} else {
					PreparedStatement newPlayer = Main.c.prepareStatement("INSERT INTO `player_status` values(?,0,0," + valor + ");");

					newPlayer.setString(1, Main.plugin.fakeoriginal.get(p.getUniqueId()).toString());
					newPlayer.execute();
					newPlayer.close();
				}
			}
		} catch (Exception e2) {

		}
	}
}
