package me.ghost.Constructor;

import java.util.List;

import org.bukkit.inventory.ItemStack;

public class Kit {
	public String name;
	public List<ItemStack> items;
	public List<String> kitInfo;
	public ItemStack icon;
	public Integer custo;

	public Kit(String kitname, List<String> kitInfo, List<ItemStack> kititems, ItemStack icon, Integer custo) {
		name = kitname.toLowerCase();
		this.kitInfo = kitInfo;
		items = kititems;
		this.icon = icon;
		this.custo = custo;
	}
}
