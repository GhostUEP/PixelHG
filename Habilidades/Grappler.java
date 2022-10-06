package me.ghost.Habilidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import me.ghost.Main;
import me.ghost.Constructor.Gancho;
import me.ghost.Constructor.Kit;
import me.ghost.Enums.Estagio;
import me.ghost.Manager.CooldownManager;
import me.ghost.Manager.KitInterface;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Grappler extends KitInterface {
	public Map<UUID, Gancho> gancho = new HashMap<>();

	public Grappler(Main main) {
		super(main);
	}

	@EventHandler
	public void onLeash(PlayerLeashEntityEvent e) {
		Player p = e.getPlayer();
		if ((e.getPlayer().getItemInHand().getType().equals(Material.LEASH)) && hasAbility(p)) {
			e.setCancelled(true);
			if (CooldownManager.isInCooldown(p.getUniqueId(), "combatlog")) {
				return;
			}
			if (Main.est == Estagio.PREJOGO) {
				return;
			}
			e.getPlayer().updateInventory();
			e.setCancelled(true);
			if (!gancho.containsKey(p.getUniqueId())) {
				return;
			}
			if (!((Gancho) gancho.get(p.getUniqueId())).isHooked()) {
				return;
			}
			double d = (gancho.get(p.getUniqueId())).getBukkitEntity().getLocation().distance(p.getLocation());
			double t = d;
			double v_x = (1.0D + 0.04000000000000001D * t) * ((gancho.get(p.getUniqueId())).getBukkitEntity().getLocation().getX() - p.getLocation().getX()) / t;
			double v_y = 0;
			double v_z = (1.0D + 0.04000000000000001D * t) * ((gancho.get(p.getUniqueId())).getBukkitEntity().getLocation().getZ() - p.getLocation().getZ()) / t;
			if ((gancho.get(p.getUniqueId())).getBukkitEntity().getLocation().getY() - p.getLocation().getY() == 0.0) {
				v_y = p.getVelocity().getY();
			} else if ((gancho.get(p.getUniqueId())).getBukkitEntity().getLocation().getY() - p.getLocation().getY() < 0.5) {
				v_y = (1.0D + 0.03D * t) * (((gancho.get(p.getUniqueId())).getBukkitEntity().getLocation().getY() - p.getLocation().getY()) / t);
			} else if ((gancho.get(p.getUniqueId())).getBukkitEntity().getLocation().getY() - p.getLocation().getY() > 0.5) {
				v_y = (1.0D + 0.03D * t) * (((gancho.get(p.getUniqueId())).getBukkitEntity().getLocation().getY() - p.getLocation().getY()) / t);

			}

			Vector v = p.getVelocity();
			v.setX(v_x);
			v.setY(v_y);
			v.setZ(v_z);
			p.setVelocity(v);

			p.getWorld().playSound(p.getLocation(), Sound.DIG_GRAVEL, 1.0F, 1.0F);
		}
	}

	@EventHandler
	public void onClicar(PlayerInteractEvent event) {
		final Player p = event.getPlayer();
		if (p.getItemInHand() != null && p.getItemInHand().getType() != Material.AIR && event.getPlayer().getItemInHand().getType().equals(Material.LEASH) && hasAbility(p)) {
			event.setCancelled(true);
			if ((event.getAction() == Action.LEFT_CLICK_AIR) || (event.getAction() == Action.LEFT_CLICK_BLOCK)) {
				if (CooldownManager.isInCooldown(p.getUniqueId(), "combatlog")) {
					return;
				}
				if (Main.est == Estagio.PREJOGO) {
					return;
				}
				if (gancho.containsKey(p.getUniqueId())) {
					(gancho.get(p.getUniqueId())).remove();
				}
				final Gancho nmsHook = new Gancho(p.getWorld(), ((CraftPlayer) p).getHandle());
				nmsHook.spawn(p.getEyeLocation().add(p.getLocation().getDirection().getX(), p.getLocation().getDirection().getY(), p.getLocation().getDirection().getZ()), p.getLocation().getDirection().multiply(10.0D));
				gancho.put(p.getUniqueId(), nmsHook);
			} else {
				if (!gancho.containsKey(p.getUniqueId())) {
					return;
				}
				if (!(gancho.get(p.getUniqueId())).isHooked()) {
					p.sendMessage(ChatColor.RED + "Seu gancho não está preso em nada!");
					return;
				}
				double d = (gancho.get(p.getUniqueId())).getBukkitEntity().getLocation().distance(p.getLocation());
				double t = d;
				double v_x = (1.0D + 0.04000000000000001D * t) * ((gancho.get(p.getUniqueId())).getBukkitEntity().getLocation().getX() - p.getLocation().getX()) / t;
				double v_y = 0;
				double v_z = (1.0D + 0.04000000000000001D * t) * ((gancho.get(p.getUniqueId())).getBukkitEntity().getLocation().getZ() - p.getLocation().getZ()) / t;
				if ((gancho.get(p.getUniqueId())).getBukkitEntity().getLocation().getY() - p.getLocation().getY() == 0.0) {
					v_y = p.getVelocity().getY();
				} else if ((gancho.get(p.getUniqueId())).getBukkitEntity().getLocation().getY() - p.getLocation().getY() < 0.5) {
					v_y = (1.0D + 0.03D * t) * (((gancho.get(p.getUniqueId())).getBukkitEntity().getLocation().getY() - p.getLocation().getY()) / t);
				} else if ((gancho.get(p.getUniqueId())).getBukkitEntity().getLocation().getY() - p.getLocation().getY() > 0.5) {
					v_y = (1.0D + 0.03D * t) * (((gancho.get(p.getUniqueId())).getBukkitEntity().getLocation().getY() - p.getLocation().getY()) / t);

				}
				Vector v = p.getVelocity();
				v.setX(v_x);
				v.setY(v_y);
				v.setZ(v_z);
				p.setVelocity(v);

				p.getWorld().playSound(p.getLocation(), Sound.DIG_GRAVEL, 1.0F, 1.0F);
			}
		}
	}

	@EventHandler
	public void onSlot(PlayerItemHeldEvent e) {
		if (gancho.containsKey(e.getPlayer().getUniqueId())) {
			((Gancho) gancho.get(e.getPlayer().getUniqueId())).remove();
			gancho.remove(e.getPlayer().getUniqueId());
		}
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		ItemStack item = e.getItemDrop().getItemStack();
		Player p = e.getPlayer();
		if ((item.getType() == Material.LEASH) && (item.hasItemMeta()) && (item.getItemMeta().getDisplayName().contains("Grappler")) && hasAbility(p)) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.RED + "Você não pode dropar esse item");
			p.updateInventory();
		}
	}

	@EventHandler
	public void dmg(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player))
			return;
		if (Main.est != Estagio.JOGO)
			return;
		Player p = (Player) e.getEntity();
		if (hasAbility(p, "grappler")) {
			if (e.getCause() == DamageCause.FALL) {
				if (gancho.containsKey(p.getUniqueId())) {
					if (gancho.get(p.getUniqueId()).isHooked()) {
						if (e.getDamage() >= 20) {
							e.setCancelled(true);
							p.damage(1.0D);
							p.sendMessage(ChatColor.GREEN + "Com esse dano de altura você iria morrer, cuidado...");
						} else {
							e.setCancelled(true);
							p.damage(1.0D);
						}
					}
				}
			}
		}
	}

	@Override
	public Kit getKit() {
		ArrayList<ItemStack> kititems = new ArrayList<>();
		kititems.add(createItem(Material.LEASH, ChatColor.GOLD + "" + ChatColor.BOLD + "Grappler"));
		return new Kit("grappler", Arrays.asList(new String[] { "Use seu laço para se locomover" }), kititems, new ItemStack(Material.LEASH), 35000);
	}
}