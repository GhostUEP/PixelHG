package me.ghost.Listener;

import me.ghost.Main;
import me.ghost.Enums.Estagio;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class InvencibilidadeListener implements Listener {
	@EventHandler
	public void Dano(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		if (Main.est == Estagio.INVENCIBILIDADE) {
			e.setCancelled(true);
			return;
		}
	}

	@EventHandler
	public void Dano2(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		if (Main.est == Estagio.INVENCIBILIDADE) {
			e.setCancelled(true);
			return;
		}
	}

	@EventHandler
	public void Comida(FoodLevelChangeEvent e) {
		if (Main.est == Estagio.INVENCIBILIDADE) {
			e.setCancelled(true);
			return;
		}
	}
}
