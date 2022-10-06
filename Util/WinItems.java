package me.ghost.Util;

import java.util.ArrayList;

import me.ghost.Manager.PlayerManager;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class WinItems {
	public WinItems() {
		ArrayList<ItemStack> items = new ArrayList<>();
		for (int i = 0; i <= 1; i++) {
			items.add(new ItemStack(Material.DIAMOND));
		}
		for (int i = 0; i <= 1; i++) {
			items.add(new ItemStack(Material.DIAMOND));
		}
		for (int i = 0; i <= 1; i++) {
			items.add(new ItemStack(Material.DIAMOND));
		}
		for (int i = 0; i <= 1; i++) {
			items.add(new ItemStack(Material.DIAMOND));
		}
		for (int i = 0; i <= 1; i++) {
			items.add(new ItemStack(Material.DIAMOND));
		}
		
		for (int i = 0; i <= 1; i++) {
			items.add(new ItemStack(Material.EXP_BOTTLE));
		}
		for (int i = 0; i <= 1; i++) {
			items.add(new ItemStack(Material.EXP_BOTTLE));
		}
		for (int i = 0; i <= 1; i++) {
			items.add(new ItemStack(Material.EXP_BOTTLE));
		}
		for (int i = 0; i <= 1; i++) {
			items.add(new ItemStack(Material.EXP_BOTTLE));
		}
		for (int i = 0; i <= 1; i++) {
			items.add(new ItemStack(Material.EXP_BOTTLE));
		}
		for (int i = 0; i <= 1; i++) {
			items.add(new ItemStack(Material.WATER_BUCKET));
		}
		for (int i = 0; i <= 1; i++) {
			items.add(new ItemStack(Material.WATER_BUCKET));
		}
		for (int i = 0; i <= 1; i++) {
			items.add(new ItemStack(Material.WATER_BUCKET));
		}
		for (int i = 0; i <= 1; i++) {
			items.add(new ItemStack(Material.WATER_BUCKET));
		}
		for (int i = 0; i <= 1; i++) {
			items.add(new ItemStack(Material.WATER_BUCKET));
		}
		
		PlayerManager.winItems = items;
	}
}
