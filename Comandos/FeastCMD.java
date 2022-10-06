package me.ghost.Comandos;

import me.ghost.Main;
import me.ghost.Enums.Estagio;
import me.ghost.Tempo.Jogo;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeastCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Voce nao e um player");
			return true;
		}
		Player p = (Player) sender;
		if (label.equalsIgnoreCase("feast")) {
			if (Main.est != Estagio.JOGO) {
				p.sendMessage(ChatColor.RED + "O Feast ainda não começou!");
				return true;
			}
			if (!Jogo.feast2.spawned) {
				p.sendMessage(ChatColor.RED + "O Feast ainda não começou!");
			} else {
				p.sendMessage(ChatColor.AQUA + "Bússola apontando para: " + ChatColor.GRAY + "Feast");
				p.setCompassTarget(Main.plugin.feastlocation);
			}
			return true;
		}

		return false;
	}
}
