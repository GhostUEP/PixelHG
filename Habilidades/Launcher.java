package me.ghost.Habilidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import me.ghost.Main;
import me.ghost.Constructor.Kit;
import me.ghost.Manager.KitInterface;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

public class Launcher extends KitInterface {

	public Launcher(Main main) {
		super(main);

	}

	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		if (event.getBlock().hasMetadata("Launcher")) {
			event.getBlock().removeMetadata("Launcher", Main.plugin);
			event.setCancelled(true);
			Item item = event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation().clone().add(0.5, 0, 0.1), new ItemStack(event.getBlock().getType()));
			event.getBlock().setType(Material.AIR);
			ItemStack itemstack = item.getItemStack().clone();
			ItemMeta meta = itemstack.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "Launcher");
			itemstack.setItemMeta(meta);
			item.setItemStack(itemstack);
		}
	}

	@EventHandler
	public void onExplode(EntityExplodeEvent event) {
		Iterator<Block> itel = event.blockList().iterator();
		while (itel.hasNext()) {
			Block block = itel.next();
			if (block.hasMetadata("Launcher")) {
				BlockBreakEvent newEvent = new BlockBreakEvent(block, null);
				onBreak(newEvent);
				itel.remove();
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player p = event.getPlayer();
		if (!Main.plugin.NaoEstaJogando(p)) {
			Block b = event.getTo().getBlock().getRelative(BlockFace.DOWN);
			if (b.hasMetadata("Launcher") && b.getMetadata("Launcher").size() > 0) {
				double strength = 0;
				Block under = b;
				while (under.getType() == b.getType() && under.hasMetadata("Launcher") && under.getData() == b.getData()) {
					under = under.getRelative(BlockFace.DOWN);
					strength++;
				}
				strength /= 2;
				BlockFace face = (BlockFace) b.getMetadata("Launcher").get(0).value();
				double y = (double) face.getModY() + 1.5D * strength;
				if (y == 0)
					y = 1.5D * strength;
				strength *= 1;
				Vector vector = new Vector((double) face.getModX() * strength, y, (double) face.getModZ() * strength);
				if (!(b.getRelative(BlockFace.UP).getType() == Material.STONE_PLATE || b.getRelative(BlockFace.UP).getType() == Material.WOOD_STEP)) {
					p.setFallDistance(-1000);
				}

				p.setVelocity(vector);
			}
		}
	}

	@EventHandler
	public void onPiston(BlockPistonExtendEvent event) {
		for (Block b : event.getBlocks())
			if (b.hasMetadata("Launcher"))
				event.setCancelled(true);
	}

	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		if (!event.getItemInHand().hasItemMeta())
			return;
		if (event.getItemInHand().getItemMeta().getDisplayName().contains("Launcher")) {
			BlockFace face = event.getBlockAgainst().getFace(event.getBlock());
			if (face == BlockFace.DOWN)
				face = BlockFace.UP;
			event.getBlock().setMetadata("Launcher", new FixedMetadataValue(Main.plugin, face));
		}
	}

	@Override
	public Kit getKit() {
		ArrayList<ItemStack> kititems = new ArrayList<>();
		ItemStack kangarooitem = new ItemStack(Material.SPONGE, 16);
		ItemMeta kangaroo = kangarooitem.getItemMeta();
		kangaroo.setDisplayName(ChatColor.GOLD + "Launcher");
		kangarooitem.setItemMeta(kangaroo);
		kititems.add(kangarooitem);
		return new Kit("launcher", Arrays.asList(new String[] { "Pula alto com suas esponjas" }), kititems, new ItemStack(Material.SPONGE), 15000);
	}
}