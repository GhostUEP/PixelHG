package me.ghost.Habilidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import me.ghost.Main;
import me.ghost.Constructor.Kit;
import me.ghost.Enums.Estagio;
import me.ghost.Manager.KitInterface;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Cannibal extends KitInterface {

	public Cannibal(Main main) {
		super(main);
	}

	@EventHandler
	public void onSnail(EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof Player))
			return;
		if (!(event.getDamager() instanceof Player))
			return;
		Player p = (Player) event.getEntity();
		Player d = (Player) event.getDamager();
		if (!(d instanceof Player))
			return;
		if (!hasAbility(d)) {
			return;
		}

		if (Main.est != Estagio.JOGO) {
			return;
		}
		Random r = new Random();
		if (p instanceof Player) {
			if (r.nextInt(3) == 0) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 5 * 20, 0));
				d.setFoodLevel(d.getFoodLevel() + 1);
			}
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public Kit getKit() {
		ArrayList<ItemStack> kititems = new ArrayList<>();
		kititems.add(new ItemStack(Material.RAW_FISH, 2));
		kititems.add(new ItemStack(Material.getMaterial(383), (short) 1, (short) 98));
		return new Kit("cannibal", Arrays.asList(new String[] { "Roube a comida de seus inimigos e deixe eles com fome" }), kititems, new ItemStack(Material.getMaterial(383), (short) 1, (short) 98), 15000);
	}
}