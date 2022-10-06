package me.ghost.Util;

import java.util.ArrayList;

import me.ghost.Main;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class Mode {
	public ArrayList<Player> admin = new ArrayList<Player>();
	public ArrayList<Player> youtuber = new ArrayList<Player>();
	private Main m;

	public Mode(Main m) {
		this.m = m;
	}

	public void setAdmin(Player p) {
		if (!admin.contains(p) || admin.isEmpty()) {
			admin.add(p);
		}
		p.setGameMode(GameMode.CREATIVE);
		m.vanish.makeVanished(p);
		m.vanish.updateVanished();
		Main.plugin.resetPlayersPreJogo(p);
		p.sendMessage(ChatColor.DARK_PURPLE + "Você está no modo ADMIN");
		p.sendMessage(ChatColor.DARK_PURPLE + "Invisivel para MOD e abaixo");
	}

	public void setYoutuber(Player p) {
		if (!youtuber.contains(p) || youtuber.isEmpty()) {
			youtuber.add(p);
		}
		if (!m.NaoEstaJogando(p))
			m.RemoverJogador(p);
		p.setGameMode(GameMode.ADVENTURE);
		p.setAllowFlight(true);
		p.setFlying(true);
		m.vanish.makeVanished(p);
		m.vanish.updateVanished();
		p.sendMessage(ChatColor.AQUA + "Você está no modo Espectador");
		p.sendMessage(ChatColor.DARK_PURPLE + "Invisivel para Players");
	}

	public void setPlayer(Player p) {
		if (admin.contains(p)) {
			p.sendMessage(ChatColor.DARK_PURPLE + "Você saiu do modo Admin");
		}
		if (youtuber.contains(p)) {
			p.sendMessage(ChatColor.AQUA + "Você saiu do modo Espectador");
		}

		admin.remove(p);
		youtuber.remove(p);
		p.setGameMode(GameMode.SURVIVAL);
		Main.plugin.addContador(p);
		m.vanish.makeVisible(p);
		m.vanish.updateVanished();
	}

	public boolean isAdmin(Player p) {
		return admin.contains(p);
	}

	public boolean isSpectating(Player p) {
		return youtuber.contains(p);
	}

}