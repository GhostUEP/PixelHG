package me.ghost.Habilidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import me.ghost.Main;
import me.ghost.Constructor.GladiatorFight;
import me.ghost.Constructor.Kit;
import me.ghost.Enums.Estagio;
import me.ghost.Manager.KitInterface;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Gladiator extends KitInterface {

	public static List<UUID> playersIn1v1;
	public static List<Block> gladiatorBlocks;

	public Gladiator(Main main) {
		super(main);
		playersIn1v1 = new ArrayList<UUID>();
		gladiatorBlocks = new ArrayList<>();
	}

	@EventHandler
	public void onEntityClick(PlayerInteractEntityEvent event) {
		Player p = event.getPlayer();
		Entity e = event.getRightClicked();
		ItemStack i = p.getItemInHand();
		if (!(e instanceof Player))
			return;
		if (!hasAbility(p))
			return;
		if (i.getType() == null)
			return;
		if (i.getType() != Material.IRON_FENCE)
			return;
		if (i.getItemMeta() == null)
			return;
		if (Main.est != Estagio.JOGO) {
			p.sendMessage(ChatColor.RED + "Você não pode usar isso na invencibilidade");
			return;
		}
		if (((Player) e).isDead())
			return;
		if (playersIn1v1.contains(p.getUniqueId()))
			return;
		if (playersIn1v1.contains(((Player) e).getUniqueId()))
			return;
		new GladiatorFight(p, (Player) e);
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		ItemStack i = p.getItemInHand();
		if (event.getAction() != Action.PHYSICAL && hasAbility(p) && i.getType() != null && i.getType() == Material.IRON_FENCE) {
			p.updateInventory();
			event.setCancelled(true);
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlock(BlockDamageEvent event) {
		if (gladiatorBlocks.contains(event.getBlock()) && event.getBlock().getType() == Material.GLASS) {
			final Block b = event.getBlock();
			final Player p = event.getPlayer();
			p.sendBlockChange(b.getLocation(), Material.BEDROCK, (byte) 0);
		}
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		ItemStack item = e.getItemDrop().getItemStack();
		Player p = e.getPlayer();
		if ((item.getType() == Material.IRON_FENCE) && (item.hasItemMeta()) && (item.getItemMeta().getDisplayName().contains("Tire 1v1")) && hasAbility(p)) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.RED + "Você não pode dropar esse item");
			p.updateInventory();
		}
	}

	@EventHandler
	public void onExplode(EntityExplodeEvent event) {
		Iterator<Block> blockIt = event.blockList().iterator();
		while (blockIt.hasNext()) {
			Block b = blockIt.next();
			if (gladiatorBlocks.contains(b)) {
				blockIt.remove();
			}
		}
	}

	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		if (gladiatorBlocks.contains(event.getBlock())) {
			event.setCancelled(true);
		}
	}

	public static boolean inGladiator(Player p) {
		return playersIn1v1.contains(p.getUniqueId());
	}

	@Override
	public Kit getKit() {
		ArrayList<ItemStack> kititems = new ArrayList<>();
		kititems.add(createItem(Material.IRON_FENCE, ChatColor.RED + "" + ChatColor.BOLD + "Tire 1v1"));
		return new Kit("gladiator", Arrays.asList(new String[] { "Puxe seus inimigos para uma arena, e tire 1v1 com eles" }), kititems, new ItemStack(Material.IRON_FENCE), 35000);
	}
}