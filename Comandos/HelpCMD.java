package me.ghost.Comandos;

import java.text.SimpleDateFormat;

import me.ghost.Main;
import me.ghost.Enums.Estagio;
import me.ghost.Listener.DeathListener;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Voce nao e um player");
			return true;
		}
		Player p = (Player) sender;
		if (label.equalsIgnoreCase("help")) {
			if (Main.est == Estagio.PREJOGO) {
				p.sendMessage(ChatColor.GRAY + "Estagio: " + ChatColor.GREEN + "Inciando");
				p.sendMessage(ChatColor.GRAY + "Seu kit: " + ChatColor.GREEN + Main.plugin.kit.getKitName(Main.plugin.kit.getPlayerKitnosurprise(p)));
				p.sendMessage(ChatColor.GRAY + "IP: " + ChatColor.GREEN + Main.plugin.IP);

			}
			if (Main.est == Estagio.INVENCIBILIDADE) {
				p.sendMessage(ChatColor.GRAY + "Estagio: " + ChatColor.GREEN + "Invencibilidade");
				p.sendMessage(ChatColor.GRAY + "Seu kit: " + ChatColor.GREEN + Main.plugin.kit.getKitName(Main.plugin.kit.getPlayerKitnosurprise(p)));
				p.sendMessage(ChatColor.GRAY + "IP: " + ChatColor.GREEN + Main.plugin.IP);

			}
			if (Main.est == Estagio.JOGO) {
				int millis = Main.TempoPartida * 1000;
				SimpleDateFormat df = new SimpleDateFormat("mm:ss");
				String time = df.format(Integer.valueOf(millis));
				p.sendMessage(ChatColor.GRAY + "Estagio: " + ChatColor.GREEN + "Jogo: " + time);
				p.sendMessage(ChatColor.GRAY + "Seu kit: " + ChatColor.GREEN + Main.plugin.kit.getKitName(Main.plugin.kit.getPlayerKitnosurprise(p)));
				p.sendMessage(ChatColor.GRAY + "Players: " + ChatColor.GREEN + Main.plugin.jogadores.size());
				p.sendMessage(ChatColor.GRAY + "Kills: " + ChatColor.GREEN + DeathListener.getKs(p));
				p.sendMessage(ChatColor.GRAY + "IP: " + ChatColor.GREEN + Main.plugin.IP);

			}
			if (Main.est == Estagio.VITORIA) {
				p.sendMessage(ChatColor.GRAY + "Estagio: " + ChatColor.GREEN + "Vitória");
				p.sendMessage(ChatColor.GRAY + "Seu kit: " + ChatColor.GREEN + Main.plugin.kit.getKitName(Main.plugin.kit.getPlayerKitnosurprise(p)));
				p.sendMessage(ChatColor.GRAY + "Kills: " + ChatColor.GREEN + DeathListener.getKs(p));
				p.sendMessage(ChatColor.GRAY + "IP: " + ChatColor.GREEN + Main.plugin.IP);

			}
			return true;
		}

		return false;
	}
}
