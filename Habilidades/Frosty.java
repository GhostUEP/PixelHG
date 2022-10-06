package me.ghost.Habilidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import me.ghost.Main;
import me.ghost.Constructor.Kit;
import me.ghost.Eventos.TimeSecondEvent;
import me.ghost.Manager.CooldownManager;
import me.ghost.Manager.KitInterface;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Frosty extends KitInterface {
	static HashSet<Integer> list = new HashSet<>();

	@SuppressWarnings("deprecation")
	public Frosty(Main main) {
		super(main);
		list.add(Integer.valueOf(Material.WATER.getId()));
		list.add(Integer.valueOf(Material.STATIONARY_WATER.getId()));
		list.add(Integer.valueOf(Material.WATER_LILY.getId()));

	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void ob(ProjectileHitEvent e) {
		if (!(e.getEntity() instanceof Snowball))
			return;
		Player p = (Player) e.getEntity().getShooter();
		if (!hasAbility(p))
			return;
		Location l = e.getEntity().getLocation();
		if (l.getBlock() == null)
			return;
		Block b = l.getBlock();
		if (list.contains(Integer.valueOf(b.getTypeId()))) {
			if (CooldownManager.isInCooldown(p.getUniqueId(), "frosty")) {
				int falta = CooldownManager.getTimeLeft(p.getUniqueId(), "frosty");
				p.sendMessage(ChatColor.RED + "Frosty em cooldown, faltando: " + falta + " segundos");
				return;
			}
			final List<Block> blocks = Con.getConnectedBlocks(b);
			if (blocks.size() > 200) {
				p.sendMessage(ChatColor.RED + "Voce nao pode mudar mais de 200 blocos.");
				return;
			}
			for (int i = 0; i < blocks.size(); i++) {
				final int i2 = i;
				((Block) blocks.get(i)).setType(Material.AIR);
				if (!CooldownManager.isInCooldown(p.getUniqueId(), "frosty")) {
					CooldownManager c = new CooldownManager(p.getUniqueId(), "frosty", 5);
					c.start();
				}
				new BukkitRunnable() {

					@Override
					public void run() {
						((Block) blocks.get(i2)).setType(Material.ICE);

					}
				}.runTaskLater(Main.plugin, 1);
			}
		} else {
			b.setType(Material.getMaterial(78));
		}
	}

	@EventHandler
	public void onquebrar(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		if (!hasAbility(p))
			return;
		if (b.getType() == Material.SNOW_BLOCK) {
			if (!(p.getItemInHand().getType().toString().contains("SPADE"))) {
				e.setCancelled(true);
				b.setType(Material.AIR);
				p.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.SNOW_BALL, 4));
			} else {
				e.setCancelled(true);
				b.setType(Material.AIR);
				p.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.SNOW_BALL, 8));
			}
		}
		if (b.getType() == Material.SNOW) {
			if (!(p.getItemInHand().getType().toString().contains("SPADE"))) {
				e.setCancelled(true);
				b.setType(Material.AIR);
				p.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.SNOW_BALL, 1));
			} else {
				e.setCancelled(true);
				b.setType(Material.AIR);
				p.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.SNOW_BALL, 2));
			}
		}
	}

	@EventHandler
	public void onestar(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (hasAbility(p)) {
			Block b = p.getLocation().getBlock();
			if (b.getType() == Material.SNOW) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 3, 1));
			} else if (b.getRelative(BlockFace.UP).getType() == Material.SNOW) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 3, 1));
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onestar2(TimeSecondEvent e) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (hasAbility(p)) {
				Block b = p.getLocation().getBlock();
				if (b.getType() == Material.SNOW) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 3, 1));
				} else if (b.getRelative(BlockFace.UP).getType() == Material.SNOW) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 3, 1));
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

	@Override
	public Kit getKit() {
		ArrayList<ItemStack> kititems = new ArrayList<>();
		kititems.add(new ItemStack(Material.SNOW_BALL, 4));
		return new Kit("frosty", Arrays.asList(new String[] { "Corra mais rápido na neve, e congele lagos" }), kititems, new ItemStack(Material.ICE), 10000);
	}
}
