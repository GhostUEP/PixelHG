package me.ghost.Listener;

import me.ghost.Main;
import me.ghost.Enums.Estagio;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class VitoriaListener implements Listener {
	@EventHandler
	public void Dano(EntityDamageEvent e) {
		if (Main.est == Estagio.VITORIA) {
			if (e.getEntity() instanceof Player) {
				e.setCancelled(true);
			}
		}

	}

}
