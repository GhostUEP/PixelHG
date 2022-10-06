package me.ghost.Util;

import java.util.HashMap;
import java.util.UUID;

import me.ghost.Main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Vanish {
	private static HashMap<UUID, VLevel> vanished = new HashMap<>();

	public Vanish(Main main) {
	}

	public void makeVanished(Player p) {
		if (p.hasPermission("PixelHG.vs.Admin")) {
			makeVanished(p, VLevel.ADMIN);
		} else if (p.hasPermission("PixelHG.vs.Mod")) {
			makeVanished(p, VLevel.MOD);
		} else if (p.hasPermission("PixelHG.vs.Youtuber")) {
			makeVanished(p, VLevel.YOUTUBER);
		}
	}

	@SuppressWarnings("deprecation")
	public void makeVanished(Player p, VLevel level) {
		if (level.equals(VLevel.YOUTUBER)) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				player.showPlayer(p);
				if (player.getName().equals(p.getName()))
					continue;
				if (!(player.hasPermission("PixelHG.vs.Mod") || player.hasPermission("PixelHG.vs.Admin")))
					player.hidePlayer(p);
			}
		} else if (level.equals(VLevel.MOD)) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				player.showPlayer(p);
				if (player.getName().equals(p.getName()))
					continue;
				if (!(player.hasPermission("PixelHG.vs.Mod") || player.hasPermission("PixelHG.vs.Admin")))
					player.hidePlayer(p);
			}
		} else if (level.equals(VLevel.ADMIN)) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				player.showPlayer(p);
				if (player.getName().equals(p.getName()))
					continue;
				if (!(player.hasPermission("PixelHG.vs.Admin")))
					player.hidePlayer(p);
			}
		}
		vanished.put(p.getUniqueId(), level);
	}

	public boolean isVanished(Player p) {
		return vanished.containsKey(p.getUniqueId()) && !vanished.get(p.getUniqueId()).equals(VLevel.PLAYER);
	}

	public VLevel getPlayerLevel(Player p) {
		return vanished.get(p.getUniqueId());
	}

	@SuppressWarnings("deprecation")
	public void updateVanished() {
		for (Player p : Bukkit.getOnlinePlayers())
			if (isVanished(p)) {
				// makeVanished(p, vanished.get(p.getUniqueId()));
				makeVanished(p);
			} else {
				makeVisible(p);
			}
	}

	@SuppressWarnings("deprecation")
	public void makeVisible(Player p) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.showPlayer(p);
		}
		vanished.put(p.getUniqueId(), VLevel.PLAYER);
	}
}
