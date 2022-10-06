package me.ghost.Habilidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import me.ghost.Main;
import me.ghost.Constructor.Kit;
import me.ghost.Enums.Estagio;
import me.ghost.Manager.CooldownManager;
import me.ghost.Manager.KitInterface;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class Kangaroo extends KitInterface {

	ArrayList<String> jumpa = new ArrayList<String>();
	private HashMap<String, Integer> inta = new HashMap<String, Integer>();

	public Kangaroo(Main main) {
		super(main);

	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Block b = p.getLocation().getBlock();

		if (hasAbility(p)) {
			if (p.getItemInHand().getType() == Material.FIREWORK) {
				e.setCancelled(true);
				if (inta.get(p.getName()) == 1) {
					if (e.getAction() == Action.RIGHT_CLICK_AIR || (e.getAction() == Action.RIGHT_CLICK_BLOCK || (e.getAction() == Action.LEFT_CLICK_AIR || (e.getAction() == Action.RIGHT_CLICK_AIR)))) {
						if (CooldownManager.isInCooldown(p.getUniqueId(), "combatlog")) {
							// "Você está em combate");
							return;
						}
						if (b.getType() != Material.AIR || (b.getRelative(BlockFace.DOWN).getType() != Material.AIR) || b.getRelative(BlockFace.DOWN).getType() != Material.WATER || b.getRelative(BlockFace.DOWN).getType() != Material.STATIONARY_WATER) {
						} else if (jumpa.contains(p.getName())) {
						}

						if (inta.get(p.getName()) == 1) {
							if (p.isSneaking()) {
								Vector v1 = p.getLocation().getDirection().multiply(1.0D).setY(0.50D);
								p.setVelocity(v1);
								inta.put(p.getName(), 0);
							}
						}

						if (jumpa.contains(p.getName())) {
						}

						if (inta.get(p.getName()) == 1) {
							if (!p.isSneaking()) {
								Vector v2 = p.getLocation().getDirection().multiply(0.30D).setY(0.85D);
								p.setVelocity(v2);
								inta.put(p.getName(), 0);
							}
						}
					}
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void dmg(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player))
			return;
		if (Main.est != Estagio.JOGO)
			return;
		Player p = (Player) e.getEntity();
		if (hasAbility(p)) {
			if (e.getCause() == DamageCause.FALL) {
				if (e.getDamage() >= 7) {
					e.setCancelled(true);
					p.damage(7);
				}
			}
		}
	}

	@EventHandler
	public void fly(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		Block b = p.getLocation().getBlock();

		if (hasAbility(p)) {
			if (b.getRelative(BlockFace.DOWN).getType() == Material.AIR || b.getRelative(BlockFace.DOWN).getType() == Material.WATER || b.getRelative(BlockFace.DOWN).getType() == Material.STATIONARY_WATER) {
				jumpa.add(p.getName());
			} else {
				if (b.getType() != Material.AIR || (b.getRelative(BlockFace.DOWN).getType() != Material.AIR) || b.getRelative(BlockFace.DOWN).getType() != Material.WATER || b.getRelative(BlockFace.DOWN).getType() != Material.STATIONARY_WATER) {
					inta.put(p.getName(), 1);
					jumpa.remove(p.getName());
				}
			}
		}
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		ItemStack item = e.getItemDrop().getItemStack();
		Player p = e.getPlayer();
		if (hasAbility(p)) {
			if (item.getType() != Material.FIREWORK)
				return;
			if (!(item.hasItemMeta()))
				return;
			if (item.getItemMeta().getDisplayName().contains("Kangaroo")) {
				p.sendMessage(ChatColor.RED + "Você não pode dropar esse item");
				e.setCancelled(true);
				p.updateInventory();
			}
		}
	}
	@Override
	public Kit getKit() {
		ArrayList<ItemStack> kititems = new ArrayList<>();
		ItemStack kangarooitem = new ItemStack(Material.FIREWORK);
		ItemMeta kangaroo = kangarooitem.getItemMeta();
		kangaroo.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Kangaroo");
		kangarooitem.setItemMeta(kangaroo);
		kititems.add(kangarooitem);
		return new Kit("kangaroo", Arrays.asList(new String[] { "Use seu foguete para saltar longe" }), kititems, new ItemStack(Material.FIREWORK), 30000);
	}
}