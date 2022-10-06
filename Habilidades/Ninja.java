package me.ghost.Habilidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import me.ghost.Main;
import me.ghost.Constructor.Kit;
import me.ghost.Enums.Estagio;
import me.ghost.Manager.CooldownManager;
import me.ghost.Manager.KitInterface;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;

public class Ninja extends KitInterface {
	private HashMap<String, NinjaHit> ninja;

	public Ninja(Main main) {
		super(main);
		ninja = new HashMap<>();
	}

	@EventHandler
	public void onNinjaHit(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
			final Player damager = (Player) event.getDamager();
			Player damaged = (Player) event.getEntity();
			if (hasAbility(damager)) {
				NinjaHit ninjaHit = ninja.get(damager.getName());
				if (ninjaHit == null)
					ninjaHit = new NinjaHit(damaged);
				else
					ninjaHit.setTarget(damaged);
				ninja.put(damager.getName(), ninjaHit);
			}
		}
	}

	@EventHandler
	public void onShift(PlayerToggleSneakEvent event) {
		Player p = event.getPlayer();
		if (!event.isSneaking())
			return;
		if (!hasAbility(p))
			return;
		if (Main.est == Estagio.PREJOGO)
			return;
		if (!ninja.containsKey(p.getName()))
			return;
		NinjaHit ninjaHit = ninja.get(p.getName());
		Player target = ninjaHit.getTarget();
		if (target.isDead())
			return;
		if (p.getLocation().distance(target.getLocation()) > 50)
			return;
		if (p.getLocation().getY() - target.getLocation().getY() > 20)
			return;
		if (ninjaHit.getTargetExpires() < System.currentTimeMillis())
			return;
		if (CooldownManager.isInCooldown(p.getUniqueId(), "ninja")) {
			int timeleft = CooldownManager.getTimeLeft(p.getUniqueId(), "ninja");
			p.sendMessage(ChatColor.RED + "Ninja em cooldown, faltando: " + timeleft + " segundos");
			return;
		}

		if (Main.plugin.admin.isAdmin(target) || Main.plugin.admin.isSpectating(target))
			return;
		if (!CooldownManager.isInCooldown(p.getUniqueId(), "ninja")) {
			CooldownManager c = new CooldownManager(p.getUniqueId(), "ninja", 10);
			c.start();
		}
		p.teleport(target.getLocation());
		ninjaHit.teleport();
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player p = event.getEntity();
		if (!ninja.containsKey(p.getName()))
			return;
		ninja.remove(p.getName());
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player p = event.getPlayer();
		if (!ninja.containsKey(p.getName()))
			return;
		ninja.remove(p.getName());
	}

	private static class NinjaHit {
		private Player target;
		private long targetExpires;

		public NinjaHit(Player target) {
			this.target = target;
			this.targetExpires = System.currentTimeMillis() + 15000;
		}

		public Player getTarget() {
			return target;
		}

		public long getTargetExpires() {
			return targetExpires;
		}

		public void teleport() {

		}

		public void setTarget(Player player) {
			this.target = player;
			this.targetExpires = System.currentTimeMillis() + 20000;
		}

	}

	@Override
	public Kit getKit() {
		return new Kit("ninja", Arrays.asList(new String[] { "Vá para trás de seus inimigos, e mate-os" }), new ArrayList<ItemStack>(), new ItemStack(Material.COAL_BLOCK), 35000);
	}
}