package me.ghost.Manager;

import java.lang.reflect.Field;
import java.util.Random;

import me.ghost.Main;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class Teste {
	static int i = 0;

	public static void AleatorioRodar(final Player p) {
		if (!Main.plugin.aleatorio.contains(p.getUniqueId())) {
			Main.plugin.aleatorio.add(p.getUniqueId());

		}
		if (!Main.plugin.aleatorio2.contains(p.getUniqueId())) {
			Main.plugin.aleatorio2.add(p.getUniqueId());

		}
		i = 0;
		p.getInventory().clear();
		new BukkitRunnable() {

			@Override
			public void run() {
				if (Main.plugin.aleatorio.contains(p.getUniqueId())) {
					Main.plugin.aleatorio.remove(p.getUniqueId());
				}

				ItemStack item = p.getInventory().getItem(4);
				Main.plugin.kit.forceKit(p, item.getItemMeta().getDisplayName());
				ItemStack vidro = new ItemStack(Material.THIN_GLASS);
				ItemMeta vidroim = vidro.getItemMeta();
				vidroim.setDisplayName(ChatColor.GRAY + "");
				vidro.setItemMeta(vidroim);
				p.getInventory().setItem(0, vidro);
				p.getInventory().setItem(1, vidro);
				p.getInventory().setItem(2, vidro);
				p.getInventory().setItem(3, vidro);
				p.getInventory().setItem(5, vidro);
				p.getInventory().setItem(6, vidro);
				p.getInventory().setItem(7, vidro);
				p.getInventory().setItem(8, vidro);
				Random r = new Random();
				int chance = r.nextInt(3);
				if (chance == 0) {
					lancarFogos(p.getLocation().add(0, 2, 0), Color.PURPLE, FireworkEffect.Type.STAR);
				} else if (chance == 1) {
					lancarFogos(p.getLocation().add(0, 2, 0), Color.RED, FireworkEffect.Type.CREEPER);
				} else if (chance == 2) {
					lancarFogos(p.getLocation().add(0, 2, 0), Color.AQUA, FireworkEffect.Type.BALL);
				}

				new BukkitRunnable() {

					@Override
					public void run() {
						if (Main.plugin.aleatorio2.contains(p.getUniqueId())) {
							Main.plugin.aleatorio2.remove(p.getUniqueId());
						}
						PlayerManager.Items(p);
					}
				}.runTaskLater(Main.plugin, 20 * 3);

			}
		}.runTaskLater(Main.plugin, 20 * 5);

		new BukkitRunnable() {

			public void run() {
				if (Main.plugin.aleatorio.contains(p.getUniqueId())) {
					p.closeInventory();
					p.getInventory().setHeldItemSlot(4);
					p.getInventory().setItem(i, getRandom(p));
					i++;
					if (i >= 9) {
						i = 0;
					}

				} else
					cancel();
			}
		}.runTaskTimer(Main.plugin, 1, 1);
		new BukkitRunnable() {

			public void run() {
				if (Main.plugin.aleatorio.contains(p.getUniqueId())) {
					p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1.0F, 1.5F);
				} else
					cancel();
			}
		}.runTaskTimer(Main.plugin, 1, 5);
	}

	public static ItemStack getRandom(Player p) {
		String kit = Main.plugin.kit.getViableKit(p);
		char[] stringArray = kit.toCharArray();
		stringArray[0] = Character.toUpperCase(stringArray[0]);
		kit = new String(stringArray);
		ItemStack item = new ItemStack(Main.plugin.kit.items.get(kit.toLowerCase()).icon);
		ItemMeta itemim = item.getItemMeta();
		itemim.setDisplayName(kit);
		itemim.setLore(Main.plugin.kit.items.get(kit.toLowerCase()).kitInfo);
		item.setItemMeta(itemim);
		return item;

	}

	public static void lancarFogos(Location l, Color c, FireworkEffect.Type t) {

		Firework fw = (Firework) l.getWorld().spawn(l, Firework.class);
		FireworkEffect effect = FireworkEffect.builder().trail(false).flicker(true).withColor(c).with(t).build();
		FireworkMeta fwm = fw.getFireworkMeta();
		fwm.clearEffects();
		fwm.addEffect(effect);
		try {
			Field f = fwm.getClass().getDeclaredField("power");
			f.setAccessible(true);
			f.set(fwm, Integer.valueOf(-1));
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		fw.setFireworkMeta(fwm);
	}
}
