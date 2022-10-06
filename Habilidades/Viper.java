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

public class Viper extends KitInterface {

	public Viper(Main main) {
		super(main);

	}

	@EventHandler
	public void onViper(EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof Player))
			return;
		if (!(event.getDamager() instanceof Player))
			return;
		Player p = (Player) event.getEntity();
		Player viper = (Player) event.getDamager();
		if (!hasAbility(viper)) {
			return;
		}
		if (Main.est != Estagio.JOGO) {
			return;
		}
		Random r = new Random();
		if (p instanceof Player) {
			if (r.nextInt(3) == 0) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 5 * 20, 0));
			}
		}
	}

	@Override
	public Kit getKit() {
		ArrayList<ItemStack> kititems = new ArrayList<>();
		return new Kit("viper", Arrays.asList(new String[] { "Use seu ataque venenoso e de um dano adicional em seus inimigos" }), kititems, new ItemStack(Material.SPIDER_EYE), 20000);
	}
}