package me.ghost.Comandos;

import me.ghost.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PvPCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {

			Player p = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("pvp")) {
				if (sender.hasPermission("PixelHG.pex.Mod")) {
					if (args.length > 0) {
						if (args.length == 1) {
							if (args[0].equalsIgnoreCase("off")) {
								Main.plugin.pvp = false;
								Bukkit.broadcastMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "O PvP foi: " + ChatColor.RED + "Desativado");
							} else if (args[0].equalsIgnoreCase("on")) {
								Main.plugin.pvp = true;
								Bukkit.broadcastMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "O PvP foi: " + ChatColor.GREEN + "Ativado");
							}
						} else {
							p.sendMessage(ChatColor.RED + "Use: /pvp [off/on]");
						}
					} else {
						p.sendMessage(ChatColor.RED + "Use: /pvp [off/on]");
					}
				} else {
					p.sendMessage(ChatColor.RED + "Você não tem permissão para esse comando");
				}
				return true;
			}
			if (cmd.getName().equalsIgnoreCase("dano")) {
				if (sender.hasPermission("PixelHG.pex.Mod")) {
					if (args.length > 0) {
						if (args.length == 1) {
							if (args[0].equalsIgnoreCase("off")) {
								Main.plugin.dano = false;
								Bukkit.broadcastMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "O Dano foi: " + ChatColor.RED + "Desativado");
							} else if (args[0].equalsIgnoreCase("on")) {
								Main.plugin.dano = true;
								Bukkit.broadcastMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "O Dano foi: " + ChatColor.GREEN + "Ativado");
							}
						} else {
							p.sendMessage(ChatColor.RED + "Use: /dano [off/on]");
						}
					} else {
						p.sendMessage(ChatColor.RED + "Use: /dano [off/on]");
					}
				} else {
					p.sendMessage(ChatColor.RED + "Você não tem permissão para esse comando");
				}
				return true;
			}
		} else if (sender == Bukkit.getConsoleSender()) {
			if (cmd.getName().equalsIgnoreCase("pvp")) {
				if (args.length > 0) {
					if (args.length == 1) {
						if (args[0].equalsIgnoreCase("off")) {
							Main.plugin.pvp = false;
							Bukkit.broadcastMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "O PvP foi: " + ChatColor.RED + "Desativado");
						} else if (args[0].equalsIgnoreCase("on")) {
							Main.plugin.pvp = true;
							Bukkit.broadcastMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "O PvP foi: " + ChatColor.GREEN + "Ativado");
						}
					} else {
						Main.plugin.getLogger().info(ChatColor.RED + "Use: /pvp [off/on]");
					}
				} else {
					Main.plugin.getLogger().info(ChatColor.RED + "Use: /pvp [off/on]");
				}
				return true;
			}
			if (cmd.getName().equalsIgnoreCase("dano")) {
				if (args.length > 0) {
					if (args.length == 1) {
						if (args[0].equalsIgnoreCase("off")) {
							Main.plugin.dano = false;
							Bukkit.broadcastMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "O Dano foi: " + ChatColor.RED + "Desativado");
						} else if (args[0].equalsIgnoreCase("on")) {
							Main.plugin.dano = true;
							Bukkit.broadcastMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "O Dano foi: " + ChatColor.GREEN + "Ativado");
						}
					} else {
						Main.plugin.getLogger().info(ChatColor.RED + "Use: /dano [off/on]");
					}
				} else {
					Main.plugin.getLogger().info(ChatColor.RED + "Use: /dano [off/on]");
				}
				return true;
			}
		}
		return false;
	}
}