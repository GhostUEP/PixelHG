package me.ghost.Habilidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import me.ghost.Main;
import me.ghost.Constructor.Kit;
import me.ghost.Manager.KitInterface;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Miner extends KitInterface {
	static HashSet<Integer> list = new HashSet<>();

	@SuppressWarnings("deprecation")
	public Miner(Main main) {
		super(main);
		list.add(Integer.valueOf(Material.IRON_ORE.getId()));
		list.add(Integer.valueOf(Material.GOLD_ORE.getId()));
		list.add(Integer.valueOf(Material.COAL_ORE.getId()));
		list.add(Integer.valueOf(Material.REDSTONE_ORE.getId()));
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMiner(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		if (!hasAbility(p))
			return;
		if (list.contains(Integer.valueOf(b.getTypeId()))) {
			final List<Block> blocks = Con.getConnectedBlocks(b);
			for (int i = 0; i < blocks.size(); i++) {
				((Block) blocks.get(i)).breakNaturally();
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onEat(PlayerItemConsumeEvent event) {
		if (!hasAbility(event.getPlayer()))
			return;
		if (!event.getItem().hasItemMeta())
			return;
		if (!event.getItem().getItemMeta().hasDisplayName())
			return;
		if (event.getPlayer().hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
			event.getPlayer().sendMessage(ChatColor.RED + "Você já comeu uma  maçã");
			event.setCancelled(true);
			return;
		}
		if ((event.getItem().getType() == Material.APPLE && event.getItem().getItemMeta().getDisplayName().contains("Maça"))) {
			event.getItem().setAmount(event.getItem().getAmount() - 1);
			if (event.getItem().getAmount() == 0) {
				event.getPlayer().setItemInHand(new ItemStack(0));
			}
			event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 600, 1));
		}
	}

	public static class Con {
		static List<Block> unchecked = new LinkedList<>();
		static List<Block> checked = new LinkedList<>();
		static List<Block> confirmed = new LinkedList<>();

		@SuppressWarnings("deprecation")
		public static List<Block> getConnectedBlocks(Block block) {
			BlockFace bf = null;

			unchecked.clear();
			checked.clear();
			confirmed.clear();
			unchecked.add(block);
			while (unchecked.size() > 0) {
				if (!isChecked((Block) unchecked.get(0))) {
					for (int i = 0; i < 6; i++) {
						if (i == 0) {
							bf = BlockFace.DOWN;
						} else if (i == 1) {
							bf = BlockFace.EAST;
						} else if (i == 2) {
							bf = BlockFace.NORTH;
						} else if (i == 3) {
							bf = BlockFace.SOUTH;
						} else if (i == 4) {
							bf = BlockFace.UP;
						} else if (i == 5) {
							bf = BlockFace.WEST;
						}
						if ((list.contains(Integer.valueOf(((Block) unchecked.get(0)).getRelative(bf).getTypeId()))) && (!isChecked(((Block) unchecked.get(0)).getRelative(bf)))) {
							unchecked.add(((Block) unchecked.get(0)).getRelative(bf));
						}
					}
					checked.add((Block) unchecked.get(0));
				}
				unchecked.remove(0);
			}
			for (int i = 0; i < checked.size(); i++) {
				if (!((Block) checked.get(i)).getType().equals(Material.AIR)) {
					confirmed.add((Block) checked.get(i));
				}
			}
			return confirmed;
		}

		public static boolean isChecked(Block block) {
			for (int i = 0; i < checked.size(); i++) {
				if (((Block) checked.get(i)).equals(block)) {
					return true;
				}
			}
			return false;
		}
	}

	@Override
	public Kit getKit() {
		ArrayList<ItemStack> kititems = new ArrayList<>();
		ItemStack item = new ItemStack(Material.STONE_PICKAXE);
		item.addEnchantment(Enchantment.DURABILITY, 1);
		item.addEnchantment(Enchantment.DIG_SPEED, 1);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Picareta do Miner");
		item.setItemMeta(im);
		kititems.add(item);
		kititems.add(createItem(Material.APPLE, 5, ChatColor.RED + "Maça"));
		ItemStack miner = new ItemStack(Material.STONE_PICKAXE);
		miner.addEnchantment(Enchantment.DURABILITY, 1);
		miner.addEnchantment(Enchantment.DIG_SPEED, 1);
		return new Kit("miner", Arrays.asList(new String[] { "Use suas habilidades de minerador e minere vários minérios ao mesmo tempo" }), kititems, new ItemStack(miner), 16000);
	}

}
