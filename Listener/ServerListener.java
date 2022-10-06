package me.ghost.Listener;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;

import me.ghost.Main;
import me.ghost.Enums.Estagio;
import me.ghost.Eventos.TimeSecondEvent3;
import me.ghost.Util.CharAPI;
import me.ghost.Util.Feast;
import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.server.v1_7_R4.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_7_R4.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_7_R4.PacketPlayOutPlayerInfo;
import net.minecraft.util.com.mojang.authlib.GameProfile;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class ServerListener implements Listener {
	public static boolean chat = true;

	@EventHandler
	public void onPing(ServerListPingEvent event) {
		if (Main.est == Estagio.PREJOGO) {
			if (Main.plugin.PlayersPreJogo.size() < 10) {
				event.setMotd(ChatColor.GRAY + "-------" + ChatColor.AQUA + "" + ChatColor.BOLD + "Inicia em: " + ChatColor.RED + "" + ChatColor.BOLD + "..." + ChatColor.GRAY + "-------\n" + ChatColor.AQUA + "" + ChatColor.BOLD + "█ " + ChatColor.WHITE + "█" + ChatColor.AQUA + " █" + ChatColor.WHITE + " █" + ChatColor.AQUA + " █ " + CharAPI.preFix2() + ChatColor.AQUA + " █ " + ChatColor.WHITE + "█ " + ChatColor.AQUA + "█ " + ChatColor.WHITE + "█ " + ChatColor.AQUA + "█");
			} else {
				int millis = Main.TempoPrejogo * 1000;
				SimpleDateFormat df = new SimpleDateFormat("mm:ss");
				String time = df.format(Integer.valueOf(millis));
				event.setMotd(ChatColor.GRAY + "-------" + ChatColor.AQUA + "" + ChatColor.BOLD + "Inicia em: " + ChatColor.RED + "" + ChatColor.BOLD + time + ChatColor.GRAY + "-------\n" + ChatColor.AQUA + "" + ChatColor.BOLD + "█ " + ChatColor.WHITE + "█" + ChatColor.AQUA + " █" + ChatColor.WHITE + " █" + ChatColor.AQUA + " █ " + CharAPI.preFix2() + ChatColor.AQUA + " █ " + ChatColor.WHITE + "█ " + ChatColor.AQUA + "█ " + ChatColor.WHITE + "█ " + ChatColor.AQUA + "█");
			}
		} else if (Main.est == Estagio.INVENCIBILIDADE) {
			int millis = Main.TempoInvencibilidade * 1000;
			SimpleDateFormat df = new SimpleDateFormat("mm:ss");
			String time = df.format(Integer.valueOf(millis));
			event.setMotd(ChatColor.GRAY + "----" + ChatColor.YELLOW + "" + ChatColor.BOLD + "Invencibilidade: " + ChatColor.RED + "" + ChatColor.BOLD + time + ChatColor.GRAY + "----\n" + ChatColor.AQUA + "" + ChatColor.BOLD + "█ " + ChatColor.WHITE + "█" + ChatColor.AQUA + " █" + ChatColor.WHITE + " █" + ChatColor.AQUA + " █ " + CharAPI.preFix2() + ChatColor.AQUA + " █ " + ChatColor.WHITE + "█ " + ChatColor.AQUA + "█ " + ChatColor.WHITE + "█ " + ChatColor.AQUA + "█");
		} else if (Main.est == Estagio.JOGO) {
			int millis = Main.TempoPartida * 1000;
			SimpleDateFormat df = new SimpleDateFormat("mm:ss");
			String time = df.format(Integer.valueOf(millis));
			event.setMotd(ChatColor.GRAY + "--------" + ChatColor.RED + "" + ChatColor.BOLD + "Partida: " + ChatColor.RED + "" + ChatColor.BOLD + time + ChatColor.GRAY + "--------\n" + ChatColor.AQUA + "" + ChatColor.BOLD + "█ " + ChatColor.WHITE + "█" + ChatColor.AQUA + " █" + ChatColor.WHITE + " █" + ChatColor.AQUA + " █ " + CharAPI.preFix2() + ChatColor.AQUA + " █ " + ChatColor.WHITE + "█ " + ChatColor.AQUA + "█ " + ChatColor.WHITE + "█ " + ChatColor.AQUA + "█");
		} else if (Main.est == Estagio.VITORIA) {
			int millis = Main.TempoPartida * 1000;
			SimpleDateFormat df = new SimpleDateFormat("mm:ss");
			String time = df.format(Integer.valueOf(millis));
			event.setMotd(ChatColor.GRAY + "--------" + ChatColor.GOLD + "" + ChatColor.BOLD + "Vitoria: " + ChatColor.GOLD + "" + ChatColor.BOLD + time + ChatColor.GRAY + "--------\n" + ChatColor.AQUA + "" + ChatColor.BOLD + "█ " + ChatColor.WHITE + "█" + ChatColor.AQUA + " █" + ChatColor.WHITE + " █" + ChatColor.AQUA + " █ " + CharAPI.preFix2() + ChatColor.AQUA + " █ " + ChatColor.WHITE + "█ " + ChatColor.AQUA + "█ " + ChatColor.WHITE + "█ " + ChatColor.AQUA + "█");
		} else
			event.setMotd(ChatColor.GRAY + "Reiniciando...");
	}

	@EventHandler
	public void onComando(PlayerCommandPreprocessEvent e) {
		if (e.getMessage().contains("bukkit:me")) {
			e.setCancelled(true);
		}
		if (e.getMessage().contains("/me")) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onfeasty(PlayerInteractEvent e) {
		Block b = e.getClickedBlock();
		if (Feast.isFeastBlock(b)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onRainStart(WeatherChangeEvent event) {
		if (!event.isCancelled()) {
			if ((event.toWeatherState())) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onMensagem(TimeSecondEvent3 e) {
		Bukkit.broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Compre sua entrada para o torneio valendo um DeathAdder Chroma e um vip PRO de 40 dias!");
		Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + "http://loja.mc-pixel.com");
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onFake(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (Main.plugin.fake.containsKey(p.getUniqueId())) {
			e.setJoinMessage(ChatColor.AQUA + Main.plugin.fake.get(p.getUniqueId()).toString() + " entrou no server.");
			p.sendMessage(ChatColor.RED + "Você entrou de um Nick Fake: " + Main.plugin.fake.get(p.getUniqueId()).toString());
			EntityPlayer gay = ((CraftPlayer) p).getHandle();
			PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(gay.getId());
			for (Player todos : Bukkit.getOnlinePlayers()) {
				if (todos != p) {
					((CraftPlayer) todos).getHandle().playerConnection.sendPacket(destroy);
				}
			}
			GameProfile pf = gay.getProfile();
			pf.getProperties().clear();
			try {
				Field f = pf.getClass().getDeclaredField("name");
				f.setAccessible(true);
				f.set(pf, Main.plugin.fake.get(p.getUniqueId()).toString());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			PacketPlayOutNamedEntitySpawn npc = new PacketPlayOutNamedEntitySpawn(gay);
			PacketPlayOutPlayerInfo inforemove = PacketPlayOutPlayerInfo.removePlayer(gay);
			PacketPlayOutPlayerInfo infoadd = PacketPlayOutPlayerInfo.addPlayer(gay);

			for (Player todos : Bukkit.getOnlinePlayers()) {
				((CraftPlayer) todos).getHandle().playerConnection.sendPacket(inforemove);
				if (todos != p) {
					((CraftPlayer) todos).getHandle().playerConnection.sendPacket(npc);

				}
				((CraftPlayer) todos).getHandle().playerConnection.sendPacket(infoadd);
			}
			p.setDisplayName(ChatColor.GRAY + Main.plugin.fake.get(p.getUniqueId()).toString());
			p.setPlayerListName(ChatColor.GRAY + Main.plugin.fake.get(p.getUniqueId()).toString());
		}
	}

	@EventHandler
	public void onstaffchat(AsyncPlayerChatEvent e) {
		String msg = e.getMessage();
		Player p = e.getPlayer();
		if (msg == "%") {
			e.setCancelled(true);
		}
		if (msg.startsWith("@")) {
			if (p.hasPermission("PixelHG.admin")) {
				e.setCancelled(true);
				Main.plugin.MandarParaAdmins(ChatColor.GRAY + "[" + p.getName() + "]" + ChatColor.RED + "Staff: " + ChatColor.GRAY + msg.replace("@", ""));
			}
		}
		if (Main.est != Estagio.PREJOGO && Main.est != Estagio.VITORIA && Main.plugin.NaoEstaJogando(p) && !p.hasPermission("PixelHG.pex.chat")) {
			e.setCancelled(true);
			Main.plugin.MandarParaSpecs(ChatColor.GRAY + "[MORTO]" + p.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.WHITE + msg);
		}
	}

}