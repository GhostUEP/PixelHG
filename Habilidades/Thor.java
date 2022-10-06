package me.ghost.Habilidades;

import java.util.ArrayList;
import java.util.Arrays;

import me.ghost.Main;
import me.ghost.Constructor.Kit;
import me.ghost.Enums.Estagio;
import me.ghost.Manager.CooldownManager;
import me.ghost.Manager.KitInterface;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Thor extends KitInterface {

	public Thor(Main main) {
		super(main);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onThorStone(PlayerInteractEvent event) {
		final Player p = event.getPlayer();
		ItemStack item = p.getItemInHand();
		if (!hasAbility(p)) {
			return;
		}
		if (item == null) {
			return;
		}
		if (item.getType() != Material.WOOD_AXE) {
			return;
		}
		if (event.getAction().name().contains("RIGHT")) {
			if (Main.est == Estagio.PREJOGO)
				return;
			if (CooldownManager.isInCooldown(p.getUniqueId(), "thor")) {
				int timeleft = CooldownManager.getTimeLeft(p.getUniqueId(), "thor");
				p.sendMessage(ChatColor.RED + "Thor em cooldown, faltando: " + timeleft + " segundos");
				return;
			}
			Location local = p.getTargetBlock(null, 100).getLocation();
			if (local.getBlock().getType() == Material.AIR)
				return;
			if (local.distance(p.getLocation()) > 16) {
				return;
			}
			Location altitude = local.getWorld().getHighestBlockAt(local).getLocation().subtract(0, 1, 0);
			if (altitude.getBlock().getType() == Material.NETHERRACK) {
				LightningStrike strike = p.getWorld().strikeLightning(altitude);
				strike.setMetadata("DontHurt", new FixedMetadataValue(Main.plugin, p.getName()));
				if (altitude.getY() >= 90) {
					p.getWorld().createExplosion(altitude, 3);
					return;
				}
				if (altitude.getBlock().getType() != Material.BEDROCK) {
					altitude.getBlock().setType(Material.NETHERRACK);
					altitude.getBlock().getRelative(BlockFace.UP).setType(Material.FIRE);
				}
				if (!CooldownManager.isInCooldown(p.getUniqueId(), "thor")) {
					CooldownManager c = new CooldownManager(p.getUniqueId(), "thor", 5);
					c.start();
				}
			} else {
				LightningStrike strike = p.getWorld().strikeLightning(altitude);
				strike.setMetadata("DontHurt", new FixedMetadataValue(Main.plugin, p.getName()));
				if (altitude.getBlock().getType() != Material.BEDROCK) {
					altitude.getBlock().setType(Material.NETHERRACK);
					altitude.getBlock().getRelative(BlockFace.UP).setType(Material.FIRE);
				}
				if (!CooldownManager.isInCooldown(p.getUniqueId(), "thor")) {
					CooldownManager c = new CooldownManager(p.getUniqueId(), "thor", 5);
					c.start();
				}
			}
		}
	}

	@EventHandler
	public void onBlockIgnite(BlockIgniteEvent event) {
		if (event.getCause() == IgniteCause.LIGHTNING) {
			event.setCancelled(true);
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onHabilidadeDeHitReceberDano(EntityDamageByEntityEvent event) {
		Entity vitima = event.getEntity();
		Entity atacante = event.getDamager();
		if (vitima.isDead()) {
			return;
		}
		if (!(vitima instanceof Player))
			return;
		final Player p = (Player) vitima;
		if (atacante instanceof LightningStrike && atacante.hasMetadata("DontHurt") && p.getName().equals(event.getDamager().getMetadata("DontHurt").get(0).value())) {
			event.setCancelled(true);
			return;
		}
		if (atacante instanceof LightningStrike) {
			event.setDamage(4);
		}
		if (atacante.hasMetadata("Bencao")) {
			event.setCancelled(true);
		}
	}

	@Override
	public Kit getKit() {
		ArrayList<ItemStack> kititems = new ArrayList<>();
		kititems.add(new ItemStack(Material.WOOD_AXE));
		return new Kit("thor", Arrays.asList(new String[] { "Use seu machado para soltar raios em seus inimigos" }), kititems, new ItemStack(Material.WOOD_AXE), 20000);
	}
}