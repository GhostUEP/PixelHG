package me.ghost.Habilidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import me.ghost.Main;
import me.ghost.Constructor.Kit;
import me.ghost.Enums.Estagio;
import me.ghost.Eventos.TimeSecondEvent2;
import me.ghost.Manager.KitInterface;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

public class Magma extends KitInterface {

	public Magma(Main main) {
		super(main);

	}

	@EventHandler
	public void onMagma(EntityDamageEvent event) {
		if (Main.est != Estagio.JOGO) {
			return;
		}
		Entity entity = event.getEntity();
		if (!(entity instanceof Player)) {
			return;
		}
		Player p = (Player) entity;
		if (!hasAbility(p)) {
			return;
		}
		DamageCause fire = event.getCause();
		if (fire == DamageCause.FIRE || fire == DamageCause.LAVA || fire == DamageCause.FIRE_TICK) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onDAmager(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player))
			return;
		if (!(e.getDamager() instanceof Player))
			return;
		Player p = (Player) e.getEntity();
		Player d = (Player) e.getDamager();
		if (!hasAbility(p)) {
			return;
		}
		if (Main.est != Estagio.JOGO) {
			return;
		}
		Random r = new Random();
		if (d instanceof Player) {
			if (r.nextInt(5) == 0) {
				d.setFireTicks(100);
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPoseidon(TimeSecondEvent2 e) {
		if (Main.est != Estagio.JOGO)
			return;
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (!hasAbility(p)) {
				continue;
			}
			Block b = p.getLocation().getBlock();
			if (b.getType() == Material.WATER) {
				p.damage(3.0D);
			} else if (b.getType() == Material.STATIONARY_WATER) {
				p.damage(3.0D);
			}
		}
	}

	@Override
	public Kit getKit() {
		return new Kit("magma", Arrays.asList(new String[] { "Coloque fogo em seus inimigos quando te batem" }), new ArrayList<ItemStack>(), new ItemStack(Material.BLAZE_POWDER), 25000);
	}
}
