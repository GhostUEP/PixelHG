package me.ghost.Comandos;

import me.ghost.Main;
import me.ghost.Manager.PlayerManager;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Voce nao e um player");
			return true;
		}
		Player p = (Player) sender;
		if (label.equalsIgnoreCase("kit")) {
			if (args.length == 0) {
				Main.plugin.kit.printKitChat(p);
			} else if (args.length == 1) {
				Main.plugin.kit.setKit(p, args[0]);
			} else {
				Main.plugin.kit.printKitChat(p);
			}
			return true;
		}
		if (label.equalsIgnoreCase("checkwinner")) {
			if (p.hasPermission("PixelHG.admin")) {
				PlayerManager.checkWinner();
			}
			return true;
		}

		return false;
	}
}
