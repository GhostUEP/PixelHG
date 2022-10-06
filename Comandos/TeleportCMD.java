package me.ghost.Comandos;

import me.ghost.Main;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("teleport")) {
			Player p = (Player) sender;
			if (!Main.plugin.admin.isSpectating(p)) {
				p.sendMessage("Você não é um espectador");
				return true;
			}
			Player player = (Player) sender;
			if (args.length == 0) {
				p.sendMessage(ChatColor.RED + "Use: /teleport [Player]");
			} else if (args.length == 1) {
				Player targetPlayer = player.getServer().getPlayer(args[0]);
				player.teleport(targetPlayer.getLocation());
			}
		}
		return false;
	}
}
