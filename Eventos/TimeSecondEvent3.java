package me.ghost.Eventos;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TimeSecondEvent3 extends Event {
	public static final HandlerList handlers = new HandlerList();

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
