package me.ghost.Comandos;

import me.ghost.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ExecCMD implements CommandExecutor {

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (label.equalsIgnoreCase("exec")) {
			if ((sender instanceof Player)) {
				Player p2 = (Player) sender;
				if (!p2.hasPermission("PixelHG.pex.Mod")) {
					p2.sendMessage(ChatColor.RED + "Sem permissao para esse comando.");
					return true;
				}
				if (args.length > 0) {
					if (args.length == 1) {
						p2.sendMessage(ChatColor.RED + "Use: /exec [player] [algo]");
						return true;
					}
					if (args.length >= 2) {
						StringBuilder sb = new StringBuilder();
						for (int i = 1; i < args.length; i++) {
							sb.append(args[i]);
							sb.append(" ");
						}
						String name = sb.toString();
						Player p3 = Bukkit.getPlayer(args[0]);
						if (p3 == null) {
							p2.sendMessage(ChatColor.RED + "O Player não está online");
							return true;
						}
						p2.sendMessage(ChatColor.GREEN + "Você forçou " + p3.getName() + " a fazer algo");
						p3.chat(name);
						return true;
					}
				} else {
					p2.sendMessage(ChatColor.RED + "Use: /exec [player] [algo]");
				}
			} else if (sender == Bukkit.getConsoleSender()) {
				if (args.length > 0) {
					if (args.length == 1) {
						Main.plugin.getLogger().info(ChatColor.RED + "Use: /exec [player] [algo]");
						return true;
					}
					if (args.length >= 2) {
						StringBuilder sb = new StringBuilder();
						for (int i = 1; i < args.length; i++) {
							sb.append(args[i]);
							sb.append(" ");
						}
						String name = sb.toString();
						Player p3 = Bukkit.getPlayer(args[0]);
						if (p3 == null) {
							Main.plugin.getLogger().info(ChatColor.RED + "O Player não está online");
							return true;
						}
						Main.plugin.getLogger().info(ChatColor.GREEN + "Você forçou " + p3.getName() + " a fazer algo");
						p3.chat(name);
						return true;
					}
				} else {
					Main.plugin.getLogger().info(ChatColor.RED + "Use: /exec [player] [algo]");
				}
			}
		}
		if (label.equalsIgnoreCase("execall")) {
			if ((sender instanceof Player)) {
				Player p2 = (Player) sender;
				if (!p2.hasPermission("PixelHG.pex.Mod")) {
					p2.sendMessage(ChatColor.RED + "Sem permissao para esse comando.");
					return true;
				}
				if (args.length > 0) {
					if (args.length == 0) {
						p2.sendMessage(ChatColor.RED + "Use: /execall [algo]");
						return true;
					}
					if (args.length >= 1) {
						StringBuilder sb = new StringBuilder();
						for (int i = 0; i < args.length; i++) {
							sb.append(args[i]);
							sb.append(" ");
						}
						String name = sb.toString();
						p2.sendMessage(ChatColor.GREEN + "Você forçou todos do server a fazerem algo");
						for (Player online : Bukkit.getOnlinePlayers()) {
							online.chat(name);
						}

					}
				} else {
					p2.sendMessage(ChatColor.RED + "Use: /execall [algo]");
				}
			} else if (sender == Bukkit.getConsoleSender()) {
				if (args.length > 0) {
					if (args.length == 0) {
						Main.plugin.getLogger().info(ChatColor.RED + "Use: /execall [algo]");
						return true;
					}
					if (args.length >= 1) {
						StringBuilder sb = new StringBuilder();
						for (int i = 0; i < args.length; i++) {
							sb.append(args[i]);
							sb.append(" ");
						}
						String name = sb.toString();
						Main.plugin.getLogger().info(ChatColor.GREEN + "Você forçou todos do server a fazerem algo");
						for (Player online : Bukkit.getOnlinePlayers()) {
							online.chat(name);
						}

					}
				} else {
					Main.plugin.getLogger().info(ChatColor.RED + "Use: /execall [algo]");
				}
			}
		}
		return false;
	}

}
