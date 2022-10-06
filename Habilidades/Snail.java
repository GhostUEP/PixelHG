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

public class Snail extends KitInterface {

	public Snail(Main main) {
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
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5 * 20, 0));
			}
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public Kit getKit() {
		ArrayList<ItemStack> kititems = new ArrayList<>();
		return new Kit("snail", Arrays.asList(new String[] { "Use seu veneno de lentidão para combar melhor seus inimigos, e mata-los" }), kititems, new ItemStack(Material.getMaterial(373), (short) 1, (short) 8234), 20000);
	}
}
