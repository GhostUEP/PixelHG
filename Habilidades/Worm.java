package me.ghost.Habilidades;

import java.util.ArrayList;
import java.util.Arrays;

import me.ghost.Main;
import me.ghost.Constructor.Kit;
import me.ghost.Manager.KitInterface;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class Worm extends KitInterface {

	public Worm(Main main) {
		super(main);
	}

	@EventHandler
	public void onWorm(BlockDamageEvent event) {
		if (hasAbility(event.getPlayer()) && ((event.getBlock().getType() == Material.DIRT))) {
			Player p = event.getPlayer();
			Damageable hp = p;
			if (hp.getHealth() < 20.0D) {
				double hp2 = hp.getHealth() + 0.5D;
				if (hp2 > 20.0D) {
					hp2 = 20.0D;
				}
				hp.setHealth(hp2);
			} else if (p.getFoodLevel() < 20) {
				p.setFoodLevel(p.getFoodLevel() + 1);
			}
			event.getBlock().setType(Material.AIR);
			event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.DIRT));
		}
	}

	@EventHandler
	public void onWormDamage(EntityDamageEvent e) {
		if (e.isCancelled())
			return;
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if ((hasAbility(p) && (e.getCause() == EntityDamageEvent.DamageCause.FALL))) {
				Location loc = e.getEntity().getLocation();
				Location l = loc.subtract(0.0D, 1.0D, 0.0D);
				if (l.getBlock().getType() == Material.DIRT) {
					e.setCancelled(true);
					p.damage(1.0D);
				}
			}
		}
	}

	@Override
	public Kit getKit() {
		return new Kit("worm", Arrays.asList(new String[] { "Quebre terras com 1 hit e se alimente delas" }), new ArrayList<ItemStack>(), new ItemStack(Material.DIRT), 7000);
	}
}
