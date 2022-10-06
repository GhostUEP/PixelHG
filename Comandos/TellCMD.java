package me.ghost.Comandos;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import me.ghost.Main;
import me.ghost.Eventos.PrivateMessageEvent;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TellCMD implements CommandExecutor {

	Logger log = Main.plugin.getLogger();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			log.info(ChatColor.RED + "Você não é um player");
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("pm") || cmd.getName().equalsIgnoreCase("tell") || cmd.getName().equalsIgnoreCase("msg") || cmd.getName().equalsIgnoreCase("w")) {
			if (Main.plugin.chat == false) {
				p.sendMessage(ChatColor.RED + "O chat está desativado");
				return true;
			}
			if (args.length == 0) {
				p.sendMessage(ChatColor.RED + "Use: /pm [player] [menssagem]");
				return true;
			}
			if (args.length > 1) {
				if (!Main.plugin.admin.isAdmin(p) || !Main.plugin.admin.isSpectating(p)) {
					Player player = Bukkit.getPlayer(args[0]);
					if (player == null) {
						p.sendMessage(ChatColor.RED + "O player não esta online");
						return true;
					}
					if (Main.plugin.admin.isAdmin(player) || Main.plugin.admin.isSpectating(player) && !p.hasPermission("PixelHG.admin")) {
						p.sendMessage(ChatColor.RED + "O player não esta online");
						return true;
					}
					if (Main.plugin.admin.isAdmin(p) || Main.plugin.admin.isSpectating(p) && !p.hasPermission("PixelHG.admin")) {
						p.sendMessage(ChatColor.RED + "O player não esta online");
						return true;
					}
					if (Main.ignoring.containsKey(player.getName())) {
						List<String> ignores = Main.ignoring.get(player.getName());
						if (ignores.contains(p.getName())) {
							p.sendMessage(ChatColor.RED + "Este jogador esta te ignorando");
							return true;
						}
					}
					PrivateMessageEvent event = new PrivateMessageEvent(sender, player, StringUtils.join(args, " ").substring(args[0].length() + 1), false);
					Bukkit.getPluginManager().callEvent(event);
					if (event.isCancelled()) {
						return true;
					}
					player.sendMessage(ChatColor.GRAY + "[" + event.getSenderDisplayName() + ChatColor.RESET + ChatColor.GRAY + " -> EU] " + ChatColor.RESET + event.getMessage());
					sender.sendMessage(ChatColor.GRAY + "[EU -> " + player.getDisplayName() + ChatColor.RESET + ChatColor.GRAY + "] " + ChatColor.RESET + event.getMessage());
					Main.lastMsg.put(sender.getName(), player.getName());
					Main.lastMsg.put(player.getName(), sender.getName());
				}
			}
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("r") || cmd.getName().equalsIgnoreCase("reply")) {
			if (Main.plugin.chat == false) {
				p.sendMessage(ChatColor.RED + "O chat está desativado");
				return true;
			}
			if (args.length == 0) {
				p.sendMessage(ChatColor.RED + "Escreva alguma menssagem");
				return true;
			}
			if (Main.lastMsg.containsKey(sender.getName())) {
				Player player = Bukkit.getPlayer((String) Main.lastMsg.get(sender.getName()));
				if (player == null) {
					p.sendMessage(ChatColor.RED + "O player não esta online");
					return true;
				}
				if (Main.plugin.admin.isAdmin(player) || Main.plugin.admin.isSpectating(player) && !p.hasPermission("PixelHG.admin")) {
					p.sendMessage(ChatColor.RED + "O player não esta online");
					return true;
				}
				if (Main.plugin.admin.isAdmin(p) || Main.plugin.admin.isSpectating(p) && !p.hasPermission("PixelHG.admin")) {
					p.sendMessage(ChatColor.RED + "O player não esta online");
					return true;
				}
				if (Main.ignoring.containsKey(player.getName())) {
					List<String> ignores = Main.ignoring.get(player.getName());
					if (ignores.contains(p.getName())) {
						p.sendMessage(ChatColor.RED + "Este jogador esta te ignorando");
						return true;
					}
				}
				PrivateMessageEvent event = new PrivateMessageEvent(sender, player, StringUtils.join(args, " "), true);
				Bukkit.getPluginManager().callEvent(event);
				if (event.isCancelled()) {
					return true;
				}
				player.sendMessage(ChatColor.GRAY + "[" + event.getSenderDisplayName() + ChatColor.RESET + ChatColor.GRAY + " -> EU] " + ChatColor.RESET + event.getMessage());
				sender.sendMessage(ChatColor.GRAY + "[EU -> " + event.getReceiverDisplayName() + ChatColor.RESET + ChatColor.GRAY + "] " + ChatColor.RESET + event.getMessage());
				Main.lastMsg.put(sender.getName(), player.getName());
				Main.lastMsg.put(player.getName(), sender.getName());
			} else {
				p.sendMessage(ChatColor.RED + "Nenhum player encontrado para retornar");
			}
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("ignore")) {
			{
				if (Main.plugin.chat == false) {
					p.sendMessage(ChatColor.RED + "O chat está desativado");
					return true;
				}
				if (args.length > 0) {
					Player ignore = Bukkit.getPlayer(args[0]);
					if (ignore == null) {
						sender.sendMessage(ChatColor.RED + "O player não esta online");
						return true;
					}
					if (Main.plugin.admin.isAdmin(ignore) || Main.plugin.admin.isSpectating(ignore) && !sender.hasPermission("PixelHG.admin")) {
						sender.sendMessage(ChatColor.RED + "O player não esta online");
						return true;
					}
					if (Main.plugin.admin.isAdmin(p) || Main.plugin.admin.isSpectating(p) && !p.hasPermission("PixelHG.admin")) {
						p.sendMessage(ChatColor.RED + "O player não esta online");
						return true;
					}
					if ((ignore.hasPermission("PixelHG.admin")) || (sender.getName().equals(ignore.getName()))) {
						p.sendMessage(ChatColor.RED + "Este player não pode ser ignorado");
						return true;
					}
					List<String> ignores = new ArrayList<String>();
					if (Main.ignoring.containsKey(sender.getName())) {
						ignores = Main.ignoring.get(sender.getName());
					}
					if (ignores.contains(ignore.getName())) {
						ignores.remove(ignore.getName());
						p.sendMessage(ChatColor.RED + "Deixou de ignorar: " + ignore.getName());
					} else {
						ignores.add(ignore.getName());
						p.sendMessage(ChatColor.RED + "Ignorando: " + ignore.getName());
					}
					if (ignores.size() == 0) {
						Main.ignoring.remove(sender.getName());
					} else {
						Main.ignoring.put(sender.getName(), ignores);
					}
				} else {
					p.sendMessage(ChatColor.RED + "Use: /ignore [player]");
				}
				return true;
			}
		}
		return false;
	}

}