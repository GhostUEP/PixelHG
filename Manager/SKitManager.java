package me.ghost.Manager;

import java.util.Collection;
import java.util.HashMap;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class SKitManager {
	public static HashMap<String, SKitManager> kits = new HashMap<>();
	private ItemStack[] armor;
	private ItemStack[] inv;
	private Collection<PotionEffect> effects;

	public SKitManager(Player p) {
		this.inv = p.getInventory().getContents();
		this.armor = p.getInventory().getArmorContents();
		this.effects = p.getActivePotionEffects();
	}

	public static void addKit(Player sender, String s, SKitManager sk) {
		if (kits.containsKey(s)) {
			sender.sendMessage(ChatColor.RED + "O kit: [" + s + "] já foi criado");
			return;
		}
		kits.put(s, sk);
		sender.sendMessage(ChatColor.GREEN + "Kit: [" + s + "] criado");
	}

	public static void removeKit(Player sender, String s) {
		if (!kits.containsKey(s)) {
			sender.sendMessage(ChatColor.RED + "O kit: [" + s + "] não foi encontrado");
			return;
		}
		kits.remove(s);
		sender.sendMessage(ChatColor.GREEN + "Kit: [" + s + "] removido");
	}

	@SuppressWarnings("deprecation")
	public static void applyKit(Player sender, String s, Player target) {
		if (!kits.containsKey(s)) {
			sender.sendMessage(ChatColor.RED + "O kit: [" + s + "] não foi encontrado");
			return;
		}
		SKitManager sk = kits.get(s);
		if (target == null) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p != sender) {
					sender.sendMessage(ChatColor.GREEN + "Kit: [" + s + "] aplicado");
				}
				p.getInventory().setArmorContents(sk.getArmor());
				p.getInventory().setContents(sk.getInventory());
				for (PotionEffect effect : sk.getEffects()) {
					p.addPotionEffect(effect);
				}
			}
			sender.sendMessage(ChatColor.GREEN + "Kit: [" + s + "] aplicado em todos os players");
			return;
		}
		target.getInventory().setArmorContents(sk.getArmor());
		target.getInventory().setContents(sk.getInventory());
		for (PotionEffect effect : sk.getEffects()) {
			target.addPotionEffect(effect);
		}
		sender.sendMessage(ChatColor.GREEN + "Kit: [" + s + "] aplicado em: " + target.getName());

	}

	public ItemStack[] getInventory() {
		return this.inv;
	}

	public ItemStack[] getArmor() {
		return this.armor;
	}

	public Collection<PotionEffect> getEffects() {
		return this.effects;
	}
}
