package me.ghost.Comandos;

import me.ghost.Manager.SKitManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SkitCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Voce não é um player");
		}
		Player p = (Player) sender;
		if (!p.hasPermission("PixelHG.pex.Mod")) {
			p.sendMessage(ChatColor.RED + "Você não tem permissão para esse comando");
			return true;
		}
		if (label.equalsIgnoreCase("skit")) {
			if (args.length > 0) {
				if (args.length == 2) {
					String kit = args[1];
					if (args[0].equalsIgnoreCase("criar")) {
						SKitManager.addKit(p, kit, new SKitManager(p));
						return true;
					}
					if (args[0].equalsIgnoreCase("aplicar")) {
						SKitManager.applyKit(p, kit, null);
						return true;
					}
					if (args[0].equalsIgnoreCase("remover")) {
						SKitManager.removeKit(p, kit);
						return true;
					}
				}
				if (args.length == 3) {
					Player target = Bukkit.getPlayer(args[2]);
					if (target == null) {
						p.sendMessage(ChatColor.RED + "Jogador não encontrado");
						return true;
					}
					if (args[0].equalsIgnoreCase("aplicar")) {
						String kit = args[1];
						SKitManager.applyKit(p, kit, target);
						return true;
					}

				}
			}
			p.sendMessage(ChatColor.RED + "Use: /skit [criar/aplicar/remover] [player]");
			return true;
		}
		return false;
	}

}
