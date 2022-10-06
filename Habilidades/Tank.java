package me.ghost.Habilidades;

import java.util.ArrayList;
import java.util.Arrays;

import me.ghost.Main;
import me.ghost.Constructor.Kit;
import me.ghost.Manager.KitInterface;
import me.ghost.Manager.PlayerManager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class Tank extends KitInterface {

	public Tank(Main main) {
		super(main);

	}

	@EventHandler
	public void onTank(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player))
			return;
		Player p = (Player) e.getEntity();
		if (!hasAbility(p))
			return;
		if ((e.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) || (e.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)) {
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onTank2(final PlayerDeathEvent e) {
		if (!(e.getEntity().getKiller() instanceof Player))
			return;
		if (e.getEntity().getKiller() == null)
			return;
		if (!hasAbility(e.getEntity().getKiller()))
			return;
		if (hasAbility(e.getEntity().getKiller()) && ((e.getEntity() instanceof Player)) && ((e.getEntity().getKiller() instanceof Player)))
			e.getEntity().getWorld().createExplosion(e.getEntity().getLocation(), 2.0F);
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
			public void run() {
				PlayerManager.dropItems(e.getEntity(), e.getEntity().getKiller().getLocation());
			}
		}, 20L);
	}

	@Override
	public Kit getKit() {
		return new Kit("tank", Arrays.asList(new String[] { "Mate e exploda seus inimigos" }), new ArrayList<ItemStack>(), new ItemStack(Material.TNT), 15000);
	}
}