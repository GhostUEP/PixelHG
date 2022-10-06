package me.ghost.Habilidades;

import java.util.ArrayList;
import java.util.Arrays;

import me.ghost.Main;
import me.ghost.Constructor.Kit;
import me.ghost.Manager.KitInterface;

import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class Cultivator extends KitInterface {

	public Cultivator(Main main) {
		super(main);

	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onCultiavator(BlockPlaceEvent event) {
		Player p = event.getPlayer();
		if (!hasAbility(p)) {
			return;
		}
		if (event.getBlock().getType() == Material.SAPLING) {
			event.getBlock().setType(Material.AIR);
			boolean arvore;
			arvore = event.getBlock().getWorld().generateTree(event.getBlock().getLocation(), TreeType.TREE);
			if (!arvore)
				event.getBlock().setTypeIdAndData(Material.SAPLING.getId(), (byte) event.getBlock().getData(), false);
		} else if (event.getBlock().getType() == Material.CROPS)
			event.getBlock().setData((byte) 7);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Kit getKit() {
		return new Kit("cultivator", Arrays.asList(new String[] { "Cultive tudo mais rápido, e ajude seu time com isso" }), new ArrayList<ItemStack>(), new ItemStack(Material.getMaterial(6)), 5000);
	}
}