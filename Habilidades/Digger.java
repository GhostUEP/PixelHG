package me.ghost.Habilidades;

import java.util.ArrayList;
import java.util.Arrays;

import me.ghost.Main;
import me.ghost.Constructor.Kit;
import me.ghost.Manager.KitInterface;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Digger extends KitInterface {

	public Digger(Main main) {
		super(main);

	}

	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		ItemStack item = event.getItemInHand();
		Player p = event.getPlayer();
		if (!hasAbility(p))
			return;
		if (Gladiator.gladiatorBlocks.contains(event.getBlockAgainst()))
			return;
		if (Gladiator.gladiatorBlocks.contains(event.getBlockPlaced()))
			return;
		if (item.getType() == Material.DRAGON_EGG) {
			final Block b = event.getBlock();
			b.setType(Material.AIR);
			p.sendMessage(ChatColor.GREEN + "Ovo pronto!!");
			new BukkitRunnable() {
				@Override
				public void run() {
					int dist = (int) Math.ceil((double) (5 - 1) / 2);
					for (int y = -1; y >= -5; y--) {
						for (int x = -dist; x <= dist; x++) {
							for (int z = -dist; z <= dist; z++) {
								if (b.getY() + y <= 0)
									continue;
								Block block = b.getWorld().getBlockAt(b.getX() + x, b.getY() + y, b.getZ() + z);
								if (block.getType() != Material.BEDROCK)
									block.setType(Material.AIR);
							}
						}
					}
				}

			}.runTaskLater(Main.plugin, 30L);
		}
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		ItemStack item = e.getItemDrop().getItemStack();
		Player p = e.getPlayer();
		if ((item.getType() == Material.DRAGON_EGG) && hasAbility(p)) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.RED + "Você não pode dropar esse item");
			p.updateInventory();
		}
	}

	@Override
	public Kit getKit() {
		ArrayList<ItemStack> kititems = new ArrayList<>();
		kititems.add(createItem(Material.DRAGON_EGG, 10, ChatColor.RED + "Digger"));
		return new Kit("digger", Arrays.asList(new String[] { "Use seus ovos para cavar buracos gigantes" }), kititems, new ItemStack(Material.DRAGON_EGG), 7000);
	}

}