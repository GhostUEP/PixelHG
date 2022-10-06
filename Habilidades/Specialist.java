package me.ghost.Habilidades;

import java.util.ArrayList;
import java.util.Arrays;

import me.ghost.Main;
import me.ghost.Constructor.Kit;
import me.ghost.Enums.Estagio;
import me.ghost.Manager.KitInterface;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Specialist extends KitInterface {

	public Specialist(Main main) {
		super(main);
	}

	@EventHandler
	private void onPlayerEdnaldo(PlayerInteractEvent event) {
		final Player p = event.getPlayer();
		Action a = event.getAction();
		final ItemStack item = event.getItem();
		if ((a == Action.RIGHT_CLICK_BLOCK || a == Action.RIGHT_CLICK_AIR) && hasAbility(p)) {
			
			if (item != null && item.getType() == Material.BOOK) {
				if (Main.est == Estagio.PREJOGO)
					return;
				event.setCancelled(true);
				p.openEnchanting(null, true);
			}
		}
	}

	@EventHandler
	public void matar(PlayerDeathEvent event) {
		Player p = event.getEntity().getKiller();
		if (p == null)
			return;
		if (hasAbility(p)) {
			p.setLevel(p.getLevel() + 1);
		}
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		ItemStack item = e.getItemDrop().getItemStack();
		Player p = e.getPlayer();
		if ((item.getType() == Material.BOOK) && (item.hasItemMeta()) && (item.getItemMeta().getDisplayName().contains("Encante")) && hasAbility(p)) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.RED + "Você não pode dropar esse item");
			p.updateInventory();
		}
	}

	@Override
	public Kit getKit() {
		ArrayList<ItemStack> kititems = new ArrayList<>();
		ItemStack spectime = new ItemStack(Material.BOOK);
		ItemMeta spect = spectime.getItemMeta();
		spect.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Encante");
		spectime.setItemMeta(spect);
		kititems.add(spectime);
		return new Kit("specialist", Arrays.asList(new String[] { "Encante seus items com seu livro para ficar mais forte" }), kititems, new ItemStack(Material.BOOK), 27000);
	}

}