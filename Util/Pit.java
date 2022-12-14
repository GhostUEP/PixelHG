package me.ghost.Util;

import java.util.ArrayList;

import me.ghost.Main;
import me.ghost.Constructor.BO3API;
import me.ghost.Manager.ServerManager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Pit {
	public int time = 300;
	public Block central;
	public static boolean spawned = false;
	public static ArrayList<Block> feastBlocks = new ArrayList<>();
	public ArrayList<Block> chests = new ArrayList<>();
	public ArrayList<ItemStack> feastItems;

	public Pit(Main m) {
		World w = m.getServer().getWorld("world");
		int x = 0;
		int z = 0;
		int y = w.getHighestBlockYAt(x, z);
		central = new Location(w, x, y, z).getBlock();
	}

	@SuppressWarnings("deprecation")
	public void spawnPit() {
		spawned = true;
		ServerManager.spawnBordaPit();
		final Location loc = central.getLocation();
		loc.setY(10);
		for (final BO3API bo3 : Main.plugin.pit) {
			final Block b = new Location(loc.getWorld(), loc.getX() + bo3.getX(), loc.getY() + bo3.getY(), loc.getZ() + bo3.getZ()).getBlock();
			b.setType(Material.STONE);
			b.setData(b.getData());
			int y = 1;
			while (y <= 120) {
				Location l = new Location(loc.getWorld(), loc.getX() + bo3.getX(), loc.getY() + bo3.getY() + y, loc.getZ() + bo3.getZ());
				l.getBlock().setType(Material.AIR);
				y++;
			}
			new BukkitRunnable() {

				@Override
				public void run() {
					for (Player p : Bukkit.getOnlinePlayers()) {
						p.teleport(new Location(p.getWorld(), 0, p.getWorld().getHighestBlockYAt(0, 0), 0));
					}

				}
			}.runTaskLater(Main.plugin, 5);
		}
	}
}
