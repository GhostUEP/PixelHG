package me.ghost.Habilidades;

import java.util.ArrayList;
import java.util.Arrays;

import me.ghost.Main;
import me.ghost.Constructor.Kit;
import me.ghost.Manager.KitInterface;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class Lumberjack extends KitInterface {

	public Lumberjack(Main main) {
		super(main);

	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		if (!hasAbility(p)) {
			return;
		}
		if (b.getType() == Material.LOG || b.getType() == Material.LOG_2) {
			World w = Bukkit.getServer().getWorlds().get(0);
			Double d = Double.valueOf(b.getLocation().getY() + 1.0D);
			Location l = new Location(w, b.getLocation().getX(), d.doubleValue(), b.getLocation().getZ());
			while (l.getBlock().getType() == Material.LOG || l.getBlock().getType() == Material.LOG_2) {
				l.getBlock().breakNaturally();
				d = Double.valueOf(d.doubleValue() + 1.0D);
				l.setY(d.doubleValue());
			}
		}
	}

	@Override
	public Kit getKit() {
		ArrayList<ItemStack> kititems = new ArrayList<>();
		kititems.add(new ItemStack(Material.WOOD_AXE));
		return new Kit("lumberjack", Arrays.asList(new String[] { "Pegue madeiras mais f?cil com seu machado de lenhador" }), kititems, new ItemStack(Material.WOOD_AXE), 5000);
	}
}