package me.ghost.Comandos;

import me.ghost.Main;
import me.ghost.Util.Feast;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FFeastCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Voce não é um player");
		}
		Player p = (Player) sender;
		if (!p.hasPermission("PixelHG.pex.Mod")) {
			p.sendMessage(ChatColor.RED + "Você não tem permissão para esse comando");
			return true;
		}
		if (label.equalsIgnoreCase("ffeast")) {
			Feast f = new Feast(Main.plugin);
			f.generateFeast();
			f.generateChests();
			return true;
		}
		return false;
	}
}
