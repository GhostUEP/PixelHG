package me.ghost.Habilidades;

import java.util.ArrayList;
import java.util.Arrays;

import me.ghost.Main;
import me.ghost.Constructor.Kit;
import me.ghost.Manager.KitInterface;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Demoman extends KitInterface {

	public Demoman(Main main) {
		super(main);

	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Block b = event.getClickedBlock();
		if (event.getAction() == Action.PHYSICAL && b != null && b.hasMetadata("Placer") && b.getType() == Material.STONE_PLATE && b.getRelative(BlockFace.DOWN).getType() == Material.GRAVEL) {
			b.removeMetadata("Placer", Main.plugin);
			b.setType(Material.AIR);
			b.getWorld().createExplosion(b.getLocation().clone().add(0.5, 0.5, 0.5), 4F);
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onBlockPlace(BlockPlaceEvent event) {
		if (event.getBlock().getType() == Material.STONE_PLATE && hasAbility(event.getPlayer())) {
			event.getBlock().removeMetadata("Placer", Main.plugin);
			event.getBlock().setMetadata("Placer", new FixedMetadataValue(Main.plugin, event.getPlayer().getName()));
		}
	}

	@Override
	public Kit getKit() {
		ArrayList<ItemStack> kititems = new ArrayList<>();
		kititems.add(new ItemStack(Material.GRAVEL, 6));
		kititems.add(new ItemStack(Material.STONE_PLATE, 6));
		return new Kit("demoman", Arrays.asList(new String[] { "Use suas armadilhas para explodir seus inimigos" }), kititems, new ItemStack(Material.GRAVEL), 12000);
	}

}