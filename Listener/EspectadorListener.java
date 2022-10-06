package me.ghost.Listener;

import me.ghost.Main;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;

public class EspectadorListener implements Listener {

	@EventHandler
	public void Item(PlayerPickupItemEvent e) {
		Player p = e.getPlayer();
		if (Main.plugin.admin.isAdmin(p) || Main.plugin.admin.isSpectating(p)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void Comida(FoodLevelChangeEvent e) {
		Player p = (Player) e.getEntity();
		if (Main.plugin.admin.isAdmin(p) || Main.plugin.admin.isSpectating(p)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void Inventario(PlayerInteractEntityEvent event) {
		if (Main.plugin.admin.isAdmin(event.getPlayer())) {
			if (event.getRightClicked() instanceof Player) {
				event.getPlayer().openInventory(((Player) event.getRightClicked()).getInventory());
			}
		}
	}

	@EventHandler
	public void Collision(VehicleEntityCollisionEvent e) {
		if (!(e.getEntity() instanceof Player))
			return;
		Player p = (Player) e.getEntity();
		if (Main.plugin.admin.isAdmin(p) || Main.plugin.admin.isSpectating(p)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onVehicleDestroy(VehicleDestroyEvent e) {
		Entity entity = e.getAttacker();
		if (!(entity instanceof Player))
			return;
		Player p = (Player) e.getAttacker();
		if (Main.plugin.admin.isAdmin(p) || Main.plugin.admin.isSpectating(p)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onVehicleEnter(VehicleEnterEvent e) {
		if (!(e instanceof Player))
			return;
		Player p = (Player) e.getEntered();
		if (Main.plugin.admin.isAdmin(p) || Main.plugin.admin.isSpectating(p)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onVehicleDamage(VehicleDamageEvent e) {
		Entity entity = e.getAttacker();
		if ((entity instanceof Player))
			return;
		Player p = (Player) e.getAttacker();
		if (Main.plugin.admin.isAdmin(p) || Main.plugin.admin.isSpectating(p)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void Quebrar(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (Main.plugin.admin.isSpectating(p)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void Colocar(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if (Main.plugin.admin.isSpectating(p)) {
			e.setCancelled(true);
		}

	}

	@EventHandler
	public void Interagir(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (Main.plugin.admin.isSpectating(p)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void Dano(EntityDamageEvent event) {
		if ((event.getEntity() instanceof Player)) {
			Player p = (Player) event.getEntity();
			if (Main.plugin.admin.isSpectating(p)) {
				event.setCancelled(true);
			}

		}

	}

	@EventHandler
	public void onBucketFill(PlayerBucketFillEvent e) {
		Player p = e.getPlayer();

		if (Main.plugin.admin.isSpectating(p)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onBucketEmpty(PlayerBucketEmptyEvent e) {
		Player p = e.getPlayer();

		if (Main.plugin.admin.isSpectating(p)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onEntityShootArrow(EntityShootBowEvent e) {
		if (!(e.getEntity() instanceof Player))
			return;
		Player p = (Player) e.getEntity();
		if (Main.plugin.admin.isSpectating(p)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent e) {
		Player p = e.getPlayer();

		if (Main.plugin.admin.isSpectating(p)) {
			e.setCancelled(true);
		}

	}

	@EventHandler
	public void onPlayerEntityShear(PlayerShearEntityEvent e) {
		Player p = e.getPlayer();
		if (Main.plugin.admin.isSpectating(p)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void DamagePlayers2(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player)) {
			return;
		}
		Player p = (Player) e.getDamager();
		if (Main.plugin.admin.isSpectating(p)) {
			e.setCancelled(true);
			return;
		}
	}

	@EventHandler
	public void onYoutuber(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (Main.plugin.admin.isSpectating(p)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onYoutuber2(InventoryInteractEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (Main.plugin.admin.isSpectating(p)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onYoutuber3(PlayerInteractEntityEvent event) {
		if (Main.plugin.admin.isSpectating(event.getPlayer())) {
			if (event.getRightClicked() instanceof Player) {
				event.getPlayer().openInventory(((Player) event.getRightClicked()).getInventory());
			}
		}
	}
}
