package me.ghost.Tempo;

import me.ghost.Main;
import me.ghost.API.ScoreboardAPI;
import me.ghost.Enums.Estagio;
import me.ghost.Eventos.GameStartEvent;
import me.ghost.Util.CharAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class Invencibilidade {
	private static int taskInv;

	@SuppressWarnings("deprecation")
	public static void ComercarInvencibilidade(int timer) {
		Main.TempoInvencibilidade = timer;
		Bukkit.getPluginManager().callEvent(new GameStartEvent());
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			Main.plugin.resetPlayersPreJogo(p);
			p.removePotionEffect(PotionEffectType.SLOW);
			p.removePotionEffect(PotionEffectType.JUMP);
			p.setFoodLevel(20);

			if (Main.plugin.admin.isAdmin(p)) {
				p.getInventory().clear();
			} else {
				p.setAllowFlight(false);
				p.setFlying(false);
				Main.plugin.jogadores.add(p.getUniqueId());
				if (Main.plugin.kit.getPlayerKit(p) == null) {
					p.getInventory().setHeldItemSlot(0);
					p.getInventory().clear();
					p.getInventory().addItem(new ItemStack(Material.COMPASS));
				} else {
					p.getInventory().setHeldItemSlot(0);
					p.getInventory().clear();
					if (!Main.plugin.kitsonoff.contains(Main.plugin.kit.KITS.get(p.getUniqueId()))) {
						Main.plugin.kit.giveItem(p);
					}
					p.getInventory().addItem(new ItemStack(Material.COMPASS));

				}
			}

		}

		Main.est = Estagio.INVENCIBILIDADE;
		Bukkit.getWorld("world").setTime(0);
		Main.plugin.chat = false;
		Bukkit.broadcastMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "O Chat foi desativado para reduzir o lag inicial.");
		Bukkit.broadcastMessage(CharAPI.preFix() + ChatColor.RED + " A partida acabou de começar, vocês tem: " + ChatColor.WHITE + "2 minutos de invencibilidade para de prepararem, boa sorte!");

		taskInv = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
			public void run() {
				Main.TempoInvencibilidade -= 1;
				for (Player p : Bukkit.getOnlinePlayers()) {
					ScoreboardAPI.Invencibilidade(p);
				}
				switch (Main.TempoInvencibilidade) {
				case 60:
					Bukkit.broadcastMessage(CharAPI.preFix() + ChatColor.RED + " A Invencibilidade acaba em: 1 minuto!");
					break;
				case 45:
					Bukkit.broadcastMessage(CharAPI.preFix() + ChatColor.RED + " A Invencibilidade acaba em: 45 segundos!");
					break;
				case 30:
					Bukkit.broadcastMessage(CharAPI.preFix() + ChatColor.RED + " A Invencibilidade acaba em: 30 segundos!");
					Main.plugin.chat = true;
					Bukkit.broadcastMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "O Chat foi: " + ChatColor.GREEN + "Ativado");
					break;
				case 10:
					Bukkit.broadcastMessage(CharAPI.preFix() + ChatColor.RED + " A Invencibilidade acaba em: 10 segundos!");
					break;
				case 5:
					Bukkit.broadcastMessage(CharAPI.preFix() + ChatColor.RED + " A Invencibilidade acaba em: 5 segundos!");
					for (Player online : Bukkit.getOnlinePlayers()) {
						online.playSound(online.getLocation(), Sound.CLICK, 10.0F, 1.0F);
					}
					break;
				case 4:
					Bukkit.broadcastMessage(CharAPI.preFix() + ChatColor.RED + " A Invencibilidade acaba em: 4 segundos!");
					for (Player online : Bukkit.getOnlinePlayers()) {
						online.playSound(online.getLocation(), Sound.CLICK, 10.0F, 1.0F);
					}
					break;
				case 3:
					Bukkit.broadcastMessage(CharAPI.preFix() + ChatColor.RED + " A Invencibilidade acaba em: 3 segundos!");
					for (Player online : Bukkit.getOnlinePlayers()) {
						online.playSound(online.getLocation(), Sound.CLICK, 10.0F, 1.0F);
					}
					break;
				case 2:
					Bukkit.broadcastMessage(CharAPI.preFix() + ChatColor.RED + " A Invencibilidade acaba em: 2 segundos!");
					for (Player online : Bukkit.getOnlinePlayers()) {
						online.playSound(online.getLocation(), Sound.CLICK, 10.0F, 1.0F);
					}
					break;
				case 1:
					Bukkit.broadcastMessage(CharAPI.preFix() + ChatColor.RED + " A Invencibilidade acaba em: 1 segundo!");
					for (Player online : Bukkit.getOnlinePlayers()) {
						online.playSound(online.getLocation(), Sound.CLICK, 10.0F, 1.0F);
					}
					break;
				case 0:
					Bukkit.getScheduler().cancelTask(taskInv);
					Bukkit.broadcastMessage(CharAPI.preFix() + ChatColor.RED + " A Invencibilidade acabou!");
					Bukkit.getServer().getWorld("world").setDifficulty(Difficulty.HARD);
					for (Player online : Bukkit.getOnlinePlayers()) {
						online.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
						online.playSound(online.getLocation(), Sound.ANVIL_LAND, 10.0F, 1.0F);
					}
					Jogo.ComeçarJogo(120);
					break;
				}
			}
		}, 0L, 20L);
	}
}
