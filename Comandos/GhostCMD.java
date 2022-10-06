package me.ghost.Comandos;

import me.ghost.Util.CharAPI;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GhostCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Voce nao e um player");
			return true;
		}
		Player p = (Player) sender;
		if (label.equalsIgnoreCase("ghost")) {
			p.sendMessage(CharAPI.getCubeYellow() + CharAPI.getCubeYellow() + CharAPI.getCubeYellow() + CharAPI.getCubeYellow() + CharAPI.getCubeYellow() + CharAPI.getCubeYellow() + CharAPI.getCubeYellow() + CharAPI.getCubeYellow());
			p.sendMessage(CharAPI.getCubeYellow() + CharAPI.getCubeYellow() + CharAPI.getCubeGold() + CharAPI.getCubeGold() + CharAPI.getCubeGold() + CharAPI.getCubeGold() + CharAPI.getCubeYellow() + CharAPI.getCubeYellow());
			p.sendMessage(CharAPI.getCubeYellow() + CharAPI.getCubeGold() + CharAPI.getCubeGold() + CharAPI.getCubeGold() + CharAPI.getCubeGold() + CharAPI.getCubeGold() + CharAPI.getCubeGold() + CharAPI.getCubeYellow());
			p.sendMessage(CharAPI.getCubeGold() + CharAPI.getCubeWhite() + CharAPI.getCubeGreen() + CharAPI.getCubeGold() + CharAPI.getCubeGold() + CharAPI.getCubeGreen() + CharAPI.getCubeWhite() + CharAPI.getCubeGold());
			p.sendMessage(CharAPI.getCubeGold() + CharAPI.getCubeWhite() + CharAPI.getCubeGreen() + CharAPI.getCubeGold() + CharAPI.getCubeGold() + CharAPI.getCubeGreen() + CharAPI.getCubeWhite() + CharAPI.getCubeGold());
			p.sendMessage(CharAPI.getCubeGold() + CharAPI.getCubeGold() + CharAPI.getCubeGold() + CharAPI.getCubeGold() + CharAPI.getCubeGold() + CharAPI.getCubeGold() + CharAPI.getCubeGold() + CharAPI.getCubeGold());
			p.sendMessage(CharAPI.getCubeGold() + CharAPI.getCubeGold() + CharAPI.getCubeGold() + CharAPI.getCubeGold() + CharAPI.getCubeGold() + CharAPI.getCubeGold() + CharAPI.getCubeGold() + CharAPI.getCubeGold());
			p.sendMessage(CharAPI.getCubeGold() + CharAPI.getCubeGold() + CharAPI.getCubeGold() + CharAPI.getCubeGold() + CharAPI.getCubeGold() + CharAPI.getCubeGold() + CharAPI.getCubeGold() + CharAPI.getCubeGold());
			return true;
		}
		return false;
	}
}
