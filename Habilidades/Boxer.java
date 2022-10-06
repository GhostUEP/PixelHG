package me.ghost.Habilidades;

import java.util.ArrayList;
import java.util.Arrays;

import me.ghost.Main;
import me.ghost.Constructor.Kit;
import me.ghost.Manager.KitInterface;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class Boxer extends KitInterface {

	public Boxer(Main main) {
		super(main);
	}

	@EventHandler
	public void onBoxer(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player))
			return;
		if (!(e.getDamager() instanceof Player))
			return;
		Player p = (Player) e.getDamager();

		if (hasAbility(p)) {
			if (p.getItemInHand().getType() == Material.AIR) {
				e.setDamage(e.getDamage() + 2.0D);
			}
		}
	}

	@EventHandler
	public void OnBoxer2(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player))
			return;
		if (!(e.getDamager() instanceof Player))
			return;
		Player p = (Player) e.getEntity();

		if (hasAbility(p)) {
			if (e.getDamage() > 1)
				e.setDamage(e.getDamage() - 1.0D);
		}
	}

	@Override
	public Kit getKit() {
		return new Kit("boxer", Arrays.asList(new String[] { "Seja mais forte, e use seu punho para matar os inimigos" }), new ArrayList<ItemStack>(), new ItemStack(Material.STONE_SWORD), 25000);
	}
}