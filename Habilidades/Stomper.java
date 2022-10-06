package me.ghost.Habilidades;

import java.util.ArrayList;
import java.util.Arrays;

import me.ghost.Main;
import me.ghost.Constructor.Kit;
import me.ghost.Enums.Estagio;
import me.ghost.Manager.KitInterface;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

public class Stomper extends KitInterface {

	public Stomper(Main main) {
		super(main);

	}

	@EventHandler
	public void onStomper(EntityDamageEvent event) {
		if (Main.est != Estagio.JOGO) {
			return;
		}
		if (event.isCancelled())
			return;
		Entity stomper = event.getEntity();
		if (!(stomper instanceof Player)) {

			return;
		}

		Player stomped = (Player) stomper;
		if (!hasAbility(stomped)) {
			return;
		}
		DamageCause cause = event.getCause();
		if (cause != DamageCause.FALL) {
			return;
		}
		double dmg = event.getDamage();
		if (dmg > 4) {
			if (Main.plugin.invencivel.contains(stomped.getUniqueId()))
				return;
			event.setCancelled(true);
			stomped.damage(4.0D);
		}
		for (Entity entity : stomped.getNearbyEntities(5, 2, 5)) {
			if (!(entity instanceof Player)) {
				continue;
			}
			Player stompado = (Player) entity;
			double dmg2 = dmg;
			if (stompado.isSneaking() && dmg2 > 7) {
				dmg2 = 7;

			}
			stompado.damage(dmg2, stomped);
		}

	}

	@Override
	public Kit getKit() {
		return new Kit("stomper", Arrays.asList(new String[] { "Esmague seus inimigos e mate-os" }), new ArrayList<ItemStack>(), new ItemStack(Material.IRON_BOOTS), 30000);
	}
}
