package me.ghost.Manager;

import java.util.ArrayList;
import java.util.Random;

import me.ghost.Main;
import me.ghost.Constructor.BO3API;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

public class ServerManager {
	public static void spawnBordaPit() {
		for (int x = -50; x <= 50; x++) {
			if (x == -50 || x == 50) {
				for (int z = -50; z <= 50; z++) {
					for (int y = 0; y <= 250; y++) {
						Location loc = new Location(Bukkit.getWorld("world"), x, y, z);
						if (!loc.getChunk().isLoaded())
							loc.getChunk().load();
						if (new Random().nextBoolean()) {
							loc.getBlock().setType(Material.BEDROCK);
						} else {
							loc.getBlock().setType(Material.BEDROCK);

						}

					}
				}
			}
		}
		for (int z = -50; z <= 50; z++) {
			if (z == -50 || z == 50) {
				for (int x = -50; x <= 50; x++) {
					for (int y = 0; y <= 250; y++) {
						Location loc = new Location(Bukkit.getWorld("world"), x, y, z);
						if (!loc.getChunk().isLoaded())
							loc.getChunk().load();
						if (new Random().nextBoolean()) {
							loc.getBlock().setType(Material.BEDROCK);
						} else {
							loc.getBlock().setType(Material.BEDROCK);
						}
					}
				}
			}
		}
	}

	public static void gerarBorda() {
		for (int x = -500; x <= 500; x++) {
			if (x == -500 || x == 500) {
				for (int z = -500; z <= 500; z++) {
					for (int y = 0; y <= 250; y++) {
						Location loc = new Location(Bukkit.getWorld("world"), x, y, z);
						if (!loc.getChunk().isLoaded())
							loc.getChunk().load();
						Block b = loc.getBlock();
						Main.plugin.forcefieldblock.add(b);
						if (new Random().nextBoolean()) {
							loc.getBlock().setType(Material.WOOL);
						} else {
							loc.getBlock().setType(Material.LAPIS_BLOCK);

						}

					}
				}
			}
		}
		for (int z = -500; z <= 500; z++) {
			if (z == -500 || z == 500) {
				for (int x = -500; x <= 500; x++) {
					for (int y = 0; y <= 250; y++) {
						Location loc = new Location(Bukkit.getWorld("world"), x, y, z);
						if (!loc.getChunk().isLoaded())
							loc.getChunk().load();
						Block b = loc.getBlock();
						Main.plugin.forcefieldblock.add(b);
						if (new Random().nextBoolean()) {
							loc.getBlock().setType(Material.WOOL);
						} else {
							loc.getBlock().setType(Material.LAPIS_BLOCK);
						}
					}
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	public static void spawnDelayedObject(ArrayList<BO3API> bo3, final Location loc) {
		final ArrayList<BO3API> blocos = bo3;

		new BukkitRunnable() {

			@Override
			public void run() {
				if (blocos.size() == 0) {
					cancel();
				}
				BO3API b = blocos.get(new Random().nextInt(blocos.size()));
				if (b == null) {
					cancel();
				}
				final Block bloco = new Location(loc.getWorld(), loc.getX() + b.getX(), loc.getY() + b.getY(), loc.getZ() + b.getZ()).getBlock();
				bloco.setType(b.getType());
				bloco.setData(b.getData());
				blocos.remove(b);
			}
		}.runTaskTimer(Main.plugin, 1, 1);
	}

}
