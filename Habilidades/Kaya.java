package me.ghost.Habilidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

import me.ghost.Main;
import me.ghost.Constructor.Kit;
import me.ghost.Manager.KitInterface;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class Kaya extends KitInterface {
	private transient HashMap<Block, UUID> kayaBlocks = new HashMap<>();

	public Kaya(Main main) {

		super(main);
		ShapelessRecipe recipe = new ShapelessRecipe(new ItemStack(Material.GRASS));
		recipe.addIngredient(Material.DIRT);
		recipe.addIngredient(Material.SEEDS);
		Bukkit.addRecipe(recipe);

	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onCraft(PrepareItemCraftEvent event) {
		if (event.getRecipe().getResult() != null && event.getRecipe().getResult().getType() == Material.GRASS) {
			for (HumanEntity entity : event.getViewers())
				if (hasAbility((Player) entity))
					return;
			event.getInventory().setItem(0, new ItemStack(0, 0));
		}
	}

	@EventHandler
	public void onColocar(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		if (!hasAbility(p))
			return;
		if (b.getType() != Material.GRASS)
			return;
		kayaBlocks.put(b, p.getUniqueId());
	}

	@EventHandler
	public void onQuebrar(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		if (kayaBlocks.containsKey(b)) {
			e.setCancelled(true);
			kayaBlocks.remove(b);
			b.setType(Material.AIR);
			p.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.GRASS));
		}
	}

	@EventHandler
	public void onMover(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		Block b = e.getTo().getBlock().getRelative(BlockFace.DOWN);
		if (kayaBlocks.containsKey(b)) {
			if (Main.plugin.NaoEstaJogando(p))
				return;
			if (kayaBlocks.get(b) != p.getUniqueId()) {
				kayaBlocks.remove(b);
				b.setType(Material.AIR);
				Location l = b.getLocation();
				Block b1 = l.add(1, 0, 0).getBlock();
				Block b2 = l.add(0, 0, 1).getBlock();
				Block b3 = l.subtract(1, 0, 0).getBlock();
				Block b4 = l.subtract(0, 0, 1).getBlock();
				Block b5 = l.add(1, 0, 1).getBlock();
				Block b6 = l.subtract(1, 0, 1).getBlock();
				Block b7 = l.add(1, 0, -1).getBlock();
				Block b8 = l.add(-1, 0, 1).getBlock();
				if (kayaBlocks.containsKey(b1)) {
					kayaBlocks.remove(b1);
					b1.setType(Material.AIR);
				}
				if (kayaBlocks.containsKey(b2)) {
					kayaBlocks.remove(b2);
					b2.setType(Material.AIR);
				}
				if (kayaBlocks.containsKey(b3)) {
					kayaBlocks.remove(b3);
					b3.setType(Material.AIR);
				}
				if (kayaBlocks.containsKey(b4)) {
					kayaBlocks.remove(b4);
					b4.setType(Material.AIR);
				}
				if (kayaBlocks.containsKey(b5)) {
					kayaBlocks.remove(b5);
					b5.setType(Material.AIR);
				}
				if (kayaBlocks.containsKey(b6)) {
					kayaBlocks.remove(b6);
					b6.setType(Material.AIR);
				}
				if (kayaBlocks.containsKey(b7)) {
					kayaBlocks.remove(b7);
					b7.setType(Material.AIR);
				}
				if (kayaBlocks.containsKey(b8)) {
					kayaBlocks.remove(b8);
					b8.setType(Material.AIR);
				}
			}
		}
	}

	@Override
	public Kit getKit() {
		ArrayList<ItemStack> kititems = new ArrayList<>();
		kititems.add(new ItemStack(Material.GRASS, 10));
		return new Kit("kaya", Arrays.asList(new String[] { "Use sua grama fake para fazer armadilhas" }), kititems, new ItemStack(Material.GRASS), 10000);
	}

}