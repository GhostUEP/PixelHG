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

public class Fireman extends KitInterface {

	public Fireman(Main main) {
		super(main);

	}

	@EventHandler
	public void onFireman(EntityDamageEvent event) {
		if (Main.est != Estagio.JOGO) {
			return;
		}
		Entity entity = event.getEntity();
		if (!(entity instanceof Player)) {
			return;
		}
		Player fireman = (Player) entity;
		if (!hasAbility(fireman)) {
			return;
		}
		DamageCause fire = event.getCause();
		if (fire == DamageCause.FIRE || fire == DamageCause.LAVA || fire == DamageCause.FIRE_TICK) {
			event.setCancelled(true);
		}
	}

	@Override
	public Kit getKit() {
		ArrayList<ItemStack> kititems = new ArrayList<>();
		kititems.add(new ItemStack(Material.WATER_BUCKET));
		return new Kit("fireman", Arrays.asList(new String[] { "Seja invencivel a coisas que queimam" }), kititems, new ItemStack(Material.WATER_BUCKET), 20000);
	}
}
