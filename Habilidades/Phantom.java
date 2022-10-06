package me.ghost.Habilidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import me.ghost.Main;
import me.ghost.Constructor.Kit;
import me.ghost.Enums.Estagio;
import me.ghost.Eventos.TimeSecondEvent;
import me.ghost.Manager.CooldownManager;
import me.ghost.Manager.KitInterface;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Phantom extends KitInterface {
	public static HashMap<Player, Integer> voo = new HashMap<>();
	public static HashMap<Player, ItemStack[]> armadura = new HashMap<>();

	public Phantom(Main main) {
		super(main);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPhantom(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack i = p.getItemInHand();
		if (!hasAbility(p))
			return;
		if (Main.est == Estagio.PREJOGO)
			return;
		if (e.getAction().name().contains("RIGHT")) {
			if (i.getType() == Material.FEATHER && i.getItemMeta().getDisplayName().contains("Phantom")) {
				if (CooldownManager.isInCooldown(p.getUniqueId(), "phantom")) {
					int timeleft = CooldownManager.getTimeLeft(p.getUniqueId(), "phantom");
					p.sendMessage(ChatColor.RED + "Phantom em cooldown, faltando: " + timeleft + " segundos");
					return;
				}
				if (!CooldownManager.isInCooldown(p.getUniqueId(), "phantom")) {
					CooldownManager c = new CooldownManager(p.getUniqueId(), "phantom", 60);
					c.start();
				}
				voo.put(p, 6);
				p.getWorld().playSound(p.getLocation(), Sound.WITHER_DEATH, 2, 1);
				p.setAllowFlight(true);
				p.setFlying(true);
				for (Player player : Bukkit.getOnlinePlayers()) {
					if ((player.getLocation().distance(p.getLocation()) <= 20) && (player != p)) {
						player.sendMessage(ChatColor.BOLD + "Um Phantom esta voando por perto\nNão é hack, apenas a habilidade do kit.");
					}
				}
				PlayerInventory inv = p.getInventory();
				armadura.put(p, inv.getArmorContents());

				ItemStack capacete = colorIn(Material.LEATHER_HELMET, org.bukkit.Color.WHITE);
				ItemMeta capaceteim = capacete.getItemMeta();
				capaceteim.setDisplayName(ChatColor.GOLD + "Armadura");
				capacete.setItemMeta(capaceteim);

				ItemStack capacete2 = colorIn(Material.LEATHER_CHESTPLATE, org.bukkit.Color.WHITE);
				ItemMeta capaceteim2 = capacete2.getItemMeta();
				capaceteim2.setDisplayName(ChatColor.GOLD + "Armadura");
				capacete2.setItemMeta(capaceteim2);

				ItemStack capacete3 = colorIn(Material.LEATHER_LEGGINGS, org.bukkit.Color.WHITE);
				ItemMeta capaceteim3 = capacete3.getItemMeta();
				capaceteim3.setDisplayName(ChatColor.GOLD + "Armadura");
				capacete3.setItemMeta(capaceteim3);

				ItemStack capacete4 = colorIn(Material.LEATHER_BOOTS, org.bukkit.Color.WHITE);
				ItemMeta capaceteim4 = capacete4.getItemMeta();
				capaceteim4.setDisplayName(ChatColor.GOLD + "Armadura");
				capacete4.setItemMeta(capaceteim4);

				inv.setHelmet(capacete);
				inv.setChestplate(capacete2);
				inv.setLeggings(capacete3);
				inv.setBoots(capacete4);
				p.updateInventory();

			}
		}

	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		ItemStack item = e.getItemDrop().getItemStack();
		Player p = e.getPlayer();
		if ((item.getType() == Material.FEATHER) && (item.hasItemMeta()) && (item.getItemMeta().getDisplayName().contains("Phantom")) && hasAbility(p)) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.RED + "Você não pode dropar esse item");
			p.updateInventory();
		}

	}

	@EventHandler
	public void onLick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		ItemStack i = e.getCurrentItem();
		if (hasAbility(p)) {
			if (e.getInventory() != null) {
				if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
					if ((i.hasItemMeta()) && (i.getItemMeta().getDisplayName().contains("Armadura"))) {
						e.setCancelled(true);
						p.closeInventory();
					}
				}
			}
		}
	}

	@EventHandler
	public void onTempo(TimeSecondEvent e) {
		Iterator<Player> itel = voo.keySet().iterator();
		while (itel.hasNext()) {
			Player p = itel.next();
			voo.put(p, voo.get(p) - 1);
			if (voo.get(p) <= 0) {
				itel.remove();
				p.setFallDistance(0);
				p.setAllowFlight(false);
				p.getInventory().setArmorContents(armadura.remove(p));
				p.getWorld().playSound(p.getLocation(), Sound.WITHER_SPAWN, 2, 1);
				p.sendMessage(ChatColor.RED + "Seu tempo de voo esgotou!");
				voo.remove(p);
			} else
				p.sendMessage(ChatColor.RED + "Tempo de voo restante: " + voo.get(p));
		}
	}

	public ItemStack colorIn(Material mat, org.bukkit.Color c) {
		ItemStack armor = new ItemStack(mat);
		LeatherArmorMeta metaa = (LeatherArmorMeta) armor.getItemMeta();
		metaa.setColor(c);
		armor.setItemMeta(metaa);
		return armor;
	}

	@Override
	public Kit getKit() {
		ArrayList<ItemStack> kititems = new ArrayList<>();
		ItemStack blinkitem = new ItemStack(Material.FEATHER);
		ItemMeta blink = blinkitem.getItemMeta();
		blink.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Phantom");
		blinkitem.setItemMeta(blink);
		kititems.add(blinkitem);
		return new Kit("phantom", Arrays.asList(new String[] { "Use sua pena para voar por um determinado tempo" }), kititems, new ItemStack(Material.FEATHER), 10000);
	}
}
