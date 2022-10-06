package me.ghost.Habilidades;

import java.util.ArrayList;
import java.util.Arrays;

import me.ghost.Main;
import me.ghost.Constructor.Kit;
import me.ghost.Manager.KitInterface;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

public class Viking extends KitInterface {

	public Viking(Main main) {
		super(main);
	}

	@EventHandler
	public void onDamageViking(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player)) {
			return;
		}
		Player d = (Player) e.getDamager();
		ItemStack item = d.getItemInHand();
		if (e.getEntity() instanceof Player || e.getEntity() instanceof LivingEntity) {
			if (!hasAbility(d)) {
				return;
			}
			if (item.getType().name().contains("_AXE")) {
				e.setDamage(e.getDamage() + 2);
			}
		}
	}

	@EventHandler
	public void Ongastar(PlayerItemDamageEvent e) {
		Player p = e.getPlayer();
		if (!hasAbility(p))
			return;
		ItemStack item = e.getItem();
		if (item.getType().name().contains("_AXE")) {
			e.setCancelled(true);
		}
	}

	@Override
	public Kit getKit() {
		return new Kit("viking", Arrays.asList(new String[] { "Use seus machados e mate seus inimigos com a força deles" }), new ArrayList<ItemStack>(), new ItemStack(Material.IRON_AXE), 25000);
	}
}
