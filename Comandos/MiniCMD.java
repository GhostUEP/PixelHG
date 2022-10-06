package me.ghost.Comandos;

import me.ghost.Main;
import me.ghost.Util.Minifeast;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class MiniCMD implements CommandExecutor, Listener {

	public boolean isInt(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
		}
		return false;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Sem comandos no console!");
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("mini")) {
			if ((sender.hasPermission("PixelHG.pex.Mod"))) {
				new Minifeast(Main.plugin);
				return true;
			}
		}
		return false;
	}
}