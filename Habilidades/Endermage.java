package me.ghost.Habilidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import me.ghost.Main;
import me.ghost.Constructor.Kit;
import me.ghost.Enums.Estagio;
import me.ghost.Manager.KitInterface;
import me.ghost.Manager.PlayerManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class Endermage extends KitInterface {
	private ArrayList<Block> endermages;
	private ArrayList<UUID> inventarioFull;

	public Endermage(Main main) {
		super(main);
		endermages = new ArrayList<Block>();
		inventarioFull = new ArrayList<>();
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		final Player p = event.getPlayer();
		Action a = event.getAction();
		ItemStack item = event.getItem();
		final Block b = event.getClickedBlock();
		if (!a.toString().contains("BLOCK"))
			return;
		if (item == null)
			return;
		if (item.getType() != Material.NETHER_BRICK_ITEM)
			return;
		if (!hasAbility(p))
			return;
		if (Main.est == Estagio.PREJOGO)
			return;
		event.setCancelled(true);
		if (!b.getRelative(BlockFace.UP).isEmpty()) {
			p.sendMessage(ChatColor.RED + "Sem espaço suficiente");
			p.updateInventory();
			return;
		}
		Location loc = b.getLocation();
		Block b2 = loc.clone().add(0.0, 1.0, 0.0).getBlock();
		if (!b2.getRelative(BlockFace.UP).isEmpty()) {
			p.sendMessage(ChatColor.RED + "Sem espaço suficiente");
			p.updateInventory();
			return;
		}

		if (endermages.contains(b))
			return;
		endermages.add(b);
		item.setAmount(0);
		if (item.getAmount() == 0)
			event.getPlayer().setItemInHand(null);
		final Material material = b.getType();
		final byte dataValue = b.getData();
		final Location portal = b.getLocation().clone().add(0.5, 0.5, 0.5);
		portal.getBlock().setType(Material.ENDER_PORTAL_FRAME);
		for (int i = 0; i <= 5; i++) {
			final int no = i;
			new BukkitRunnable() {
				int puxou = 0;

				@Override
				public void run() {
					if (portal.getBlock().getType() != Material.ENDER_PORTAL_FRAME)
						return;
					if (no < 5) {
						for (final Player gamer : Bukkit.getServer().getOnlinePlayers()) {
							if (gamer == p)
								continue;
							if (Main.plugin.admin.isAdmin(gamer) || Main.plugin.admin.isSpectating(gamer))
								continue;
							if (!isEnderable(portal, gamer.getLocation()))
								continue;
							if (hasAbility(gamer, "endermage"))
								continue;
							puxou += 1;
							gamer.teleport(portal.clone().add(0, 0.5, 0));
							p.teleport(portal.clone().add(0, 0.5, 0));
							p.sendMessage(ChatColor.RED + "Você foi puxado pelo(a) " + p.getName() + "! Você está invencivel por 5 segundos");
							p.sendMessage(ChatColor.RED +  "Você puxou algúem! Você está invencivel por 5 segundos");
							Main.plugin.invencivel.add(gamer.getUniqueId());
							Main.plugin.invencivel.add(p.getUniqueId());
							if (puxou == 1) {
								if (p.getInventory().firstEmpty() == -1) {
									p.sendMessage(ChatColor.RED + "Sem espaço no inventário");
									inventarioFull.add(p.getUniqueId());
								} else {
									ItemStack blinkitem = new ItemStack(Material.NETHER_BRICK_ITEM);
									ItemMeta blink = blinkitem.getItemMeta();
									blink.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Portal de Endermage");
									blinkitem.setItemMeta(blink);
									p.getInventory().addItem(blinkitem);
									PlayerManager.itemportal.add(blinkitem);
								}
							}
							endermages.remove(b);
							portal.getBlock().setTypeIdAndData(material.getId(), dataValue, true);
							Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
								public void run() {
									Main.plugin.invencivel.remove(gamer.getUniqueId());
									Main.plugin.invencivel.remove(p.getUniqueId());
								}
							}, 100);
						}
					} else {
						portal.getBlock().setTypeIdAndData(material.getId(), dataValue, true);
						if (p.getInventory().firstEmpty() == -1) {
							p.sendMessage(ChatColor.RED + "Sem espaço no inventário");
							inventarioFull.add(p.getUniqueId());
						} else {
							ItemStack blinkitem = new ItemStack(Material.NETHER_BRICK_ITEM);
							ItemMeta blink = blinkitem.getItemMeta();
							blink.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Portal de Endermage");
							blinkitem.setItemMeta(blink);
							p.getInventory().addItem(blinkitem);
							PlayerManager.itemportal.add(blinkitem);
						}
						endermages.remove(b);
					}
				}
			}.runTaskLater(Main.plugin, i * 20);
		}
	}

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (!(event.getWhoClicked() instanceof Player))
			return;
		Player p = (Player) event.getWhoClicked();
		if (!hasAbility(p))
			return;
		if (inventarioFull.contains(p)) {
			if (p.getInventory().firstEmpty() == -1) {
			p.sendMessage(ChatColor.RED + "Sem espaço no inventário");
				inventarioFull.add(p.getUniqueId());
			} else {
				ItemStack blinkitem = new ItemStack(Material.NETHER_BRICK_ITEM);
				ItemMeta blink = blinkitem.getItemMeta();
				blink.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Portal de Endermage");
				blinkitem.setItemMeta(blink);
				p.getInventory().addItem(blinkitem);
				PlayerManager.itemportal.add(blinkitem);
			}
		}
	}

	@EventHandler
	public void onHabilidadeDeHitReceberDano(EntityDamageEvent event) {

		Entity vitima = event.getEntity();
		if (vitima.isDead()) {
			return;
		}
		if (!(vitima instanceof Player))
			return;
		Player p = (Player) vitima;
		if (Main.plugin.invencivel.contains(p.getUniqueId())) {
			event.setCancelled(true);
			return;
		}
	}

	@EventHandler
	public void onHabilidadeDeHitDarDano(EntityDamageByEntityEvent event) {

		Entity damager = event.getDamager();
		if (damager.isDead()) {
			return;
		}
		if (!(damager instanceof Player))
			return;
		Player p = (Player) damager;
		if (Main.plugin.invencivel.contains(p.getUniqueId())) {
			event.setCancelled(true);
			return;
		}
	}

	private boolean isEnderable(Location portal, Location player) {
		return Math.abs(portal.getX() - player.getX()) < 3 && Math.abs(portal.getZ() - player.getZ()) < 3 && Math.abs(portal.getY() - player.getY()) > 2;
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		ItemStack item = e.getItemDrop().getItemStack();
		Player p = e.getPlayer();
		if ((item.getType() == Material.NETHER_BRICK_ITEM) && (item.hasItemMeta()) && (item.getItemMeta().getDisplayName().contains("Endermage")) && hasAbility(p)) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.RED + "Você não pode dropar esse item");
			p.updateInventory();
		}
	}

	@Override
	public Kit getKit() {
		ArrayList<ItemStack> kititems = new ArrayList<>();
		ItemStack blinkitem = new ItemStack(Material.NETHER_BRICK_ITEM);
		ItemMeta blink = blinkitem.getItemMeta();
		blink.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Portal de Endermage");
		blinkitem.setItemMeta(blink);
		kititems.add(blinkitem);
		return new Kit("endermage", Arrays.asList(new String[] { "Use seu portal para puxar players para você" }), kititems, new ItemStack(Material.NETHER_BRICK_ITEM), 30000);
	}
}