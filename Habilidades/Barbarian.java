package me.ghost.Habilidades;

import java.util.ArrayList;
import java.util.Arrays;

import me.ghost.Main;
import me.ghost.Constructor.Kit;
import me.ghost.Manager.KitInterface;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Barbarian extends KitInterface {

	public Barbarian(Main main) {
		super(main);

	}

	@EventHandler
	public void asd(PlayerItemDamageEvent e) {
		Player p = e.getPlayer();
		if (!hasAbility(p))
			return;
		ItemStack item = e.getItem();
		if (!(item.getType() == Material.WOOD_SWORD || item.getType() == Material.STONE_SWORD || item.getType() == Material.DIAMOND_SWORD || item.getType() == Material.IRON_SWORD))
			return;
		if (!(item.hasItemMeta()))
			return;
		if (item.getItemMeta().getDisplayName().contains("Barbarian")) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onKill(PlayerDeathEvent e) {
		if (!(e.getEntity().getKiller() instanceof Player))
			return;
		if (!(e.getEntity() instanceof Player))
			return;
		Player p = (Player) e.getEntity().getKiller();
		if (!hasAbility(p))
			return;
		Integer durabilidade = 0;
		Integer durabilidade2 = 0;
		for (ItemStack item : p.getInventory().getContents()) {
			if (!(item.hasItemMeta()))
				return;
			if (!(item.getType() == Material.WOOD_SWORD || item.getType() == Material.STONE_SWORD || item.getType() == Material.DIAMOND_SWORD || item.getType() == Material.IRON_SWORD))
				return;
			if (item.getType() == Material.WOOD_SWORD) {
				durabilidade = 20;
			}
			if (item.getType() == Material.STONE_SWORD) {
				durabilidade = 43;

			}
			if (item.getType() == Material.IRON_SWORD) {
				durabilidade = 62;

			}
			if (item.getType() == Material.DIAMOND_SWORD) {
				durabilidade = 520;

			}
			durabilidade2 = durabilidade;
			if (item.getItemMeta().getDisplayName().contains("Barbarian")) {
				if (item.getType() == Material.WOOD_SWORD) {
					if (item.getDurability() < durabilidade) {
						durabilidade2 = durabilidade2 - item.getDurability();
						item.setType(Material.STONE_SWORD);
						item.setDurability((short) (130 - durabilidade2));
					} else {
						item.setDurability((short) ((short) item.getDurability() - durabilidade));
					}
				} else if (item.getType() == Material.STONE_SWORD) {
					if (item.getDurability() <= durabilidade) {
						durabilidade2 = durabilidade2 - item.getDurability();
						item.setType(Material.IRON_SWORD);
						item.setDurability((short) (249 - durabilidade2));
					} else {
						item.setDurability((short) ((short) item.getDurability() - durabilidade));
					}
				} else if (item.getType() == Material.IRON_SWORD) {
					if (item.getDurability() <= durabilidade) {
						durabilidade2 = durabilidade2 - item.getDurability();
						item.setType(Material.DIAMOND_SWORD);
						item.setDurability((short) (1560 - durabilidade2));
					} else {
						item.setDurability((short) ((short) item.getDurability() - durabilidade));
					}
				} else if (item.getType() == Material.DIAMOND_SWORD) {
					if (item.getDurability() <= durabilidade) {
						item.addEnchantment(Enchantment.DAMAGE_ALL, 1);
						item.setDurability((short) 0);
					} else {
						item.setDurability((short) ((short) item.getDurability() - durabilidade));
					}
				}
			}

		}
	}

	@EventHandler
	public void onXp(PlayerExpChangeEvent e) {
		Player p = e.getPlayer();
		int xp = e.getAmount();
		Integer durabilidade = 0;
		Integer durabilidade2 = 0;
		durabilidade = xp;
		if (!hasAbility(p))
			return;
		for (ItemStack item : p.getInventory().getContents()) {
			if (!(item.getType() == Material.WOOD_SWORD || item.getType() == Material.STONE_SWORD || item.getType() == Material.DIAMOND_SWORD || item.getType() == Material.IRON_SWORD))
				return;
			if (!(item.hasItemMeta()))
				return;
			if (item.getType() == Material.WOOD_SWORD) {
				durabilidade = xp + 3;
			}
			if (item.getType() == Material.STONE_SWORD) {
				durabilidade = xp + 6;

			}
			if (item.getType() == Material.IRON_SWORD) {
				durabilidade = xp + 12;

			}
			if (item.getType() == Material.DIAMOND_SWORD) {
				durabilidade = xp + 24;

			}
			durabilidade2 = durabilidade;
			if (item.getItemMeta().getDisplayName().contains("Barbarian")) {
				if (item.getType() == Material.WOOD_SWORD) {
					if (item.getDurability() < durabilidade) {
						durabilidade2 = durabilidade2 - item.getDurability();
						item.setType(Material.STONE_SWORD);
						item.setDurability((short) (130 - durabilidade2));
					} else {
						item.setDurability((short) ((short) item.getDurability() - durabilidade));
					}
				} else if (item.getType() == Material.STONE_SWORD) {
					if (item.getDurability() <= durabilidade) {
						durabilidade2 = durabilidade2 - item.getDurability();
						item.setType(Material.IRON_SWORD);
						item.setDurability((short) (249 - durabilidade2));
					} else {
						item.setDurability((short) ((short) item.getDurability() - durabilidade));
					}
				} else if (item.getType() == Material.IRON_SWORD) {
					if (item.getDurability() <= durabilidade) {
						durabilidade2 = durabilidade2 - item.getDurability();
						item.setType(Material.DIAMOND_SWORD);
						item.setDurability((short) (1560 - durabilidade2));
					} else {
						item.setDurability((short) ((short) item.getDurability() - durabilidade));
					}
				} else if (item.getType() == Material.DIAMOND_SWORD) {
					if (item.getDurability() <= durabilidade) {
						item.addEnchantment(Enchantment.DAMAGE_ALL, 1);
						item.setDurability((short) 0);
					} else {
						item.setDurability((short) ((short) item.getDurability() - durabilidade));
					}
				}
			}
		}
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		ItemStack item = e.getItemDrop().getItemStack();
		Player p = e.getPlayer();
		if (hasAbility(p)) {
			if (!(item.getType() == Material.WOOD_SWORD || item.getType() == Material.STONE_SWORD || item.getType() == Material.DIAMOND_SWORD || item.getType() == Material.IRON_SWORD))
				return;
			if (!(item.hasItemMeta()))
				return;
			if (item.getItemMeta().getDisplayName().contains("Barbarian")) {
				p.sendMessage(ChatColor.RED + "Você não pode dropar esse item");
				e.setCancelled(true);
				p.updateInventory();
			}
		}
	}

	@Override
	public Kit getKit() {
		ArrayList<ItemStack> kititems = new ArrayList<>();
		ItemStack kangarooitem = new ItemStack(Material.WOOD_SWORD);
		kangarooitem.addEnchantment(Enchantment.DURABILITY, 3);
		ItemMeta kangaroo = kangarooitem.getItemMeta();
		kangaroo.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Barbarian");
		kangarooitem.setItemMeta(kangaroo);
		kangarooitem.setDurability((short) 58);
		kititems.add(kangarooitem);
		ItemStack barbarian = new ItemStack(Material.WOOD_SWORD);
		barbarian.addEnchantment(Enchantment.DURABILITY, 3);
		barbarian.setDurability((short) 58);
		return new Kit("barbarian", Arrays.asList(new String[] { "Evolua sua espada matando inimigos ou pegando xp" }), kititems, barbarian, 15000);
	}
}