package me.ghost.Habilidades;

import java.util.ArrayList;
import java.util.Arrays;

import me.ghost.Main;
import me.ghost.Constructor.Kit;
import me.ghost.Manager.KitInterface;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

public class Fisherman extends KitInterface {

	public Fisherman(Main main) {
		super(main);

	}

	@EventHandler
	public void onFishermanPlayer(PlayerFishEvent e) {
		final Player p = e.getPlayer();
		if (!hasAbility(p))
			return;
		if (e.getState() == PlayerFishEvent.State.CAUGHT_ENTITY) {
			if (!(e.getCaught() instanceof Player))
				return;
			Player c = (Player) e.getCaught();
			World w = p.getLocation().getWorld();
			double x = p.getLocation().getBlockX() + 0.5D;
			double y = p.getLocation().getBlockY();
			double z = p.getLocation().getBlockZ() + 0.5D;
			float yaw = c.getLocation().getYaw();
			float pitch = c.getLocation().getPitch();
			Location loc = new Location(w, x, y, z, yaw, pitch);
			if (!hasAbility(c, "endermage"))
				p.getItemInHand().setDurability((short) 0);
			if (c == p) {
				c.setFallDistance(0);
			}
			c.teleport(loc);
		}
	}

	@EventHandler
	public void onFishermanMobs(PlayerFishEvent e) {
		final Player p = e.getPlayer();
		if (!hasAbility(p))
			return;
		if (e.getState() == PlayerFishEvent.State.CAUGHT_ENTITY) {
			if (!(e.getCaught() instanceof LivingEntity))
				return;
			LivingEntity c = (LivingEntity) e.getCaught();
			World w = p.getLocation().getWorld();
			double x = p.getLocation().getBlockX() + 0.5D;
			double y = p.getLocation().getBlockY();
			double z = p.getLocation().getBlockZ() + 0.5D;
			float yaw = c.getLocation().getYaw();
			float pitch = c.getLocation().getPitch();
			Location loc = new Location(w, x, y, z, yaw, pitch);
			c.teleport(loc);
			p.getItemInHand().setDurability((short) 0);
		}
	}

	@Override
	public Kit getKit() {
		ArrayList<ItemStack> kititems = new ArrayList<>();
		kititems.add(new ItemStack(Material.FISHING_ROD));
		return new Kit("fisherman", Arrays.asList(new String[] { "Pesque seus inimigos e use sua estratégia para mata-los" }), kititems, new ItemStack(Material.FISHING_ROD), 10000);
	}
}
