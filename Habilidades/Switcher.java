package me.ghost.Habilidades;

import java.util.ArrayList;
import java.util.Arrays;

import me.ghost.Main;
import me.ghost.Constructor.Kit;
import me.ghost.Enums.Estagio;
import me.ghost.Manager.KitInterface;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class Switcher extends KitInterface {

	public Switcher(Main main) {
		super(main);

	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void snowball(EntityDamageByEntityEvent e) {
		if (Main.est == Estagio.PREJOGO)
			return;
		if (((e.getDamager() instanceof Snowball)) && ((e.getEntity() instanceof Player))) {
			Snowball s = (Snowball) e.getDamager();
			Player shooter = (Player) s.getShooter();
			if (!hasAbility(shooter))
				return;
			if (!(s.getShooter() instanceof Player))
				return;
			Location shooterLoc = shooter.getLocation();
			shooter.teleport(e.getEntity().getLocation());
			e.getEntity().teleport(shooterLoc);
		}
	}

	@Override
	public Kit getKit() {
		ArrayList<ItemStack> kititems = new ArrayList<>();
		kititems.add(new ItemStack(Material.SNOW_BALL, 16));
		return new Kit("switcher", Arrays.asList(new String[] { "Acerte seus inimigos com bolas de neve, e troque de lugar com eles" }), kititems, new ItemStack(Material.SNOW_BALL), 10000);
	}
}