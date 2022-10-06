package me.ghost.Util;

import java.util.ArrayList;
import java.util.Random;

import me.ghost.Main;
import me.ghost.Constructor.BO3API;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Minifeast {
	public int time = 300;
	private Block central;
	public ArrayList<BO3API> minifeast1;
	public ArrayList<Block> chests = new ArrayList<>();
	public ArrayList<ItemStack> miniItems;
	public ArrayList<String> viableKits;

	public Minifeast(Main m) {
		this.minifeast1 = m.minifeast1;
		viableKits = new ArrayList<>();
		Random r = new Random();
		World w = m.getServer().getWorld("world");
		int size = (int) 1000 / 2;
		int x = (size / 2) + r.nextInt(size / 2);
		int z = (size / 2) + r.nextInt(size / 2);
		if (r.nextBoolean())
			x = -x;
		if (r.nextBoolean())
			z = -z;
		int y = w.getHighestBlockYAt(x, z);
		central = new Location(w, x, y, z).getBlock();
		System.out.print("Minifeast: " + x + " " + y + " " + z);
		miniFeastItems();
		generateMinifeast();
	}

	@SuppressWarnings("deprecation")
	public void generateMinifeast() {
		Location loc = central.getLocation();
		for (final BO3API bo3 : minifeast1) {
			loc.getWorld().getChunkAt(loc).load();
			final Block b = new Location(loc.getWorld(), loc.getX() + bo3.getX(), loc.getY() + bo3.getY(), loc.getZ() + bo3.getZ()).getBlock();
			Chunk chunk = loc.getBlock().getChunk();
			if (!chunk.isLoaded())
				chunk.load();
			b.setType(bo3.getType());
			b.setData(bo3.getData());
			if (bo3.getType() == Material.CHEST)
				chests.add(b);

		}
		spawnItems();
		int realX = central.getX();
		int realZ = central.getZ();
		int x = realX;
		int z = realZ;
		while (x % 50 != 0) {
			x++;
		}
		while (z % 50 != 0) {
			z++;
		}
		Bukkit.getServer().broadcastMessage(ChatColor.RED + "Um Minifeast spawnou entre: (X: " + x + " e " + (x - 100) + ") " + "e" + " (Z: " + z + " e " + (z - 100) + ")");

	}

	public void spawnItems() {
		if (miniItems == null) {
			return;
		}
		for (Block b : chests) {
			if (b.getState() instanceof Chest) {
				Chest chest = (Chest) b.getState();
				Inventory inv = chest.getBlockInventory();
				int items = 15;
				Random r = new Random();
				int size = miniItems.size() - 1;
				while (items > 0) {
					int i = r.nextInt(size);
					if (miniItems.size() - 1 < i) {
						items--;
						continue;
					}
					ItemStack item = miniItems.get(i);
					if (item != null && item.getType() != Material.AIR && item.getAmount() > 0) {
						inv.addItem(item);
						chest.update();
						miniItems.remove(i);
					}
					items--;
				}
			}
		}
	}

	public void miniFeastItems() {
		ArrayList<ItemStack> items = new ArrayList<>();
		ItemStack wool = new ItemStack(Material.WOOL);
		ItemMeta woolim = wool.getItemMeta();
		woolim.setDisplayName(ChatColor.RED + "Pegar Kit");
		wool.setItemMeta(woolim);
		for (int i = 0; i <= 3; i++) {
			items.add(new ItemStack(Material.DIAMOND));
		}

		for (int i = 0; i <= 7; i++) {
			items.add(new ItemStack(Material.IRON_INGOT));
		}
		for (int i = 0; i <= 6; i++) {
			items.add(new ItemStack(Material.INK_SACK, 1, (short) 3));
		}
		for (int i = 0; i <= 5; i++) {
			items.add(new ItemStack(Material.MUSHROOM_SOUP));
		}
		for (int i = 0; i <= 10; i++) {
			items.add(new ItemStack(Material.TNT));
		}
		for (int i = 0; i <= 7; i++) {
			items.add(new ItemStack(Material.EXP_BOTTLE));
		}
		for (int i = 0; i <= 1; i++) {
			items.add(new ItemStack(Material.POTION, 1, (short) 16386));
		}
		for (int i = 0; i <= 1; i++) {
			items.add(new ItemStack(Material.POTION, 1, (short) 16388));
		}
		for (int i = 0; i <= 3; i++) {
			items.add(wool);
		}
		for (int i = 0; i <= 1; i++) {
			items.add(new ItemStack(Material.POTION, 1, (short) 16394));
		}
		for (int i = 0; i <= 1; i++) {
			items.add(new ItemStack(Material.POTION, 1, (short) 16396));
		}
		miniItems = items;
	}
}