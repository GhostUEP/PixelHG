package me.ghost.Comandos;

import me.ghost.Main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ForceKitCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("forcekit")) {
			Player p = (Player) sender;
			if (!p.hasPermission("PixelHG.pex.Mod")) {
				p.sendMessage("Você não tem permissão para esse comando");
				return true;
			}
			if (args.length == 0) {
				p.sendMessage(ChatColor.RED + "Use: /forcekit [Player/All] [Kit]");
			} else if (args.length == 1) {
				p.sendMessage(ChatColor.RED + "Use: /forcekit [Player/All] [Kit]");
			} else if (args.length == 2) {
				String all = args[0].toString();
				String kit = args[1].toString();

				Main.plugin.kit.forceKit(p, all, kit);

			}
		}
		return false;
	}
}
