package me.ghost.Comandos;

import me.ghost.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Voce não é um player");
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("chat")) {
			if (sender.hasPermission("PixelHG.pex.Mod")) {
				if (args.length > 0) {
					if (args.length == 1) {
						if (args[0].equalsIgnoreCase("off")) {
							Main.plugin.chat = false;
							Bukkit.broadcastMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "O Chat foi: " + ChatColor.RED + "Desativado");
						} else if (args[0].equalsIgnoreCase("on")) {
							Main.plugin.chat = true;
							Bukkit.broadcastMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "O Chat foi: " + ChatColor.GREEN + "Ativado");
						}
					} else {
						p.sendMessage(ChatColor.RED + "Use: /chat [off/on]");
					}
				} else {
					p.sendMessage(ChatColor.RED + "Use: /chat [off/on]");
				}
			} else {
				p.sendMessage(ChatColor.RED + "Você não tem permissão para esse comando");
			}
			return true;
		}
		return false;
	}
}