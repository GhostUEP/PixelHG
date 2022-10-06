package me.ghost.Tempo;

import me.ghost.Main;
import me.ghost.Enums.Estagio;
import me.ghost.Manager.PlayerManager;
import me.ghost.Util.Feast;
import me.ghost.Util.Minifeast;
import me.ghost.Util.Pit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

public class Jogo {
	public static Feast feast2;
	public static int taskGame;

	public static void ComeçarJogo(int Contagem) {
		Main.est = Estagio.JOGO;
		Main.TempoPartida = Contagem;
		final Feast feast = new Feast(Main.plugin);
		feast2 = feast;
		taskGame = Integer.valueOf(Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {

			public void run() {
				Main.TempoPartida += 1;

				if (!feast.spawned && Main.TempoPartida == 900) {
					feast.generateFeast();
					Main.plugin.feastlocation = new Location(Bukkit.getWorld("world"), feast.central.getX(), feast.central.getY(), feast.central.getZ());
					Bukkit.getServer().broadcastMessage(ChatColor.RED + "O Feast vai começar em: (" + feast.central.getX() + ", " + feast.central.getY() + ", " + feast.central.getZ() + "). Em: 5 minutos.");
				}
				if (feast.spawned && Main.TempoPartida == 960) {
					Bukkit.getServer().broadcastMessage(ChatColor.RED + "O Feast vai começar em: (" + feast.central.getX() + ", " + feast.central.getY() + ", " + feast.central.getZ() + "). Em: 4 minutos.");
				}
				if (feast.spawned && Main.TempoPartida == 1020) {
					Bukkit.getServer().broadcastMessage(ChatColor.RED + "O Feast vai começar em: (" + feast.central.getX() + ", " + feast.central.getY() + ", " + feast.central.getZ() + "). Em: 3 minutos.");
				}
				if (feast.spawned && Main.TempoPartida == 1080) {
					Bukkit.getServer().broadcastMessage(ChatColor.RED + "O Feast vai começar em: (" + feast.central.getX() + ", " + feast.central.getY() + ", " + feast.central.getZ() + "). Em: 2 minutos.");
				}
				if (feast.spawned && Main.TempoPartida == 1140) {
					Bukkit.getServer().broadcastMessage(ChatColor.RED + "O Feast vai começar em: (" + feast.central.getX() + ", " + feast.central.getY() + ", " + feast.central.getZ() + "). Em: 1 minuto.");
				}
				if (feast.spawned && Main.TempoPartida == 1155) {
					Bukkit.getServer().broadcastMessage(ChatColor.RED + "O Feast vai começar em: (" + feast.central.getX() + ", " + feast.central.getY() + ", " + feast.central.getZ() + "). Em: 45 segundos.");
				}
				if (feast.spawned && Main.TempoPartida == 1170) {
					Bukkit.getServer().broadcastMessage(ChatColor.RED + "O Feast vai começar em: (" + feast.central.getX() + ", " + feast.central.getY() + ", " + feast.central.getZ() + "). Em: 30 segundos.");
				}
				if (feast.spawned && Main.TempoPartida == 1185) {
					Bukkit.getServer().broadcastMessage(ChatColor.RED + "O Feast vai começar em: (" + feast.central.getX() + ", " + feast.central.getY() + ", " + feast.central.getZ() + "). Em: 15 segundos.");
				}
				if (feast.spawned && Main.TempoPartida == 1190) {
					Bukkit.getServer().broadcastMessage(ChatColor.RED + "O Feast vai começar em: (" + feast.central.getX() + ", " + feast.central.getY() + ", " + feast.central.getZ() + "). Em: 10 segundos.");
				}
				if (feast.spawned && Main.TempoPartida == 1195) {
					Bukkit.getServer().broadcastMessage(ChatColor.RED + "O Feast vai começar em: (" + feast.central.getX() + ", " + feast.central.getY() + ", " + feast.central.getZ() + "). Em: 5 segundos.");
				}
				if (feast.spawned && Main.TempoPartida == 1196) {
					Bukkit.getServer().broadcastMessage(ChatColor.RED + "O Feast vai começar em: (" + feast.central.getX() + ", " + feast.central.getY() + ", " + feast.central.getZ() + "). Em: 4 segundos.");
				}
				if (feast.spawned && Main.TempoPartida == 1197) {
					Bukkit.getServer().broadcastMessage(ChatColor.RED + "O Feast vai começar em: (" + feast.central.getX() + ", " + feast.central.getY() + ", " + feast.central.getZ() + "). Em: 3 segundos.");
				}
				if (feast.spawned && Main.TempoPartida == 1198) {
					Bukkit.getServer().broadcastMessage(ChatColor.RED + "O Feast vai começar em: (" + feast.central.getX() + ", " + feast.central.getY() + ", " + feast.central.getZ() + "). Em: 2 segundos.");
				}
				if (feast.spawned && Main.TempoPartida == 1199) {
					Bukkit.getServer().broadcastMessage(ChatColor.RED + "O Feast vai começar em: (" + feast.central.getX() + ", " + feast.central.getY() + ", " + feast.central.getZ() + "). Em: 1 segundo.");
				}
				if (feast.spawned && Main.TempoPartida == 1200) {
					feast.generateChests();
				}
				if (Main.TempoPartida == 2700) {
					Bukkit.getServer().broadcastMessage(ChatColor.RED + "A Arena final vai começar em 5 mintuos.");
				}
				if (Main.TempoPartida == 3000) {
					if (Pit.spawned == false) {
						Main.pit2.spawnPit();
					}
				}
				if (Main.TempoPartida == 3598) {
					PlayerManager.check();

				}
				if (Main.TempoPartida == 600) {
					new Minifeast(Main.plugin);
				}

				if (Main.TempoPartida == 1500) {
					new Minifeast(Main.plugin);
				}
				if (Main.TempoPartida == 1800) {
					new Minifeast(Main.plugin);
				}
				if (Main.TempoPartida == 2400) {
					new Minifeast(Main.plugin);
				}
			}
		}, 0, 20L));
	}
}
