package me.ghost.Listener;

import me.ghost.Main;
import me.ghost.Enums.Estagio;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PreJogoListener implements Listener {
	@EventHandler
	public void BreakBlock(BlockBreakEvent e) {

		if (Main.est == Estagio.PREJOGO) {
			e.setCancelled(true);
		}
	}

	public void PlaceBlock(BlockPlaceEvent e) {

		if (Main.est == Estagio.PREJOGO) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void PlayerInteract(PlayerInteractEvent e) {
		if (Main.est == Estagio.PREJOGO) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void Dano(EntityDamageEvent e) {
		if (Main.est == Estagio.PREJOGO) {
			if (e.getEntity() instanceof Player) {
				e.setCancelled(true);
			} else {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void Comida(FoodLevelChangeEvent e) {
		// Player p = (Player) e.getEntity();

		if (Main.est != Estagio.JOGO) {
			e.setCancelled(true);
		}

		if ((e.getEntity() instanceof Player)) {
			((Player) e.getEntity()).setSaturation(4.0F);
		}

		// if (Main.plugin.admin.isAdmin(p) ||
		// Main.plugin.admin.isSpectating(p)) {
		// e.setCancelled(true);
		// }
	}

	@EventHandler
	public void Spawnar(EntitySpawnEvent e) {
		if (Main.est == Estagio.PREJOGO) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void Baude(PlayerBucketFillEvent e) {
		// Player p = e.getPlayer();

		if (Main.est == Estagio.PREJOGO) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void Baude2(PlayerBucketEmptyEvent e) {
		// Player p = e.getPlayer();
		if (Main.est == Estagio.PREJOGO) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void Arco(EntityShootBowEvent e) {
		if (!(e.getEntity() instanceof Player))
			return;
		// Player p = (Player) e.getEntity();

		if (((e.getEntity() instanceof Player)) && (Main.est == Estagio.PREJOGO)) {
			e.getBow().setDurability((short) 0);
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void Dropar(PlayerDropItemEvent e) {
		// Player p = e.getPlayer();
		if (Main.est == Estagio.PREJOGO) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void Explodir(EntityExplodeEvent e) {
		if (Main.est == Estagio.PREJOGO) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void Pegar(PlayerPickupItemEvent e) {
		// Player p = e.getPlayer();

		if (Main.est == Estagio.PREJOGO) {
			e.setCancelled(true);
		}
	}
}
