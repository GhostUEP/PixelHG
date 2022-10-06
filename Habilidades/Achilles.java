package me.ghost.Habilidades;

import java.util.ArrayList;
import java.util.Arrays;

import me.ghost.Main;
import me.ghost.Constructor.Kit;
import me.ghost.Manager.KitInterface;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class Achilles extends KitInterface {

	public Achilles(Main main) {
		super(main);

	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDamageAchilles(EntityDamageByEntityEvent e) {
		if (((e.getEntity() instanceof Player))) {
			if (e.getDamager() instanceof Player) {
				Player damager = (Player) e.getDamager();
				Player p = (Player) e.getEntity();
				if (hasAbility(p)) {
					if (damager.getItemInHand().getType() == Material.GOLD_SWORD) {
						e.setDamage(2);
						damager.sendMessage(ChatColor.RED + p.getName() + " É Kit Achilles, itens de madeira matam ele mais rápido");
					} else if (damager.getItemInHand().getType() == Material.STONE_SWORD) {
						e.setDamage(2);
						damager.sendMessage(ChatColor.RED + p.getName() + " É Kit Achilles, itens de madeira matam ele mais rápido");
					} else if (damager.getItemInHand().getType() == Material.IRON_SWORD) {
						e.setDamage(2);
						damager.sendMessage(ChatColor.RED + p.getName() + " É Kit Achilles, itens de madeira matam ele mais rápido");
					} else if (damager.getItemInHand().getType() == Material.DIAMOND_SWORD) {
						e.setDamage(2);
						damager.sendMessage(ChatColor.RED + p.getName() + " É Kit Achilles, itens de madeira matam ele mais rápido");
					} else if (damager.getItemInHand().getType().name().contains("WOOD") || damager.getItemInHand().getType() == Material.STICK) {
						e.setDamage(6);
						damager.getItemInHand().setDurability((short) 0);
					}
				}
			}
		}
	}

	@Override
	public Kit getKit() {
		return new Kit("achilles", Arrays.asList(new String[] { "Tome menos dano para qualquer ataque, porém, sua fraquese é madeira" }), new ArrayList<ItemStack>(), new ItemStack(Material.WOOD_SWORD), 7000);
	}
}
