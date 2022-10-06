package me.ghost.Habilidades;

import java.util.ArrayList;
import java.util.Arrays;

import me.ghost.Main;
import me.ghost.Constructor.Kit;
import me.ghost.Enums.Estagio;
import me.ghost.Manager.KitInterface;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Anchor extends KitInterface {

	public Anchor(Main main) {
		super(main);

	}

	@EventHandler
	public void onAnchor(EntityDamageByEntityEvent e) {
		if (((e.getEntity() instanceof Player)) && ((e.getDamager() instanceof Player))) {
			if (Main.est != Estagio.JOGO)
				return;
			final Player p = (Player) e.getEntity();
			Player d = (Player) e.getDamager();
			if (hasAbility(d)) {
				p.setVelocity(new Vector());
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
					public void run() {
						p.setVelocity(new Vector());
						p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 4.0F, 4.0F);
					}
				}, 1L);
			}
		}
	}

	@EventHandler
	public void onAnchorMob(EntityDamageByEntityEvent e) {
		if (((e.getEntity() instanceof LivingEntity)) && ((e.getDamager() instanceof Player))) {
			if (Main.est != Estagio.JOGO)
				return;
			final LivingEntity p = (LivingEntity) e.getEntity();
			Player d = (Player) e.getDamager();
			if (hasAbility(d)) {
				p.setVelocity(new Vector());
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
					public void run() {
						p.setVelocity(new Vector());
					}
				}, 1L);
			}
		}
	}

	@EventHandler
	public void onAnchor2(EntityDamageByEntityEvent e) {
		if (((e.getEntity() instanceof Player)) && ((e.getDamager() instanceof Player))) {
			if (Main.est != Estagio.JOGO)
				return;
			final Player p = (Player) e.getEntity();
			if (hasAbility(p)) {
				p.setVelocity(new Vector());
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
					public void run() {
						p.setVelocity(new Vector());
						p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 4.0F, 4.0F);
					}
				}, 1L);
			}
		}
	}

	@EventHandler
	public void onAnchor3(EntityDamageEvent e) {
		if (((e.getEntity() instanceof Player))) {
			if (Main.est != Estagio.JOGO)
				return;
			final Player p = (Player) e.getEntity();
			if (hasAbility(p)) {
				p.setVelocity(new Vector());
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
					public void run() {
						p.setVelocity(new Vector());
					}
				}, 1L);
			}
		}
	}

	@Override
	public Kit getKit() {
		return new Kit("anchor", Arrays.asList(new String[] { "Não tome knockback de seus inimigos, e também não de" }), new ArrayList<ItemStack>(), new ItemStack(Material.ANVIL), 15000);
	}
}
