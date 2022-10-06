package me.ghost.Manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import me.ghost.Main;
import me.ghost.Constructor.BO3API;
import me.ghost.Enums.Estagio;
import me.ghost.Listener.DeathListener;
import me.ghost.Listener.PlayerListener;
import me.ghost.Util.WinItems;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerManager {
	public static UUID winner = null;
	public static ArrayList<Block> chests = new ArrayList<>();
	public static ArrayList<ItemStack> winItems;
	public static ArrayList<ItemStack> itemportal = new ArrayList<>();

	public static void dropItems(Player p, Location l) {
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		for (ItemStack item : p.getPlayer().getInventory().getContents())
			if (item != null && item.getType() != Material.AIR)
				items.add(item.clone());
		for (ItemStack item : p.getPlayer().getInventory().getArmorContents())
			if (item != null && item.getType() != Material.AIR)
				items.add(item.clone());
		if (p.getPlayer().getItemOnCursor() != null && p.getPlayer().getItemOnCursor().getType() != Material.AIR)
			items.add(p.getPlayer().getItemOnCursor().clone());
		dropItems(p, items, l);
	}

	public static void updateKits(Player player) {
		UUID p = player.getUniqueId();
		ArrayList<String> kits = new ArrayList<>();
		try {
			PreparedStatement sql = Main.plugin.skits.prepareStatement("SELECT * FROM `Kits` WHERE Player='" + p.toString().replace("-", "") + "';");
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				kits.add(result.getString("Kits"));
			}
			result.close();
			sql.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Main.plugin.seuskits.put(p, kits);
	}

	@SuppressWarnings("deprecation")
	public static void dropItems(Player p, List<ItemStack> items, Location l) {
		if (Main.est != Estagio.PREJOGO) {
			World world = l.getWorld();
			for (ItemStack item : items) {
				if (item == null || item.getType() == Material.AIR)
					continue;
				if (item.getType() == Material.POTION) {
					if (item.getDurability() == 8201) {
						continue;
					}
				}
				if (Main.plugin.kit.hasAbility(p, "kangaroo") && (item.getType() == Material.FIREWORK) && (item.hasItemMeta()) && (item.getItemMeta().getDisplayName().contains("Kangaroo")))
					continue;
				if (Main.plugin.kit.hasAbility(p, "endermage") && (item.getType() == Material.NETHER_BRICK_ITEM) && (item.hasItemMeta()) && (item.getItemMeta().getDisplayName().contains("Portal de Endermage")))
					continue;
				if (Main.plugin.kit.hasAbility(p, "specialist") && (item.getType() == Material.BOOK) && (item.hasItemMeta()) && (item.getItemMeta().getDisplayName().contains("Encante")))
					continue;
				if (Main.plugin.kit.hasAbility(p, "barbarian") && (item.hasItemMeta()) && (item.getItemMeta().getDisplayName().contains("Barbarian")))
					continue;
				if (Main.plugin.kit.hasAbility(p, "phantom") && (item.hasItemMeta()) && (item.getItemMeta().getDisplayName().contains("Phantom")))
					continue;
				if (Main.plugin.kit.hasAbility(p, "grappler") && (item.hasItemMeta()) && (item.getItemMeta().getDisplayName().contains("Grappler")))
					continue;
				if (Main.plugin.kit.hasAbility(p, "digger") && (item.hasItemMeta()) && (item.getItemMeta().getDisplayName().contains("Digger")))
					continue;
				if (Main.plugin.kit.hasAbility(p, "thermo") && (item.getType() == Material.DAYLIGHT_DETECTOR) && (item.hasItemMeta()) && (item.getItemMeta().getDisplayName().contains("Transformador")))
					continue;
				if (Main.plugin.kit.hasAbility(p, "gladiator") && (item.getType() == Material.IRON_FENCE) && (item.hasItemMeta()) && (item.getItemMeta().getDisplayName().contains("Tire 1v1")))
					continue;
				if (item.getType() == Material.SKULL_ITEM)
					continue;
				if (item.hasItemMeta())
					world.dropItemNaturally(l, item.clone()).getItemStack().setItemMeta(item.getItemMeta());
				else
					world.dropItemNaturally(l, item);
			}
			p.getPlayer().getInventory().setArmorContents(new ItemStack[4]);
			p.getPlayer().getInventory().clear();
			p.getPlayer().setItemOnCursor(new ItemStack(0));
			for (PotionEffect pot : p.getActivePotionEffects()) {
				p.removePotionEffect(pot.getType());
				break;
			}
		}
	}

	public static void Items(Player p) {
		p.getInventory().clear();
		ItemStack spectime = new ItemStack(Material.CHEST);
		ItemMeta spect = spectime.getItemMeta();
		spect.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Menu de Kits");
		spectime.setItemMeta(spect);

		p.getInventory().setItem(4, spectime);
		p.getInventory().setHeldItemSlot(4);

	}

	public static Location getRespawnLocation() {
		Random r = new Random();
		int x = 200 + r.nextInt(200);
		int z = 200 + r.nextInt(200);
		if (r.nextBoolean())
			x = -x;
		if (r.nextBoolean())
			z = -z;

		World world = Bukkit.getServer().getWorlds().get(0);
		int y = world.getHighestBlockYAt(x, z) + 3;
		Location loc = new Location(world, x, y, z);
		if (!loc.getChunk().isLoaded()) {
			loc.getChunk().load();
		}
		return loc;
	}

	public static void checkWinner() {
		if (winner != null)
			return;
		Iterator<UUID> iterator = Main.plugin.jogadores.iterator();
		while (iterator.hasNext()) {
			UUID uuid = iterator.next();
			if (Bukkit.getServer().getPlayer(uuid) == null && !PlayerListener.relog.contains(uuid))
				iterator.remove();
		}
		if (Main.plugin.jogadores.size() > 1)
			return;
		if (Main.plugin.jogadores.size() < 1) {
			Bukkit.getServer().shutdown();
			return;
		}
		Bukkit.getServer().getScheduler().cancelAllTasks();
		Main.est = Estagio.VITORIA;
		final Player p = Bukkit.getServer().getPlayer(Main.plugin.jogadores.get(0));
		if (p == null) {
			Bukkit.getServer().shutdown();
			return;
		}
		winner = p.getUniqueId();
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
			@Override
			public void run() {
				giveWinnerItems(p);
			}
		}, 50);
	}

	@SuppressWarnings({ "deprecation" })
	public static void giveWinnerItems(final Player p) {
		Location l = p.getLocation();
		Random r = new Random();
		spawnRandomFirework(l.add(-10 + r.nextInt(20), 0, -10 + r.nextInt(20)));
		spawnRandomFirework(l.add(-10 + r.nextInt(20), 0, -10 + r.nextInt(20)));
		spawnRandomFirework(l.add(-10 + r.nextInt(20), 0, -10 + r.nextInt(20)));
		spawnRandomFirework(l.add(-10 + r.nextInt(20), 0, -10 + r.nextInt(20)));
		spawnRandomFirework(l.add(-10 + r.nextInt(20), 0, -10 + r.nextInt(20)));
		spawnRandomFirework(l.add(-10 + r.nextInt(20), 0, -10 + r.nextInt(20)));
		new BukkitRunnable() {
			@Override
			public void run() {
				spawnRandomFirework(p.getLocation().add(-10, 0, -10));
				spawnRandomFirework(p.getLocation().add(-10, 0, 10));
				spawnRandomFirework(p.getLocation().add(10, 0, -10));
				spawnRandomFirework(p.getLocation().add(10, 0, 10));
				spawnRandomFirework(p.getLocation().add(-5, 0, -5));
				spawnRandomFirework(p.getLocation().add(-5, 0, 5));
				spawnRandomFirework(p.getLocation().add(5, 0, -5));
				spawnRandomFirework(p.getLocation().add(5, 0, 5));
				spawnRandomFirework(p.getLocation().add(-4, 0, -3));
				spawnRandomFirework(p.getLocation().add(-3, 0, 4));
				spawnRandomFirework(p.getLocation().add(2, 0, -6));
				spawnRandomFirework(p.getLocation().add(1, 0, 9));
				p.sendMessage(ChatColor.AQUA + "Você ganhou a partida!!!");
			}
		}.runTaskTimer(Main.plugin, 10, 30L);
		PixelsManager.darpixels(p, 100);
		PixelsManager.darWin(p, 1);
		int kills = DeathListener.getKs(p);
		if (kills >= 20) {
			// ComprarManager.darCoins(p, 140);
			p.sendMessage(ChatColor.AQUA + "Parabéns, ganhou o dobro de Pixels por ter 20 kills ou mais.");
			p.sendMessage(ChatColor.AQUA + "Kills: " + kills + ".");
		} else {
			// ComprarManager.darCoins(p, 70);
			p.sendMessage(ChatColor.AQUA + "Kills: " + kills + ".");
		}
		new WinItems();
		for (BO3API bo3 : Main.plugin.win) {
			l.setY(105);
			Block b = new Location(l.getWorld(), l.getX() + bo3.getX(), l.getY() + bo3.getY(), l.getZ() + bo3.getZ()).getBlock();
			b.setType(bo3.getType());
			b.setData(bo3.getData());
			if (bo3.getType() == Material.CHEST)
				chests.add(b);
		}
		spawnItems();
		p.teleport(l.add(0, 11, 0));
		p.getWorld().setTime(0);
		p.getInventory().clear();
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
			@Override
			public void run() {
				p.kickPlayer(ChatColor.AQUA + "Você venceu no PixelHG!\n" + ChatColor.AQUA + "Reiniciando...");
				for (Player player : Bukkit.getServer().getOnlinePlayers()) {
					player.kickPlayer(ChatColor.AQUA + p.getName() + " Venceu a partida!\n" + ChatColor.AQUA + "Reiniciando...");
				}
				Bukkit.getServer().shutdown();
			}
		}, 20 * 30);
	}

	HashMap<UUID, Integer> kills = new HashMap<UUID, Integer>();

	@SuppressWarnings("deprecation")
	public static void check() {
		int currKills = 0;
		UUID maxKillsPlayer = null;
		for (Player p : Bukkit.getOnlinePlayers()) {
			int killsAmt = DeathListener.getKs(p);
			if (killsAmt > currKills) {
				maxKillsPlayer = p.getUniqueId();
				currKills = killsAmt;
			}
		}

		if (maxKillsPlayer != null) {
			Player playerWithMostKills = Bukkit.getPlayer(maxKillsPlayer);
			for (Player wner : Bukkit.getOnlinePlayers()) {
				if (wner != playerWithMostKills) {
					if (!Main.plugin.NaoEstaJogando(wner))
						if (wner.hasPermission("PixelHG.pex.Mod")) {
							wner.getInventory().clear();
							Main.plugin.admin.setAdmin(wner);
						} else {
							wner.kickPlayer(ChatColor.AQUA + "Você perdeu.");
							wner.getInventory().clear();
						}
					Main.plugin.RemoverJogador(wner);
				}
			}
		}
		checkWinner();
	}

	public static void spawnItems() {
		if (winItems == null) {
			return;
		}
		for (Block b : chests) {
			if (b.getState() instanceof Chest) {
				Chest chest = (Chest) b.getState();
				Inventory inv = chest.getBlockInventory();
				int items = 15;
				Random r = new Random();
				int size = winItems.size() - 1;
				if (size <= 0)
					return;
				while (items > 0) {
					int i = r.nextInt(size);
					if (winItems.size() - 1 < i) {
						items--;
						continue;
					}
					ItemStack item = winItems.get(i);
					if (item != null && item.getType() != Material.AIR && item.getAmount() > 0) {
						inv.addItem(item);
						chest.update();
						winItems.remove(i);
					}
					items--;
				}
			}
		}
	}

	public static void spawnRandomFirework(Location loc) {
		Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
		FireworkMeta fwm = fw.getFireworkMeta();
		Random r = new Random();
		int rt = r.nextInt(4) + 1;
		Type type = Type.BALL;
		if (rt == 1)
			type = Type.STAR;
		if (rt == 2)
			type = Type.BALL_LARGE;
		if (rt == 3)
			type = Type.BALL_LARGE;
		if (rt == 4)
			type = Type.STAR;

		Color c1 = Color.AQUA;
		Color c2 = Color.PURPLE;
		Color c3 = Color.ORANGE;
		FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withColor(c2).withFade(c3).with(type).trail(r.nextBoolean()).build();
		fwm.addEffect(effect);
		int rp = r.nextInt(2) + 1;
		fwm.setPower(rp);
		fw.setFireworkMeta(fwm);
	}
}
