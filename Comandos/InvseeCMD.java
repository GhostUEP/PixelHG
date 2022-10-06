package me.ghost.Comandos;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InvseeCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("invsee")) {
			Player p = (Player) sender;
			if (!p.hasPermission("PixelHG.pex.Mod")) {
				p.sendMessage("Você não tem permissão para esse comando");
				return true;
			}
			Player player = (Player) sender;
			if (args.length == 0) {
				p.sendMessage(ChatColor.RED + "Use: /invsee [Player]");
			} else if (args.length == 1) {
				Player targetPlayer = player.getServer().getPlayer(args[0]);
				Inventory targetPlayerInventory = targetPlayer.getInventory();
				player.openInventory(targetPlayerInventory);
			}
		}
		return false;
	}
}
