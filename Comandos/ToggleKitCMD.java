package me.ghost.Comandos;

import me.ghost.Main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleKitCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("togglekit")) {
			Player p = (Player) sender;
			if (!p.hasPermission("PixelHG.pex.Mod")) {
				p.sendMessage("Você não tem permissão para esse comando");
				return true;
			}
			if (args.length == 0) {
				p.sendMessage(ChatColor.RED + "Use: /togglekit [Kit/All] [On/Off]");
			} else if (args.length == 1) {
				p.sendMessage(ChatColor.RED + "Use: /togglekit [Kit/All] [On/Off]");
			} else if (args.length == 2) {
				if (args[1].equalsIgnoreCase("on") || args[1].equalsIgnoreCase("off")) {
					Main.plugin.kit.toggleKit(p, args[0], args[1]);
				} else {
					p.sendMessage(ChatColor.RED + "Use: /togglekit [Kit/All] [On/Off]");
				}

			}
		}
		return false;
	}
}
