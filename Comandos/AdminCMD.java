package me.ghost.Comandos;

import me.ghost.Main;
import me.ghost.Listener.DeathListener;
import me.ghost.Manager.PlayerManager;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class AdminCMD implements CommandExecutor, Listener {

	public boolean isInt(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
		}
		return false;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Voce nao e um player");
			return true;
		}
		Player p = (Player) sender;
		if (label.equalsIgnoreCase("admin")) {
			if (!p.hasPermission("PixelHG.admin")) {
				p.sendMessage(ChatColor.RED + "Sem permissão para esse comando.");
				return true;
			}
			if (Main.plugin.admin.isAdmin(p)) {
				Main.plugin.admin.setPlayer(p);
			} else {
				if (!Main.plugin.NaoEstaJogando(p)) {
					String playerKit = ChatColor.AQUA + p.getName() + "(" + Main.plugin.kit.getKitName(Main.plugin.kit.getPlayerKit(p)) + ")";
					DeathListener.deathMessage.put(p.getUniqueId(), playerKit + " desistiu da partida");
					DeathListener.deathMessage(playerKit + " desistiu da partida");
					PlayerManager.dropItems(p, p.getLocation());
					Main.plugin.admin.setAdmin(p);
					Main.plugin.RemoverJogador(p);
					PlayerManager.checkWinner();
				} else {
					Main.plugin.admin.setAdmin(p);
				}
			}
			return true;
		}
		return false;
	}

}
