package me.ghost.Listener;

import java.util.Random;

import me.ghost.Main;
import me.ghost.Enums.Estagio;
import me.ghost.Eventos.TimeSecondEvent2;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ForceFieldListener implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void ForcefieldDamagePositive(PlayerMoveEvent e) {
		final Player p = e.getPlayer();
		Location loc = p.getLocation();
		World w = p.getWorld();
		Damageable hp = p;
		EntityDamageEvent event = new EntityDamageEvent(p, DamageCause.VOID, hp.getHealth());
		if (Main.est == Estagio.PREJOGO) {
			if (w.getSpawnLocation().getWorld().setSpawnLocation(0, p.getWorld().getHighestBlockYAt(0, 0), 0)) {
				if ((Math.abs(loc.getBlockX() + w.getSpawnLocation().getBlockX()) >= 500) || (Math.abs(loc.getBlockZ() + w.getSpawnLocation().getBlockZ()) >= 500)) {
					int x = new Random().nextInt(5) + 30;
					int y = new Random().nextInt(5) + 90;
					int z = new Random().nextInt(5) + 30;
					Location locs = new Location(p.getWorld(), x, y, z);
					p.teleport(locs);
				}
			}
		}
		if (Main.est != Estagio.PREJOGO) {
			if (w.getSpawnLocation().getWorld().setSpawnLocation(0, p.getWorld().getHighestBlockYAt(0, 0), 0)) {
				if ((Math.abs(loc.getBlockX() + w.getSpawnLocation().getBlockX()) >= 500) || (Math.abs(loc.getBlockZ() + w.getSpawnLocation().getBlockZ()) >= 500)) {
					if (!Main.plugin.NaoEstaJogando(p)) {
						p.setFireTicks(500);
						p.damage(6.0D);
						p.setLastDamageCause(event);
					}
				}
			}
		}

		if (Main.est != Estagio.PREJOGO) {
			if (p.getLocation().getY() > 130) {
				if (!Main.plugin.NaoEstaJogando(p) && Main.est != Estagio.VITORIA) {
					p.setFireTicks(10);
					p.damage(7.0D);
					p.setLastDamageCause(event);
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void on15Segundos(TimeSecondEvent2 e) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (Main.plugin.admin.isAdmin(p))
				continue;
			Location loc = p.getLocation();
			World w = p.getWorld();
			if (Main.est == Estagio.PREJOGO && Main.TempoPrejogo <= 15) {
				p.setAllowFlight(false);
				p.setFlying(false);
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10000, 10000));
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 10000, -10000));
				p.setFoodLevel(1);
				if (w.getSpawnLocation().getWorld().setSpawnLocation(0, p.getWorld().getHighestBlockYAt(0, 0), 0)) {
					if ((Math.abs(loc.getBlockX() + w.getSpawnLocation().getBlockX()) >= 25) || (Math.abs(loc.getBlockZ() + w.getSpawnLocation().getBlockZ()) >= 25)) {
						if (Main.plugin.admin.isAdmin(p))
							continue;
						Location local = new Location(p.getWorld(), 0, p.getWorld().getHighestBlockYAt(0, 0), 0);
						Random r = new Random();
						Location loc2 = new Location(p.getWorld(), -5 + r.nextInt(20), local.getY() + 1, -5 + r.nextInt(20));
						p.teleport(loc2);
					}
				}
			}
		}
	}

	@EventHandler
	public void onffbreak(BlockBreakEvent event) {
		final Player p = event.getPlayer();
		Block b = event.getBlock();
		Location loc = b.getLocation();
		World w = p.getWorld();
		if (w.getSpawnLocation().getWorld().setSpawnLocation(0, p.getWorld().getHighestBlockYAt(0, 0), 0)) {
			if ((Math.abs(loc.getBlockX() + w.getSpawnLocation().getBlockX()) >= 490) || (Math.abs(loc.getBlockZ() + w.getSpawnLocation().getBlockZ()) >= 490)) {
				if (b.getType() == Material.RED_MUSHROOM || b.getType() == Material.BROWN_MUSHROOM)
					return;
				p.sendMessage(ChatColor.RED + "Você não pode quebrar blocos perto do Forcefield");
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onffplace(BlockPlaceEvent event) {
		final Player p = event.getPlayer();
		Block b = event.getBlockPlaced();
		Location loc = b.getLocation();
		World w = p.getWorld();
		if (w.getSpawnLocation().getWorld().setSpawnLocation(0, p.getWorld().getHighestBlockYAt(0, 0), 0)) {
			if ((Math.abs(loc.getBlockX() + w.getSpawnLocation().getBlockX()) >= 490) || (Math.abs(loc.getBlockZ() + w.getSpawnLocation().getBlockZ()) >= 490)) {
				p.sendMessage(ChatColor.RED + "Você não pode colocar blocos perto do Forcefield");
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onFF(BlockBreakEvent event) {
		if (Main.plugin.forcefieldblock.contains(event.getBlock())) {
			event.getPlayer().sendMessage(ChatColor.RED + "Você não pode quebrar blocos do Forcefield");
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onEntityExplodeFF(EntityExplodeEvent event) {
		if (Main.plugin.forcefieldblock.contains(event.getLocation().getBlock())) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void fogo(BlockBurnEvent e) {
		if (Main.plugin.forcefieldblock.contains(e.getBlock())) {
			e.setCancelled(true);
		}
	}
}
