package me.ghost.Comandos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import me.ghost.Main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ContaCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Voce nao e um player");
			return true;
		}
		Player p = (Player) sender;
		if (label.equalsIgnoreCase("conta")) {
			String wins = "";
			String kills = "";
			String mortes = "";
			Double abates = 0.0;
			Double mortes2 = 0.0;

			try {
				if (!Main.plugin.fake.containsKey(p.getUniqueId())) {
					if (Main.playerDataContainsPlayerStatus(p.getName())) {
						PreparedStatement sql = Main.c.prepareStatement("SELECT wins FROM `player_status` WHERE player=?;");
						sql.setString(1, p.getName());

						ResultSet result = sql.executeQuery();
						result.next();

						wins = result.getString("wins");
						result.close();
						sql.close();
					} else {
						wins = "0";
					}
				} else {
					if (Main.playerDataContainsPlayerStatus(Main.plugin.fakeoriginal.get(p.getUniqueId()).toString())) {
						PreparedStatement sql = Main.c.prepareStatement("SELECT wins FROM `player_status` WHERE player=?;");
						sql.setString(1, Main.plugin.fakeoriginal.get(p.getUniqueId()).toString());

						ResultSet result = sql.executeQuery();
						result.next();

						wins = result.getString("wins");
						result.close();
						sql.close();
					} else {
						wins = "0";
					}
				}
				if (!Main.plugin.fake.containsKey(p.getUniqueId())) {
					if (Main.playerDataContainsPlayerStatus(p.getName())) {
						PreparedStatement sql = Main.c.prepareStatement("SELECT kills FROM `player_status` WHERE player=?;");
						sql.setString(1, p.getName());

						ResultSet result = sql.executeQuery();
						result.next();

						kills = result.getString("kills");
						abates = result.getDouble("kills");
						result.close();
						sql.close();
					} else {
						kills = "0";
						abates = 0.0;

					}
				} else {
					if (Main.playerDataContainsPlayerStatus(Main.plugin.fakeoriginal.get(p.getUniqueId()).toString())) {
						PreparedStatement sql = Main.c.prepareStatement("SELECT kills FROM `player_status` WHERE player=?;");
						sql.setString(1, Main.plugin.fakeoriginal.get(p.getUniqueId()).toString());

						ResultSet result = sql.executeQuery();
						result.next();

						kills = result.getString("kills");
						abates = result.getDouble("kills");
						result.close();
						sql.close();
					} else {
						kills = "0";
						abates = 0.0;
					}
				}
				if (!Main.plugin.fake.containsKey(p.getUniqueId())) {
					if (Main.playerDataContainsPlayerStatus(p.getName())) {
						PreparedStatement sql = Main.c.prepareStatement("SELECT mortes FROM `player_status` WHERE player=?;");
						sql.setString(1, p.getName());

						ResultSet result = sql.executeQuery();
						result.next();

						mortes = result.getString("mortes");
						mortes2 = result.getDouble("mortes");
						result.close();
						sql.close();
					} else {
						mortes = "0";
						mortes2 = 0.0;
					}
				} else {
					if (Main.playerDataContainsPlayerStatus(Main.plugin.fakeoriginal.get(p.getUniqueId()).toString())) {
						PreparedStatement sql = Main.c.prepareStatement("SELECT mortes FROM `player_status` WHERE player=?;");
						sql.setString(1, Main.plugin.fakeoriginal.get(p.getUniqueId()).toString());

						ResultSet result = sql.executeQuery();
						result.next();

						mortes = result.getString("mortes");
						mortes2 = result.getDouble("mortes");
						result.close();
						sql.close();
					} else {
						mortes = "0";
						mortes2 = 0.0;
					}
				}
			} catch (Exception e2) {

			}
			Double kd = abates / mortes2;

			p.sendMessage(ChatColor.GRAY + "Wins: " + ChatColor.GREEN + wins);
			p.sendMessage(ChatColor.GRAY + "Kills: " + ChatColor.GREEN + kills);
			p.sendMessage(ChatColor.GRAY + "Mortes: " + ChatColor.GREEN + mortes);
			p.sendMessage(ChatColor.GRAY + "KDR: " + ChatColor.GREEN + kd.toString());
			return true;
		}

		return false;
	}
}
