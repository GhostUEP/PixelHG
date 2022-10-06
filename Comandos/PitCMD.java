package me.ghost.Comandos;

import me.ghost.Main;
import me.ghost.Util.Pit;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PitCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Voce n�o � um player");
		}
		Player p = (Player) sender;
		if (!p.hasPermission("PixelHG.pex.Mod")) {
			p.sendMessage(ChatColor.RED + "Voc� n�o tem permiss�o para esse comando");
			return true;
		}
		if (label.equalsIgnoreCase("pit")) {
			if (Pit.spawned) {
				p.sendMessage(ChatColor.RED + "O Pit j� foi spawnado");
				return true;
			}
			Main.pit2.spawnPit();
			return true;
		}
		return false;
	}
}
