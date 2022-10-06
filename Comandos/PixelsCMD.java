package me.ghost.Comandos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import me.ghost.Main;
import me.ghost.Util.CharAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PixelsCMD implements CommandExecutor {
	String pixels = "";
	Integer pixels2 = 0;

	@SuppressWarnings("static-access")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Voce não é um player");
		}
		Player p2 = (Player) sender;
		if (label.equalsIgnoreCase("pixels")) {
			if (args.length > 0) {
				if (args.length == 1) {
					if (!p2.hasPermission("PixelHG.pex.Mod")) {
						p2.sendMessage(ChatColor.RED + "Você não tem permissão para esse comando");
						return true;
					}
					Player p = Bukkit.getPlayer(args[0]);
					if (p == null) {
						p2.sendMessage(ChatColor.RED + "Player nao encontrado");
						return true;
					}
					try {
						if (!Main.plugin.fake.containsKey(p.getUniqueId())) {
							if (Main.plugin.playerDataContainsPlayer(p.getName())) {
								PreparedStatement sql = Main.plugin.c.prepareStatement("SELECT pixels FROM `player_pixels` WHERE player=?;");

								sql.setString(1, p.getName());

								ResultSet result = sql.executeQuery();
								result.next();

								pixels = result.getString("pixels");
								sql.close();
							} else {
								pixels = "0";
							}
						} else {
							if (Main.plugin.playerDataContainsPlayer(Main.plugin.fakeoriginal.get(p.getUniqueId()).toString())) {
								PreparedStatement sql = Main.plugin.c.prepareStatement("SELECT pixels FROM `player_pixels` WHERE player=?;");

								sql.setString(1, Main.plugin.fakeoriginal.get(p.getUniqueId()).toString());

								ResultSet result = sql.executeQuery();
								result.next();

								pixels = result.getString("pixels");
								sql.close();
							} else {
								pixels = "0";
							}
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					p2.sendMessage(ChatColor.GRAY + "O Player " + p.getName() + " tem: " + ChatColor.AQUA + pixels + CharAPI.getCube());
					return true;
				}
				if (args.length == 3) {
					if (args[0].equalsIgnoreCase("dar")) {
						if (!p2.hasPermission("PixelHG.pex.Mod")) {
							p2.sendMessage(ChatColor.RED + "Você não tem permissão para esse comando");
							return true;
						}
						Player p = Bukkit.getPlayer(args[1]);
						pixels2 = Integer.valueOf(args[2]);
						if (p == null) {
							p2.sendMessage(ChatColor.RED + "Player nao encontrado");
							return true;
						}
						if (pixels2 < 1) {
							p2.sendMessage("Insira um valor válido");
							return true;
						}
						try {
							int pixelsantes = 0;
							if (!Main.plugin.fake.containsKey(p.getUniqueId())) {
								if (Main.plugin.playerDataContainsPlayer(p.getName())) {
									PreparedStatement sql = Main.plugin.c.prepareStatement("SELECT pixels FROM `player_pixels` WHERE player=?;");

									sql.setString(1, p.getName());

									ResultSet result = sql.executeQuery();
									result.next();

									pixelsantes = result.getInt("pixels");

									PreparedStatement loginsUpdate = Main.plugin.c.prepareStatement("UPDATE `player_pixels` SET pixels=? WHERE player=?;");
									loginsUpdate.setInt(1, pixelsantes + pixels2);

									loginsUpdate.setString(2, p.getName());

									loginsUpdate.executeUpdate();
									p.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "+" + pixels2.toString() + ChatColor.AQUA + CharAPI.getCube());
									p2.sendMessage(ChatColor.AQUA + pixels2.toString() + ChatColor.AQUA + CharAPI.getCube() + ChatColor.GRAY + " adicionados para " + p.getName());

									loginsUpdate.close();
									sql.close();
									result.close();
								} else {
									PreparedStatement newPlayer = Main.plugin.c.prepareStatement("INSERT INTO `player_pixels` values(?," + pixels2 + ");");
									p.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "+" + pixels2.toString() + ChatColor.AQUA + CharAPI.getCube());
									p2.sendMessage(ChatColor.AQUA + pixels2.toString() + ChatColor.AQUA + CharAPI.getCube() + ChatColor.GRAY + " adicionados para " + p.getName());
									newPlayer.setString(1, p.getName());
									newPlayer.execute();
									newPlayer.close();
								}
							} else {
								if (Main.plugin.playerDataContainsPlayer(Main.plugin.fakeoriginal.get(p.getUniqueId()).toString())) {
									PreparedStatement sql = Main.plugin.c.prepareStatement("SELECT pixels FROM `player_pixels` WHERE player=?;");

									sql.setString(1, Main.plugin.fakeoriginal.get(p.getUniqueId()).toString());

									ResultSet result = sql.executeQuery();
									result.next();

									pixelsantes = result.getInt("pixels");

									PreparedStatement loginsUpdate = Main.plugin.c.prepareStatement("UPDATE `player_pixels` SET pixels=? WHERE player=?;");
									loginsUpdate.setInt(1, pixelsantes + pixels2);

									loginsUpdate.setString(2, Main.plugin.fakeoriginal.get(p.getUniqueId()).toString());

									loginsUpdate.executeUpdate();
									p.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "+" + pixels2.toString() + ChatColor.AQUA + CharAPI.getCube());
									p2.sendMessage(ChatColor.AQUA + pixels2.toString() + ChatColor.AQUA + CharAPI.getCube() + ChatColor.GRAY + " adicionados para " + p.getName());

									loginsUpdate.close();
									sql.close();
									result.close();
								} else {
									PreparedStatement newPlayer = Main.plugin.c.prepareStatement("INSERT INTO `player_pixels` values(?," + pixels2 + ");");
									p.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "+" + pixels2.toString() + ChatColor.AQUA + CharAPI.getCube());
									p2.sendMessage(ChatColor.AQUA + pixels2.toString() + ChatColor.AQUA + CharAPI.getCube() + ChatColor.GRAY + " adicionados para " + p.getName());
									newPlayer.setString(1, Main.plugin.fakeoriginal.get(p.getUniqueId()).toString());
									newPlayer.execute();
									newPlayer.close();
								}
							}
						} catch (Exception e2) {

						}
						return true;
					}
					if (args[0].equalsIgnoreCase("remover")) {
						if (!p2.hasPermission("PixelHG.pex.Mod")) {
							p2.sendMessage(ChatColor.RED + "Você não tem permissão para esse comando");
							return true;
						}
						Player p = Bukkit.getPlayer(args[1]);
						pixels2 = Integer.valueOf(args[2]);
						if (p == null) {
							p2.sendMessage(ChatColor.RED + "Player nao encontrado");
							return true;
						}
						if (pixels2 < 1) {
							p2.sendMessage("Insira um valor válido");
							return true;
						}
						try {
							int pixelsantes = 0;
							if (!Main.plugin.fake.containsKey(p.getUniqueId())) {
								if (Main.plugin.playerDataContainsPlayer(p.getName())) {
									PreparedStatement sql = Main.plugin.c.prepareStatement("SELECT pixels FROM `player_pixels` WHERE player=?;");

									sql.setString(1, p.getName());

									ResultSet result = sql.executeQuery();
									result.next();

									pixelsantes = result.getInt("pixels");

									if (pixels2 > pixelsantes) {
										p2.sendMessage(ChatColor.GRAY + "O player não tem pixels o suficiente para serem tirados.");
										return true;
									}
									PreparedStatement loginsUpdate = Main.plugin.c.prepareStatement("UPDATE `player_pixels` SET pixels=? WHERE player=?;");
									loginsUpdate.setInt(1, pixelsantes - pixels2);
									loginsUpdate.setString(2, p.getName());
									loginsUpdate.executeUpdate();
									p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "-" + pixels2.toString() + ChatColor.AQUA + CharAPI.getCube());
									p2.sendMessage(ChatColor.AQUA + pixels2.toString() + ChatColor.AQUA + CharAPI.getCube() + ChatColor.GRAY + " removidos de " + p.getName());

									loginsUpdate.close();
									sql.close();
									result.close();
								} else {
									p2.sendMessage(ChatColor.GRAY + "O player não tem pixels o suficiente para serem tirados.");
								}
							} else {
								if (Main.plugin.playerDataContainsPlayer(Main.plugin.fakeoriginal.get(p.getUniqueId()).toString())) {
									PreparedStatement sql = Main.plugin.c.prepareStatement("SELECT pixels FROM `player_pixels` WHERE player=?;");

									sql.setString(1, Main.plugin.fakeoriginal.get(p.getUniqueId()).toString());

									ResultSet result = sql.executeQuery();
									result.next();

									pixelsantes = result.getInt("pixels");

									if (pixels2 > pixelsantes) {
										p2.sendMessage(ChatColor.GRAY + "O player não tem pixels o suficiente para serem tirados.");
										return true;
									}
									PreparedStatement loginsUpdate = Main.plugin.c.prepareStatement("UPDATE `player_pixels` SET pixels=? WHERE player=?;");
									loginsUpdate.setInt(1, pixelsantes - pixels2);
									loginsUpdate.setString(2, Main.plugin.fakeoriginal.get(p.getUniqueId()).toString());
									loginsUpdate.executeUpdate();
									p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "-" + pixels2.toString() + ChatColor.AQUA + CharAPI.getCube());
									p2.sendMessage(ChatColor.AQUA + pixels2.toString() + ChatColor.AQUA + CharAPI.getCube() + ChatColor.GRAY + " removidos de " + p.getName());

									loginsUpdate.close();
									sql.close();
									result.close();
								} else {
									p2.sendMessage(ChatColor.GRAY + "O player não tem pixels o suficiente para serem tirados.");
								}
							}
						} catch (Exception e2) {

						}
						return true;
					}
				}
			}
			try {
				if (!Main.plugin.fake.containsKey(p2.getUniqueId())) {
					if (Main.plugin.playerDataContainsPlayer(p2.getName())) {
						PreparedStatement sql = Main.plugin.c.prepareStatement("SELECT pixels FROM `player_pixels` WHERE player=?;");
						sql.setString(1, p2.getName());

						ResultSet result = sql.executeQuery();
						result.next();

						pixels = result.getString("pixels");
						sql.close();
					} else {
						pixels = "0";
					}
				} else {
					if (Main.plugin.playerDataContainsPlayer(Main.plugin.fakeoriginal.get(p2.getUniqueId()).toString())) {
						PreparedStatement sql = Main.plugin.c.prepareStatement("SELECT pixels FROM `player_pixels` WHERE player=?;");
						sql.setString(1, Main.plugin.fakeoriginal.get(p2.getUniqueId()).toString());

						ResultSet result = sql.executeQuery();
						result.next();

						pixels = result.getString("pixels");
						sql.close();
					} else {
						pixels = "0";
					}
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			p2.sendMessage(ChatColor.GRAY + "Voce possui: " + ChatColor.AQUA + pixels + CharAPI.getCube());
			return true;
		}
		return false;
	}

}
