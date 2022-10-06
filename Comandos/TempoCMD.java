package me.ghost.Comandos;

import java.text.SimpleDateFormat;

import me.ghost.Main;
import me.ghost.Enums.Estagio;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class TempoCMD implements CommandExecutor, Listener {

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
		final Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("tempo")) {
			if ((sender.hasPermission("PixelHG.tempo")) && (args.length == 1)) {
				if (Main.est == Estagio.PREJOGO) {
					Main.TempoPrejogo = Integer.valueOf(Integer.parseInt(args[0]));
					int millis = Main.TempoPrejogo * 1000;
					SimpleDateFormat df = new SimpleDateFormat("mm:ss");
					String time = df.format(Integer.valueOf(millis));
					p.sendMessage(ChatColor.AQUA + "Tempo mudado para: " + time);
				} else if (Main.est == Estagio.INVENCIBILIDADE) {
					Main.TempoInvencibilidade = Integer.valueOf(Integer.parseInt(args[0]));
					int millis = Main.TempoInvencibilidade * 1000;
					SimpleDateFormat df = new SimpleDateFormat("mm:ss");
					String time = df.format(Integer.valueOf(millis));
					p.sendMessage(ChatColor.AQUA + "Tempo mudado para: " + time);
				} else if (Main.est == Estagio.JOGO) {
					Main.TempoPartida = Integer.valueOf(Integer.parseInt(args[0]));
					int millis = Main.TempoPartida * 1000;
					SimpleDateFormat df = new SimpleDateFormat("mm:ss");
					String time = df.format(Integer.valueOf(millis));
					p.sendMessage(ChatColor.AQUA + "Tempo mudado para: " + time);
				}
			} else if ((args.length != 1) && (sender.hasPermission("PixelHG.tempo"))) {
				p.sendMessage(ChatColor.RED + "Escreva o tempo desejado");
				return true;
			}
		}
		return false;
	}
}