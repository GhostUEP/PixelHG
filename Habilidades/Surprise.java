package me.ghost.Habilidades;

import java.util.ArrayList;
import java.util.Arrays;

import me.ghost.Main;
import me.ghost.Constructor.Kit;
import me.ghost.Eventos.GameStartEvent;
import me.ghost.Manager.KitInterface;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;

public class Surprise extends KitInterface {

	public Surprise(Main main) {
		super(main);

	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onSurprise(GameStartEvent e) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (hasAbility(p)) {
				Main.plugin.kit.setSurprise(p);
			}
		}
	}

	@Override
	public Kit getKit() {
		ArrayList<ItemStack> kititems = new ArrayList<>();
		return new Kit("surprise", Arrays.asList(new String[] { "Pegue um kit aleatório do servidor" }), kititems, new ItemStack(Material.NAME_TAG), 16000);
	}
}
