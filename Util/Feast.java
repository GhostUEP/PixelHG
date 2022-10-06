package me.ghost.Util;

import java.util.ArrayList;
import java.util.Random;

import me.ghost.Main;
import me.ghost.Constructor.BO3API;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Feast {
	public int time = 300;
	public Block central;
	public boolean spawned = false;
	private Main m;
	public static ArrayList<Block> feastBlocks = new ArrayList<>();
	public ArrayList<Block> chests = new ArrayList<>();
	public ArrayList<ItemStack> feastItems;

	public Feast(Main m) {
		Random r = new Random();
		this.m = m;
		World w = m.getServer().getWorld("world");
		int x = -100 + r.nextInt(200);
		int z = -100 + r.nextInt(200);
		int y = w.getHighestBlockYAt(x, z);
		central = new Location(w, x, y, z).getBlock();
	}

	@SuppressWarnings("deprecation")
	public void generateFeast() {
		spawned = true;
		Location loc = central.getLocation();
		for (final BO3API bo3 : Main.plugin.feast) {
			loc.getWorld().getChunkAt(loc).load();
			final Block b = new Location(loc.getWorld(), loc.getX() + bo3.getX(), loc.getY() + bo3.getY(), loc.getZ() + bo3.getZ()).getBlock();
			int y = 0;
			while (y <= 11) {
				Location l = new Location(loc.getWorld(), loc.getX() + bo3.getX(), loc.getY() + bo3.getY() + y, loc.getZ() + bo3.getZ());
				l.getWorld().getChunkAt(l).load();
				l.getBlock().setType(Material.AIR);
				l.getBlock().setTypeId(0);
				y++;
				feastBlocks.add(l.getBlock());
			}
			while (y <= 11) {
				Location l = new Location(loc.getWorld(), loc.getX() + bo3.getX(), loc.getY() + bo3.getY() + y, loc.getZ() + bo3.getZ());
				l.getWorld().getChunkAt(l).load();
				l.getBlock().setType(Material.AIR);
				l.getBlock().setTypeId(0);
				y++;
				feastBlocks.add(l.getBlock());
			}
			b.setType(bo3.getType());
			b.setData(bo3.getData());
			while (y <= 11) {
				Location l = new Location(loc.getWorld(), loc.getX() + bo3.getX(), loc.getY() + bo3.getY() + y, loc.getZ() + bo3.getZ());
				l.getWorld().getChunkAt(l).load();
				if (l.getBlock().getType() == Material.GRASS)
					continue;
				if (l.getBlock().getType() == Material.CHEST)
					continue;
				l.getBlock().setType(Material.AIR);
				y++;
				feastBlocks.add(l.getBlock());
			}

		}
	}

	public void gerarFeast() {
		final Location loc = central.getLocation();
		loc.getWorld().getChunkAt(loc).load();

		new BukkitRunnable() {

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				for (final BO3API bo3 : Main.plugin.feast) {
					final Block b = new Location(loc.getWorld(), loc.getX() + bo3.getX(), loc.getY() + bo3.getY(), loc.getZ() + bo3.getZ()).getBlock();
					int y = 1;
					while (y <= 11) {
						Location l = new Location(loc.getWorld(), loc.getX() + bo3.getX(), loc.getY() + bo3.getY() + y, loc.getZ() + bo3.getZ());
						l.getBlock().setType(Material.AIR);
						y++;
						feastBlocks.add(l.getBlock());
						b.setType(Material.AIR);
						new BukkitRunnable() {

							@Override
							public void run() {
								b.setType(bo3.getType());
								b.setData(bo3.getData());
								feastBlocks.add(b);

							}
						}.runTaskLater(Main.plugin, 1);

					}
				}

			}
		}.runTaskLater(Main.plugin, 20L);
	}

	@SuppressWarnings("deprecation")
	public void generateChests() {
		new FeastItems(this);
		final Location loc = central.getLocation();
		for (BO3API bo3 : Main.plugin.bausfeast) {
			Block b = new Location(loc.getWorld(), loc.getX() + bo3.getX(), loc.getY() + 1 + bo3.getY(), loc.getZ() + bo3.getZ()).getBlock();
			b.setType(bo3.getType());
			b.setData(bo3.getData());
			if (bo3.getType() == Material.CHEST)
				chests.add(b);
		}
		spawnItems();
		m.getServer().broadcastMessage(ChatColor.RED + "O Feast spawnou em " + "(" + central.getX() + ", " + central.getY() + ", " + central.getZ() + ").");
	}

	public void spawnItems() {
		if (feastItems == null) {
			return;
		}
		for (Block b : chests) {
			if (b.getState() instanceof Chest) {
				Chest chest = (Chest) b.getState();
				Inventory inv = chest.getBlockInventory();
				int items = 15;
				Random r = new Random();
				int size = feastItems.size() - 1;
				if (size <= 0)
					return;
				while (items > 0) {
					int i = r.nextInt(size);
					if (feastItems.size() - 1 < i) {
						items--;
						continue;
					}
					ItemStack item = feastItems.get(i);
					if (item != null && item.getType() != Material.AIR && item.getAmount() > 0) {
						inv.addItem(item);
						chest.update();
						feastItems.remove(i);
					}
					items--;
				}
			}
		}
	}

	public static boolean isFeastBlock(Block b) {
		return feastBlocks.contains(b) && (Main.TempoPartida < 1200);
	}
}
