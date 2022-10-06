package me.ghost.Comandos;

import me.ghost.Main;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayersCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (label.equalsIgnoreCase("players")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Você não é um player!");
				return true;
			}
			if (!sender.hasPermission("PixelHG.admin")) {
				sender.sendMessage(ChatColor.RED + "Você não tem permissão para esse comando.");
				return true;
			}
			String server = null;

			if (args.length > 0) {
				server = args[0];
			}

			Player player = (Player) sender;
			Main.plugin.getCount(player, server);
			

		}
		return false;
	}

}
