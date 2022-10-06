package me.ghost.Habilidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import me.ghost.Main;
import me.ghost.Constructor.Kit;
import me.ghost.Enums.Estagio;
import me.ghost.Manager.CooldownManager;
import me.ghost.Manager.KitInterface;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class Thermo extends KitInterface {
	static HashSet<Integer> list = new HashSet<>();
	static HashSet<Integer> list2 = new HashSet<>();

	@SuppressWarnings("deprecation")
	public Thermo(Main main) {
		super(main);
		list.add(Integer.valueOf(Material.WATER.getId()));
		list.add(Integer.valueOf(Material.STATIONARY_WATER.getId()));
		list.add(Integer.valueOf(Material.WATER_LILY.getId()));

		list2.add(Integer.valueOf(Material.LAVA.getId()));
		list2.add(Integer.valueOf(Material.STATIONARY_LAVA.getId()));
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBreak123(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if (hasAbility(player)) {
			if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (player.getItemInHand().hasItemMeta() && player.getItemInHand().getItemMeta().getDisplayName().contains("Transformador")) {
					e.setCancelled(true);
					if (Main.est != Estagio.JOGO) {
						player.sendMessage(ChatColor.RED + "Voce só pode usar o Thermo, quando a invencibilidade acabar.");
						return;
					}
					if (CooldownManager.isInCooldown(player.getUniqueId(), "thermo")) {
						int falta = CooldownManager.getTimeLeft(player.getUniqueId(), "thermo");
						player.sendMessage(ChatColor.RED + "Thermo em cooldown, faltando: " + falta + " segundos");
						return;
					}
					Location local = player.getTargetBlock(null, 100).getLocation();
					if (local.getBlock().getType() == Material.AIR)
						return;
					if (local.distance(player.getLocation()) > 5) {
						return;
					}

					Block b = local.getBlock();
					if (list.contains(Integer.valueOf(b.getTypeId()))) {
						final List<Block> blocks = Con.getConnectedBlocks(b);
						if (blocks.size() > 200) {
							player.sendMessage(ChatColor.RED + "Voce nao pode mudar mais que 200 blocos.");
							return;
						}
						for (int i = 0; i < blocks.size(); i++) {
							final int i2 = i;
							((Block) blocks.get(i)).setType(Material.AIR);
							if (!CooldownManager.isInCooldown(player.getUniqueId(), "thermo")) {
								CooldownManager c = new CooldownManager(player.getUniqueId(), "thermo", 5);
								c.start();
							}
							new BukkitRunnable() {

								@Override
								public void run() {
									((Block) blocks.get(i2)).setType(Material.LAVA);

								}
							}.runTaskLater(Main.plugin, 1);
						}
					}
					if (list2.contains(Integer.valueOf(b.getTypeId()))) {
						final List<Block> blocks = Con2.getConnectedBlocks(b);
						if (blocks.size() > 200) {
							player.sendMessage(ChatColor.RED + "Voce nao pode mudar mais que 200 blocos.");
							return;
						}
						for (int i = 0; i < blocks.size(); i++) {
							final int i2 = i;
							((Block) blocks.get(i)).setType(Material.AIR);
							if (!CooldownManager.isInCooldown(player.getUniqueId(), "thermo")) {
								CooldownManager c = new CooldownManager(player.getUniqueId(), "thermo", 5);
								c.start();
							}
							new BukkitRunnable() {

								@Override
								public void run() {
									((Block) blocks.get(i2)).setType(Material.WATER);

								}
							}.runTaskLater(Main.plugin, 1);
						}
					}
				}
			}
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

	public static class Con2 {
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
						if ((list2.contains(Integer.valueOf(((Block) unchecked.get(0)).getRelative(bf).getTypeId()))) && (!isChecked(((Block) unchecked.get(0)).getRelative(bf)))) {
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

	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		ItemStack item = e.getItemDrop().getItemStack();
		Player p = e.getPlayer();
		if ((item.getType() == Material.DAYLIGHT_DETECTOR) && (item.hasItemMeta()) && (item.getItemMeta().getDisplayName().contains("Transformador")) && hasAbility(p)) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.RED + "Você não pode dropar esse item");
			p.updateInventory();
		}
	}

	@Override
	public Kit getKit() {
		ArrayList<ItemStack> kititems = new ArrayList<>();
		ItemStack blinkitem = new ItemStack(Material.DAYLIGHT_DETECTOR);
		ItemMeta blink = blinkitem.getItemMeta();
		blink.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Transformador");
		blinkitem.setItemMeta(blink);
		kititems.add(blinkitem);
		return new Kit("thermo", Arrays.asList(new String[] { "Tranforme água em lava e lava em água" }), kititems, new ItemStack(Material.DAYLIGHT_DETECTOR), 20000);
	}
}
