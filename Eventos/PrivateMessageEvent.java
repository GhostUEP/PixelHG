package me.ghost.Eventos;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PrivateMessageEvent extends Event implements Cancellable {
	private static final HandlerList handlers = new HandlerList();
	private boolean cancelled = false;
	private String message;
	private CommandSender sender;
	private CommandSender receiver;
	private String displayNameSender;
	private String displayNameReceiver;
	private boolean replying;

	public PrivateMessageEvent(CommandSender sender, CommandSender receiver,
			String message, boolean reply) {
		this.sender = sender;
		this.receiver = receiver;
		if (Bukkit.getPlayerExact(sender.getName()) != null) {
			this.displayNameSender = Bukkit.getPlayerExact(sender.getName())
					.getDisplayName();
		} else {
			this.displayNameSender = sender.getName();
		}
		if (Bukkit.getPlayerExact(receiver.getName()) != null) {
			this.displayNameReceiver = Bukkit
					.getPlayerExact(receiver.getName()).getDisplayName();
		} else {
			this.displayNameReceiver = receiver.getName();
		}
		this.message = message;
		this.replying = reply;
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public String getSenderDisplayName() {
		return this.displayNameSender;
	}

	public String getReceiverDisplayName() {
		return this.displayNameReceiver;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public boolean isCancelled() {
		return this.cancelled;
	}

	public void setCancelled(boolean arg0) {
		this.cancelled = arg0;
	}

	public void setMessage(String newMsg) {
		this.message = newMsg;
	}

	public String getMessage() {
		return this.message;
	}

	public CommandSender getSender() {
		return this.sender;
	}

	public CommandSender getReceiver() {
		return this.receiver;
	}

	public boolean isReply() {
		return this.replying;
	}
}
