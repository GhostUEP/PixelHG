package me.ghost.Comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class TpallCMD implements CommandExecutor, Listener {

	public boolean isInt(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Sem comandos no console!");
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("tpall")) {
			if ((sender.hasPermission("PixelHG.pex.Mod"))) {
				Player p2 = (Player) sender;
				for (Player p : Bukkit.getOnlinePlayers()) {
					p.teleport(p2.getLocation());
				}
				return true;
			}
		}
		return false;
	}
}