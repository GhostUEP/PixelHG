package me.ghost.Tempo;

import java.util.Random;

import me.ghost.Main;
import me.ghost.API.ScoreboardAPI;
import me.ghost.Enums.Estagio;
import me.ghost.Util.CharAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class PreJogo {
	public static int taskComeçando;

	public static void ComeçarPreJogo(int seconds) {
		Main.TempoPrejogo = seconds;
		Main.est = Estagio.PREJOGO;

		taskComeçando = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
			@SuppressWarnings("deprecation")
			public void run() {
				Bukkit.getWorld("world").setTime(0);
				for (Player online : Bukkit.getOnlinePlayers()) {
					if (Main.plugin.PlayersPreJogo.size() < 10) {
						ScoreboardAPI.SetSemPlayers2(online);
					} else {
						ScoreboardAPI.SetSemPlayers(online);
					}
				}
				Main.TempoPrejogo -= 1;
				if (Main.plugin.PlayersPreJogo.size() < 10 && Main.TempoPrejogo > 10) {
					Main.TempoPrejogo = 301;
				}
				if (Main.plugin.PlayersPreJogo.size() >= 60 && Main.TempoPrejogo < 300 && Main.TempoPrejogo > 30) {
					Main.TempoPrejogo = 30;

				}
				switch (Main.TempoPrejogo) {
				case 299:
					Bukkit.broadcastMessage(CharAPI.preFix() + ChatColor.AQUA + " A Partida vai iniciar em: 5 minutos.");
					break;
				case 240:
					Bukkit.broadcastMessage(CharAPI.preFix() + ChatColor.AQUA + " A Partida vai iniciar em: 4 minutos.");
					break;
				case 180:
					Bukkit.broadcastMessage(CharAPI.preFix() + ChatColor.AQUA + " A Partida vai iniciar em: 3 minutos.");
					break;
				case 120:
					Bukkit.broadcastMessage(CharAPI.preFix() + ChatColor.AQUA + " A Partida vai iniciar em: 2 minutos.");
					break;
				case 60:
					Bukkit.broadcastMessage(CharAPI.preFix() + ChatColor.AQUA + " A Partida vai iniciar em: 1 minuto.");
					break;
				case 45:
					Bukkit.broadcastMessage(CharAPI.preFix() + ChatColor.AQUA + " A Partida vai iniciar em: 45 segundos.");
					break;
				case 30:
					Bukkit.broadcastMessage(CharAPI.preFix() + ChatColor.AQUA + " A Partida vai iniciar em: 30 segundos.");
					break;
				case 15:
					Location local = new Location(Bukkit.getWorld("world"), 0, Bukkit.getWorld("world").getHighestBlockYAt(0, 0), 0);

					for (Player p : Bukkit.getOnlinePlayers()) {
						Random r = new Random();
						Location loc = new Location(Bukkit.getWorld("world"), -20 + r.nextInt(20), local.getY() + 10, -20 + r.nextInt(20));
						loc.getWorld().getChunkAt(loc).load();
						p.teleport(loc);
					}
					break;
				case 10:
					Bukkit.broadcastMessage(CharAPI.preFix() + ChatColor.AQUA + " A Partida vai iniciar em: 10 segundos.");
					for (Player online : Bukkit.getOnlinePlayers()) {
						online.playSound(online.getLocation(), Sound.NOTE_PLING, 10.0F, 1.0F);
					}
					break;
				case 5:
					Bukkit.broadcastMessage(CharAPI.preFix() + ChatColor.AQUA + " A Partida vai iniciar em: 5 segundos.");
					for (Player online : Bukkit.getOnlinePlayers()) {
						online.playSound(online.getLocation(), Sound.CLICK, 10.0F, 1.0F);
					}
					break;
				case 4:
					Bukkit.broadcastMessage(CharAPI.preFix() + ChatColor.AQUA + " A Partida vai iniciar em: 4 segundos.");
					for (Player online : Bukkit.getOnlinePlayers()) {
						online.playSound(online.getLocation(), Sound.CLICK, 10.0F, 1.0F);
					}
					break;
				case 3:
					Bukkit.broadcastMessage(CharAPI.preFix() + ChatColor.AQUA + " A Partida vai iniciar em: 3 segundos.");
					for (Player online : Bukkit.getOnlinePlayers()) {
						online.playSound(online.getLocation(), Sound.CLICK, 10.0F, 1.0F);
					}
					break;
				case 2:
					Bukkit.broadcastMessage(CharAPI.preFix() + ChatColor.AQUA + " A Partida vai iniciar em: 2 segundos.");
					for (Player online : Bukkit.getOnlinePlayers()) {
						online.playSound(online.getLocation(), Sound.CLICK, 10.0F, 1.0F);
					}
					break;
				case 1:
					Bukkit.broadcastMessage(CharAPI.preFix() + ChatColor.AQUA + " A Partida vai iniciar em: 1 segundo.");
					for (Player online : Bukkit.getOnlinePlayers()) {
						online.playSound(online.getLocation(), Sound.CLICK, 10.0F, 1.0F);
					}
					break;
				case 0:
					Bukkit.getScheduler().cancelTask(taskComeçando);
					for (Player online : Bukkit.getOnlinePlayers()) {
						online.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

					}
					Invencibilidade.ComercarInvencibilidade(121);
					break;
				}
			}
		}, 0L, 20L);
	}
}
